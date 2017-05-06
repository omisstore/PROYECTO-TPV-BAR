package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import estructura.Fichero;
import estructura.Pescado;

import estructura.enumeraciones.Iva;

import estructura.enumeraciones.TipoPescado;
import estructura.exceptions.ProductoNoEncontradoExcepcion;

/**
 * Modificar datos de un pescado
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class ModificarPescado extends AnadirProducto {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Constructor
	 * @throws ProductoNoEncontradoExcepcion 
	 * @param indicador, del pescado a modificar
	 */
	public ModificarPescado(int identificador) throws ProductoNoEncontradoExcepcion {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Modificar pescado");
		
		Pescado pescadoAModificar = (Pescado)Fichero.tpv.getProductoListaProductos(identificador);
		
		textFieldNombre.setText(pescadoAModificar.getNombre());
		textAreaDescripcion.setText(pescadoAModificar.getDescripcion());
		textFieldPrecio.setText(String.valueOf(pescadoAModificar.getPrecio()));
		textFieldStock.setText(String.valueOf(pescadoAModificar.getStock()));
		comboBoxIva.setSelectedItem(pescadoAModificar.getIva());
		comboBoxTipoPescado.setSelectedItem(pescadoAModificar.getTipo());
		textFieldPesoPescado.setText(String.valueOf(pescadoAModificar.getPeso()));
		
		panelBebida.setVisible(false);
		panelCarne.setVisible(false);
		btnCrear.setText("Modificar");
		
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pescadoAModificar.setNombre(textFieldNombre.getText());
					pescadoAModificar.setDescripcion(textAreaDescripcion.getText());
					pescadoAModificar.setStock(Integer.valueOf(textFieldStock.getText()));
					pescadoAModificar.setPrecio(Double.valueOf(textFieldPrecio.getText()));
					pescadoAModificar.setIva((Iva)comboBoxIva.getSelectedItem());
					pescadoAModificar.setTipo(((TipoPescado)comboBoxTipoPescado.getSelectedItem()));
					pescadoAModificar.setPeso(Float.valueOf(textFieldPesoPescado.getText()));
					
					setVisible(false);
					JOptionPane.showMessageDialog(contentPanel, "Se ha modificado el pescado", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});
	}

}
