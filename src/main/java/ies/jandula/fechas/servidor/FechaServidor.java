package ies.jandula.fechas.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaServidor 
{
	public static final int PUERTO = 2000;
    
    public static void main(String[] args) 
    {
        
        ServerSocket socketServer = null;
        Socket socketCliente = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        
        try 
        {
            socketServer = new ServerSocket(PUERTO);            
            
            socketCliente = socketServer.accept();
            
            dataInputStream = new DataInputStream(socketCliente.getInputStream());
            String fecha1 = dataInputStream.readUTF();
            String fecha2 = dataInputStream.readUTF();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
            Date fechaConvertida1 = dateFormat.parse(fecha1);
			
            Date fechaConvertida2 = dateFormat.parse(fecha2);           
            
                        
            dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
            
            if(fechaConvertida1.before(fechaConvertida2))
            {
            	dataOutputStream.writeUTF(String.valueOf(fechaConvertida2));
            }
            else
            {
            	dataOutputStream.writeUTF(String.valueOf(fechaConvertida1));
            }
            
            
            
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        catch (ParseException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
            try 
            {
                if (dataInputStream != null)
                {
                    dataInputStream.close();
                }
                if (dataOutputStream != null)
                {
                    dataOutputStream.close();
                }
                if (socketCliente != null)
                {
                    socketCliente.close();
                }
                if (socketServer != null)
                {
                    socketServer.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }       
    }
}
