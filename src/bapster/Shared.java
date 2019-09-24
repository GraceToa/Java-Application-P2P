package bapster;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Classe que crea un HashMap que contendrá las relaciones <id_user, user>
 * 
 * @author GraceToa
 * @version 04/03/2018
 */
public class Shared {

	private static HashMap<Integer, User> sharedFilesclients = new HashMap<Integer, User>();

	public Shared(HashMap<Integer, User> sharedFilesclients) {
		super();
		this.sharedFilesclients = sharedFilesclients;
	}

	public Shared() {
	}

	public HashMap<Integer, User> getSharedFilesclients() {
		return sharedFilesclients;
	}

	public void setSharedFilesclients(HashMap<Integer, User> sharedFilesclients) {
		this.sharedFilesclients = sharedFilesclients;
	}

	@Override
	public String toString() {
		return "Shared [sharedFilesclients=" + sharedFilesclients + "]";
	}

	public HashMap<Integer, User> addFilesClients(int id, User user) {
		sharedFilesclients.put(id, user);
		return sharedFilesclients;
	}

	public static User getFile(String nameFile) {
		User userFind = new User();
		for (Entry<Integer, User> entry : sharedFilesclients.entrySet()) {
			for (int i = 0; i < entry.getValue().getNamesFiles().length; i++) {
				String[] namesFiles = entry.getValue().getNamesFiles();
				for (int j = 0; j < namesFiles.length; j++) {
					if (Arrays.asList(namesFiles).contains(nameFile)) {
						userFind = entry.getValue();
					}
				}
			}
		}
		return userFind;
	}

	/**
	 * Método que elimina del HashMap un usuario mediante el id (KEY) desvinculando
	 * asi su listado de archivos del servidor
	 * 
	 * @param id
	 */
	public void deleteUserHashMap(int id) {
		sharedFilesclients.remove(id);
	}

	/**
	 * Método XAT utiliza el listado de usuarios del HashMap donde cada usuario
	 * tiene un PrintWriter por el cual enviaremos una sentencia (msg) a todos los
	 * usuarios(printWriter) que no sean del propio usuario
	 * 
	 * @param msg
	 * @param printOwnClient
	 */
	public void writeOtherPrinters(String msg, PrintWriter printOwnClient) {
		for (Entry<Integer, User> entry : sharedFilesclients.entrySet()) {
			if (printOwnClient != entry.getValue().getPrintWriter()) {
				entry.getValue().getPrintWriter().println(msg);
			}
		}
	}

	/**
	 * Método que guarda en un String el listado de los archivos de todos los
	 * clientes desde el HashMap, para enviarlos via email
	 * 
	 * @return
	 */
	public String getNameFilesClients() {
		String nameFilesClients = null;
		for (Entry<Integer, User> entry : sharedFilesclients.entrySet()) {
			for (int i = 0; i < entry.getValue().getNamesFiles().length; i++) {
				String[] namesFiles = entry.getValue().getNamesFiles();
				for (int j = 0; j < namesFiles.length; j++) {
					nameFilesClients += namesFiles[j] + " ";
				}
			}
		}
		return nameFilesClients;
	}
}
