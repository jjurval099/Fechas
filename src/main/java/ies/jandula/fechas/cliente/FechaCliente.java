package ies.jandula.fechas.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import ies.jandula.fechas.servidor.FechaServidor;

public class FechaCliente 
{
private static final String HOST ="localhost";
	
	public static void main(String[] args) 
	{
		String fecha1;
		String fecha2;
		
		Scanner scanner = null;
        Socket socketCliente = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
                
        try 
        {
        	scanner = new Scanner (System.in);
        	
			socketCliente = new Socket(HOST, FechaServidor.PUERTO);
			
			System.out.println("Introduce una fecha");
			fecha1 = scanner.nextLine();
			
			System.out.println("Introduce otra fecha");
			fecha2 = scanner.nextLine();
			
			
			dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());    
	        dataOutputStream.writeUTF(String.valueOf(fecha1));
	        dataOutputStream.writeUTF(String.valueOf(fecha2));
	        
	        dataInputStream = new DataInputStream(socketCliente.getInputStream());
			System.out.println(dataInputStream.readUTF());
        } 
        catch (UnknownHostException unknownHostException) 
        {
            unknownHostException.printStackTrace();
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally 
        {
        	try 
            {
                if (dataOutputStream != null)
                {
                    dataOutputStream.close();
                }
                if (dataInputStream != null)
                {
                    dataInputStream.close();
                }
                if (socketCliente != null)
                {
                    socketCliente.close();
                }
                if (scanner != null)
                {
                    scanner.close();
                }
            } 
            catch (IOException ioException) 
            {
                ioException.printStackTrace();
            }
		}		
	}
}
