package bapster;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe que crea una conexión entre el cliente y el servidor via socket y le
 * pasa una lista de archivos de una carpeta que el cliente quiere compartir
 * 
 * @author GraceToa
 * @version 04/03/2018
 */
public class BapsterClient1 {
	public static void main(String[] args) {

		final String directory = "/Users/GraceToa/Documents/BasterJava/Test/Client1";

		String host = "127.0.0.1";
		int port = 30000;
		try (Socket socketClient = new Socket(host, port);
				BufferedReader bufferIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				PrintWriter printerOut = new PrintWriter(socketClient.getOutputStream(), true);
				BufferedReader inFromConsole = new BufferedReader(new InputStreamReader(System.in));) {

			String namesFiles = sendServerListToShare(directory);
			// send namesFiles to Server
			printerOut.println(namesFiles);

			BapsterClientReader1 bapsterClientReader = new BapsterClientReader1(bufferIn);
			bapsterClientReader.start();

			String userInConsole;
			while ((userInConsole = inFromConsole.readLine()) != null && !userInConsole.equals("exit")) {
				printerOut.println(userInConsole);
			}
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene la lista de los nombres de los archivos de la carpeta
	 * compartida y le pasa al servidor en forma de string
	 * 
	 * @param directory
	 * @return
	 */
	public static String sendServerListToShare(String directory) {
		String namesFiles = null;
		File[] files = new File(directory).listFiles();
		for (File file : files) {
			namesFiles += file.getName() + ",";
		}
		return namesFiles;
	}
}
