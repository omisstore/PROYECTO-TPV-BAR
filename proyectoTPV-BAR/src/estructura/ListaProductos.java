package estructura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import estructura.enumeraciones.Corte;
import estructura.enumeraciones.Envase;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoBebida;
import estructura.enumeraciones.TipoCarne;
import estructura.enumeraciones.TipoPescado;
import estructura.exceptions.ListaVaciaException;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PesoNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.ProductoNoEncontradoExcepcion;
import estructura.exceptions.ProductoYaExisteException;
import estructura.exceptions.StockNoValidoException;

/**
 * Lista de productos. Maneja productos
 * 
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class ListaProductos implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> listaProductos = new ArrayList<Producto>();
	private int ultimoIdentificadorProductos;

	/**
	 * Anadir una bebida a la liasta de productos
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param stock
	 * @param precio
	 * @param iva
	 * @param envase
	 * @param tipo
	 * @throws NombreNoValidoException
	 * @throws StockNoValidoException
	 * @throws PrecioNoValidoException
	 */
	public void addBebida(String nombre, String descripcion, int stock, double precio, Iva iva, Envase envase,
			TipoBebida tipo) throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException {
		refrescarUltimoIndicadorProducto();
		listaProductos.add(new Bebida((this.ultimoIdentificadorProductos + 1), nombre, descripcion, stock, precio, iva,
				envase, tipo));
	}

	/**
	 * Anadir una carne a la lista de productos
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param stock
	 * @param precio
	 * @param iva
	 * @param tipo
	 * @param corte
	 * @param peso
	 * @throws NombreNoValidoException
	 * @throws StockNoValidoException
	 * @throws PrecioNoValidoException
	 * @throws PesoNoValidoException
	 */
	public void addCarne(String nombre, String descripcion, int stock, double precio, Iva iva, TipoCarne tipo,
			Corte corte, float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		refrescarUltimoIndicadorProducto();
		listaProductos.add(new Carne((this.ultimoIdentificadorProductos + 1), nombre, descripcion, stock, precio, iva,
				tipo, corte, peso));
	}

	/**
	 * Anadir un pescado a la lista de productos
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param stock
	 * @param precio
	 * @param iva
	 * @param tipo
	 * @param peso
	 * @throws NombreNoValidoException
	 * @throws StockNoValidoException
	 * @throws PrecioNoValidoException
	 * @throws PesoNoValidoException
	 */
	public void addPescado(String nombre, String descripcion, int stock, double precio, Iva iva, TipoPescado tipo,
			float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		refrescarUltimoIndicadorProducto();
		listaProductos.add(new Pescado((this.ultimoIdentificadorProductos + 1), nombre, descripcion, stock, precio, iva,
				tipo, peso));
	}

	/**
	 * Anadir producto a la lista de productos
	 * 
	 * @param producto
	 * 
	 */
	public void add(Producto producto) throws ProductoYaExisteException {
		listaProductos.add(producto);
	}

	/**
	 * Borrar producto
	 * 
	 * @param producto
	 *            a borrar de la lista de productos
	 */
	public void remove(Producto producto) throws ProductoNoEncontradoExcepcion {

		if (!listaProductos.remove(producto)) {
			throw new ProductoNoEncontradoExcepcion("ERROR:No se pudo borrar el producto.");
		}

	}

	/**
	 * Borrar producto por posicion
	 * 
	 * @return posicion borrado
	 */
	public Producto remove(int posicion) throws ProductoNoEncontradoExcepcion {
		try {
			return listaProductos.remove(posicion - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new ProductoNoEncontradoExcepcion("ERROR:No se pudo borrar el producto.");
		}
	}

	/**
	 * Borrar producto por posicion indicada por la fila seleccionada de la
	 * tabla.
	 * 
	 * @return posicion borrado
	 */
	public Producto removeForTable(int posicion) throws ProductoNoEncontradoExcepcion {
		try {
			return listaProductos.remove(posicion);
		} catch (IndexOutOfBoundsException e) {
			throw new ProductoNoEncontradoExcepcion("ERROR:No se pudo borrar el producto.");
		}
	}

	/**
	 * Comprobar si existe un producto en la lista
	 * 
	 * @return si existe o no
	 */
	public boolean contains(Producto producto) {
		return listaProductos.contains(producto);
	}

	/**
	 * Tamnio de la lista de productos
	 * 
	 * @return
	 */
	public int size() {
		return listaProductos.size();
	}

	/**
	 * Limpiar la lista de productos
	 */
	public void clear() {
		listaProductos.clear();
	}

	/**
	 * Refresca y actualiza el campo contador, para que los siguientes productos
	 * a crear, sean correlativos.
	 */
	private void refrescarUltimoIndicadorProducto() {
		int ultimoContador = 0;
		for (Producto producto : listaProductos) {
			if (producto.getIdentificador() > ultimoContador)
				ultimoContador = producto.getIdentificador();
		}
		this.ultimoIdentificadorProductos = ultimoContador;
	}

	/**
	 * 
	 * @param i,
	 *            posicion de la lista
	 * @return producto que se encuentra en la posicion de la lista
	 * @throws ProductoNoEncontradoExcepcion
	 */
	public Producto get(int i) throws ProductoNoEncontradoExcepcion {
		try {
			return listaProductos.get(i);
		} catch (IndexOutOfBoundsException e) {
			throw new ProductoNoEncontradoExcepcion("ERROR:No existe producto");
		}

	}

	/**
	 * @param producto
	 * @return posicion del producto en la lista
	 */
	public int indexOf(Producto producto) {
		return listaProductos.indexOf(producto);
	}

	/**
	 * Obtener la posicion del elemento en la lista, indetificado por su
	 * identificador unico
	 * 
	 * @param indicador
	 * @return posicion del elemento en la lista
	 */
	public int obtenerPosicionPorIndicador(int indicador) {
		return listaProductos.indexOf(new Producto(indicador));
	}

	/**
	 * Convertir la lista de productos a matriz
	 * 
	 * @return matriz obtenda con la lista de productos
	 */
	public String[][] pasoAMatriz() {
		Collections.sort(listaProductos);
		String[][] matriz = new String[listaProductos.size()][3];
		int j = 0;

		for (Producto producto : listaProductos) {
			matriz[j][0] = String.valueOf(producto.getStock());
			matriz[j][1] = producto.getNombre();
			matriz[j][2] = String.valueOf(((Cobrable) producto).cobrar() + "€");
			j++;

		}

		return matriz;
	}

	/**
	 * Convertir la lista de productos solo de bebidas a matriz
	 * 
	 * @return matriz obtenda con la lista de productos
	 */
	public String[][] pasoAMatrizBebida() {
		Collections.sort(listaProductos);
		String[][] matriz = new String[sizeBebidas()][8];
		int j = 0;
		for (int i = 0; i < listaProductos.size(); i++) {
			if (listaProductos.get(i) instanceof Bebida) {
				matriz[j][0] = String.valueOf(((Bebida) (listaProductos.get(i))).getIdentificador());
				matriz[j][1] = ((Bebida) (listaProductos.get(i))).getNombre();
				matriz[j][2] = ((Bebida) (listaProductos.get(i))).getDescripcion();
				matriz[j][3] = String.valueOf(((Bebida) (listaProductos.get(i))).getStock());
				matriz[j][4] = String.valueOf(((Bebida) (listaProductos.get(i))).getPrecio());
				matriz[j][5] = String.valueOf(((Bebida) (listaProductos.get(i))).getIva().valor() + "%");
				matriz[j][6] = ((Bebida) (listaProductos.get(i))).getTamanio();
				matriz[j][7] = String.valueOf(((Bebida) (listaProductos.get(i))).getTipo().impuesto() + "%");
				j++;
			}

		}
		return matriz;

	}

	/**
	 * Convertir la lista de productos solo de Carne a matriz
	 * 
	 * @return matriz obtenda con la lista de productos
	 */
	public String[][] pasoAMatrizCarne() {
		Collections.sort(listaProductos);
		String[][] matriz = new String[sizeCarnes()][9];
		int j = 0;
		for (int i = 0; i < listaProductos.size(); i++) {
			if (listaProductos.get(i) instanceof Carne) {
				matriz[j][0] = String.valueOf(((Carne) (listaProductos.get(i))).getIdentificador());
				matriz[j][1] = ((Carne) (listaProductos.get(i))).getNombre();
				matriz[j][2] = ((Carne) (listaProductos.get(i))).getDescripcion();
				matriz[j][3] = String.valueOf(((Carne) (listaProductos.get(i))).getStock());
				matriz[j][4] = String.valueOf(((Carne) (listaProductos.get(i))).getPrecio());
				matriz[j][5] = String.valueOf(((Carne) (listaProductos.get(i))).getIva().valor() + "%");
				matriz[j][6] = String.valueOf(((Carne) (listaProductos.get(i))).getTipo().impuesto() + "%");
				matriz[j][7] = ((Carne) (listaProductos.get(i))).getCorte().name();
				matriz[j][8] = String.valueOf(((Carne) (listaProductos.get(i))).getPeso());
				j++;
			}
		}
		return matriz;

	}

	/**
	 * Convertir la lista de productos solo de Pescados a matriz
	 * 
	 * @return matriz obtenda con la lista de productos
	 */
	public String[][] pasoAMatrizPescado() {
		Collections.sort(listaProductos);
		String[][] matriz = new String[sizePescados()][8];
		int j = 0;
		for (int i = 0; i < listaProductos.size(); i++) {
			if (listaProductos.get(i) instanceof Pescado) {
				matriz[j][0] = String.valueOf(((Pescado) (listaProductos.get(i))).getIdentificador());
				matriz[j][1] = ((Pescado) (listaProductos.get(i))).getNombre();
				matriz[j][2] = ((Pescado) (listaProductos.get(i))).getDescripcion();
				matriz[j][3] = String.valueOf(((Pescado) (listaProductos.get(i))).getStock());
				matriz[j][4] = String.valueOf(((Pescado) (listaProductos.get(i))).getPrecio());
				matriz[j][5] = String.valueOf(((Pescado) (listaProductos.get(i))).getIva().valor() + "%");
				matriz[j][6] = String.valueOf(((Pescado) (listaProductos.get(i))).getTipo().impuesto() + "%");
				matriz[j][7] = String.valueOf(((Pescado) (listaProductos.get(i))).getPeso());
				j++;
			}
		}
		return matriz;

	}

	/**
	 * Mostrar todos los productos de la lista
	 * 
	 * @return todos los productos
	 * @throws ListaVaciaException
	 */
	public String mostrarTodos() throws ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		Collections.sort(listaProductos);
		StringBuilder cadena = new StringBuilder("---TODOS PRODUCTOS---\n");
		int i = 0;
		for (Producto producto : listaProductos) {
			cadena.append((i + 1) + ")" + producto.toString() + "\n");
			i++;
		}

		return cadena.toString();
	}

	/**
	 * Mostrar de forma breve todos los productos de la lista
	 * 
	 * @return toda la lista con formato breve
	 * @throws ListaVaciaException
	 */
	public String mostrarTodosBreve() throws ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		Collections.sort(listaProductos);
		StringBuilder cadena = new StringBuilder("---TODOS PRODUCTOS---\n");
		int i = 0;
		for (Producto producto : listaProductos) {
			cadena.append((i + 1) + ")" + ((Bebida) producto).getNombre() + "\t" + ((Cobrable) producto).cobrar() + "\n");
			i++;
		}

		return cadena.toString();
	}

	/**
	 * Mostrar solo las bebidas de la lista de productos
	 * 
	 * @return las bebidas de la lista
	 * @throws ListaVaciaException
	 */
	public String mostrarBebidas() throws ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		Collections.sort(listaProductos);
		StringBuilder cadena = new StringBuilder("---BEBIDAS---\n");
		int i = 0;
		for (Producto producto : listaProductos) {
			if (producto instanceof Bebida) {
				cadena.append(producto.toString() + "\n");
				i++;
			}
		}
		if (i == 0)
			throw new ListaVaciaException("ERROR:No existen bebidas");

		return cadena.toString();
	}

	/**
	 * Mostrar solo las carnes de la lista de productos
	 * 
	 * @return las carnes de la lista de productos
	 * @throws ListaVaciaException
	 */
	public String mostrarCarnes() throws ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		Collections.sort(listaProductos);
		StringBuilder cadena = new StringBuilder("---CARNES---\n");
		int i = 0;
		for (Producto producto : listaProductos) {
			if (producto instanceof Carne) {
				cadena.append(producto.toString() + "\n");
				i++;
			}
		}
		if (i == 0)
			throw new ListaVaciaException("ERROR:No existen carnes");

		return cadena.toString();
	}

	/**
	 * Mostrar solo los pescados de la lista de productos
	 * 
	 * @return los pescados
	 * @throws ListaVaciaException
	 */
	public String mostrarPescados() throws ListaVaciaException {
		if (isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		Collections.sort(listaProductos);
		StringBuilder cadena = new StringBuilder("---PESCADOS---\n");
		int i = 0;
		for (Producto producto : listaProductos) {
			if (producto instanceof Pescado) {
				cadena.append(producto.toString() + "\n");
				i++;
			}
		}
		if (i == 0)
			throw new ListaVaciaException("ERROR:No existen pescados");

		return cadena.toString();
	}

	/**
	 * Compribar si esta vacia la lista
	 * 
	 * @return si esta vacia la lista de productos.
	 */
	public boolean isEmpty() {
		return listaProductos.isEmpty();
	}

	/**
	 * Numero de bebidas que contiene la lista de productos
	 * 
	 * @return numero de bebidas
	 */
	public int sizeBebidas() {
		int numerodeBebidas = 0;
		for (Producto producto : listaProductos) {
			if (producto instanceof Bebida) {
				numerodeBebidas++;
			}
		}
		return numerodeBebidas;
	}

	/**
	 * Numero de carnes que contiene la lista de productos
	 * 
	 * @return numero de carnes
	 */
	public int sizeCarnes() {
		int numerodeCarnes = 0;
		for (Producto producto : listaProductos) {
			if (producto instanceof Carne) {
				numerodeCarnes++;
			}
		}
		return numerodeCarnes;
	}

	/**
	 * Numero de pescados que dispone la lista de productos
	 * 
	 * @return numero de pescadoss
	 */
	public int sizePescados() {
		int numerodePescados = 0;
		for (Producto producto : listaProductos) {
			if (producto instanceof Pescado) {
				numerodePescados++;
			}
		}
		return numerodePescados;
	}

}
