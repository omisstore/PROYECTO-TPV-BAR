package estructura;

import java.io.Serializable;
import java.time.LocalDate;

import estructura.enumeraciones.Corte;
import estructura.enumeraciones.Envase;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoBebida;
import estructura.enumeraciones.TipoCarne;
import estructura.enumeraciones.TipoPescado;
import estructura.exceptions.CantidadNoValidaException;
import estructura.exceptions.FicheroNoExisteException;
import estructura.exceptions.ListaVaciaException;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PesoNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.ProductoNoEncontradoExcepcion;
import estructura.exceptions.ProductoYaExisteException;
import estructura.exceptions.StockNoValidoException;
import estructura.exceptions.TelefonoNoValidoException;
import estructura.exceptions.VueltaNoValidaException;
import tui.utiles.Teclado;

/**
 * Tpv
 * 
 * @author Javier Ponferrada Lopez
 * @version 1.0
 */
public class Tpv implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListaProductos listaPrincipal = new ListaProductos();
	private ListaProductos listaProductos = new ListaProductos();
	private Empresa empresa;
	private FicheroTickets fileRegistro;
	private boolean modificado;

	public Tpv() {
		this.empresa = new Empresa();
		try {
			fileRegistro = new FicheroTickets("resource/tickets.txt");
		} catch (FicheroNoExisteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Contructor TPV
	 * 
	 * @throws TelefonoNoValidoException
	 * @throws NombreNoValidoException
	 * @throws FicheroNoExisteException
	 * 
	 */
	public Tpv(String nombre) throws NombreNoValidoException, TelefonoNoValidoException, FicheroNoExisteException {
		this.empresa = new Empresa(nombre);
		fileRegistro = new FicheroTickets("resource/tickets.txt");
	}

	public ListaProductos getListaProductos() {
		return listaProductos;
	}

	/**
	 * Asignar un nombre a la empresa dueña del TPV
	 * 
	 * @throws NombreNoValidoException
	 */
	public void asignarNombreEmpresa(String nombreEmpresa) throws NombreNoValidoException {
		this.empresa.setNombre(nombreEmpresa);
	}

	/**
	 * Asignar una localidad a la empresa dueña del TPV
	 */
	public void asignarLocalidadEmpresa(String localidad) {
		this.empresa.setLocalidad(localidad);
	}

	/**
	 * Asignar una calle a la empresa dueña del TPV
	 */
	public void asignarCalleEmpresa(String calle) {
		this.empresa.setCalle(calle);
	}

	/**
	 * Asignar un numero de telefono a la empresa dueña del TPV
	 * 
	 * @throws TelefonoNoValidoException
	 */
	public void asignarNumeroTelefonoEmpresa(String numTelefono) throws TelefonoNoValidoException {
		this.empresa.setNumeroTelefono(numTelefono);
	}

	/**
	 * 
	 * @return nombre de la empresa
	 */
	public String getNombreEmpresa() {
		return empresa.getNombre();
	}

	/**
	 * 
	 * @return provincia de la empresa
	 */
	public String getProvinciaEmpresa() {
		return empresa.getProvincia();
	}

	/**
	 * 
	 * @return Teléfono de la empresa
	 */
	public String getTelefonoEmpresa() {
		return empresa.getNumeroTelefono();
	}

	/**
	 * 
	 * @return localidad de la empresa
	 */
	public String getLocalidadEmpresa() {
		return empresa.getLocalidad();
	}

	/**
	 * 
	 * @return calle de la empresa
	 */
	public String getCalleEmpresa() {
		return empresa.getCalle();
	}

	/**
	 * Asignar una provincia a la empresa dueña del TPV
	 * 
	 * @throws TelefonoNoValidoException
	 */
	public void asignarProvinciaEmpresa(String provincia) {
		this.empresa.setProvincia(provincia);
	}

	/**
	 * Borrar producto de las lista de procutos.
	 * 
	 * @return Producto Borrado
	 * @throws ProductoNoEncontradoExcepcion
	 *             ------------------Configuracion-TPV---------------
	 * @throws ListaVaciaException
	 */
	public void removeProductoListaProductos(Producto producto)
			throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		if (listaProductos.isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		listaProductos.remove(producto);
	}

	/**
	 * Borrar producto de las lista de procutos, por identificador unico
	 * 
	 * @param identificador
	 *            unico del producto
	 * @throws ProductoNoEncontradoExcepcion
	 *             ------------------Configuracion-TPV---------------
	 * @throws ListaVaciaException
	 */
	public void removeProductoListaProductos(int identificador)
			throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		if (listaProductos.isEmpty())
			throw new ListaVaciaException("ERROR:Lista vacia");
		listaProductos.remove(new Producto(identificador));
	}

	/**
	 * Borrar producto de la lista principal (cuenta)
	 * 
	 * @param posicion
	 * @return producto borrado
	 * @throws ListaVaciaException
	 * @throws ProductoNoEncontradoExcepcion
	 *             ----------------Utilidad-Principal-TPV-----------
	 */
	public Producto removeProductoListaPrincipal(int posicion)
			throws ListaVaciaException, ProductoNoEncontradoExcepcion {
		if (listaPrincipal.isEmpty())
			throw new ListaVaciaException("ERROR:Cuenta vacia");
		return listaPrincipal.removeForTable(posicion);
	}

	/**
	 * Borrar producto de las lista de Principal.
	 * 
	 * @return Producto Borrado
	 * @param producto
	 *            a borrar
	 * @throws ProductoNoEncontradoExcepcion
	 *             ----------------Utilidad-Principal-TPV-----------
	 * @throws ListaVaciaException
	 */
	public void removeProductoListaPrincipal(Producto producto)
			throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		if (listaPrincipal.isEmpty())
			throw new ListaVaciaException("ERROR:Cuenta vacia");
		listaPrincipal.remove(producto);
	}

	public ListaProductos getListaPrincipal() {
		return listaPrincipal;
	}

	/**
	 * ----------------Utilidad-Principal-TPV-----------
	 * 
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws StockNoValidoException
	 * @throws FicheroNoExisteException
	 * @throws ListaVaciaException
	 * @return ticket
	 */
	public String cobrarTui() throws ProductoNoEncontradoExcepcion, StockNoValidoException, FicheroNoExisteException,
			ListaVaciaException {
		if (listaPrincipal.size() == 0)
			throw new ListaVaciaException("ERROR:Cuenta vacia");

		double dineroCliente = 0;
		double vuelta = 0;
		double cuenta;
		do {
			cuenta = precioTotalListaPrincipal();
			System.out.println("TOTAL CUENTA : " + cuenta + "€");
			dineroCliente += Teclado.leerDecimalPositivo("Introduce dinero cliente:");
			vuelta = dineroCliente - cuenta;
			vuelta = Math.round(vuelta * 100d) / 100d;
			if (vuelta >= 0) {
				System.out.println("Vuelta: " + Math.round(vuelta * 100d) / 100d + "€");
				vuelta = 0;
			} else if (vuelta < 0)
				System.out.println("Queda por pagar: " + Math.abs(vuelta));

		} while (vuelta < 0 || vuelta > 0);

		String ticket = imprimirTicket();
		reducirStocksTui();
		saveCobro();
		listaPrincipal.clear();
		return ticket;
	}

	/**
	 * Cobrar cuenta en GUI
	 * 
	 * @param dineroCliente
	 * @return vuelta al cliente
	 * @throws StockNoValidoException
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws FicheroNoExisteException
	 * @throws VueltaNoValidaException
	 * @throws ListaVaciaException 
	 */
	public double cobrarGui(double dineroCliente) throws StockNoValidoException, ProductoNoEncontradoExcepcion,
			FicheroNoExisteException, VueltaNoValidaException, ListaVaciaException {
		double vuelta;
		vuelta = dineroCliente - precioTotalListaPrincipal();
		vuelta = Math.round(vuelta * 100d) / 100d;

		if (vuelta >= 0) {
			saveCobro();
			listaPrincipal.clear();
			return vuelta;
		} else
			throw new VueltaNoValidaException("El dinero del clente no es suficiente, para cobrar");

	}

	/**
	 * Guardar el cobro en el fichero de tickets
	 * 
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws FicheroNoExisteException
	 * @throws ListaVaciaException 
	 * 
	 */
	private void saveCobro() throws FicheroNoExisteException, ProductoNoEncontradoExcepcion, ListaVaciaException {
		fileRegistro.escribir(imprimirTicket()+"\n--------------------------\n");
	}

	/**
	 * ----------------Utilidad-Principal-TPV-----------
	 * 
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws ListaVaciaException
	 */
	public String comanda() throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		return imprimirComanda();
	}

	/**
	 * Reducir stock de todos los productos que contiene la lista principal de
	 * producto.Usado solo en la interfaz de texto
	 * 
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws StockNoValidoException
	 */
	private void reducirStocksTui() throws StockNoValidoException, ProductoNoEncontradoExcepcion {
		for (int i = 0; i < listaPrincipal.size(); i++) {
			listaPrincipal.get(i).decrementarStock(1);
		}

	}

	/**
	 * Obtener el producto de la lista la lista identificado por su
	 * indentificador unico
	 * 
	 * @param identificador
	 * @return
	 * @throws ProductoNoEncontradoExcepcion
	 */
	public Producto getProductoListaProductos(int identificador) throws ProductoNoEncontradoExcepcion {
		return listaProductos.get(listaProductos.obtenerPosicionPorIndicador(identificador));
	}

	/**
	 * Vuelca un producto de la lista de productos a La lista principal de
	 * productos
	 * 
	 * @param producto
	 *            a volcar
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws ProductoYaExisteException
	 */
	public void volcarProductoListaProductosAListaPrincipal(Producto producto)
			throws ProductoNoEncontradoExcepcion, ProductoYaExisteException {
		listaPrincipal.add(listaProductos.get(listaProductos.indexOf(producto)));
	}


	/**
	 * Vuelca un producto o varios de la lista de productos a La lista principal
	 * de productos, por identificador.
	 * 
	 * @param indicador,
	 *            del producto de la lista de productos
	 * @param cantidad,
	 *            cantidad de veces que se ha pedido el producto
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws CantidadNoValidaException
	 * @throws PrecioNoValidoException
	 * @throws StockNoValidoException
	 * @throws NombreNoValidoException
	 * @throws PesoNoValidoException
	 * @throws ProductoYaExisteException
	 */
	public void volcarProductoListaProductosAListaPrincipalIndentificador(int identificador, int cantidad)
			throws ProductoNoEncontradoExcepcion, CantidadNoValidaException, NombreNoValidoException,
			StockNoValidoException, PrecioNoValidoException, PesoNoValidoException, ProductoYaExisteException {

		Producto producto = new Producto(identificador);
		int descontar = listaProductos.get(listaProductos.indexOf(producto)).getStock() - cantidad;
		if (descontar < 0 || cantidad <0)
			throw new CantidadNoValidaException("Error: La cantidad indicada supera al stock del articulo.");
		else 
			listaProductos.get(listaProductos.indexOf(producto)).setStock(descontar);
		
		if(listaPrincipal.contains(producto)){//Comprueba si la cuenta ya dispone del producto a añadir
			listaPrincipal.get(listaPrincipal.indexOf(producto)).incrementarStock(cantidad);
			listaPrincipal.get(listaPrincipal.indexOf(producto)).incrementarPrecio(listaProductos.get(listaProductos.indexOf(producto)).getPrecio()*cantidad);
		
		}else if (listaProductos.get(listaProductos.indexOf(producto)) instanceof Bebida) {
			Bebida bebida = (Bebida) listaProductos.get(listaProductos.indexOf(producto));
			listaPrincipal.add(new Bebida(bebida.getIdentificador(), bebida.getNombre(),bebida.getDescripcion(), cantidad, bebida.getPrecio()*cantidad,
					bebida.getIva(), bebida.getEnvase(), bebida.getTipo()));
		} else if (listaProductos.get(listaProductos.indexOf(producto)) instanceof Carne) {
			Carne carne = (Carne) listaProductos.get(listaProductos.indexOf(producto));
			listaPrincipal.add(new Carne(carne.getIdentificador(),carne.getDescripcion(), carne.getNombre(), cantidad, carne.getPrecio()*cantidad,
					carne.getIva(), carne.getTipo(), carne.getCorte(), carne.getPeso()));
		} else if (listaProductos.get(listaProductos.indexOf(producto)) instanceof Pescado) {
			Pescado pescado = (Pescado) listaProductos.get(listaProductos.indexOf(producto));
			listaPrincipal.add(new Pescado(pescado.getIdentificador(), pescado.getNombre(),pescado.getDescripcion(), cantidad, pescado.getPrecio()*cantidad,
					pescado.getIva(), pescado.getTipo(), pescado.getPeso()));
		}

	}
	
	/**
	 * Volcar el stock de la lista principal(cuenta) al producto de la lista de productos
	 * @param posicionArticulo de la lista principal a volcar
	 * @throws ProductoNoEncontradoExcepcion 
	 * @throws StockNoValidoException 
	 */
	public void volcarListaPrincipalAListaProductos(int posicionArticulo) throws ProductoNoEncontradoExcepcion, StockNoValidoException{
		listaProductos.get(listaProductos.indexOf(listaPrincipal.get(posicionArticulo))).incrementarStock(listaPrincipal.get(posicionArticulo).getStock());
	}
	
	

	/**
	 * Vuelca un producto o varios de la lista de productos a La lista principal
	 * de productos
	 * 
	 * @param producto,
	 *            a volcar
	 * @param cantidad,
	 *            cantidad de veces que se ha pedido el producto
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws CantidadNoValidaException
	 * @throws ProductoYaExisteException
	 */
	public void volcarProductoListaProductosAListaPrincipal(Producto producto, int cantidad)
			throws ProductoNoEncontradoExcepcion, CantidadNoValidaException, ProductoYaExisteException {
		int descontar = listaProductos.get(listaProductos.indexOf(producto)).getStock() - cantidad;
		if (descontar < 0)
			throw new CantidadNoValidaException("El Stock es menor que la cantidad.");
		for (int i = 0; i < cantidad; i++) {

			listaPrincipal.add(listaProductos.get(listaProductos.indexOf(producto)));

		}
	}

	/**
	 * Anadir bebida a la lista de productos
	 * 
	 * @param nombre,de
	 *            la bebida(producto)
	 * @param descripcion,
	 *            de la bebida(producto)
	 * @param stock,
	 *            de la bebida(producto)
	 * @param precio,
	 *            de la bebida(producto)
	 * @param iva,
	 *            de la bebida(producto)
	 * @param envase,
	 *            de la bebida
	 * @param tipoBebida,
	 *            de la bebida
	 * @throws PrecioNoValidoException
	 * @throws StockNoValidoException
	 * @throws NombreNoValidoException
	 * 
	 */
	public void addBebidaAListaProductos(String nombre, String descripcion, int stock, double precio, Iva iva,
			Envase envase, TipoBebida tipoBebida)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException {
		listaProductos.addBebida(nombre, descripcion, stock, precio, iva, envase, tipoBebida);
	}

	/**
	 * Anadir carne a la lista de productos
	 * 
	 * @param nombre,de
	 *            la carne(producto)
	 * @param descripcion,
	 *            de la bebida(producto)
	 * @param stock,
	 *            de la carne(producto)
	 * @param precio,
	 *            de la carne(producto)
	 * @param iva,
	 *            de la carne(producto)
	 * @param tipoCarne
	 * @param corte,
	 *            tipo de corte de la carne
	 * @param peso,
	 *            de la carne
	 * @throws PesoNoValidoException
	 * @throws PrecioNoValidoException
	 * @throws StockNoValidoException
	 * @throws NombreNoValidoException
	 */
	public void addCarneAListaProductos(String nombre, String descripcion, int stock, double precio, Iva iva,
			TipoCarne tipoCarne, Corte corte, float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		listaProductos.addCarne(nombre, descripcion, stock, precio, iva, tipoCarne, corte, peso);
		;
	}

	/**
	 * Anadir pescado a la lista de productos
	 * 
	 * @param nombre,de
	 *            la pescado(producto)
	 * @param descripcion,
	 *            de la pescado(producto)
	 * @param stock,
	 *            de la pescado(producto)
	 * @param precio,
	 *            de la pescado(producto)
	 * @param iva,
	 *            de la pescado(producto)
	 * @param tipoPescado
	 * @param peso,
	 *            del pescado
	 * @throws PesoNoValidoException
	 * @throws PrecioNoValidoException
	 * @throws StockNoValidoException
	 * @throws NombreNoValidoException
	 */
	public void addPescadoAListaProductos(String nombre, String descripcion, int stock, double precio, Iva iva,
			TipoPescado tipoPescado, float peso)
			throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException, PesoNoValidoException {
		listaProductos.addPescado(nombre, descripcion, stock, precio, iva, tipoPescado, peso);
	}

	/**
	 * Imprime el ticket de la lista principal
	 * 
	 * @return ticket de compra
	 * @throws ProductoNoEncontradoExcepcion
	 *             ----------------Utilidad-Principal-TPV-----------
	 * @throws ListaVaciaException
	 */
	public String imprimirTicket() throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		if (listaPrincipal.size() == 0)
			throw new ListaVaciaException("Cuenta vacia");

		StringBuilder ticket = new StringBuilder("\t" + empresa.getNombre() + "\n" + "\t----------------------\n"
				+ "\tC/ " + empresa.getCalle() + "/ \n\t" + empresa.getLocalidad() + "/ " + empresa.getProvincia()
				+ "\n" + "\t\tTelf:" + empresa.getNumeroTelefono() + "\n");
		ticket.append("\t---------------------\n" + "\tFecha: " + LocalDate.now() + "\n\t---------------------\n");
		ticket.append("\t" + "UND.\t" + "Nombre" + "\t" + "PVP.\n");

		for (int i = 0; i < listaPrincipal.size(); i++) {
			if (listaPrincipal.get(i) instanceof Bebida) {
				ticket.append("\t"+listaPrincipal.get(i).getStock()+"\t" + ((Bebida) listaPrincipal.get(i)).getNombre() + "\t"
						+ ((Bebida) listaPrincipal.get(i)).cobrar() + "€\n");
			} else if (listaPrincipal.get(i) instanceof Carne) {
				ticket.append("\t"+listaPrincipal.get(i).getStock()+"\t" + ((Carne) listaPrincipal.get(i)).getNombre() + "\t"
						+ ((Carne) listaPrincipal.get(i)).cobrar() + "€\n");
			} else if (listaPrincipal.get(i) instanceof Pescado) {
				ticket.append("\t"+listaPrincipal.get(i).getStock()+"\t" + ((Pescado) listaPrincipal.get(i)).getNombre() + "\t"
						+ ((Pescado) listaPrincipal.get(i)).cobrar() + "€\n");
			}

		}
		ticket.append("\n\t---------------------\n" + "\tTOTAL:\t" + precioTotalListaPrincipal() + "€\n");
		return ticket.toString();

	}

	/**
	 * Imprime la comanda de la lista Principal
	 * 
	 * @throws ProductoNoEncontradoExcepcion
	 *             ----------------Utilidad-Principal-TPV-----------
	 * @throws ListaVaciaException
	 */
	public String imprimirComanda() throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		if (listaPrincipal.size() == 0)
			throw new ListaVaciaException("Cuenta vacía.");

		StringBuilder comanda = new StringBuilder("*******************\n");
		for (int i = 0; i < listaPrincipal.size(); i++) {
			comanda.append(listaPrincipal.get(i).getStock()+"* "+ listaPrincipal.get(i).getNombre() + "\n");
		}
		comanda.append("*******************");
		return comanda.toString();
	}

	/**
	 * Precio total ListaPrincial
	 * 
	 * @return precio Total
	 * @throws ProductoNoEncontradoExcepcion
	 */
	public double precioTotalListaPrincipal() throws ProductoNoEncontradoExcepcion {
		double total = 0;
		for (int i = 0; i < listaPrincipal.size(); i++) {
			if (listaPrincipal.get(i) instanceof Bebida) {
				total += ((Bebida) listaPrincipal.get(i)).cobrar();
			} else if (listaPrincipal.get(i) instanceof Carne) {
				total += ((Carne) listaPrincipal.get(i)).cobrar();
			} else if (listaPrincipal.get(i) instanceof Pescado) {
				total += ((Pescado) listaPrincipal.get(i)).cobrar();
			}

		}
		return Math.round(total * 100d) / 100d;
	}

	/**
	 * Muestra todos los productos de la lista de productos
	 * 
	 * @return lista de todos los productos
	 * @throws ListaVaciaException
	 */
	public String mostrarListaProducos() throws ListaVaciaException {
		return listaProductos.mostrarTodos();
	}

	/**
	 * Muestra las bebidas de la lista de productos
	 * 
	 * @return lista de bebidas
	 * @throws ListaVaciaException
	 */
	public String mostrarBebidasListaProducos() throws ListaVaciaException {
		return listaProductos.mostrarBebidas();
	}

	/**
	 * Muestra las carnes de la lista de productos
	 * 
	 * @return lista de carnes
	 * @throws ListaVaciaException
	 */
	public String mostrarCarnesListaProducos() throws ListaVaciaException {
		return listaProductos.mostrarCarnes();
	}

	/**
	 * Muestra los pescados de la lista de productos
	 * 
	 * @return lista de pescados
	 * @throws ListaVaciaException
	 */
	public String mostrarPescadosListaProducos() throws ListaVaciaException {
		return listaProductos.mostrarPescados();
	}

	/**
	 * Mostrar todos los datos de la empresa
	 * 
	 * @return datos de la empresa
	 */
	public String mostrarEmpresa() {
		return empresa.toString();
	}

	/**
	 * @return Si esta modificado o no el TPV
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * Indicar que se ha modificado o no el TPV
	 * 
	 * @param modificado
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	 * @return numero de bebidas en la lista de productos
	 */
	public int numeroDeBebidasListaDeProductos() {
		return listaProductos.sizeBebidas();
	}

	/**
	 * @return numero de carnes de la lista de productos
	 */
	public int numeroDeCarnesListaDeProductos() {
		return listaProductos.sizeCarnes();
	}

	/**
	 * @return numero de pescados en la lista de productos
	 */
	public int numeroDePescadosListaDeProductos() {
		return listaProductos.sizePescados();
	}

	/**
	 * Pasar a matriz, solo las bebidas de la lista de productos
	 * 
	 * @return matriz de bebidas
	 */
	public String[][] pasoAMatrizBebidaListaProductos() {
		return listaProductos.pasoAMatrizBebida();
	}

	/**
	 * Pasar a matriz, solo las carnes de la lista de productos
	 * 
	 * @return matriz de carnes
	 */
	public String[][] pasoAMatrizCarneListaProductos() {
		return listaProductos.pasoAMatrizCarne();
	}

	/**
	 * Pasar a matriz, solo los pescados de la lista de productos
	 * 
	 * @return matriz de pescados
	 */
	public String[][] pasoAMatrizPescadoListaProductos() {
		return listaProductos.pasoAMatrizPescado();
	}

	/**
	 * Pasar a matriz la cuenta
	 * 
	 * @return matriz de la cuenta
	 */
	public String[][] pasoAMatrizCuenta() {
		return listaPrincipal.pasoAMatriz();
	}

	/**
	 * Mostrar lista principal (cuenta)
	 * 
	 * @return lista principal
	 * @throws ListaVaciaException
	 */
	public String mostrarListaPrincipal() throws ListaVaciaException {
		return listaPrincipal.mostrarTodosBreve();
	}
	
	/**
	 * 
	 * @param posicionArticuloCuenta
	 * @param stockADecrementar
	 * @throws ProductoNoEncontradoExcepcion
	 * @throws StockNoValidoException 
	 */
	public void decrementarStockProductoCuenta(int posicionArticuloCuenta, int stockADecrementar) throws ProductoNoEncontradoExcepcion, StockNoValidoException {
		listaPrincipal.get(posicionArticuloCuenta).decrementarStock(stockADecrementar);
		listaProductos.get(listaProductos.indexOf(listaPrincipal.get(posicionArticuloCuenta))).incrementarStock(stockADecrementar);
		
	}
	
	/**
	 * Comprobar que no exista ningún articulo en la cuenta con el número de stock a 0
	 * @throws ProductoNoEncontradoExcepcion 
	 */
	public void comprobarStockCuenta() throws ProductoNoEncontradoExcepcion {
		for (int i = 0; i < listaPrincipal.size(); i++) {
			if(listaPrincipal.get(i).getStock() == 0)
				listaPrincipal.removeForTable(i);
		}
		
	}

	/**
	 * Refrescar los precios de todos los productos de la lista tomando en cuenta su stock y el precio del articulo.
	 * @throws PrecioNoValidoException
	 * @throws ProductoNoEncontradoExcepcion
	 */
	public void refrescarPrecioProductoCuenta() throws PrecioNoValidoException, ProductoNoEncontradoExcepcion {
		for (int i = 0; i < listaPrincipal.size(); i++) {
			listaPrincipal.get(i).setPrecio(listaProductos.get(listaProductos.indexOf(listaPrincipal.get(i))).getPrecio()*listaPrincipal.get(i).getStock());
		}
	}
	
	/**
	 * Leer los ticket del fichero de registros
	 * @return tickets
	 * @throws FicheroNoExisteException
	 */
	public String leerTickets() throws FicheroNoExisteException {
		return fileRegistro.leer();
	}

}
