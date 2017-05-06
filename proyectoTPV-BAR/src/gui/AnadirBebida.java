package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;
import estructura.enumeraciones.Envase;
import estructura.enumeraciones.Iva;
import estructura.enumeraciones.TipoBebida;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Añadir bebida
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class AnadirBebida extends AnadirProducto {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	/**
	 * Constructor
	 */
	public AnadirBebida() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		
		panelCarne.setVisible(false);
		panelPescado.setVisible(false);

				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							anadirBebidaAListaProductos();
							JOptionPane.showMessageDialog(contentPanel, "La bebida ha sido añadida.", "",
									JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							Fichero.tpv.setModificado(true);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

					private void anadirBebidaAListaProductos()
							throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException {
						try {
							Fichero.tpv.addBebidaAListaProductos(textFieldNombre.getText(), textAreaDescripcion.getText(), Integer.valueOf(textFieldStock.getText()),Double.valueOf(textFieldPrecio.getText()), (Iva)comboBoxIva.getSelectedItem(), (Envase)comboBoxEnvase.getSelectedItem(), (TipoBebida)comboBoxTipoBebida.getSelectedItem());
						} catch (NumberFormatException e) {
							throw new PrecioNoValidoException("ERROR:Precio o Stock no valido");
						}
						
					}
				});

	}

}
