package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 *	Modificar datos de la empresa
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class ModificarDatosEmpresa extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldTelefono;
	private JTextField textFieldProvincia;
	private JTextField textFieldLocalida;
	private JTextField textFieldCalle;

	/**
	 * Constructor
	 */
	public ModificarDatosEmpresa() {
		setModal(true);
		setResizable(false);
		setTitle("Modificar Datos Empresa");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre empresa");
		lblNombre.setBounds(29, 24, 120, 16);
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(161, 19, 200, 26);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Nº Teléfono");
		lblTelefono.setBounds(29, 58, 91, 16);
		contentPanel.add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(161, 53, 130, 26);
		contentPanel.add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(29, 91, 91, 16);
		contentPanel.add(lblProvincia);
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setBounds(161, 86, 200, 26);
		contentPanel.add(textFieldProvincia);
		textFieldProvincia.setColumns(10);
		
		JLabel lblLocalida = new JLabel("Localida");
		lblLocalida.setBounds(29, 127, 91, 16);
		contentPanel.add(lblLocalida);
		
		textFieldLocalida = new JTextField();
		textFieldLocalida.setColumns(10);
		textFieldLocalida.setBounds(161, 122, 200, 26);
		contentPanel.add(textFieldLocalida);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(29, 160, 91, 16);
		contentPanel.add(lblCalle);
		
		textFieldCalle = new JTextField();
		textFieldCalle.setColumns(10);
		textFieldCalle.setBounds(161, 155, 200, 26);
		contentPanel.add(textFieldCalle);
		
		//Rellenar todos los campos
		textFieldNombre.setText(Fichero.tpv.getNombreEmpresa());
		textFieldTelefono.setText(Fichero.tpv.getTelefonoEmpresa());
		textFieldProvincia.setText(Fichero.tpv.getProvinciaEmpresa());
		textFieldLocalida.setText(Fichero.tpv.getLocalidadEmpresa());
		textFieldCalle.setText(Fichero.tpv.getCalleEmpresa());
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		label.setBounds(360, 24, 18, 16);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(292, 57, 18, 16);
		contentPanel.add(label_1);
		
		JLabel lblDatosObligatorios = new JLabel("* Datos obligatorios");
		lblDatosObligatorios.setForeground(Color.RED);
		lblDatosObligatorios.setBounds(21, 217, 184, 16);
		contentPanel.add(lblDatosObligatorios);
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okModificar = new JButton("Modificar");
				okModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Fichero.tpv.asignarNombreEmpresa(textFieldNombre.getText());
							Fichero.tpv.asignarNumeroTelefonoEmpresa(textFieldTelefono.getText());
							Fichero.tpv.asignarProvinciaEmpresa(textFieldProvincia.getText());
							Fichero.tpv.asignarLocalidadEmpresa(textFieldLocalida.getText());
							Fichero.tpv.asignarCalleEmpresa(textFieldCalle.getText());
							JOptionPane.showMessageDialog(null, "Se ha modificado los datos de la empresa, correctamente", "",
									JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							Fichero.tpv.setModificado(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						} 
						
					}
				});
				buttonPane.add(okModificar);
				getRootPane().setDefaultButton(okModificar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
