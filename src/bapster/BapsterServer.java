package bapster;

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe Servidor acepta las peticiones de múltiples usuarios y las gestiona
 * mediante threads, el servidor creará un objeto Shared que compartira todos
 * los threads
 * 
 * @author GraceToa
 * @version 04/03/2018
 */
public class BapsterServer {

	private static final Shared Shared = new Shared();

	public static void main(String[] args) throws IOException {
		int portNumber = 30000;
		boolean listening = true;
		final AtomicInteger count = new AtomicInteger(1);

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

			while (listening) {
				new BapsterServerThread(serverSocket.accept(), count.getAndIncrement(), Shared).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}

}
