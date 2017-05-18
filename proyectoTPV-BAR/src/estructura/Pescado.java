package estructura;

import java.io.Serializable;

import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoPescado;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PesoNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;

public class Pescado extends Producto implements Cobrable,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoPescado tipo;
	private float peso;
	
	public Pescado(String nombre, String descripcion, int stock, double precio, Iva iva, TipoPescado tipo, float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		super(nombre, descripcion, stock, precio, iva);
		setTipo(tipo);
		setPeso(peso);
	}
	
	public Pescado(int identificador,String nombre, int stock, double precio, Iva iva, TipoPescado tipo, float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		super(identificador,nombre, stock, precio, iva);
		setTipo(tipo);
		setPeso(peso);
	}
	
	/**
	 * @return tipo de pescado
	 * */
	public TipoPescado getTipo() {
		return tipo;
	}
	
	/**
	 * Asignar un tipo de pescado
	 * @param tipo, a asignar
	 * */
	public void setTipo(TipoPescado tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * @return peso del pescado a servir al cliente
	 * */
	public float getPeso() {
		return peso;
	}
	
	/**
	 * Asignar un peso al pescado
	 * @param peso, a asignar.
	 * @throws PesoNoValidoException
	 * */
	public void setPeso(float peso) throws PesoNoValidoException {
		if(peso<0)
			throw new PesoNoValidoException("ERRO:Peso incorrecto.");
		this.peso = peso;
	}

	/**
	 * Aplica los impuestos necesarios para dicho producto.
	 * @return precio del pescado a cobrar(Bruto)
	 * */
	@Override
	public double cobrar() {
		double precioConImpuesto;
		precioConImpuesto = getPrecio() + (getPrecio() * getTipo().impuesto());
		precioConImpuesto += getPrecio() * getIva().valor();
		return Math.round(precioConImpuesto*100d)/100d ;
	}

	@Override
	public String toString() {
		return super.toString()+"[tipo= " + tipo + ", peso= " + peso + "]";
	}
	
	
}
