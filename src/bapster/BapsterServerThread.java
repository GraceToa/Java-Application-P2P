package bapster;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe que gestiona los theards, cada uno muestra por pantalla la información
 * recibida de cada cliente como su lista de archivos y los diferentes comandas:
 * Command: '/list' or '/get <nameFile>' or '/w <msg>' or '/send <mail>' or '/q'
 * Cada vez que se cree un Thread se instanciara un nuevo usuario, el id de
 * usuario será Theard-safe que será el mismo que el key del Hashmap(Shared)
 * Cada usuario guardaremos id(thread-safe),printWriter(utilizaremos para
 * conectarle y pedirle que nos envie un archivo),lista de
 * archivos(namesFiles[]),
 * 
 * @author GraceToa
 * @version 04/03/2018
 */
public class BapsterServerThread extends Thread {
	private Socket clientSocket = null;
	private int id;
	private int state = 0;
	private String namesFiles[];
	private Shared shared;
	private int portClients = 30002 + 1;

	public BapsterServerThread(Socket clientSocket, int id, Shared shared) {
		super("BapsterServerThread");
		this.clientSocket = clientSocket;
		this.id = id;
		this.shared = shared;
	}

	public void run() {

		try (

				PrintWriter printerOut = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
			String inputLine;
			User user = null;

			while ((inputLine = inReader.readLine()) != null) {
				if (state == 0) {
					namesFiles = (inputLine.split(","));
					printerOut.println("File save¡ Command: '/list' or '/get <nameFile>' or '/w <msg>' or '/q' ");
					user = new User(id, printerOut, namesFiles);
					shared.addFilesClients(id, user);
					state = 1;
				} else if (state != 0) {
					if (inputLine.equals("/list")) {
						printerOut.println(shared.getSharedFilesclients().toString());
					} else if (inputLine.contains("/get")) {
						String nameFileFind = inputLine.substring(5);
						User userFind = Shared.getFile(nameFileFind);
						if (userFind != null) {
							readFileServer(printerOut, userFind.getPrintWriter(), portClients, nameFileFind);
						} else {
							printerOut.println("This's file not exist¡¡");
						}
					} else if (inputLine.contains("/w")) {
						String msg = inputLine.substring(2);
						shared.writeOtherPrinters(msg, printerOut);
					} else if (inputLine.contains("/send")) {
						String from = inputLine.substring(5);
						sendEmailClient(from, shared.getNameFilesClients());
					} else if (inputLine.contains("/q")) {
						shared.deleteUserHashMap(id);
						printerOut.println("Bye");
					} else {
						printerOut.println("File save¡ Command: '/list' or '/get <nameFile>' or '/w <msg>' or '/q' ");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que crea un thread y socket para leer del propietario del archivo, el
	 * socket escuchara por un puerto que ira incrementando cada vez que un thread
	 * acceda a este, recibirá por parámetro el PrintWriter sobre el que estaba
	 * conectado, el printWriter del usuario propietario del archivo al cual le
	 * comunicaremos que nos envie ese archivo a ese puerto Cuando reciba el archivo
	 * le enviará directamente al cliente que lo demando(printerOut) que este a su
	 * vez mostrara por pantalla el contenido de ese archivo
	 * 
	 * @param printerOut
	 * @param printerOutUser
	 * @param portClients
	 * @param nameFileFind
	 */
	public void readFileServer(PrintWriter printerOut, PrintWriter printerOutUser, int portClients,
			String nameFileFind) {

		Runnable serverTask = new Runnable() {
			@Override
			public void run() {
				try {
					ServerSocket serverSocket2 = new ServerSocket(portClients);
					printerOutUser.println("/send" + " " + nameFileFind + " " + "from" + " " + portClients);
					Socket clientSocket = serverSocket2.accept();
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

					String inputLine2;
					while ((inputLine2 = in.readLine()) != null) {
						printerOut.println(inputLine2);// printWrither send request file
					}
					serverSocket2.close();
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
	 * Método que envia un email mediante la comanda /w <gracetoa29@hotmail.com> 
	 * Utiliza la Apri JavaMail, y los jars correspondientes
	 * el contenido del email sera la lista de archivos de los clientes
	 * obtenidos del Objeto Shared
	 * 
	 * @param to
	 * @param namesFilesClients
	 */
	public static void sendEmailClient(String to, String namesFilesClients) {
		String username = "*************";
		String password = "*********";//cambiar el password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			// compose the message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("List Files Bapster");
			message.setText(namesFilesClients);

			// send message
			Transport.send(message);
			System.out.println("message sent successfully");

		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}// end class
