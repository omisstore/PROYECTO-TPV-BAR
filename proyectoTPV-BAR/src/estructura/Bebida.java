package estructura;

import java.io.Serializable;

import estructura.enumeraciones.Envase;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoBebida;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;
/**
 * Bebida
 * @author Javier Ponferrada López
 * @version 1.0
 * */
public class Bebida extends Producto implements Cobrable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Envase envase;
	private TipoBebida tipo;
	private String tamanio;
	
	public Bebida(String nombre, String descripcion, int stock, double precio, Iva iva,Envase envase,TipoBebida tipo)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException {
		super(nombre, descripcion, stock, precio, iva);
		setEnvase(envase);
		setTipo(tipo);
		setTamanio(envase.tamanio());
	}
	
	public Bebida(int identificador,String nombre, int stock, double precio, Iva iva,Envase envase,TipoBebida tipo)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException {
		super(identificador,nombre, stock, precio, iva);
		setEnvase(envase);
		setTipo(tipo);
		setTamanio(envase.tamanio());
	}
	
	
	/**
	 * @return tamanio de la bebida
	 * */
	public String getTamanio() {
		return tamanio;
	}

	/**
	 * Asigna un tamanio a la botella
	 * @param tamanio, a asignar
	 * */
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}


	/**
	 * @return envase de la bebida
	 * */
	public Envase getEnvase() {
		return envase;
	}

	/**
	 * Asignar un envase a la babida
	 * @param envase, a asignar
	 * */
	public void setEnvase(Envase envase) {
		this.envase = envase;
	}

	/**
	 * @return tipo de bebida
	 * */
	public TipoBebida getTipo() {
		return tipo;
	}

	/**
	 * Asiginar el tipo de bebida.
	 * @param ti	po, de bebida
	 * */
	public void setTipo(TipoBebida tipo) {
		this.tipo = tipo;
	}

	/**
	 * Aplica los impuestos necesarios para dicho producto.
	 * @return precio de la bebida a cobrar(Bruto)
	 * */
	@Override
	public double cobrar() {
		double precioConImpuesto;
		precioConImpuesto = getPrecio() + (getPrecio()*getTipo().impuesto());
		precioConImpuesto += getIva().valor()*getPrecio();
		return Math.round(precioConImpuesto*100d)/100d;
	}
	
	@Override
	public String toString() {
		return super.toString()+"[envase= " + envase + ", tipo= " + tipo + ", tamanio= " + tamanio + "]";
	}
	
	

}
