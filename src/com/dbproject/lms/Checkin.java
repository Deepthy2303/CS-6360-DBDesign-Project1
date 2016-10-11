package com.dbproject.lms;

import java.sql.*;

public class Checkin {

	static Connection conn = null;
	
	public static String book_id;
	public static int branch_id;
	public static String Card_no;
	
	
	static public void update()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			System.out.println("hey");
			stmt.executeUpdate("UPDATE Book_Loans SET Date_in=SYSDATE() WHERE Isbn LIKE '%"+book_id+"%' AND Branch_id="+branch_id+" AND Card_no LIKE '%"+Card_no+"%';");
			stmt.executeUpdate("UPDATE book_copies SET copies_available = copies_available + 1 where Book_id LIKE '%"+book_id+"%' AND Branch_id="+branch_id+";");
			CheckoutGUI.noOfCopies--;		
			
			conn.close();
			System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}

	static public void borrower()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";

		int i = 0;


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BORROWER.fname LIKE '%"+CheckinGUI.borrowerName+"%' AND BORROWER.CARD_NO=BOOK_LOANS.Card_no;"; 
			ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next()) {

			
				CheckinGUI.row[i][0] = rs.getString("Loan_id");
				CheckinGUI.row[i][1] = rs.getString("Isbn");
				CheckinGUI.row[i][2] = rs.getString("Branch_id");
				CheckinGUI.row[i][3] = rs.getString("Card_no");
				CheckinGUI.row[i][4] = rs.getDate("Date_out");
				CheckinGUI.row[i][5] = rs.getDate("Due_date");
				CheckinGUI.row[i][6] = rs.getDate("Date_in");

				i++;
			}

	
			rs.close();
			conn.close();
			System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}
	
	static public void bookID()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";


		int i = 0;


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND BORROWER.CARD_NO=BOOK_LOANS.Card_no;";
			ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next()) {

				CheckinGUI.row[i][0] = rs.getString("Loan_id");
				CheckinGUI.row[i][1] = rs.getString("Isbn");
				CheckinGUI.row[i][2] = rs.getString("Branch_id");
				CheckinGUI.row[i][3] = rs.getString("Card_no");
				CheckinGUI.row[i][4] = rs.getDate("Date_out");
				CheckinGUI.row[i][5] = rs.getDate("Due_date");
				CheckinGUI.row[i][6] = rs.getDate("Date_in");

				i++;
			}

	
			rs.close();
			conn.close();
			System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}
	static public void bookBorrower()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";


		int i = 0;


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND Borrower.Fname LIKE '%"+CheckinGUI.borrowerName+"%' AND BORROWER.CARD_NO=BOOK_LOANS.Card_no;";
			ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next()) {

		

				CheckinGUI.row[i][0] = rs.getString("Loan_id");
				CheckinGUI.row[i][1] = rs.getString("Isbn");
				CheckinGUI.row[i][2] = rs.getString("Branch_id");
				CheckinGUI.row[i][3] = rs.getString("Card_no");
				CheckinGUI.row[i][4] = rs.getDate("Date_out");
				CheckinGUI.row[i][5] = rs.getDate("Due_date");
				CheckinGUI.row[i][6] = rs.getDate("Date_in");

				i++;
			} 

	
			rs.close();
			conn.close();
			//System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}

	static public void bookCard()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";


		int i = 0;


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND Book_Loans.Card_no LIKE '%"+CheckinGUI.cardNo+"%' AND BORROWER.CARD_NO=BOOK_LOANS.Card_no;";
			ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next()) {

			
				CheckinGUI.row[i][0] = rs.getString("Loan_id");
				CheckinGUI.row[i][1] = rs.getString("Isbn");
				CheckinGUI.row[i][2] = rs.getString("Branch_id");
				CheckinGUI.row[i][3] = rs.getString("Card_no");
				CheckinGUI.row[i][4] = rs.getDate("Date_out");
				CheckinGUI.row[i][5] = rs.getDate("Due_date");
				CheckinGUI.row[i][6] = rs.getDate("Date_in");

				i++;
			}

	
			rs.close();
			conn.close();
			//System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}

	static public void bookCardName()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";


		int i = 0;


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.ISBN LIKE '%"+CheckinGUI.bookID+"%' AND Book_Loans.Card_no LIKE '%"+CheckinGUI.cardNo+"%' AND Borrower.Fname LIKE '%"+CheckinGUI.borrowerName+"%' AND BORROWER.CARD_NO=BOOK_LOANS.Card_no;";
			ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next()) {

		
				CheckinGUI.row[i][0] = rs.getString("Loan_id");
				CheckinGUI.row[i][1] = rs.getString("Isbn");
				CheckinGUI.row[i][2] = rs.getString("Branch_id");
				CheckinGUI.row[i][3] = rs.getString("Card_no");
				CheckinGUI.row[i][4] = rs.getDate("Date_out");
				CheckinGUI.row[i][5] = rs.getDate("Due_date");
				CheckinGUI.row[i][6] = rs.getDate("Date_in");

				i++;
			}

	
			rs.close();
			conn.close();
			//System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}



	static public void cardNo()
	{
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "loveulord";


		int i = 0;


		
		try {
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			String query = "SELECT * FROM BOOK_LOANS,BORROWER WHERE BOOK_LOANS.CARD_NO LIKE '%"+CheckinGUI.cardNo+"%' AND BORROWER.CARD_NO=BOOK_LOANS.Card_no;";
			ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next()) {

			
				CheckinGUI.row[i][0] = rs.getString("Loan_id");
				CheckinGUI.row[i][1] = rs.getString("Isbn");
				CheckinGUI.row[i][2] = rs.getString("Branch_id");
				CheckinGUI.row[i][3] = rs.getString("Card_no");
				CheckinGUI.row[i][4] = rs.getDate("Date_out");
				CheckinGUI.row[i][5] = rs.getDate("Due_date");
				CheckinGUI.row[i][6] = rs.getDate("Date_in");

				i++;
			} 

	
			rs.close();
			conn.close();
			//System.out.println("Success!!");

		}

		catch(SQLException ex) 
		{
			System.out.println("Error in connection: " + ex.getMessage());
		}


	}
}
