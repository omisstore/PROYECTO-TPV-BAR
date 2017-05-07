package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;
import estructura.exceptions.ListaVaciaException;
import estructura.exceptions.ProductoNoEncontradoExcepcion;

import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Cobrar cuenta
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class CobrarCuenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Constructor
	 * @throws ProductoNoEncontradoExcepcion 
	 * @throws ListaVaciaException 
	 */
	public CobrarCuenta() throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		setModal(true);
		setResizable(false);
		setTitle("Ticket");
		setBounds(100, 100, 402, 431);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "name_3030099838011");
			{
				JTextPane textPane = new JTextPane();
				textPane.setEditable(false);
				textPane.setText(Fichero.tpv.imprimirTicket());
				scrollPane.setViewportView(textPane);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButtonAceptar = new JButton("Cobrar");
				okButtonAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							double vuelta = Fichero.tpv.cobrarGui(Double.valueOf(JOptionPane.showInputDialog(null, "Dinero del cliente?")));
							JOptionPane.showMessageDialog(null,"Cobro finalizado.\n Vuelta: "+vuelta+"€", "Cobro", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButtonAceptar.setActionCommand("OK");
				buttonPane.add(okButtonAceptar);
				getRootPane().setDefaultButton(okButtonAceptar);
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
