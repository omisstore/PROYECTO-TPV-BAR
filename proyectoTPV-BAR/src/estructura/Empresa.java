package estructura;

import java.io.Serializable;
import java.util.regex.Pattern;

import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.TelefonoNoValidoException;

/**
 * Empresa
 * */
public class Empresa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String numeroTelefono;
	private String provincia;
	private String calle;
	private String localidad;
	private static final Pattern PATRONOMBRE = Pattern.compile("([a-zA-Z\\dáéíóúÁÉÍÓÚüöÖÜ]\\s?){2,}");
	private static final Pattern PATRONTELEFONO = Pattern.compile("([0-9]{9})");
	
	/**
	 * Contructor Empresa
	 * @throws NombreNoValidoException 
	 * @throws TelefonoNoValidoException 
	 * */
	public Empresa(String nombre, String provincia, String localidad,String calle,String numeroTelefono) throws NombreNoValidoException, TelefonoNoValidoException {
		setNombre(nombre);
		setProvincia(provincia);
		setLocalidad(localidad);
		setLocalidad(localidad);
		setCalle(calle);
		setNumeroTelefono(numeroTelefono);
	}
	
	public Empresa(String nombre) throws NombreNoValidoException, TelefonoNoValidoException {
		setNombre(nombre);
	}
	
	public Empresa(){
		
	}

	public String getNombre() {
		return nombre;
	}
	/**
	 * @throws NombreNoValidoException
	 * */
	public void setNombre(String nombre) throws NombreNoValidoException {
		if(!PATRONOMBRE.matcher(nombre).matches())
			throw new NombreNoValidoException("ERROR:Nombre incorrecto");
		this.nombre = nombre;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	/**
	 * @throws TelefonoNoValidoException 
	 * 
	 */
	public void setNumeroTelefono(String numeroTelefono) throws TelefonoNoValidoException {
		if(!PATRONTELEFONO.matcher(numeroTelefono).matches())
			throw new TelefonoNoValidoException("ERROR:Numero de teléfono incorrecto");
		this.numeroTelefono = numeroTelefono;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + ", numeroTelefono=" + numeroTelefono + ", provincia=" + provincia
				+ ", calle=" + calle + ", localidad=" + localidad + "]";
	}
	
}
