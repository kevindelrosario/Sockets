package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTCP {

	// conexion entre cliente servidor
	public static void main(String[] args) {
		Scanner sr = new Scanner (System.in);
		System.out.println("Estableciendo conexion...");
		
		try {
			
			
			Socket socket = new Socket("localhost", 50000); // esta es la parte de escucha
			
			//Envolvemos los string de entrada y salida
			
			DataInputStream  dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			//Enviamos un mensaje
			System.out.println("Escribe el mensaje: ");
	
			String mensaje = sr.nextLine();		
	
			dos.writeUTF(mensaje);
			
			//RECIBIMOS  respuesta del servidor
			mensaje = dis.readUTF();
			System.out.println("Mensaje recibido: " + mensaje); //esto concatena tambien el mensaje que este en servidor
			
			//cerramos conexion (cerramos el socket)
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		System.err.println("error al crear el socket");
		
		
		
		}
	}

}
