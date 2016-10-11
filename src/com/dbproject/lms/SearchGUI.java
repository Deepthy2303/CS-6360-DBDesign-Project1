package com.dbproject.lms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class SearchGUI extends JFrame {
	
	
	public static String fullname;
	public static String isbn;
	public static String booktitle;
	
	static public Object row[][] = new Object[5000][5000];
	public static int indicate;
	Object column[] = { "ISBN", "Title", "Author(s)", "Branch_ID", "Branch_Name", "No_Of_Copies_Owned", "No_Of_Copies_Available"};

	public static SearchGUI frame;
	public JTextField textISBN;
	public JTextField textBookTitle;
	public JTextField textAuthor;
	
	Object selected[];
	public JLabel lbISBN;
	public JLabel lbBookTitle;
	public JLabel lbAuthor;
	public JLabel lberror;
	
	
	
	Checkbox chckISBN = new Checkbox();
	Checkbox chckBookTitle = new Checkbox();
	Checkbox chckAuthor = new Checkbox();
	public JButton btnSearch;
	public JButton btnNewUser;
	public JButton btnCheckout;
	public JButton btnCheckin;
	public JButton btnFine;
	
	public static int BranchID;
	public static String BookID;
	public static int NoOfCopies;
	
	public static CheckoutGUI c;
	public static CheckinGUI cin;
	public static FineGUI f;
	public static NewGUI n;
	public SearchGUI(String header)

	{
		super(header);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter the values below:");
		lblNewLabel.setBounds(10, 11, 197, 14);
		getContentPane().add(lblNewLabel);
		
		lbISBN = new JLabel("ISBN");
		lbISBN.setBounds(48, 48, 46, 14);
		getContentPane().add(lbISBN);
		
		lbBookTitle = new JLabel("Book Title");
		lbBookTitle.setBounds(48, 82, 77, 14);
		getContentPane().add(lbBookTitle);
		
		lbAuthor = new JLabel("Author");
		lbAuthor.setBounds(48, 123, 77, 14);
		getContentPane().add(lbAuthor);
		
		textISBN = new JTextField();
		textBookTitle = new JTextField();
		textAuthor = new JTextField();
		
		textISBN.setBounds(180, 45, 197, 20);
		getContentPane().add(textISBN);
		textISBN.setColumns(10);
		
		
		textBookTitle.setBounds(180, 79, 197, 20);
		getContentPane().add(textBookTitle);
		textBookTitle.setColumns(10);
		
		
		textAuthor.setBounds(180, 120, 197, 20);
		getContentPane().add(textAuthor);
		textAuthor.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(10, 166, 127, 23);
		getContentPane().add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 261, 940, 269);
		getContentPane().add(scrollPane);
		
		final JTable table = new JTable(row,column);
		scrollPane.setViewportView(table);
		
		
		chckISBN.setBounds(10, 42, 20, 23);
		getContentPane().add(chckISBN);
		
		
		chckBookTitle.setBounds(10, 76, 20, 23);
		getContentPane().add(chckBookTitle);
		
		
		chckAuthor.setBounds(10, 117, 20, 23);
		getContentPane().add(chckAuthor);
		
		lberror = new JLabel("");
		lberror.setBounds(180, 618, 401, 78);
		getContentPane().add(lberror);
		
		btnNewUser = new JButton("New User");
		btnNewUser.setBounds(442, 44, 127, 23);
		getContentPane().add(btnNewUser);
		
		btnCheckout = new JButton("Check Out!");
		btnCheckout.setBounds(217, 166, 127, 23);
		getContentPane().add(btnCheckout);
		
		btnCheckin = new JButton("Check In!");
		btnCheckin.setBounds(442, 166, 127, 23);
		getContentPane().add(btnCheckin);
		
		btnFine = new JButton("Fine Details");
		btnFine.setBounds(442, 98, 127, 23);
		getContentPane().add(btnFine);
		
		btnNewUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				//System.out.println("Test");
				
				setVisible(false);
				n.setVisible(true);
			}
		});
		btnSearch.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) 
			{
				
				if(chckAuthor.getState() && chckISBN.getState())
				{
					lberror.setText("");
					fullname=textAuthor.getText();
					isbn = textISBN.getText();
					Search.nameIsbn();

				}
				else if(chckAuthor.getState() && chckBookTitle.getState())
				{
					lberror.setText("");
					fullname=textAuthor.getText();
					booktitle = textBookTitle.getText();
					Search.nameTitle();		


				}
				else if(chckISBN.getState() && chckBookTitle.getState())
				{
					lberror.setText("");
					//System.out.println("hi");
					isbn = textISBN.getText();
					booktitle = textBookTitle.getText();
					Search.isbnTitle();


				}
				else if(chckAuthor.getState())
				{
					lberror.setText("");
					fullname=textAuthor.getText();
					Search.fullname();
				}
				else if(chckISBN.getState())
				{
					//System.out.println("Hi der");
					lberror.setText("");
					isbn = textISBN.getText();
					Search.isbn();
				
				}
				else if(chckBookTitle.getState())
				{
					lberror.setText("");
					booktitle = textBookTitle.getText();
					Search.bookTitle();
		
					}
				else 
				{
					lberror.setText("Please enter either BOOK ID,TITLE OR AUTHOR NAME OR BOTH !");
				}
				
				repaint();
			}
		

		

	});
		
	btnCheckout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			
			System.out.println("Test");
			AbstractButton button=(AbstractButton)e.getSource();
			int row=0;
			//int col=0;
			
			if(e.getActionCommand().equals(button.getActionCommand()))
			{
				row=table.getSelectedRow();
				//col=table.getSelectedColumn();
				
			}
			
	
			
			CheckoutGUI.branchId = Integer.parseInt((String)table.getValueAt(row,3));
			CheckoutGUI.bookId=(String)table.getValueAt(row,0);
			NoOfCopies=(Integer)table.getValueAt(row,6);
			
			Search.check();
		
			
			if(NoOfCopies<=Search.checkedOut)
			{
				lberror.setText("All available books have been Checked Out.");
			}
			
			else
			{
				indicate=1;
				lberror.setText(" ");
				dispose();
				c =new CheckoutGUI("Checkout");
				c.setVisible(true);
			}
		}
	});
	
	btnCheckin.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Test");
			AbstractButton button=(AbstractButton)e.getSource();
			int row=0;
			//int col=0;
			if(e.getActionCommand().equals(button.getActionCommand()))
			{
				row=table.getSelectedRow();
				//col=table.getSelectedColumn();
			}
			
			CheckinGUI.branch_search=(String)table.getValueAt(row, 3);
			CheckinGUI.book_search=(String)table.getValueAt(row,0);
			
		
			
			indicate=1;
			lberror.setText(" ");
			dispose();
			cin=new CheckinGUI("Checkin");
			cin.setVisible(true);

		}
	});
	
	btnFine.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e)
		{
			dispose();
			f=new FineGUI("Fine");
			f.setVisible(true);
		}
	});
	}
	
	public static void main(String[] args)
	{
		frame=new SearchGUI("Search");
		frame.setVisible(true);
		
		n=new NewGUI("New User");
	}
}

