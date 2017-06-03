package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructura.Fichero;
import estructura.exceptions.FicheroNoExisteException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class MostrarTickets extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public MostrarTickets() {
		setResizable(false);
		setTitle("Tickets");
		setModal(true);
		setBounds(100, 100, 247, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "name_19607117465985");
			{
				JTextPane textPaneTicket = new JTextPane();
				textPaneTicket.setEditable(false);
				scrollPane.setViewportView(textPaneTicket);
			
				try {
					textPaneTicket.setText(Fichero.tpv.leerTickets());
				} catch (FicheroNoExisteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
		}
	}

}
