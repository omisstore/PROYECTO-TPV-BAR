package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;
import estructura.exceptions.ListaVaciaException;
import estructura.exceptions.ProductoNoEncontradoExcepcion;

import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Mostrar comanda
 * @author Javier Ponferrada López
 * @version 1.0
 *
 */
public class Comanda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	/**
	 * Constructor
	 * @throws ProductoNoEncontradoExcepcion 
	 * @throws ListaVaciaException 
	 */
	public Comanda() throws ProductoNoEncontradoExcepcion, ListaVaciaException {
		setTitle("Comanda");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 370, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "name_10024331372206");
			{
				JTextPane textPane = new JTextPane();
				textPane.setEditable(false);
				textPane.setText(Fichero.tpv.comanda());
				scrollPane.setViewportView(textPane);
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
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
