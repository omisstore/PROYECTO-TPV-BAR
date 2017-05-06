package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;

import estructura.enumeraciones.Iva;

import estructura.enumeraciones.TipoPescado;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PesoNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * Añadir pescado
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class AnadirPescado extends AnadirProducto {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Constructor 
	 */
	public AnadirPescado() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(160, 74, 12, 16);
		panelCarne.add(lblNewLabel);
		
		panelCarne.setVisible(false);
		panelBebida.setVisible(false);
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					anadirPescadoAListaProductos();
					JOptionPane.showMessageDialog(contentPanel, "El pescado ha sido añadido.", "",
							JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					Fichero.tpv.setModificado(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			private void anadirPescadoAListaProductos() throws NombreNoValidoException, StockNoValidoException,
					PrecioNoValidoException, PesoNoValidoException {
				try {
					Fichero.tpv.addPescadoAListaProductos(textFieldNombre.getText(), textAreaDescripcion.getText(),
							Integer.valueOf(textFieldStock.getText()), Double.valueOf(textFieldPrecio.getText()),
							(Iva) comboBoxIva.getSelectedItem(), (TipoPescado)comboBoxTipoPescado.getSelectedItem() , Float.valueOf(textFieldPesoPescado.getText()));
				} catch (NumberFormatException e) {
					throw new PrecioNoValidoException("ERROR:Precio,Stock o Peso no valido");
				}

			}
		});
	}

}
