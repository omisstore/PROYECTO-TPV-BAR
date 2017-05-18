package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import estructura.Carne;
import estructura.Fichero;
import estructura.enumeraciones.Corte;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoCarne;
import estructura.exceptions.ProductoNoEncontradoExcepcion;
/**
 * Modificar datos de una carne
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class ModificarCarne extends AnadirProducto {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor
	 * @throws ProductoNoEncontradoExcepcion 
	 * @param indicador, de la carne a modificar
	 */
	public ModificarCarne(int identificador) throws ProductoNoEncontradoExcepcion {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Modificar carne");
		
		Carne carneAModificar = (Carne)Fichero.tpv.getProductoListaProductos(identificador);
		
		textFieldNombre.setText(carneAModificar.getNombre());
		textAreaDescripcion.setText(carneAModificar.getDescripcion());
		textFieldPrecio.setText(String.valueOf(carneAModificar.getPrecio()));
		textFieldStock.setText(String.valueOf(carneAModificar.getStock()));
		comboBoxIva.setSelectedItem(carneAModificar.getIva());
		comboBoxCorte.setSelectedItem(carneAModificar.getCorte());
		comboBoxTipoCarne.setSelectedItem(carneAModificar.getTipo());
		textFieldPesoCarne.setText(String.valueOf(carneAModificar.getPeso()));
		
		panelBebida.setVisible(false);
		panelPescado.setVisible(false);
		btnCrear.setText("Modificar");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					carneAModificar.setNombre(textFieldNombre.getText());
					carneAModificar.setDescripcion(textAreaDescripcion.getText());
					carneAModificar.setStock(Integer.valueOf(textFieldStock.getText()));
					carneAModificar.setPrecio(Double.valueOf(textFieldPrecio.getText()));
					carneAModificar.setIva((Iva)comboBoxIva.getSelectedItem());
					carneAModificar.setCorte(((Corte)comboBoxCorte.getSelectedItem()));
					carneAModificar.setTipo(((TipoCarne)comboBoxTipoCarne.getSelectedItem()));
					carneAModificar.setPeso(Float.valueOf(textFieldPesoCarne.getText()));
					
					setVisible(false);
					JOptionPane.showMessageDialog(contentPanel, "Se ha modificado la carne", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});
	}

}
