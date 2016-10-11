package com.dbproject.lms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class NewGUI extends JFrame {
	

	public JFrame frame;
	public JTextField textFirstName;
	public JTextField textLastName;
	public JTextField textAddress;
	public JTextField textPhone;
	
	public JLabel lblFirstName;
	public JLabel lblLastName;
	public JLabel lblAddress;
	public JLabel lblPhone;
	public JLabel lblSsn;
	
	public JButton btnSignup;
	public JButton btnSearch;
	
	public JLabel lblerror;
	
	public static String ssn;
	public static String fname;
	public static String lname;
	public static String address;
	public static String phone;
	public static int remove;
	public JTextField textSsn;
	public static String state;
	public static String city;

	
	public NewGUI(String header) {
		
		super(header);
		setSize(800,800);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(22, 64, 80, 14);
		getContentPane().add(lblFirstName);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(22, 103, 80, 14);
		getContentPane().add(lblLastName);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(22, 141, 80, 14);
		getContentPane().add(lblAddress);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(22, 213, 80, 14);
		getContentPane().add(lblPhone);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(149, 61, 209, 20);
		getContentPane().add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setBounds(149, 100, 209, 20);
		getContentPane().add(textLastName);
		textLastName.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setBounds(149, 138, 209, 47);
		getContentPane().add(textAddress);
		textAddress.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setBounds(149, 210, 209, 20);
		getContentPane().add(textPhone);
		textPhone.setColumns(10);
		
		btnSignup = new JButton("Sign Up");
		btnSignup.setBounds(506, 77, 209, 23);
		getContentPane().add(btnSignup);
		
		btnSearch = new JButton("Main");
		btnSearch.setBounds(506, 152, 209, 23);
		getContentPane().add(btnSearch);
		
		lblerror = new JLabel("");
		lblerror.setBounds(205, 405, 397, 65);
		getContentPane().add(lblerror);
		
		lblSsn = new JLabel("SSN");
		lblSsn.setBounds(22, 31, 80, 14);
		getContentPane().add(lblSsn);
		
		textSsn = new JTextField();
		textSsn.setBounds(149, 28, 209, 20);
		getContentPane().add(textSsn);
		textSsn.setColumns(10);
		
		btnSignup.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				ssn=textSsn.getText();
				fname=textFirstName.getText();
				lname=textLastName.getText();
				address=textAddress.getText();
		
				phone=textPhone.getText();
				
				
				New.select();
				if(!phone.matches("[0-9]+"))
				{
					lblerror.setText("Invalid Phone Number: Only Digits Allowed");
				}
				else if(remove==1)
				{
					System.out.println("Reject");
					lblerror.setText("User already exists");
				}
				else
				{
					lblerror.setText("Creating....");
					New.create();
					lblerror.setText("Your Card Number is: " + New.cardResult);
				}
				repaint();
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				SearchGUI.frame.setVisible(true);
			}
		});
	}
}
