package com.dbproject.lms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CheckoutGUI extends JFrame {

	public JFrame frame;
	public JTextField textBookID;
	public JTextField textBranchID;
	public JTextField textCardNo;
	
	public JLabel lblBookID;
	public JLabel lblBranchID;
	public JLabel lblCardNo;
	
	public JButton btnCheckout;
	public JButton btnMain;

	public static String bookTitle;
	public static String branchName;
	public static String cardName;
	public static String bookId;
	public static int branchId;
	public static String cardNo;
	public static int noOfCopies;
	public static Date date = new Date();
	public static int copiesAvailable;
	
	
	public JLabel lblerror;
	
	public CheckoutGUI(String header)
	{
		super(header);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblBookID = new JLabel("Book ID");
		lblBookID.setBounds(37, 73, 131, 14);
		getContentPane().add(lblBookID);
		
		lblBranchID = new JLabel("Branch ID");
		lblBranchID.setBounds(37, 153, 131, 14);
		getContentPane().add(lblBranchID);
	
		lblCardNo = new JLabel("Card No.");
		lblCardNo.setBounds(37, 219, 131, 14);
		getContentPane().add(lblCardNo);
		
		textBookID = new JTextField();
		textBookID.setBounds(262, 70, 150, 20);
		getContentPane().add(textBookID);
		textBookID.setColumns(10);
		
		textBranchID = new JTextField();
		textBranchID.setBounds(262, 150, 150, 20);
		getContentPane().add(textBranchID);
		textBranchID.setColumns(10);
		
		textCardNo = new JTextField();
		textCardNo.setBounds(262, 216, 150, 20);
		getContentPane().add(textCardNo);
		textCardNo.setColumns(10);
		
		btnCheckout = new JButton("CheckOut");
		btnCheckout.setBounds(560, 111, 131, 23);
		getContentPane().add(btnCheckout);
		
		lblerror = new JLabel("");
		lblerror.setBounds(137, 292, 486, 33);
		getContentPane().add(lblerror);
		
		btnMain = new JButton("Main");
		btnMain.setBounds(560, 180, 131, 23);
		getContentPane().add(btnMain);
		
		if(SearchGUI.indicate==1)
		{
			textBookID.setText(bookId);
			textBranchID.setText(Integer.toString(branchId));
			System.out.println(" flag");
		}
		
		
		btnCheckout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Calendar c= Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.DATE, 14);
				String date=df.format(c.getTime());
				System.out.println(date);
				
				
				cardNo=textCardNo.getText();
				
				Checkout.check();
				if(Checkout.flag==0)
				{
				if(noOfCopies==3)
				{
					lblerror.setText("Sorry, You have reached the checkout limit");
				}
				else
					if(copiesAvailable==0)
					{
						lblerror.setText("Sorry, No more copies available");
					}
				}
				
				else
				{
					lblerror.setText("Thank You. Have a nice day");
				}
				
				repaint();
			}
		});
		
		btnMain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				SearchGUI.frame.setVisible(true);
			}
		});
	}
}
