package estructura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import estructura.exceptions.FicheroNoExisteException;

public class FicheroTickets implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	
	/**
	 * 
	 * */
	public FicheroTickets(String ruta) throws FicheroNoExisteException {
		try {
			file = new File(ruta);
		} catch (NullPointerException e) {
			throw new FicheroNoExisteException("ERROR:Fichero de registros, no encontrado");
		}
	}
	
	/**
	 * 
	 * */
	public void escribir(String escritura) throws FicheroNoExisteException{
		
		
		try (FileWriter fichero =new FileWriter(file,true);PrintWriter pw =  new PrintWriter(fichero)){
			pw.print(escritura);
		} catch (Exception e) {
			throw new FicheroNoExisteException("ERROR:No se ha podido escribir el fichero");
		}
	}
	
	/**
	 * @throws FicheroNoExisteException 
	 * 
	 * */
	public String leer() throws FicheroNoExisteException{
		StringBuilder cadena =  new StringBuilder();
		try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
			cadena.append(fileReader.readLine());
			while (cadena!=null) {
				if (cadena!=null) {
					cadena.append(fileReader.readLine());
				}
			}
			return cadena.toString();
		} catch (IOException e) {
			throw new FicheroNoExisteException("ERROR:No se ha podido leer el fichero");
		}
		
	}
	
}
