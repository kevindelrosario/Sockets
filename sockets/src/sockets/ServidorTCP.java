package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class ServidorTCP {

	public static void main(String[] args) throws InterruptedException {
		// primero se ejecutaria este para que funcione y luego el cliente.
		boolean bucle = true;
		/**
		 * dos.write lo escribira en el otro 
		 * el dis.read leera lo que envio el servidor.
		 */
		try {
			System.out.println(" ************SERVIDOR*************");
			// serverSocket se queda escuchando por un IP y puerto
			//operacion bind
			ServerSocket serverSocket = new ServerSocket(50000);
			
			//Espera bloqueante de  conexiones con cliente: operacion acept
			
			while (bucle) {//para que siempre escuche
			Socket socket = serverSocket.accept();
		
			//La variable socket va a estar conectada al cliente que acaba de hacer la peticion
			
			System.out.println("he recibido la conexion del cliente: "
			+ socket.getInetAddress() + " -puerto: " + socket.getPort());
			

			//Envolvemos los string de entrada y salida
			DataInputStream  dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			
			//leo mensaje
	
			String mensaje = dis.readUTF(); // dis es para leer
		
			switch (mensaje) {// la fecha no funciona pero hace lo que se le pide
			
			case "hora":
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date(0);
			//System.out.println("Hora actual: " + dateFormat.format(date));
			dos.writeUTF(dateFormat.format(date));
			break;
			
			case "fecha":
			DateFormat fechaForm = new SimpleDateFormat("DD/MM/YYYY");
			Date fecha = new Date(0);
			dos.writeUTF(fechaForm.format(fecha));
			break;
			
			case "nombre":
			dos.writeUTF("kevin");
			break;
			case "salir":
				socket.close();

			break;
			
			default:
			dos.writeUTF("No te entiendo. Escribe fecha, hora, nombre, salir.");
			break;
			}
		
			System.out.println(mensaje);
		//	Thread.sleep(10000);// dormir para que espere
			
		
			
			//envio mensaje
		//	dos.writeUTF("El mensaje recibido en el servidor es: "+  mensaje);
			
			//cerramos
		//	socket.close();// cierre del bucle
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error creando el socket servidor...");
		}
		
	}
		
		
}
