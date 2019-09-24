package bapster;

import java.io.PrintWriter;
import java.util.Arrays;

public class User {
	private int idUser;
	private PrintWriter printWriter;
	private String[] namesFiles;

	public User(int idUser, PrintWriter printWriter, String[] namesFiles) {
		super();
		this.idUser = idUser;
		this.printWriter = printWriter;
		this.namesFiles = namesFiles;
	}

	public User() {
	}

	public User(int idUser, String[] namesFiles) {
		super();
		this.idUser = idUser;
		this.namesFiles = namesFiles;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String[] getNamesFiles() {
		return namesFiles;
	}

	public void setNamesFiles(String[] namesFiles) {
		this.namesFiles = namesFiles;
	}

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", namesFiles=" + Arrays.toString(namesFiles) + "]";
	}

}
