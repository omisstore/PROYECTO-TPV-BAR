package gui;



import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.enumeraciones.Corte;
import estructura.enumeraciones.Envase;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoBebida;
import estructura.enumeraciones.TipoCarne;
import estructura.enumeraciones.TipoPescado;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Añadir un producto
 * @author Javier Ponferrada López
 * @version 1.0
 */
public class AnadirProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextField textFieldNombre;
	JTextField textFieldStock;
	JTextField textFieldPrecio;
	JComboBox<Iva> comboBoxIva;
	JComboBox<Envase> comboBoxEnvase;
	JComboBox<TipoBebida> comboBoxTipoBebida;
	JComboBox<Corte> comboBoxCorte;
	JComboBox<TipoCarne> comboBoxTipoCarne;
	JComboBox<TipoPescado> comboBoxTipoPescado;
	JTextArea textAreaDescripcion;
	JPanel panelPescado;
	JPanel panelBebida;
	JPanel panelCarne;
	JButton btnCrear;
	JButton cancelButton;
	JTextField textFieldPesoPescado;
	JTextField textFieldPesoCarne;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblDatosObligatorios;
	private JLabel label_3;
	private JLabel label_4;

	/**
	 * Constructor
	 */
	public AnadirProducto() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 239);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(42, 23, 61, 16);
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(102, 18, 130, 26);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(18, 56, 85, 16);
		contentPanel.add(lblDescripcin);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(102, 56, 273, 41);
		contentPanel.add(textAreaDescripcion);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(61, 114, 61, 16);
		contentPanel.add(lblStock);
		
		textFieldStock = new JTextField();
		textFieldStock.setColumns(10);
		textFieldStock.setBounds(101, 109, 61, 26);
		contentPanel.add(textFieldStock);
		
		JLabel lblPrecio = new JLabel(" Precio neto");
		lblPrecio.setBounds(18, 147, 85, 16);
		contentPanel.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(101, 142, 61, 26);
		contentPanel.add(textFieldPrecio);
		
		comboBoxIva = new JComboBox<Iva>();
		comboBoxIva.setModel(new DefaultComboBoxModel<Iva>(Iva.values()));
		comboBoxIva.setBounds(102, 192, 93, 27);
		contentPanel.add(comboBoxIva);
		
		JLabel lblIva = new JLabel("Iva");
		lblIva.setBounds(77, 196, 26, 16);
		contentPanel.add(lblIva);
		
//		-----------------CARNE----------------
		panelCarne = new JPanel();
		panelCarne.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Carne", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCarne.setBounds(211, 109, 209, 108);
		contentPanel.add(panelCarne);
		panelCarne.setLayout(null);
		
		comboBoxTipoCarne = new JComboBox<TipoCarne>();
		comboBoxTipoCarne.setModel(new DefaultComboBoxModel<TipoCarne>(TipoCarne.values()));
		comboBoxTipoCarne.setBounds(88, 16, 115, 27);
		panelCarne.add(comboBoxTipoCarne);
		
		JLabel lblCarne = new JLabel("Tipo");
		lblCarne.setBounds(20, 20, 61, 16);
		panelCarne.add(lblCarne);
		
		JLabel lblCorte = new JLabel("Corte");
		lblCorte.setBounds(20, 46, 61, 16);
		panelCarne.add(lblCorte);
		
		comboBoxCorte = new JComboBox<Corte>();
		comboBoxCorte.setModel(new DefaultComboBoxModel<Corte>(Corte.values()));
		comboBoxCorte.setBounds(88, 42, 115, 27);
		panelCarne.add(comboBoxCorte);
		
		JLabel labelPesoCarne = new JLabel("Peso Gr");
		labelPesoCarne.setBounds(20, 74, 61, 16);
		panelCarne.add(labelPesoCarne);
		
		textFieldPesoCarne = new JTextField();
		textFieldPesoCarne.setBounds(88, 69, 72, 26);
		panelCarne.add(textFieldPesoCarne);
		textFieldPesoCarne.setColumns(10);
		
		label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(161, 74, 16, 16);
		panelCarne.add(label_3);
		
//		-----------------BEBIDA----------------
		panelBebida = new JPanel();
		panelBebida.setLayout(null);
		panelBebida.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Bebida", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBebida.setBounds(211, 109, 209, 108);

		comboBoxEnvase = new JComboBox<Envase>();
		comboBoxEnvase.setModel(new DefaultComboBoxModel<Envase>(Envase.values()));
		comboBoxEnvase.setBounds(88, 25, 115, 27);
		panelBebida.add(comboBoxEnvase);
		
		JLabel labelEnvase = new JLabel("Envase");
		labelEnvase.setBounds(20, 29, 61, 16);
		panelBebida.add(labelEnvase);
		
		JLabel labelTipoBebida = new JLabel("Tipo");
		labelTipoBebida.setBounds(20, 66, 61, 16);
		panelBebida.add(labelTipoBebida);
		
		comboBoxTipoBebida = new JComboBox<TipoBebida>();
		comboBoxTipoBebida.setModel(new DefaultComboBoxModel<TipoBebida>(TipoBebida.values()));
		comboBoxTipoBebida.setBounds(88, 62, 115, 27);
		panelBebida.add(comboBoxTipoBebida);
		contentPanel.add(panelBebida);

//		-----------------PESCADO----------------
		panelPescado = new JPanel();
		panelPescado.setBorder(new TitledBorder(null, "Pescado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPescado.setBounds(211, 109, 209, 108);
		contentPanel.add(panelPescado);
		panelPescado.setLayout(null);
		JLabel lblTipoPescado = new JLabel("Tipo");
		lblTipoPescado.setBounds(25, 27, 28, 16);
		panelPescado.add(lblTipoPescado);
		
		comboBoxTipoPescado = new JComboBox<TipoPescado>();
		comboBoxTipoPescado.setModel(new DefaultComboBoxModel<TipoPescado>(TipoPescado.values()));
		comboBoxTipoPescado.setBounds(75, 23, 117, 27);
		panelPescado.add(comboBoxTipoPescado);
		
		JLabel lblPeso = new JLabel("Peso Gr");
		lblPeso.setBounds(25, 66, 58, 16);
		panelPescado.add(lblPeso);
		
		textFieldPesoPescado = new JTextField();
		textFieldPesoPescado.setBounds(85, 62, 78, 26);
		panelPescado.add(textFieldPesoPescado);
		textFieldPesoPescado.setColumns(10);
		
		label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(165, 66, 13, 16);
		panelPescado.add(label_4);
		
		label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(234, 23, 13, 16);
		contentPanel.add(label);
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(162, 114, 13, 16);
		contentPanel.add(label_1);
		
		label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(162, 147, 13, 16);
		contentPanel.add(label_2);
		
		lblDatosObligatorios = new JLabel("* Datos obligatorios");
		lblDatosObligatorios.setForeground(Color.RED);
		lblDatosObligatorios.setBounds(308, 6, 136, 16);
		contentPanel.add(lblDatosObligatorios);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 239, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnCrear = new JButton("Crear");
				btnCrear.setActionCommand("Crear");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
