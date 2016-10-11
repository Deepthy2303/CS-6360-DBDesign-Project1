package com.dbproject.lms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "unused", "serial" })
public class FineGUI extends JFrame {

	public JFrame frame;
	public static JTextField textCardNo;
	
	public static JLabel lblCardNo;

	public static String cardNoName;
	public static double fineAmt;
	public static double estFineAmt;
	
	
	public static JButton btnCheckFine;
	public static JButton btnPayFine;
	public static JButton btnRefresh;
	public static JButton btnSearch;
	private JLabel lblerror1;
	private JLabel lblerror2;
	
	public FineGUI(String header)
	{
		super(header);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblCardNo = new JLabel("Card No");
		lblCardNo.setBounds(26, 59, 46, 14);
		getContentPane().add(lblCardNo);
		
		textCardNo = new JTextField();
		textCardNo.setBounds(114, 56, 165, 20);
		getContentPane().add(textCardNo);
		textCardNo.setColumns(10);
		
		btnCheckFine = new JButton("Check Fine");
		btnCheckFine.setBounds(47, 119, 124, 23);
		getContentPane().add(btnCheckFine);
		
		btnPayFine = new JButton("Pay Fine");
		btnPayFine.setBounds(47, 153, 124, 23);
		getContentPane().add(btnPayFine);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(47, 187, 124, 23);
		getContentPane().add(btnRefresh);
		
		btnSearch = new JButton("Main");
		btnSearch.setBounds(318, 153, 124, 23);
		getContentPane().add(btnSearch);
		
		lblerror1 = new JLabel("");
		lblerror1.setBounds(55, 286, 203, 32);
		getContentPane().add(lblerror1);
		
		lblerror2 = new JLabel("");
		lblerror2.setBounds(47, 329, 187, 34);
		getContentPane().add(lblerror2);
		
		JLabel lblNewLabel = new JLabel("Please click 'Refresh' before Checking or Paying Fine.");
		lblNewLabel.setBounds(26, 11, 295, 14);
		getContentPane().add(lblNewLabel);
		
		btnCheckFine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				lblerror1.setText(" ");
				cardNoName=textCardNo.getText();
				Fine.check();
				
				lblerror2.setText("Please PAY fine:"+fineAmt);
				
				Fine.calculate();
				lblerror2.setText("Estimated fine:" + estFineAmt);
				
				//System.out.println("Check Fine");
				
			}
		});
		
		btnPayFine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
					Fine.payFine();
					lblerror1.setText("Paid");
					lblerror2.setText(" ");
					//System.out.println("Pay fine");
			}
		});
		
		btnRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				fineAmt=0.0;
				estFineAmt=0.0;
				Fine.refresh();
				//System.out.println("Refresh");
				lblerror1.setText(" ");
				lblerror2.setText(" ");
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
