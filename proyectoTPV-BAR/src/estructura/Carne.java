package estructura;

import java.io.Serializable;

import estructura.enumeraciones.Corte;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoCarne;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PesoNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;
/**
 * Carne
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class Carne extends Producto implements Cobrable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoCarne tipo;
	private Corte corte;
	private float peso;

	public Carne(String nombre, String descripcion, int stock, double precio, Iva iva, TipoCarne tipo, Corte corte,float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		super(nombre, descripcion, stock, precio, iva);
		setTipo(tipo);
		setCorte(corte);
		setPeso(peso);
	}
	
	
	/**
	 * @return tipo de carne
	 * */
	public TipoCarne getTipo() {
		return tipo;
	}

	/**
	 * Asignar un tipo de carne
	 * @param tipo, a asignar
	 * */
	public void setTipo(TipoCarne tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return corte de la carne
	 * */
	public Corte getCorte() {
		return corte;
	}
	
	/**
	 * Asignar un tipo de corte a la carne
	 * @param corte, a asignar 
	 * */
	public void setCorte(Corte corte) {
		this.corte = corte;
	}
	
	/**
	 * @return peso de la carne a servir al cliente
	 * */
	public float getPeso() {
		return peso;
	}
	
	/**
	 * Asignar peso a la carne
	 * @param peso, a asignar
	 * @throws PesoNoValidoException 
	 * */
	public void setPeso(float peso) throws PesoNoValidoException {
		if (peso<0)
			throw new PesoNoValidoException("ERROR:Peso incorrecto.");
		this.peso = peso;
	}

	/**
	 * Aplica los impuestos necesarios para dicho producto.
	 * @return precio de la carne a cobrar(Bruto)
	 * */
	@Override
	public double cobrar() {
		double precioConImpuesto;
		precioConImpuesto = getPrecio() +(getPrecio()*getTipo().impuesto());
		precioConImpuesto += getPrecio()*getIva().valor();
		return Math.round(precioConImpuesto*100d)/100d;
	}
	
	@Override
	public String toString() {
		return super.toString()+"[tipo= " + tipo + ", corte= " + corte + ", peso= " + peso + "]";
	}
}
