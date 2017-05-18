package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Bebida;
import estructura.Fichero;
import estructura.enumeraciones.Envase;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoBebida;

import estructura.exceptions.ProductoNoEncontradoExcepcion;

/**
 * Modificar datos de la bebida
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class ModificarBebida extends AnadirProducto {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor
	 * @param identificador, que indetificará de forma unívoca a dicha bebida
	 * @throws ProductoNoEncontradoExcepcion 
	 */
	public ModificarBebida(int identificador) throws ProductoNoEncontradoExcepcion {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setTitle("Modificar bebida");
		Bebida bebidaAModificar = (Bebida)Fichero.tpv.getProductoListaProductos(identificador);
		
		textFieldNombre.setText(bebidaAModificar.getNombre());
		textAreaDescripcion.setText(bebidaAModificar.getDescripcion());
		textFieldPrecio.setText(String.valueOf(bebidaAModificar.getPrecio()));
		textFieldStock.setText(String.valueOf(bebidaAModificar.getStock()));
		comboBoxIva.setSelectedItem(bebidaAModificar.getIva());
		comboBoxEnvase.setSelectedItem(bebidaAModificar.getEnvase());
		comboBoxTipoBebida.setSelectedItem(bebidaAModificar.getTipo());
		panelCarne.setVisible(false);
		panelPescado.setVisible(false);
		btnCrear.setText("Modificar");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bebidaAModificar.setNombre(textFieldNombre.getText());
					bebidaAModificar.setDescripcion(textAreaDescripcion.getText());
					bebidaAModificar.setStock(Integer.valueOf(textFieldStock.getText()));
					bebidaAModificar.setPrecio(Double.valueOf(textFieldPrecio.getText()));
					bebidaAModificar.setEnvase((Envase)comboBoxEnvase.getSelectedItem());
					bebidaAModificar.setIva((Iva)comboBoxIva.getSelectedItem());
					bebidaAModificar.setTipo((TipoBebida)comboBoxTipoBebida.getSelectedItem());
					setVisible(false);
					JOptionPane.showMessageDialog(contentPanel, "Se ha modificado la bebida", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}

		});
	}

}
