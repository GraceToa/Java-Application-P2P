package bapster;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BapsterClient2 {
	public static void main(String[] args) {

		final String directory = "/Users/GraceToa/Documents/BasterJava/Test/Client2";

		String host = "127.0.0.1";
		int port = 30000;
		try (Socket socketClient = new Socket(host, port);
				BufferedReader bufferIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				PrintWriter printerOut = new PrintWriter(socketClient.getOutputStream(), true);
				BufferedReader inFromConsole = new BufferedReader(new InputStreamReader(System.in));) {

			String namesFiles = sendServerListToShare(directory);
			// send namesFiles to Server
			printerOut.println(namesFiles);

			BapsterClientReader2 bapsterClientReader = new BapsterClientReader2(bufferIn);
			bapsterClientReader.start();

			String userInConsole;
			while ((userInConsole = inFromConsole.readLine()) != null && !userInConsole.equals("exit")) {
				printerOut.println(userInConsole);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String sendServerListToShare(String directory) {
		String namesFiles = null;
		File[] files = new File(directory).listFiles();
		for (File file : files) {
			namesFiles += file.getName() + ",";
		}
		return namesFiles;
	}
}
