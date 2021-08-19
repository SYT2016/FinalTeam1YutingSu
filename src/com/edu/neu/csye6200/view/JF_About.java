package com.edu.neu.csye6200.view;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class JF_About extends JInternalFrame {


	/**
	 * Create the frame.
	 */
	public JF_About() {
		//getContentPane().setBackground(Color.RED);
		setIconifiable(true);
		setClosable(true);
		setTitle("About");
		setBounds(600, 200, 700, 600);
		
		JLabel lblNewLabel = new JLabel("This procedure is done by groups");
		lblNewLabel.setIcon(new ImageIcon(JF_About.class.getResource("/com/edu/neu/csye6200/images/artisan.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(98)
					.addComponent(lblNewLabel)
					.addContainerGap(126, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblNewLabel)
					.addContainerGap(149, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
