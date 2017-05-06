package tui;

import estructura.Bebida;
import estructura.Carne;
import estructura.Pescado;
import estructura.Producto;
import estructura.Tpv;
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
import estructura.exceptions.StockNoValidoException;
import estructura.exceptions.TelefonoNoValidoException;
import tui.utiles.DeseaContinuar;
import tui.utiles.Menu;
import tui.utiles.Teclado;

public class TestTpv {
	private static Menu menuGeneral = new Menu("--MENU GENERAL--", new String[] { "Usar", "Configurar", "Salir" });
	private static Menu menuConfiguracion = new Menu("--CONFIGURACIÓN--",
			new String[] { "Articulos", "Datos empresa", "Salir" });
	private static Menu menuConfigurarMostrarArticulos = new Menu("--ARTICULO A MOSTRAR--",
			new String[] { "Bebida", "Carne", "Pescado", "Todos", "Salir" });
	private static Menu menuConfigurarDatosTipoArticulo = new Menu("--ARTICULO A CONFIGURAR--",
			new String[] { "Bebida", "Carne", "Pescado", "Salir" });
	private static Menu menuConfigurarAnadirArticulo = new Menu("--ARTICULO A AÑADIR--",
			new String[] { "Bebida", "Carne", "Pescado", "Salir" });
	private static Menu menuConfigurarBorrarArticulo = new Menu("--ARTICULO A BORRAR--",
			new String[] { "Bebida", "Carne", "Pescado", "Salir" });
	private static Menu menuConfigurarArticulos = new Menu("--CONFIGURACIÓN ARTICULOS--",
			new String[] { "Añadir articulo", "Borrar articulo", "Mostrar articulos", "Datos articulo", "Salir" });
	private static Menu menuEleccionIva = new Menu("--IVA--", Iva.values());
	private static Menu menuEleccionEnvase = new Menu("--ENVASE--", Envase.values());
	private static Menu menuEleccionTipoBebida = new Menu("--TIPO BEBIDA--", TipoBebida.values());
	private static Menu menuEleccionCorte = new Menu("--CORTE--", Corte.values());
	private static Menu menuEleccionTipoCarne = new Menu("--TIPO CARNE--", TipoCarne.values());
	private static Menu menuEleccionTipoPescado = new Menu("--TIPO PESCADO--", TipoPescado.values());
	private static Menu menuUso = new Menu("--MENU PRINCIPAL--", new String[] { "Añadir articulo a cuenta",
			"Borrar articulo de cuenta", "Mostrar lista cuenta", "Imprimir comanda", "Cobrar", "Salir" });
	private static Menu menuUsoAnadirArticulo = new Menu("--AÑADIR A LA CUENTA--",
			new String[] { "Bebida", "Carne", "Pescado", "Salir" });

	private static Tpv tpv;

	private static Tpv getTpv() {
		return tpv;
	}

	public static void main(String[] args) {
		try {
			tpv = new Tpv(Teclado.leerCadena("Nombre empresa:"));
			opcionesMenuGeneral();
		} catch (NombreNoValidoException | TelefonoNoValidoException | FicheroNoExisteException e) {
			System.err.println(e.getMessage());
		}

	}

	private static void opcionesMenuGeneral() {
		int opcion;
		do {
			opcion = menuGeneral.gestionar();
			gestionarMenuGeneral(opcion);
		} while (opcion != menuGeneral.getSALIR());
	}

	/**
	 * Gestiona las opciones del menu general.
	 * 
	 * @param opcion,
	 *            del menu introducida por el usuario.
	 */
	private static void gestionarMenuGeneral(int opcion) {
		switch (opcion) {
		case 1:// Usar
			opcionesMenuUso();
			break;

		case 2:// Configurar
			opcionesMenuConfigurar();
			break;
		case 3:// Salir
			break;
		}

	}

	/**
	 * Opciones del menu configurar
	 */
	private static void opcionesMenuConfigurar() {
		int opcion;
		do {
			opcion = menuConfiguracion.gestionar();
			gestionarMenuConfiguracion(opcion);
		} while (opcion != menuConfiguracion.getSALIR());

	}

	/**
	 * Gestiona las opciones del menu de configuracion
	 * 
	 * @param opcion
	 */
	private static void gestionarMenuConfiguracion(int opcion) {
		switch (opcion) {
		case 1:// Articulos
			opcionesConfigurarArticulos();
			break;
		case 2:// Datos empresa
			cambiarDatosEmpresa();
			break;
		case 3:// Salir
			break;

		}

	}

	/**
	 * Cambiar los datos de la empresa
	 */
	private static void cambiarDatosEmpresa() {
		System.out.println(getTpv().mostrarEmpresa());
		try {
			if (DeseaContinuar.deseaContinuar("Deseas cambiar la nombre de la empresa? (s/n):")) {
				getTpv().asignarNombreEmpresa(Teclado.leerCadena("Nombre:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar la provincia de la empresa? (s/n):")) {
				getTpv().asignarProvinciaEmpresa(Teclado.leerCadena("Provincia:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar la localidad de la empresa? (s/n):")) {
				getTpv().asignarLocalidadEmpresa(Teclado.leerCadena("Localidad:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar la calle de la empresa? (s/n):")) {
				getTpv().asignarCalleEmpresa(Teclado.leerCadena("Calle:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el numero de teléfono de la empresa? (s/n):")) {
				getTpv().asignarNumeroTelefonoEmpresa(Teclado.leerCadena("Teléfono:"));
			}

		} catch (NombreNoValidoException | TelefonoNoValidoException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Opciones del menu configurar Articulos de la lista de productos
	 * (configuracion)
	 */
	private static void opcionesConfigurarArticulos() {
		int opcion;
		do {
			opcion = menuConfigurarArticulos.gestionar();
			getionarOpionesConfigurarArticulos(opcion);
		} while (opcion != menuConfigurarArticulos.getSALIR());

	}

	/**
	 * Gestiona las opciones del menu configurar articulos de la lista de
	 * productos (configuracion)
	 * 
	 * @param opcion
	 */
	private static void getionarOpionesConfigurarArticulos(int opcion) {
		switch (opcion) {
		case 1:// Añadir articulo a lista de productos(configuracion)
			opcionesMenuConfigurarAnadirArticulo();
			break;
		case 2:// Borrar articulo a lista de productos(configuracion)
			opcionesMenuConfigurarBorrarArticulo();
			break;
		case 3:// Mostrar articulos de la lista de productos(configuracion)
			opcionesMenuConfigurarMostrarArticulos();
			break;
		case 4:// Datos articulo de la lista de productos(configuracion)
			opcionesMenuConfigurarDatosTipoArticulo();
			break;
		case 5:// Salir
			break;
		}
	}

	/**
	 * Opciones del menu de configuracion de los datos de los articulos de la
	 * lista de productos(configuracion)
	 */
	private static void opcionesMenuConfigurarDatosTipoArticulo() {
		int opcion;
		do {
			opcion = menuConfigurarDatosTipoArticulo.gestionar();
			gestionaOpcionesMenuConfigurarDatosTipoArticulo(opcion);
		} while (opcion != menuConfigurarDatosTipoArticulo.getSALIR());

	}

	/**
	 * Gestiona las opciones del menu de gestionar de eleccion del tipo de
	 * articulo que deseas configurar sus datos de la lista de
	 * productos(configuracion)
	 * 
	 * @param opcion
	 */
	private static void gestionaOpcionesMenuConfigurarDatosTipoArticulo(int opcion) {
		switch (opcion) {
		case 1:// Cambiar los datos de las Bebidas de la lista de productos
			cambiarDatosBebidaListaProductos();
			break;
		case 2:// Cambiar los datos de las Carnes de la lista de productos
			cambiarDatosCarnesListaProductos();
			break;
		case 3:// Cambiar los datos de los Pescados de la lista de productos
			cambiarDatosPescadosListaProductos();
			break;
		case 4:// Salir
			break;
		}

	}

	/**
	 * Cambiar los datos de un pescado de la lista de productos (configuracion)
	 */
	private static void cambiarDatosPescadosListaProductos() {
		try {
			System.out.println(getTpv().mostrarCarnesListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			Pescado pescado = (Pescado) getTpv().getListaProductos()
					.get(getTpv().getListaProductos().indexOf(producto));
			if (DeseaContinuar.deseaContinuar("Deseas cambiar el nombre? (s/n):")) {
				pescado.setNombre(Teclado.leerCadena("Nombre:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar la descripcion? (s/n):")) {
				pescado.setDescripcion(Teclado.leerCadena("Descripcion:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el stock? (s/n):")) {
				pescado.setStock(Teclado.leerEntero("Stock:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el precio? (s/n):")) {
				pescado.setPrecio(Teclado.leerDecimal("Precio:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Iva? (s/n):")) {
				pescado.setIva(Iva.values()[menuEleccionIva.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Tipo pescado? (s/n):")) {
				pescado.setTipo(TipoPescado.values()[menuEleccionTipoPescado.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el peso? (s/n):")) {
				pescado.setPeso((float) Teclado.leerDecimal("Peso:"));
			}

		} catch (NombreNoValidoException | StockNoValidoException | PrecioNoValidoException | PesoNoValidoException
				| ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Cambiar los datos de un carne de la lista de productos (configuracion)
	 */
	private static void cambiarDatosCarnesListaProductos() {
		try {
			System.out.println(getTpv().mostrarCarnesListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			Carne carne = (Carne) getTpv().getListaProductos().get(getTpv().getListaProductos().indexOf(producto));
			if (DeseaContinuar.deseaContinuar("Deseas cambiar el nombre? (s/n):")) {
				carne.setNombre(Teclado.leerCadena("Nombre:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar la descripcion? (s/n):")) {
				carne.setDescripcion(Teclado.leerCadena("Descripcion:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el stock? (s/n):")) {
				carne.setStock(Teclado.leerEntero("Stock:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el precio? (s/n):")) {
				carne.setPrecio(Teclado.leerDecimal("Precio:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Iva? (s/n):")) {
				carne.setIva(Iva.values()[menuEleccionIva.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Tipo carne? (s/n):")) {
				carne.setTipo(TipoCarne.values()[menuEleccionTipoCarne.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el corte? (s/n):")) {
				carne.setCorte(Corte.values()[menuEleccionCorte.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el peso? (s/n):")) {
				carne.setPeso((float) Teclado.leerDecimal("Peso:"));
			}

		} catch (NombreNoValidoException | StockNoValidoException | PrecioNoValidoException | PesoNoValidoException
				| ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Cambiar los datos de la bebida de la lista de productos (configuracion)
	 */
	private static void cambiarDatosBebidaListaProductos() {
		try {
			System.out.println(getTpv().mostrarBebidasListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			Bebida bebida = (Bebida) getTpv().getListaProductos().get(getTpv().getListaProductos().indexOf(producto));
			if (DeseaContinuar.deseaContinuar("Deseas cambiar el nombre? (s/n):")) {
				bebida.setNombre(Teclado.leerCadena("Nombre:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar la descripcion? (s/n):")) {
				bebida.setDescripcion(Teclado.leerCadena("Descripcion:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el stock? (s/n):")) {
				bebida.setStock(Teclado.leerEntero("Stock:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el precio? (s/n):")) {
				bebida.setPrecio(Teclado.leerDecimal("Precio:"));
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Iva? (s/n):")) {
				bebida.setIva(Iva.values()[menuEleccionIva.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Envase? (s/n):")) {
				bebida.setEnvase(Envase.values()[menuEleccionEnvase.gestionar() - 1]);
			} else if (DeseaContinuar.deseaContinuar("Deseas cambiar el Tipo de bebida? (s/n):")) {
				bebida.setTipo(TipoBebida.values()[menuEleccionTipoBebida.gestionar() - 1]);
			}

		} catch (NombreNoValidoException | StockNoValidoException | PrecioNoValidoException
				| ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Opciones del menu de configuracion para la eleccion del tipo de articulo
	 * que se desea mostrar de la lista de productos(Configuracion)
	 */
	private static void opcionesMenuConfigurarMostrarArticulos() {
		int opcion;
		do {
			opcion = menuConfigurarMostrarArticulos.gestionar();
			gestionarMenuConfigurarMostrarArticulos(opcion);
		} while (opcion != menuConfigurarMostrarArticulos.getSALIR());

	}

	/**
	 * Gestiona las opciones del menu de configuracion para la eleccion del tipo
	 * de articulo que deseas mostrar de la lista de productos(Configuracion)
	 * 
	 * @param opcion
	 */
	private static void gestionarMenuConfigurarMostrarArticulos(int opcion) {
		switch (opcion) {
		case 1:// Mostrar las Bebidas de la lista de productos
			mostrarBebidasListaDeProductos();
			break;
		case 2:// Mostrar las Carnes de la lista de productos
			mostrarCarnesListaDeProductos();
			break;
		case 3:// Mostrar los Pescados de la lista de productos
			mostrarPescadosListaDeProductos();
			break;
		case 4:// Mostra todos los articulos de la lista de productos
			mostrarListaDeProductos();
			break;
		case 5:// Salir
			break;
		}

	}

	/**
	 * Mostrar toda la lista de productos (condiguracion)
	 */
	private static void mostrarListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarListaProducos());
		} catch (ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Mostrar de la lista de productos solo los pescados (configuracion)
	 */
	private static void mostrarPescadosListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarPescadosListaProducos());
		} catch (ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Mostrar de la lista de productos solo las carnes (configuracion)
	 */
	private static void mostrarCarnesListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarCarnesListaProducos());
		} catch (ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Mostrar de la lista de productos solo las bebidas (configuracion)
	 */
	private static void mostrarBebidasListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarBebidasListaProducos());
		} catch (ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Opciones del menu para la eleccion de los productos que se desean borrar
	 * de la lista de productos(Configuracion)
	 */
	private static void opcionesMenuConfigurarBorrarArticulo() {
		int opcion;
		do {
			opcion = menuConfigurarBorrarArticulo.gestionar();
			gestionarOpcionesMenuConfigurarBorrarArticulo(opcion);
		} while (opcion != menuConfigurarBorrarArticulo.getSALIR());

	}

	/**
	 * Gestiona las opciones del menu para la eleccion de los productos que se
	 * desean borrar de la lista de productos(Configuracion)
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesMenuConfigurarBorrarArticulo(int opcion) {
		switch (opcion) {
		case 1:// Borrar Bebida de la lista de productos
			borrarBebidaListaDeProductos();
			break;
		case 2:// Borrar Carne de la lista de productos
			borrarCarneListaDeProductos();
			break;
		case 3:// Borrar Pescado de la lista de productos
			borrarPescadoListaDeProductos();
			break;
		case 4:// Salir
			break;
		}
	}

	/**
	 * Borrar de la lista de productos un pescado determinado (configuracion)
	 */
	private static void borrarPescadoListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarPescadosListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			getTpv().removeProductoListaProductos(producto);
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Borrar de la lista de productos una carne determinada (configuracion)
	 */
	private static void borrarCarneListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarCarnesListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			getTpv().removeProductoListaProductos(producto);
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Borrar de la lista de productos una bebida determinada (configuracion)
	 */
	private static void borrarBebidaListaDeProductos() {
		try {
			System.out.println(getTpv().mostrarBebidasListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			getTpv().removeProductoListaProductos(producto);
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Opciones del menu de configuracion para el añadir articulos a la lista de
	 * productos(Configuracion)
	 */
	private static void opcionesMenuConfigurarAnadirArticulo() {
		int opcion;
		do {
			opcion = menuConfigurarAnadirArticulo.gestionar();
			gestionarOpionesMenuConfigurarAnadirArticulo(opcion);
		} while (opcion != menuConfigurarAnadirArticulo.getSALIR());

	}

	/**
	 * Gestiona las opciones del menu para elegir que articulo deseas añadir a
	 * la lista de productos(Configuracion)
	 * 
	 * @param opcion
	 */
	private static void gestionarOpionesMenuConfigurarAnadirArticulo(int opcion) {
		switch (opcion) {
		case 1:// Añadir Bebida a la lista de productos
			addBebidaAListaProductos();
			break;
		case 2:// Añadir Carne a la lista de productos
			addCarneAListaDeProductos();
			break;
		case 3:// Añadir Pescado a la lista de productos
			addPescadoAListaDeProductos();
			break;
		case 4:// Salir
			break;

		}

	}

	/**
	 * Añadir pieza de pescado a la lista de procuctos (Configuracion)
	 */
	private static void addPescadoAListaDeProductos() {
		try {
			getTpv().addPescadoAListaProductos(Teclado.leerCadena("Nombre pescado:"),
					Teclado.leerCadena("Descripcion del pescado:"), Teclado.leerEntero("Stock inicial:"),
					Teclado.leerDecimal("Precio neto:"), Iva.values()[menuEleccionIva.gestionar() - 1],
					TipoPescado.values()[menuEleccionTipoPescado.gestionar() - 1],
					(float) Teclado.leerDecimal("Peso gr:"));
		} catch (NombreNoValidoException | StockNoValidoException | PrecioNoValidoException | PesoNoValidoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Añadir pieza de carne a la lista de procuctos (Configuracion)
	 */
	private static void addCarneAListaDeProductos() {
		try {
			getTpv().addCarneAListaProductos(Teclado.leerCadena("Nombre carne:"),
					Teclado.leerCadena("Descripcion de la carne:"), Teclado.leerEntero("Stock inicial:"),
					Teclado.leerDecimal("Precio neto:"), Iva.values()[menuEleccionIva.gestionar() - 1],
					TipoCarne.values()[menuEleccionTipoCarne.gestionar() - 1],
					Corte.values()[menuEleccionCorte.gestionar() - 1], (float) Teclado.leerDecimal("Peso gr:"));
		} catch (NombreNoValidoException | StockNoValidoException | PrecioNoValidoException | PesoNoValidoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Añadir una bebida a la lita de productos (Configuracion)
	 */
	private static void addBebidaAListaProductos() {
		try {
			getTpv().addBebidaAListaProductos(Teclado.leerCadena("Nombre bebida:"),
					Teclado.leerCadena("Descripcion de la bebida:"), Teclado.leerEntero("Stock inicial:"),
					Teclado.leerDecimal("Precio neto:"), Iva.values()[menuEleccionIva.gestionar() - 1],
					Envase.values()[menuEleccionEnvase.gestionar() - 1],
					TipoBebida.values()[menuEleccionTipoBebida.gestionar() - 1]);
		} catch (NombreNoValidoException | StockNoValidoException | PrecioNoValidoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Opciones del menu uso
	 */
	private static void opcionesMenuUso() {
		int opcion;
		do {
			opcion = menuUso.gestionar();
			gestionarMenuUso(opcion);
		} while (opcion != menuUso.getSALIR());
	}

	/**
	 * Gestiona las opciones del menu de Uso del TPV
	 * 
	 * @param opcion,
	 *            del menu que indica el usuario
	 */
	private static void gestionarMenuUso(int opcion) {
		switch (opcion) {
		case 1:// Añadir articulo a cuenta
			opcionesMenuUsoAnadirArticulo();
			break;
		case 2:// Borrar articulo de cuenta
			borrarArticuloListaPrincipal();
			break;
		case 3:// Mostrar lista cuenta
			mostrarListaPrincipal();
			break;
		case 4:// Imprimir comanda
			imprimirComanda();
			break;
		case 5:// Cobrar
			cobrarCuenta();
			break;
		case 6:// Salir
			break;
		}

	}

	/**
	 * Cobrar cuenta
	 */
	private static void cobrarCuenta() {
		try {
			System.out.println(getTpv().cobrarTui());

		} catch (ProductoNoEncontradoExcepcion | StockNoValidoException | FicheroNoExisteException
				| ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Imprimir comanda
	 */
	private static void imprimirComanda() {
		try {
			System.out.println(getTpv().imprimirComanda());
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Mostrar la lista principal (cuenta)
	 */
	private static void mostrarListaPrincipal() {
		try {
			System.out.println(getTpv().mostrarListaPrincipal());
		} catch (ListaVaciaException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Borrar articulo de la lista principal
	 */
	private static void borrarArticuloListaPrincipal() {
		try {
			System.out.println(getTpv().mostrarListaPrincipal());
			getTpv().removeProductoListaPrincipal(pedirPosicionListaPrincipal());
		} catch (ListaVaciaException | ProductoNoEncontradoExcepcion e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Opciones del menu de uso para añadir un tipo de articulo a la cuenta
	 * (uso)
	 */
	private static void opcionesMenuUsoAnadirArticulo() {
		int opcion;
		do {
			opcion = menuUsoAnadirArticulo.gestionar();
			gestionarOpcionesMenuUsoAnadirArticulo(opcion);
		} while (opcion != menuUsoAnadirArticulo.getSALIR());

	}

	/**
	 * Gestionar las opciones del menu para el uso de añadir articulos a la
	 * cuenta (uso)
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesMenuUsoAnadirArticulo(int opcion) {
		switch (opcion) {
		case 1:// Añadir Bebida de la lista de productos a la lista
				// principal(cuenta)
			anadirBebidaListaProductosAListaPrincipal();
			break;
		case 2:// Añadir Carne de la lista de productos a la lista
				// principal(cuenta)
			anadirCarneListaProductosAListaPrincipal();
			break;
		case 3:// Añadir Pescado a la lista de productos
			anadirPescadoListaProductosAListaPrincipal();
			break;
		case 4:// Salir
			break;
		}
	}

	/**
	 * Añadir a la cuenta(Lista principal) una cantidad indicada de pescado de
	 * la lista de productos (uso)
	 */
	private static void anadirPescadoListaProductosAListaPrincipal() {
		try {
			System.out.println(getTpv().mostrarPescadosListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			getTpv().volcarProductoListaProductosAListaPrincipal(producto, Teclado.leerEntero("Cantidad"));
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException | CantidadNoValidaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Añadir a la cuenta(Lista principal) una cantidad indicada de carne de la
	 * lista de productos (uso)
	 */
	private static void anadirCarneListaProductosAListaPrincipal() {
		try {
			System.out.println(getTpv().mostrarCarnesListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			getTpv().volcarProductoListaProductosAListaPrincipal(producto, Teclado.leerEntero("Cantidad"));
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException | CantidadNoValidaException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Añadir a la cuenta(Lista principal) una cantidad indicada de bebida de la
	 * lista de productos (uso)
	 */
	private static void anadirBebidaListaProductosAListaPrincipal() {
		try {
			System.out.println(getTpv().mostrarBebidasListaProducos());
			Producto producto = new Producto(Teclado.leerEntero("Identificador"));
			getTpv().volcarProductoListaProductosAListaPrincipal(producto, Teclado.leerEntero("Cantidad"));
		} catch (ProductoNoEncontradoExcepcion | ListaVaciaException | CantidadNoValidaException e) {
			System.err.println(e.getMessage());
		}
	}

	// /**
	// * Pide la posicion al usuario para el borrado de los bebidas justamente
	// * entre 1 y el numero de bebidas que contiene la lista (configuracion)
	// *
	// * @return
	// * @throws ListaVaciaException
	// */
	// private static int pedirPosicionBebidas() throws ListaVaciaException {
	// if (getTpv().numeroDeBebidasListaDeProductos() == 0)
	// throw new ListaVaciaException("ERROR:Lista vacia");
	// int posicion;
	// do {
	// posicion = Teclado.leerEntero("Posicion 1 y " +
	// getTpv().numeroDeBebidasListaDeProductos() + ":");
	// } while (posicion < 1 || posicion >
	// getTpv().numeroDeBebidasListaDeProductos());
	// return posicion;
	// }

	// /**
	// * Pide la posicion al usuario para el borrado de carnes justamente entre
	// 1
	// * y el numero de carnes que contiene la lista (configuracion)
	// *
	// * @return
	// * @throws ListaVaciaException
	// */
	// private static int pedirPosicionCarnes() throws ListaVaciaException {
	// if (getTpv().numeroDeCarnesListaDeProductos() == 0)
	// throw new ListaVaciaException("ERROR:Lista vacia");
	// int posicion;
	// do {
	// posicion = Teclado.leerEntero("Posicion 1 y " +
	// getTpv().numeroDeCarnesListaDeProductos() + ":");
	// } while (posicion < 1 || posicion >
	// getTpv().numeroDeCarnesListaDeProductos());
	// return posicion;
	// }
	//
	// /**
	// * Pide la posicion al usuario para el borrado de pescados justamente
	// entre
	// * 1 y el numero de pescados que contiene la lista (configuracion)
	// *
	// * @return
	// * @throws ListaVaciaException
	// */
	// private static int pedirPosicionPescados() throws ListaVaciaException {
	// if (getTpv().numeroDePescadosListaDeProductos() == 0)
	// throw new ListaVaciaException("ERROR:Lista vacia");
	// int posicion;
	// do {
	// posicion = Teclado.leerEntero("Posicion 1 y " +
	// getTpv().numeroDePescadosListaDeProductos() + ":");
	// } while (posicion < 1 || posicion >
	// getTpv().numeroDePescadosListaDeProductos());
	// return posicion;
	// }
	//
	/**
	 * 
	 * @return
	 * @throws ListaVaciaException
	 */
	private static int pedirPosicionListaPrincipal() throws ListaVaciaException {
		if (getTpv().getListaPrincipal().size() == 0)
			throw new ListaVaciaException("ERROR:Cuenta vacia");
		int posicion;
		do {
			posicion = Teclado.leerEntero("Posicion 1 y " + getTpv().getListaPrincipal().size() + ":");
		} while (posicion < 1 || posicion > getTpv().getListaPrincipal().size());
		return posicion;
	}

}
