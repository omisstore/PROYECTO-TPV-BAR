package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import estructura.Fichero;
import estructura.Filtro;
import estructura.Tpv;
import estructura.exceptions.ListaVaciaException;
import estructura.exceptions.ProductoNoEncontradoExcepcion;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.CardLayout;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * GUI Principal del TPV-BAR
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAnadirBebidaACuenta;
	private JTable tableAnadirCarneACuenta;
	private JTable tableAnadirPescadoACuenta;
	private JTable tableCuenta;
	private JLabel labelTotalResultado;
	private JTextField textFieldCantidad;
	private JTable tableListaBebidasConf;
	private JTable tableListaCarnesConf;
	private JTable tableListaPescadosConf;
	private JScrollPane scrollPaneTablaBebidaConf;
	private JScrollPane scrollPaneTablaCarneConf;
	private JScrollPane scrollPaneTablaPescadoConf;
	private JScrollPane scrollTablaAnadirBebidaACuenta;
	private JScrollPane scrollTablaAnadirCarneACuenta;
	private JScrollPane scrollTablaAnadirPescadoACuenta;
	private static JFileChooser jFileChooser = new JFileChooser();
	private static Filtro filtro = new Filtro(".obj", "Objecto");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/LogoTpv.png"));//logo del TPV
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 616);
		setTitle("Sin titulo");
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");//Crear un nuevo Tpv
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.tpv.isModificado()) {//Comprobar si el tpv se ha modificado
					Object[] opciones = new Object[] { "Si", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"No se ha guardado los cambios,¿Deseas guardar los cambios?", "No has guardado",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
					switch (respuesta) {
					case 0:
						guardarComoFichero();
						Fichero.tpv = new Tpv();
						setTitle("Sin titulo");
						Fichero.tpv.setModificado(false);
						break;
					case 1:

						setTitle("Sin titulo");
						Fichero.tpv = new Tpv();
						Fichero.tpv.setModificado(false);
						break;
					}
				} else {
					setTitle("Sin titulo");
					Fichero.tpv = new Tpv();
					Fichero.tpv.setModificado(false);
				}
			}
		});
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");//Abrir un tpv existente en un fichero
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.tpv.isModificado()) {
					Object[] opciones = new Object[] { "Si", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"No se ha guardado los cambios,¿Deseas guardar los cambios?", "No has guardado",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
					switch (respuesta) {
					case 0://Se desea guardar los cambios
						guardarComoFichero();
						break;
					case 1://Se desea abrir el fichero y por lo tanto guardar los cambios
						try {
							abrirArchivo();
						} catch (HeadlessException | ClassNotFoundException | IOException e1) {
							JOptionPane.showMessageDialog(null, "No se ha podido abrir el Tpv.", "Aceptar",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
				} else {
					try {
						abrirArchivo();
					} catch (HeadlessException | ClassNotFoundException | IOException e1) {
						JOptionPane.showMessageDialog(null, "No se ha podido abrir el tpv.", "Aceptar",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");//Guardar el tpv actual.
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getTitle() == "Sin titulo") {//Comprueba que si no hay abierto ningun tpv, se guarde el actual
					guardarComoFichero();
					Fichero.tpv.setModificado(false);
				} else {
					try {
						Fichero.guardar(Fichero.tpv, jFileChooser.getSelectedFile());
						Fichero.tpv.setModificado(false);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "No se ha podido guardar el tpv.", "Aceptar",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");//Se guarda el tpv en un fichero fichero indicado
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComoFichero();
				Fichero.tpv.setModificado(false);
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");//Salir del programa. Comprobando que no se halla modificado el TPV
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Fichero.tpv.isModificado()) {//Comprueba que se ha mododificado el tpv
					Object[] opciones = new Object[] { "Si", "No", "Cancelar" };
					int respuesta = JOptionPane.showOptionDialog(null,
							"No se ha guardado los cambios,¿Deseas guardar los cambios?", "No has guardado",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
					switch (respuesta) {
					case 0://Se desea que se guarde los cambios y se salga del programa
						guardarComoFichero();
						System.exit(0);
						break;
					case 1://Se desea salir del programa sin guardar los cambios
						System.exit(0);
						break;
					}
				} else {
					System.exit(0);
				}
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAyuda = new JMenuItem("Ver ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerAyuda ayuda = VerAyuda.getInstance();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmAyuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "name_15823434340373");

		// ---------------PRINCIPAL------------------
		JPanel panelPrincipal = new JPanel();
		tabbedPane.addTab("Principal", null, panelPrincipal, null);
		panelPrincipal.setLayout(null);

		JButton btnBebida = new JButton("BEBIDAS");//Refresca la tabla de añadir a cuenta de bebidas y solo muestra la de bebidas
		btnBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableAnadirBebidaACuenta.setVisible(true);
				tableAnadirCarneACuenta.setVisible(false);
				tableAnadirPescadoACuenta.setVisible(false);
				refrescarTablaAnadirBebidasCuenta();
				scrollTablaAnadirBebidaACuenta.setVisible(true);
				scrollTablaAnadirCarneACuenta.setVisible(false);
				scrollTablaAnadirPescadoACuenta.setVisible(false);
			}
		});
		btnBebida.setBounds(6, 364, 117, 39);
		panelPrincipal.add(btnBebida);		
		
		JButton btnCarnes = new JButton("CARNES");//Refresca la tabla de añadir a cuenta de carnes y solo muestra la de carnes
		btnCarnes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableAnadirCarneACuenta.setVisible(true);
				tableAnadirPescadoACuenta.setVisible(false);
				tableAnadirBebidaACuenta.setVisible(false);
				refrescarTablaAnadirCarnesCuena();
				scrollTablaAnadirBebidaACuenta.setVisible(false);
				scrollTablaAnadirCarneACuenta.setVisible(true);
				scrollTablaAnadirPescadoACuenta.setVisible(false);
			}
		});
		btnCarnes.setBounds(6, 404, 117, 39);
		panelPrincipal.add(btnCarnes);

		JButton btnPescados = new JButton("PESCADOS");//Refresca la tabla de añadir a cuenta de pescados y solo muestra la de pescados
		btnPescados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableAnadirPescadoACuenta.setVisible(true);
				tableAnadirCarneACuenta.setVisible(false);
				tableAnadirBebidaACuenta.setVisible(false);
				refrescarTablaAnadirPescadosCuenta();
				scrollTablaAnadirBebidaACuenta.setVisible(false);
				scrollTablaAnadirCarneACuenta.setVisible(false);
				scrollTablaAnadirPescadoACuenta.setVisible(true);
			}
		});
		btnPescados.setBounds(6, 444, 117, 39);
		panelPrincipal.add(btnPescados);

		JButton btnAadir = new JButton("AÑADIR");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// Añade a la cuenta los
														// productos de la tabla
														// de añadir a la cuenta
														// dependiendo de si ha
														// seleccionado una
														// Bebida,Pescado o
														// Carne
				try {
					if (tableAnadirCarneACuenta.getSelectedRow() != -1) {// si
																			// es
																			// distinta
																			// de
																			// -1
																			// significa
																			// que
																			// dicha
																			// tabla
																			// esta
																			// seleccionada
						int identificador = Integer.valueOf(tableAnadirCarneACuenta
								.getValueAt(tableAnadirCarneACuenta.getSelectedRow(), 0).toString());
						Fichero.tpv.volcarProductoListaProductosAListaPrincipalIndentificador(identificador,
								Integer.valueOf(textFieldCantidad.getText()));
						JOptionPane
								.showMessageDialog(
										null, "Se ha añadido a la cuenta "
												+ Integer.valueOf(textFieldCantidad.getText()) + " productos",
										"", JOptionPane.INFORMATION_MESSAGE);
						limpiarCantidad();
						tableAnadirCarneACuenta.clearSelection();
						Fichero.tpv.setModificado(true);
					} else if (tableAnadirBebidaACuenta.getSelectedRow() != -1) {
						int identificador = Integer.valueOf(tableAnadirBebidaACuenta
								.getValueAt(tableAnadirBebidaACuenta.getSelectedRow(), 0).toString());
						Fichero.tpv.volcarProductoListaProductosAListaPrincipalIndentificador(identificador,
								Integer.valueOf(textFieldCantidad.getText()));
						JOptionPane
								.showMessageDialog(
										null, "Se ha añadido a la cuenta "
												+ Integer.valueOf(textFieldCantidad.getText()) + " productos",
										"", JOptionPane.INFORMATION_MESSAGE);
						limpiarCantidad();
						tableAnadirBebidaACuenta.clearSelection();
						Fichero.tpv.setModificado(true);
					} else if (tableAnadirPescadoACuenta.getSelectedRow() != -1) {
						int identificador = Integer.valueOf(tableAnadirPescadoACuenta
								.getValueAt(tableAnadirPescadoACuenta.getSelectedRow(), 0).toString());
						Fichero.tpv.volcarProductoListaProductosAListaPrincipalIndentificador(identificador,
								Integer.valueOf(textFieldCantidad.getText()));
						JOptionPane
								.showMessageDialog(
										null, "Se ha añadido a la cuenta "
												+ Integer.valueOf(textFieldCantidad.getText()) + " productos",
										"", JOptionPane.INFORMATION_MESSAGE);
						limpiarCantidad();
						tableAnadirPescadoACuenta.clearSelection();
						Fichero.tpv.setModificado(true);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona algun produdcto, para poder añadirlo a la cuenta.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
					refrescarTotalCuenta();
					tableAnadirCarneACuenta.clearSelection();
					tableAnadirPescadoACuenta.clearSelection();
					tableAnadirBebidaACuenta.clearSelection();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				refrescarTablaCuenta();

			}
		});
		btnAadir.setBounds(559, 350, 88, 111);
		panelPrincipal.add(btnAadir);

		JButton btnCobrar = new JButton("COBRAR");//Cobrar la cuenta
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CobrarCuenta cobrarCuenta;
				try {
					cobrarCuenta = new CobrarCuenta();
					cobrarCuenta.setVisible(true);
					refrescarTablaCuenta();
					refrescarTotalCuenta();
					Fichero.tpv.setModificado(true);
				} catch (ProductoNoEncontradoExcepcion | ListaVaciaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			

			}
		});
		btnCobrar.setBounds(6, 43, 117, 47);
		panelPrincipal.add(btnCobrar);

		JButton btnComanda = new JButton("COMANDA");//MOstrar la comanda de la cuenta
		btnComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comanda comanda;
				try {
					comanda = new Comanda();
					comanda.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnComanda.setBounds(6, 91, 117, 47);
		panelPrincipal.add(btnComanda);

		JButton btnBorrarProdCuenta = new JButton("BORRAR");//Borrar de la cuenta un producto seleccionado
		btnBorrarProdCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Fichero.tpv.removeProductoListaPrincipal(tableCuenta.getSelectedRow());
					Fichero.tpv.setModificado(true);
				} catch (ListaVaciaException | ProductoNoEncontradoExcepcion e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					refrescarTotalCuenta();
					refrescarTablaCuenta();
				}

			}
		});
		btnBorrarProdCuenta.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnBorrarProdCuenta.setBounds(559, 43, 74, 47);
		panelPrincipal.add(btnBorrarProdCuenta);

		// Tabla de cuenta
		tableCuenta = new JTable();
		tableCuenta.setBounds(135, 23, 417, 305);
		tableCuenta.setRowSelectionAllowed(true);
		tableCuenta.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableCuenta.setVisible(true);

		// Tabla para añadir bebidas a la cuenta
		tableAnadirBebidaACuenta = new JTable();
		tableAnadirBebidaACuenta.setBounds(135, 350, 417, 153);
		tableAnadirBebidaACuenta.setRowSelectionAllowed(true);
		tableAnadirBebidaACuenta.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableAnadirBebidaACuenta.setVisible(true);
		

		// Tabla para añadir carnes a la cuenta
		tableAnadirCarneACuenta = new JTable();
		tableAnadirCarneACuenta.setBounds(135, 350, 417, 153);
		tableAnadirCarneACuenta.setRowSelectionAllowed(true);
		tableAnadirCarneACuenta.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableAnadirCarneACuenta.setVisible(false);

		
		// Tabla para añadir pescados a la cuenta
		tableAnadirPescadoACuenta = new JTable();
		tableAnadirPescadoACuenta.setBounds(135, 350, 417, 153);
		tableAnadirPescadoACuenta.setRowSelectionAllowed(true);
		tableAnadirPescadoACuenta.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableAnadirPescadoACuenta.setVisible(false);	
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(559, 477, 88, 26);
		panelPrincipal.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);

		JLabel lblNewLabel = new JLabel("Cant.");
		lblNewLabel.setBounds(562, 463, 61, 16);
		panelPrincipal.add(lblNewLabel);

		JLabel lblTotal = new JLabel("TOTAL");//Total de la cuenta
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTotal.setBounds(77, 275, 61, 26);
		panelPrincipal.add(lblTotal);

		labelTotalResultado = new JLabel("");
		labelTotalResultado.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTotalResultado.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		labelTotalResultado.setBounds(41, 302, 88, 26);
		panelPrincipal.add(labelTotalResultado);
		
		//ScrollPane tabla de cuenta
		JScrollPane scrollPaneTablaCuenta = new JScrollPane();
		scrollPaneTablaCuenta.setBounds(135, 23, 417, 305);
		panelPrincipal.add(scrollPaneTablaCuenta);
		
		JLabel lblTitleCuenta = new JLabel("CUENTA");
		lblTitleCuenta.setBounds(314, 6, 61, 16);
		panelPrincipal.add(lblTitleCuenta);
		
		JLabel lblTitleProductos = new JLabel("PRODUCTOS");
		lblTitleProductos.setBounds(303, 332, 88, 16);
		panelPrincipal.add(lblTitleProductos);
		scrollPaneTablaCuenta.setViewportView(tableCuenta);
		
		//ScrollPane Tabla añadir Bebida a la cuenta
		scrollTablaAnadirBebidaACuenta = new JScrollPane();
		scrollTablaAnadirBebidaACuenta.setBounds(135, 350, 417, 153);
		panelPrincipal.add(scrollTablaAnadirBebidaACuenta);
		scrollTablaAnadirBebidaACuenta.setViewportView(tableAnadirBebidaACuenta);
		
		
		//ScrollPane Tabla añadir Carne a la cuenta
		scrollTablaAnadirCarneACuenta = new JScrollPane();
		scrollTablaAnadirCarneACuenta.setBounds(135, 350, 417, 153);
		panelPrincipal.add(scrollTablaAnadirCarneACuenta);
		scrollTablaAnadirCarneACuenta.setViewportView(tableAnadirCarneACuenta);
		
		//ScrollPane Tabla añadir Pescado a la cuenta
		scrollTablaAnadirPescadoACuenta = new JScrollPane();
		scrollTablaAnadirPescadoACuenta.setBounds(135, 350, 417, 153);
		panelPrincipal.add(scrollTablaAnadirPescadoACuenta);
		scrollTablaAnadirPescadoACuenta.setViewportView(tableAnadirPescadoACuenta);
		
		// ----------CONFIGURACIÓN--------------
		JPanel panelConfig = new JPanel();
		tabbedPane.addTab("Configuración", null, panelConfig, null);
		panelConfig.setLayout(null);

		JButton btnDatosEmpresa = new JButton("DATOS EMPRESA");
		btnDatosEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDatosEmpresa modificarDatosEmpresa = new ModificarDatosEmpresa();
				modificarDatosEmpresa.setVisible(true);
			}
		});
		btnDatosEmpresa.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnDatosEmpresa.setBounds(6, 437, 117, 41);
		panelConfig.add(btnDatosEmpresa);

		// Tabla configracion de bebidas.
		tableListaBebidasConf = new JTable();
		tableListaBebidasConf.setRowSelectionAllowed(true);
		tableListaBebidasConf.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableListaBebidasConf.setBounds(129, 33, 399, 445);
//		panelConfig.add(tableListaBebidasConf);
		
		
		// Tabla configracion de Carnes.
		tableListaCarnesConf = new JTable();
		tableListaCarnesConf.setRowSelectionAllowed(true);
		tableListaCarnesConf.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableListaCarnesConf.setBounds(129, 33, 399, 445);
		//panelConfig.add(tableListaCarnesConf);
		

		
		// Tabla configracion de Pescados.
		tableListaPescadosConf = new JTable();
		tableListaPescadosConf.setRowSelectionAllowed(true);
		tableListaPescadosConf.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableListaPescadosConf.setBounds(129, 33, 399, 445);
//		panelConfig.add(tableListaPescadosConf);
		
		
		//Scroll panel de la tabla de configuracion de bebidas
		scrollPaneTablaBebidaConf = new JScrollPane();
		scrollPaneTablaBebidaConf.setBounds(129, 33, 399, 445);
		panelConfig.add(scrollPaneTablaBebidaConf);
		scrollPaneTablaBebidaConf.setViewportView(tableListaBebidasConf);
		
		
		//Scroll panel de la tabla de configuracion de bebidas
		scrollPaneTablaCarneConf = new JScrollPane();
		scrollPaneTablaCarneConf.setBounds(129, 33, 399, 445);
		panelConfig.add(scrollPaneTablaCarneConf);
		scrollPaneTablaCarneConf.setViewportView(tableListaCarnesConf);
		
		//Scroll panel de la tabla de configuracion de bebidas
		scrollPaneTablaPescadoConf = new JScrollPane();
		scrollPaneTablaPescadoConf.setBounds(129, 33, 399, 445);
		panelConfig.add(scrollPaneTablaPescadoConf);
		scrollPaneTablaPescadoConf.setViewportView(tableListaPescadosConf);
		
		
		JButton btnBebidasConf = new JButton("BEBIDAS");//Refrescar la tabla de bebidas y muestra solo la de bebidas
		btnBebidasConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListaBebidasConf.setVisible(true);
				tableListaCarnesConf.setVisible(false);
				tableListaPescadosConf.setVisible(false);
				refrescarTablaBebidasConf();
				scrollPaneTablaBebidaConf.setVisible(true);
				scrollPaneTablaCarneConf.setVisible(false);
				scrollPaneTablaPescadoConf.setVisible(false);
			}

		});
		btnBebidasConf.setBounds(6, 53, 117, 41);
		panelConfig.add(btnBebidasConf);

		JButton btnCarnesConf = new JButton("CARNES");//Refrescar la tabla de carnes y muestra solo la de carnes
		btnCarnesConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListaBebidasConf.setVisible(false);
				tableListaCarnesConf.setVisible(true);
				tableListaPescadosConf.setVisible(false);
				refrescarTablaCarnesConf();
				scrollPaneTablaBebidaConf.setVisible(false);
				scrollPaneTablaCarneConf.setVisible(true);
				scrollPaneTablaPescadoConf.setVisible(false);
			}
		});
		btnCarnesConf.setBounds(6, 106, 117, 41);
		panelConfig.add(btnCarnesConf);

		JButton btnPescadosConf = new JButton("PESCADOS");//Refrescar la tabla de pescados y muestra solo la de pescados
		btnPescadosConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListaBebidasConf.setVisible(false);
				tableListaCarnesConf.setVisible(false);
				tableListaPescadosConf.setVisible(true);
				refrescarTablaPescadosConf();
				scrollPaneTablaBebidaConf.setVisible(false);
				scrollPaneTablaCarneConf.setVisible(false);
				scrollPaneTablaPescadoConf.setVisible(true);
				
			}
		});
		btnPescadosConf.setBounds(6, 159, 117, 41);
		panelConfig.add(btnPescadosConf);

		JButton btnModificar = new JButton("MODIFICAR");//Modificar el producto seleccionado de la tabla de productos
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tableListaCarnesConf.getSelectedRow() != -1) {// si es
																		// distinta
																		// de -1
																		// significa
																		// que
																		// dicha
																		// tabla
																		// esta
																		// seleccionada
						int identificador = Integer.valueOf(
								tableListaCarnesConf.getValueAt(tableListaCarnesConf.getSelectedRow(), 0).toString());
						ModificarCarne modificarCarne = new ModificarCarne(identificador);
						modificarCarne.setVisible(true);
						tableListaCarnesConf.clearSelection();
						refrescarTablaCarnesConf();
						Fichero.tpv.setModificado(true);
					} else if (tableListaBebidasConf.getSelectedRow() != -1) {
						int identificador = Integer.valueOf(
								tableListaBebidasConf.getValueAt(tableListaBebidasConf.getSelectedRow(), 0).toString());
						ModificarBebida modificarBebida = new ModificarBebida(identificador);
						modificarBebida.setVisible(true);
						tableListaBebidasConf.clearSelection();
						refrescarTablaBebidasConf();
						Fichero.tpv.setModificado(true);
					} else if (tableListaPescadosConf.getSelectedRow() != -1) {
						int identificador = Integer.valueOf(tableListaPescadosConf
								.getValueAt(tableListaPescadosConf.getSelectedRow(), 0).toString());
						ModificarPescado modificarPescado = new ModificarPescado(identificador);
						modificarPescado.setVisible(true);
						tableListaPescadosConf.clearSelection();
						refrescarTablaPescadosConf();
						Fichero.tpv.setModificado(true);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona algun produdcto, para poder modificarlo",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnModificar.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnModificar.setBounds(530, 59, 117, 35);
		panelConfig.add(btnModificar);

		JButton btnBorrarConf = new JButton("BORRAR");
		btnBorrarConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// Borrar de la lista de
														// productos por
														// identificador
				try {
					if (tableListaCarnesConf.getSelectedRow() != -1) {// si es
																		// distinta
																		// de -1
																		// significa
																		// que
																		// dicha
																		// tabla
																		// esta
																		// seleccionada
						int identificador = Integer.valueOf(
								tableListaCarnesConf.getValueAt(tableListaCarnesConf.getSelectedRow(), 0).toString());
						Fichero.tpv.removeProductoListaProductos(identificador);
						JOptionPane.showMessageDialog(null, "La carne se ha borrado.", "",
								JOptionPane.INFORMATION_MESSAGE);
						tableListaCarnesConf.clearSelection();
						refrescarTablaCarnesConf();
						Fichero.tpv.setModificado(true);
					} else if (tableListaBebidasConf.getSelectedRow() != -1) {
						int identificador = Integer.valueOf(
								tableListaBebidasConf.getValueAt(tableListaBebidasConf.getSelectedRow(), 0).toString());
						Fichero.tpv.removeProductoListaProductos(identificador);
						JOptionPane.showMessageDialog(null, "El bebida se ha borrado.", "",
								JOptionPane.INFORMATION_MESSAGE);
						tableListaBebidasConf.clearSelection();
						refrescarTablaBebidasConf();
						Fichero.tpv.setModificado(true);
					} else if (tableListaPescadosConf.getSelectedRow() != -1) {
						int identificador = Integer.valueOf(tableListaPescadosConf
								.getValueAt(tableListaPescadosConf.getSelectedRow(), 0).toString());
						Fichero.tpv.removeProductoListaProductos(identificador);
						JOptionPane.showMessageDialog(null, "El pescado se ha borrado.", "",
								JOptionPane.INFORMATION_MESSAGE);
						tableListaPescadosConf.clearSelection();
						refrescarTablaPescadosConf();
						Fichero.tpv.setModificado(true);
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona algun produdcto, para poder borrarlo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnBorrarConf.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnBorrarConf.setBounds(530, 98, 117, 35);
		panelConfig.add(btnBorrarConf);

		JButton btnAadirBebida = new JButton("AÑADIR BEBIDA");//Boton que añade una bebida a la lista de productos
		btnAadirBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnadirBebida anadirBebida = new AnadirBebida();
				anadirBebida.setVisible(true);
				refrescarTablaBebidasConf();
			}
		});
		btnAadirBebida.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnAadirBebida.setBounds(530, 319, 117, 41);
		panelConfig.add(btnAadirBebida);

		JButton btnAadirCarne = new JButton("AÑADIR CARNE");//Boton que añade una carne a la lista de productos
		btnAadirCarne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnadirCarne anadirCarne = new AnadirCarne();
				anadirCarne.setVisible(true);
				refrescarTablaCarnesConf();
			}
		});
		btnAadirCarne.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnAadirCarne.setBounds(530, 362, 117, 41);
		panelConfig.add(btnAadirCarne);

		JButton btnAadirPescado = new JButton("AÑADIR PESCADO");//Boton que añade un pescado a la lista de productos
		btnAadirPescado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnadirPescado anadirPescado = new AnadirPescado();
				anadirPescado.setVisible(true);
				refrescarTablaPescadosConf();
			}
		});
		btnAadirPescado.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnAadirPescado.setBounds(530, 406, 117, 41);
		panelConfig.add(btnAadirPescado);
		
		JLabel lblProductos = new JLabel("PRODUCTOS");
		lblProductos.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblProductos.setBounds(279, 17, 117, 16);
		panelConfig.add(lblProductos);
		

	}


	/**
	 * Refrescar la tabla de configuracion de bebidas
	 */
	private void refrescarTablaBebidasConf() {
		tableListaBebidasConf.setModel(new DefaultTableModel(Fichero.tpv.pasoAMatrizBebidaListaProductos(),
				new String[] { "ID", "Nombre", "Descripcion", "Stock", "Precio", "Iva", "Envase", "Tipo" }));
		
	}

	/**
	 * Refrescar la tabla de configuracion de carnes
	 */
	private void refrescarTablaCarnesConf() {
		tableListaCarnesConf.setModel(new DefaultTableModel(Fichero.tpv.pasoAMatrizCarneListaProductos(),
				new String[] { "ID", "Nombre", "Descripcion", "Stock", "Precio", "Iva", "Tipo", "Corte", "Peso" }));
	}

	/**
	 * Refrescar la tabla de configuracion de Pescados
	 */
	private void refrescarTablaPescadosConf() {
		tableListaPescadosConf.setModel(new DefaultTableModel(Fichero.tpv.pasoAMatrizPescadoListaProductos(),
				new String[] { "ID", "Nombre", "Descripcion", "Stock", "Precio", "Iva", "Tipo", "Peso" }));
	}

	/**
	 * Refrescar la tabla de Añadir a la cuenta, bebidas
	 */
	private void refrescarTablaAnadirBebidasCuenta() {
		tableAnadirBebidaACuenta.setModel(new DefaultTableModel(Fichero.tpv.pasoAMatrizBebidaListaProductos(),
				new String[] { "ID", "Nombre", "Descripcion", "Stock", "Precio", "Iva", "Envase", "Tipo" }));
	}

	/**
	 * Refrescar la tabla de Añadir a la cuenta, carnes
	 */
	private void refrescarTablaAnadirCarnesCuena() {
		tableAnadirCarneACuenta.setModel(new DefaultTableModel(Fichero.tpv.pasoAMatrizCarneListaProductos(),
				new String[] { "ID", "Nombre", "Descripcion", "Stock", "Precio", "Iva", "Tipo", "Corte", "Peso" }));
	}

	/**
	 * Refrescar la tabla de Añadir a la cuenta, pescados
	 */
	private void refrescarTablaAnadirPescadosCuenta() {
		tableAnadirPescadoACuenta.setModel(new DefaultTableModel(Fichero.tpv.pasoAMatrizPescadoListaProductos(),
				new String[] { "ID", "Nombre", "Descripcion", "Stock", "Precio", "Iva", "Tipo", "Peso" }));
	}

	/**
	 * Refrescar la tabla de Añadir a la cuenta, pescados
	 */
	private void refrescarTablaCuenta() {
		tableCuenta.setModel(
				new DefaultTableModel(Fichero.tpv.pasoAMatrizCuenta(), new String[] { "Cant.", "Nombre", "PVP" }));
	}

	/**
	 * Refrecar el total de la cuenta
	 */
	private void refrescarTotalCuenta() {
		try {

			labelTotalResultado.setText(String.valueOf(Fichero.tpv.precioTotalListaPrincipal()) + "€");

		} catch (ProductoNoEncontradoExcepcion e) {
			labelTotalResultado.setText("0€");
		}
	}
	
	/**
	 * Limpiar el campo de cantidad a añadir a la cuenta
	 */
	private void limpiarCantidad() {
		textFieldCantidad.setText("");
	}
	
	/**
	 * Abrir archivo seleccionado(FileChooser)
	 * @throws HeadlessException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void abrirArchivo() throws HeadlessException, IOException, ClassNotFoundException {
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filtro);
		if (jFileChooser.showDialog(jFileChooser, "Abrir") == JFileChooser.APPROVE_OPTION) {
			Fichero.abrir(jFileChooser.getSelectedFile());
			setTitle(jFileChooser.getSelectedFile().getName());
			Fichero.tpv.setModificado(false);

		}
	}
	
	/**
	 * Guardar el tpv en la ruta especificada por el filechooser.
	 */
	private void guardarComoFichero() {
		jFileChooser.setAcceptAllFileFilterUsed(false);
		jFileChooser.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == jFileChooser.showDialog(jFileChooser, "Guardar")) {
			jFileChooser.setAcceptAllFileFilterUsed(false);

			if (jFileChooser.getSelectedFile().exists()) {
				Object[] opciones = new Object[] { "Si", "No" };
				int respuesta = JOptionPane.showOptionDialog(null, "Ya existe.", "¿Deseas sobreescribir los cambios?",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);

				switch (respuesta) {
				case 0:
					try {
						Fichero.guardar(Fichero.tpv, jFileChooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "No se ha podido guardar.", "Aceptar",
								JOptionPane.ERROR_MESSAGE);
					}

					break;
				default:
					JOptionPane.showMessageDialog(null, "No se ha podido guardar.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
					break;
				}

			} else {
				try {
					Fichero.guardar(Fichero.tpv, jFileChooser.getSelectedFile());
					JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "No se ha podido guardar.", "Aceptar",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		Fichero.tpv.setModificado(false);
	}
	
}
