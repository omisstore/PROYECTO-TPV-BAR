package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Ver la ayuda del programa
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class VerAyuda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static final VerAyuda AYUDA =  new VerAyuda();
	private JScrollPane scrollPaneCrearCarne;
	
	public static VerAyuda getInstance(){
		return AYUDA;
	}
	
	/**
	 * Constructor
	 */
	private  VerAyuda() {
		setBounds(100, 100, 500, 380);
		setTitle("Ayuda");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, "name_32040785786741");
			{
				JPanel panelInfo = new JPanel();
				tabbedPane.addTab("Info", null, panelInfo, null);
				panelInfo.setLayout(new CardLayout(0, 0));
				{
					JTextPane textPaneInfo = new JTextPane();
					textPaneInfo.setText("--TPV-BAR--\n\n-Proyecto de fin de año(Curso 2016-2017)\n-Asignatura: Programación\n-Creado por Javier Ponferrada López\n-Correo: jponferrada26@gmail.com\n");
					textPaneInfo.setEditable(false);
					panelInfo.add(textPaneInfo, "name_30313628484546");
				}
			}
			{
				JPanel panelCrear = new JPanel();
				tabbedPane.addTab("Crear", null, panelCrear, null);
				panelCrear.setLayout(new CardLayout(0, 0));
				{
					JTabbedPane tabbedPaneCrear = new JTabbedPane(JTabbedPane.TOP);
					panelCrear.add(tabbedPaneCrear, "name_30795567902955");
					{
						JPanel panelCrearBebida = new JPanel();
						tabbedPaneCrear.addTab("Bebida", null, panelCrearBebida, null);
						panelCrearBebida.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneCrearBebida = new JScrollPane();
							panelCrearBebida.add(scrollPaneCrearBebida, "name_34471746418245");
						{
							JTextPane textPaneCrearBebida = new JTextPane();
							textPaneCrearBebida.setText("Para crear una nueva bebida solo tendrémos que realizar los siguientes pasos:\n\n1).Posicionate sobre el panel de \"Configuración\"\n2).Se debe observar 3 botones, ubicados en la parte inferior derecha de la ventana.\n3).Cliqueas sobre el botón de \"AÑADIR BEBIDA\"\n4).Se nos debe abrir una ventana. La cual nos solicita que introduzcamos los siguientes datos:\n\t-Nombre: del producto a crear. *Obligatorio\n\t-Descripción: del producto a crear. (opcional)\n\t-Stock: inicial del producto que será decrementado en cada uno de los cobros\n\t-Precio neto: del producto. Será incrementado dependiendo del IVA y el Tipo\n\t-Iva: a aplicar\n\t-Envase: del producto\n\t-Tipo: si es normal o Alcohólico");
							textPaneCrearBebida.setEditable(false);
							scrollPaneCrearBebida.setViewportView(textPaneCrearBebida);
						}
						
						}
					}
					{
						JPanel panelCarne = new JPanel();
						tabbedPaneCrear.addTab("Carne", null, panelCarne, null);
						panelCarne.setLayout(new CardLayout(0, 0));
						{
							scrollPaneCrearCarne = new JScrollPane();
							panelCarne.add(scrollPaneCrearCarne, "name_34786135261014");
						}
						{
							JTextPane textPaneCrearCarne = new JTextPane();
							textPaneCrearCarne.setText("Para crear una nueva carne solo tendrémos que realizar los siguientes pasos:\n\n1).Posicionate sobre el panel de \"Configuración\"\n2).Se debe observar 3 botones, ubicados en la parte inferior derecha de la ventana.\n3).Cliqueas sobre el botón de \"AÑADIR CARNE\"\n4).Se nos debe abrir una ventana. La cual nos solicita que introduzcamos los siguientes datos:\n\t-Nombre: del producto a crear. *Obligatorio\n\t-Descripción: del producto a crear. (opcional)\n\t-Stock: inicial del producto que será decrementado en cada uno de los cobros\n\t-Precio neto: del producto. Será incrementado dependiendo del IVA y el Tipo\n\t-Iva: a aplicar\n\t-Tipo: de carne\n\t-Corte: de la carne\n\t-Peso: de la carne a servir");
							textPaneCrearCarne.setEditable(false);
							scrollPaneCrearCarne.setViewportView(textPaneCrearCarne);
						}
					}
					{
						JPanel panelPescado = new JPanel();
						tabbedPaneCrear.addTab("Pescado", null, panelPescado, null);
						panelPescado.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneCrearPescado = new JScrollPane();
							panelPescado.add(scrollPaneCrearPescado, "name_35303793353960");
						
						{
							JTextPane textPaneCrearPescado = new JTextPane();
							textPaneCrearPescado.setText("Para crear un nuevo pescado, solo tendrémos que realizar los siguientes pasos:\n\n1).Posicionate sobre el panel de \"Configuración\"\n2).Se debe observar 3 botones, ubicados en la parte inferior derecha de la ventana.\n3).Cliqueas sobre el botón de \"AÑADIR PESCADO\"\n4).Se nos debe abrir una ventana. La cual nos solicita que introduzcamos los siguientes datos:\n\t-Nombre: del producto a crear. *Obligatorio\n\t-Descripción: del producto a crear. (opcional)\n\t-Stock: inicial del producto que será decrementado en cada uno de los cobros\n\t-Precio neto: del producto. Será incrementado dependiendo del IVA y el Tipo\n\t-Iva: a aplicar\n\t-Tipo: de pescado\n\t-Peso: a servir");
							textPaneCrearPescado.setEditable(false);
							scrollPaneCrearPescado.setViewportView(textPaneCrearPescado);
						}
						}
					}
				}
			}
			{
				JPanel Borrar = new JPanel();
				tabbedPane.addTab("Borrar", null, Borrar, null);
				Borrar.setLayout(new CardLayout(0, 0));
				{
					JTabbedPane tabbedPaneBorrar = new JTabbedPane(JTabbedPane.TOP);
					Borrar.add(tabbedPaneBorrar, "name_31096219437957");
					{
						JPanel panelDeCuenta = new JPanel();
						tabbedPaneBorrar.addTab("De cuenta", null, panelDeCuenta, null);
						panelDeCuenta.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneBorrarProductoDeCuenta = new JScrollPane();
							panelDeCuenta.add(scrollPaneBorrarProductoDeCuenta, "name_36150715815729");
						
						{
							JTextPane textPaneBorrarDeCuenta = new JTextPane();
							textPaneBorrarDeCuenta.setText("Para borrar un producto de la cuenta, se deben seguir los siguientes pasos:\n\n1).Posiciónate sobre el panel \"Principal\"\n2).Nos ubicamos sobre la tabla de \"Cuenta\".\n3).Para poder borrar un producto de la cuenta, debe estar con mínimo un producto.\n4).Seleccionamos el producto a borrar.\n5).Cliqueamos sobre el botón de \"Borrar\".\n6).Al ser borrado el producto, debe disminuir el total de la cuenta.\n");
							textPaneBorrarDeCuenta.setEditable(false);
							scrollPaneBorrarProductoDeCuenta.setViewportView(textPaneBorrarDeCuenta);
						}
						}
					}
					{
						JPanel panelDeListaProductos = new JPanel();
						tabbedPaneBorrar.addTab("De lista de productos", null, panelDeListaProductos, null);
						panelDeListaProductos.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneBorrarProductoListaProductos = new JScrollPane();
							panelDeListaProductos.add(scrollPaneBorrarProductoListaProductos, "name_36780457836562");
						
						{
							JTextPane textPaneBorrarListaProductos = new JTextPane();
							textPaneBorrarListaProductos.setText("Para borrar un producto de la cuenta, se deben seguir los siguientes pasos:\n\n1).Posiciónate sobre el panel \"Configuración\"\n2).Nos ubicamos sobre la tabla de \"PRODUCTOS\".\n3).Para poder borrar un producto de la tabla de productos, debe estar con mínimo un producto.\n--A la derecha de la tabla existen 3 botones(Bebidas,Carnes,Pescados). Dependiendo de cual cliequeemos, aparecerá en la tabla los productos correspondientes.\n4).Seleccionamos de la tabla el producto a borrar.\n5).Cliqueamos sobre el botón de \"Borrar\".\n6).Al ser borrado el producto, debe desaparecer de la tabla.(Si no desapareciera, cliquea sobre el tipo de producto que es, para refrescar).");
							textPaneBorrarListaProductos.setEditable(false);
							scrollPaneBorrarProductoListaProductos.setViewportView(textPaneBorrarListaProductos);
						}
						}
					}
				}
			}
			{
				JPanel panelModificar = new JPanel();
				tabbedPane.addTab("Modificar", null, panelModificar, null);
				panelModificar.setLayout(new CardLayout(0, 0));
				{
					JTabbedPane tabbedPaneModificar = new JTabbedPane(JTabbedPane.TOP);
					panelModificar.add(tabbedPaneModificar, "name_31414821549211");
					{
						JPanel panelModificarProducto = new JPanel();
						tabbedPaneModificar.addTab("Producto", null, panelModificarProducto, null);
						panelModificarProducto.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneModificarProducto = new JScrollPane();
							panelModificarProducto.add(scrollPaneModificarProducto, "name_37385026678378");
						
						{
							JTextPane textPaneModificarProducto = new JTextPane();
							textPaneModificarProducto.setText("Para modificar un producto, se deben seguir los siguientes pasos:\n\n1).Posiciónate sobre el panel de \"Configuración\".\n--Para poder modificar el producto, debe existir previamente.\n2).Dependiendo del producto, se debe cliquear sobre los botones superior derecha. Que indican el tipo de producto que deseamos que se muestre en la tabla de productos.\n3).Selecciona el producto qu desea modificar.\n4).Cliea sobre el botón \"MODIFICAR\". El cual detectará el tipo del producto que deseas modificar. De forma que te mostrará una ventana con los datos del producto, para poder modificarlos.\n5)Una vez modificado los datos del producto, solo tendremos que cliquear sobre botón \"Modificar\" de la ventana emergente. De ésta forma aplicaremos los cambios.");
							textPaneModificarProducto.setEditable(false);
							scrollPaneModificarProducto.setViewportView(textPaneModificarProducto);
						}
						}
					}
					{
						JPanel panelDatosEmpresa = new JPanel();
						tabbedPaneModificar.addTab("Empresa", null, panelDatosEmpresa, null);
						panelDatosEmpresa.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneMosificarDatosEmpresa = new JScrollPane();
							panelDatosEmpresa.add(scrollPaneMosificarDatosEmpresa, "name_37734178992166");
						
						{
							JTextPane textPaneModificarDatosEmpresa = new JTextPane();
							textPaneModificarDatosEmpresa.setText("Para modificar los datos de la empresa dueña del tpv, tendrás que seguir los siguientes pasos:\n\n1).Posiciónate sobre el panel de \"Configuración\"\n2).Observa que existe un botón llamado \"DATOS EMPRESA\".\n3).Cliquea sobre el.\n4).Nos aparecerá una ventana emergente, con todos los datos de la empresa que pueden ser modificados.\n5).Una vez hallas modificado los datos de la empresa,podrás cliquear sobre el botón \"Modificar\". Que aplicará todos los cambios.");
							textPaneModificarDatosEmpresa.setEditable(false);
							scrollPaneMosificarDatosEmpresa.setViewportView(textPaneModificarDatosEmpresa);
						}
						}
					}
				}
			}
			{
				JPanel panelCuenta = new JPanel();
				tabbedPane.addTab("Cuenta", null, panelCuenta, null);
				panelCuenta.setLayout(new CardLayout(0, 0));
				{
					JTabbedPane tabbedPaneCuenta = new JTabbedPane(JTabbedPane.TOP);
					panelCuenta.add(tabbedPaneCuenta, "name_32141450555039");
					{
						JPanel panelCobrar = new JPanel();
						tabbedPaneCuenta.addTab("Cobrar", null, panelCobrar, null);
						panelCobrar.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneCuentaCobrar = new JScrollPane();
							panelCobrar.add(scrollPaneCuentaCobrar, "name_38322646355361");
						
						{
							JTextPane textPaneCobrar = new JTextPane();
							textPaneCobrar.setText("Para cobrar la cuenta, se deben seguir los siguientes pasos:\n\n1).Posiciónate sobre el panel \"Principal\".\n--Para poder cobrar la cuenta, debe haber previamente articulos en la cuenta.\n--Se podrá observar en la parte inferior izquierda de la tabla, el total de la cuenta.\n2).Cliquea sobre el botón \"COBRAR\", que se ubicará en la parte superior izquierda de la tabla \"Cuenta\".\n3).Se mostrará en una ventana emergente, el ticket que se imprimirá.\n4).Si estamos de acuerdo con el ticket, cliqueamos sobre el botón \"Cobrar\" de la ventana emergente.\n5).Nos aparecerá otra ventana mas pequeña, que nos solicitará el dinero del cliente, para indicarnos la vuelta a dicho comensal.\n\n--Al cobrar la cuenta, se ha descontado el stock de los productos. Y se habrá guardado en fichero de texto de \"Tickets\", el registro del pago.(Fecha + Precio cuenta)");
							textPaneCobrar.setEditable(false);
							scrollPaneCuentaCobrar.setViewportView(textPaneCobrar);
						}
						}
					}
					{
						JPanel panelComanda = new JPanel();
						tabbedPaneCuenta.addTab("Comanda", null, panelComanda, null);
						panelComanda.setLayout(new CardLayout(0, 0));
						{
							JScrollPane scrollPaneCuentaComanda = new JScrollPane();
							panelComanda.add(scrollPaneCuentaComanda, "name_38523051681542");
						
						{
							JTextPane textPaneComanda = new JTextPane();
							textPaneComanda.setText("Para imprimir la comanda, se deben seguir los siguientes pasos:\n\n1).Posiciónate sobre el panel \"Principal\".\n--Para poder imprimir la comanda, debe haber previamente articulos en la cuenta.\n2).Cliquea sobre el botón \"COMANDA\", que se ubicará en la parte superior izquierda de la tabla \"Cuenta\".\n3).Se mostrará en una ventana emergente, dicha comanda.");
							textPaneComanda.setEditable(false);
							scrollPaneCuentaComanda.setViewportView(textPaneComanda);
						}
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("Aceptar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
