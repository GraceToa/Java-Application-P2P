package bapster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe que escucha del servidor, y muestra por pantalla eso que recibe
 * Gestionará que pueda recibir la comanda /send parseará el puerto y el nombre
 * del archivo solicitado y desde un nuevo theard SendFileClient le enviará la
 * solicitud al servidor
 * 
 * @author GraceToa
 * @version 04/03/2018
 */
public class BapsterClientReader1 extends Thread {
	String host = "127.0.0.1";
	private BufferedReader bufferIn;

	public BapsterClientReader1(BufferedReader bufferIn) {
		this.bufferIn = bufferIn;
	}

	@Override
	public void run() {
		String serverAnswer;

		try {
			while ((serverAnswer = bufferIn.readLine()) != null) {
				System.out.println(serverAnswer);

				if (serverAnswer.contains("/send")) {
					String strArray[] = serverAnswer.split(" ");
					String nameFile = strArray[1];
					int portSendFile = Integer.parseInt(strArray[3]);
					String pathFileFind = searchPathFile(nameFile);
					// System.out.println(pathFileFind);
					sendFileClient(portSendFile, pathFileFind);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}// run

	/**
	 * Método que crea un Thread que se encargará de enviar el archivo solicitado al
	 * servidor, recibe por parámetro el puerto del socket servidor y la ruta del
	 * archivo solicitado
	 * 
	 * @param portSendFile
	 * @param pathFileFind
	 */
	public void sendFileClient(int portSendFile, String pathFileFind) {

		Runnable serverTask = new Runnable() {
			@Override
			public void run() {
				try {
					String host = "127.0.0.1";
					Socket socket = new Socket(host, portSendFile);
					// reading file contents
					BufferedReader contentRead = new BufferedReader(new FileReader(pathFileFind));
					// keeping output stream ready to send the contents
					OutputStream ostream = socket.getOutputStream();
					PrintWriter pwrite = new PrintWriter(ostream, true);
					String str;
					while ((str = contentRead.readLine()) != null) // reading line-by-line from file
					{
						pwrite.println(str);
					}

					socket.close();
				} catch (IOException e) {
					System.err.println("Unable to process client request");
					e.printStackTrace();
				}
			}// run
		};
		Thread serverThread = new Thread(serverTask);
		serverThread.start();

	}

	/**
	 * Método que recibe el nombre del archivo solicitado y lo busca en el
	 * directorio donde se encuentra, devuelve la ruta absoluta de ese archivo
	 * 
	 * @param nameFile
	 * @return
	 */
	public String searchPathFile(String nameFile) {
		File root = new File("/Users/GraceToa/Documents/BasterJava/Test/Client1");
		for (File temp : root.listFiles()) {
			if (temp.getName().equals(nameFile)) {
				nameFile = temp.getAbsolutePath().toString();
			}
		}
		return nameFile;
	}

}// end class
