package com.ogcg.jdbswing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.ogcg.jdbc.ConnManager;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Window {

	private JFrame frame;
	private Connection conn = ConnManager.getConnection();
	private JTextField textField;
	private JLabel lblDescription;
	
	private Operations operations = new Operations();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					window.frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							try {
								System.out.println("Closing down");
								window.conn.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} finally {
								System.exit(0);
							}
						}
						
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 334);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 0 0,grow");
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(122, 37, 242, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblId.setBounds(12, 47, 56, 16);
		panel.add(lblId);
		
		lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblDescription.setBounds(11, 100, 99, 16);
		panel.add(lblDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(122, 99, 242, 101);
		panel.add(textArea);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(39, 235, 97, 25);
		btnImprimir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Window.this.operations.printProducts(Window.this.conn);
			}
		});
		
		panel.add(btnImprimir);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.setBounds(253, 235, 97, 25);
		btnInsertar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Integer id = Integer.parseInt(textField.getText());
				String description = textArea.getText();
				Window.this.operations.addProduct(Window.this.conn, id, description);
			}
		});
		panel.add(btnInsertar);
	}
}
