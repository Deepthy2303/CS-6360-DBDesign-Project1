package com.dbproject.lms;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CheckinGUI extends JFrame {
	
	public static String bookID;
	public static String borrowerName;
	public static String cardNo;
	
	public static String branch_search;
	public static String book_search;

	public JTextField textBookID;
	public JTextField textName;
	public JTextField textCardNo;
	
	
	public JLabel lblBookID;
	public JLabel lblName;
	public JLabel lblCardNo;
	
	public Checkbox chckbxBookID;
	public Checkbox chckbxBorrower;
	public Checkbox chckbxCardNo;
	
	public static JButton btnCheckin;
	public static JButton btnSearch;
	public static JButton btnMain;
	
	static public Object row[][] = new Object[5000][5000];
	Object column[] = { "LOAN_ID","ISBN","BRANCH_ID","CARD_NO","DATE_OUT","DUE_DATE","DATE_IN" };
	Object data[];
	public JLabel lblerror;
	


	public CheckinGUI(String header)
	{
		super(header);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		chckbxBookID = new Checkbox("");
		chckbxBookID.setBounds(6, 59, 22, 23);
		getContentPane().add(chckbxBookID);
		
		chckbxBorrower = new Checkbox("");
		chckbxBorrower.setBounds(6, 85, 22, 23);
		getContentPane().add(chckbxBorrower);
		
		chckbxCardNo = new Checkbox("");
		chckbxCardNo.setBounds(6, 111, 22, 23);
		getContentPane().add(chckbxCardNo);
		
		lblBookID = new JLabel("Book ID");
		lblBookID.setBounds(34, 59, 103, 14);
		getContentPane().add(lblBookID);
		
		lblName = new JLabel("FirstName");
		lblName.setBounds(34, 89, 103, 14);
		getContentPane().add(lblName);
		
		lblCardNo = new JLabel("Card No.");
		lblCardNo.setBounds(34, 115, 103, 14);
		getContentPane().add(lblCardNo);
		
		textBookID = new JTextField();
		textBookID.setBounds(226, 59, 192, 20);
		getContentPane().add(textBookID);
		textBookID.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(226, 88, 192, 20);
		getContentPane().add(textName);
		textName.setColumns(10);
		
		textCardNo = new JTextField();
		textCardNo.setBounds(226, 114, 192, 20);
		getContentPane().add(textCardNo);
		textCardNo.setColumns(10);
		
		btnCheckin = new JButton("Check IN");
		btnCheckin.setBounds(500, 59, 89, 23);
		getContentPane().add(btnCheckin);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(500, 85, 89, 23);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 164, 712, 295);
		getContentPane().add(scrollPane);
		
		final JTable table_1 = new JTable(row,column);
		scrollPane.setViewportView(table_1);
		
		lblerror = new JLabel("");
		lblerror.setBounds(6, 329, 46, 14);
		getContentPane().add(lblerror);
		
		btnMain = new JButton("Main");
		btnMain.setBounds(500, 115, 89, 23);
		getContentPane().add(btnMain);
		
		if(SearchGUI.indicate==1)
		{
			textBookID.setText(book_search);
			//System.out.println("print");
		}
		
		
		btnCheckin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Test");
				AbstractButton button = (AbstractButton)e.getSource();
				int row=0;
				//int col=0;
				if(e.getActionCommand().equals(button.getActionCommand()))
				{
					row=table_1.getSelectedRow();
					//col=table_1.getSelectedColumn();
				}
				
				Checkin.book_id=(String)table_1.getValueAt(row,1);
				
				Checkin.branch_id=Integer.parseInt((String)table_1.getValueAt(row,2));
				Checkin.Card_no=(String) table_1.getValueAt(row,3);
				
				Checkin.update();
				
			
				
				repaint();
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				borrowerName=textName.getText();
				bookID=textBookID.getText();
				cardNo=textCardNo.getText();
				if(chckbxBorrower.getState())
				{
					for(int i=0;i<10;i++)
					{
						CheckinGUI.row[i][0] = "";
						CheckinGUI.row[i][1] = "";
						CheckinGUI.row[i][2] = "";
						CheckinGUI.row[i][3] = "";
						CheckinGUI.row[i][4] = "";
						CheckinGUI.row[i][5] = "";
						CheckinGUI.row[i][6] = "";
					}
					
					lblerror.setText(" ");
					
					//System.out.println("borrower");
					Checkin.borrower();
				}
				else if(chckbxBookID.getState())
				{
					for(int i=0;i<10;i++)
					{
						CheckinGUI.row[i][0] = "";
						CheckinGUI.row[i][1] = "";
						CheckinGUI.row[i][2] = "";
						CheckinGUI.row[i][3] = "";
						CheckinGUI.row[i][4] = "";
						CheckinGUI.row[i][5] = "";
						CheckinGUI.row[i][6] = "";
					}
					lblerror.setText(" ");
					
					//System.out.println("bookID");
					Checkin.bookID();
				
					
				}
				
				else if(chckbxCardNo.getState())
				{
					for(int i=0;i<10;i++)
					{
						CheckinGUI.row[i][0] = "";
						CheckinGUI.row[i][1] = "";
						CheckinGUI.row[i][2] = "";
						CheckinGUI.row[i][3] = "";
						CheckinGUI.row[i][4] = "";
						CheckinGUI.row[i][5] = "";
						CheckinGUI.row[i][6] = "";
					}
					
					lblerror.setText(" ");
					
					Checkin.cardNo();
					//System.out.println("Card No");
				}
				else if(chckbxBookID.getState() && chckbxBorrower.getState())
				{
					for(int i=0;i<10;i++)
					{
						CheckinGUI.row[i][0] = "";
						CheckinGUI.row[i][1] = "";
						CheckinGUI.row[i][2] = "";
						CheckinGUI.row[i][3] = "";
						CheckinGUI.row[i][4] = "";
						CheckinGUI.row[i][5] = "";
						CheckinGUI.row[i][6] = "";
					}
					lblerror.setText(" ");
					//System.out.println("bookID");
					Checkin.bookBorrower();
				
					
				}
				else if(chckbxBookID.getState() && chckbxCardNo.getState())
				{
					for(int i=0;i<10;i++)
					{
						CheckinGUI.row[i][0] = "";
						CheckinGUI.row[i][1] = "";
						CheckinGUI.row[i][2] = "";
						CheckinGUI.row[i][3] = "";
						CheckinGUI.row[i][4] = "";
						CheckinGUI.row[i][5] = "";
						CheckinGUI.row[i][6] = "";
					}
					lblerror.setText(" ");
					//System.out.println("bookID");
					Checkin.bookCard();
				
					
				}
				
				else if(chckbxBookID.getState() && chckbxCardNo.getState() && chckbxBorrower.getState())
				{
					for(int i=0;i<10;i++)
					{
						CheckinGUI.row[i][0] = "";
						CheckinGUI.row[i][1] = "";
						CheckinGUI.row[i][2] = "";
						CheckinGUI.row[i][3] = "";
						CheckinGUI.row[i][4] = "";
						CheckinGUI.row[i][5] = "";
						CheckinGUI.row[i][6] = "";
					}
					lblerror.setText(" ");
					//System.out.println("bookID");
					Checkin.bookCardName();
				
					
				}
				else
				{
					lblerror.setText("Please enter Book ID, Title and/or Author Name.");
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

