package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;
import estructura.enumeraciones.Corte;

import estructura.enumeraciones.Iva;

import estructura.enumeraciones.TipoCarne;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PesoNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;

/**
 * Añadir carne
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class AnadirCarne extends AnadirProducto {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Constructor
	 */
	public AnadirCarne() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		panelBebida.setVisible(false);
		panelPescado.setVisible(false);

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					anadirCarneAListaProductos();
					JOptionPane.showMessageDialog(contentPanel, "La carne ha sido añadida.", "",
							JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					Fichero.tpv.setModificado(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			private void anadirCarneAListaProductos() throws NombreNoValidoException, StockNoValidoException,
					PrecioNoValidoException, PesoNoValidoException {
				try {
					Fichero.tpv.addCarneAListaProductos(textFieldNombre.getText(), textAreaDescripcion.getText(),
							Integer.valueOf(textFieldStock.getText()), Double.valueOf(textFieldPrecio.getText()),
							(Iva) comboBoxIva.getSelectedItem(), (TipoCarne) comboBoxTipoCarne.getSelectedItem(),
							(Corte) comboBoxCorte.getSelectedItem(), Float.valueOf(textFieldPesoCarne.getText()));
				} catch (NumberFormatException e) {
					throw new PrecioNoValidoException("ERROR:Precio,Stock o Peso no valido");
				}

			}
		});
	}

}
