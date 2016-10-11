package com.dbproject.lms;

import java.sql.*;

public class Fine
{

	static Connection conn = null;
	
	static public void check()
	{
	
		
	try {
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

		Statement stmt = conn.createStatement();

		stmt.execute("use library;");

		
		ResultSet rs = stmt.executeQuery("SELECT SUM(fine_amt) FROM FINES,BOOK_LOANS WHERE FINES.LOAN_ID=BOOK_LOANS.LOAN_ID AND CARD_NO LIKE '%"+FineGUI.cardNoName+"%' AND paid=FALSE AND Date_in IS NOT NULL Group By Card_no;");
		

		while (rs.next()) {
			FineGUI.fineAmt = rs.getDouble("SUM(fine_amt)");
			
	} 
		
		rs.close();
		conn.close();
		//System.out.println("Success");
	}
	catch(SQLException ex) {
		System.out.println("Error in connection: " + ex.getMessage());
	}
	}
	
	static public void calculate()
	{
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

		
			Statement stmt = conn.createStatement();

		
			stmt.execute("use library;");

			
			ResultSet rs = stmt.executeQuery("SELECT SUM(fine_amt) FROM FINES,BOOK_LOANS WHERE FINES.LOAN_ID=BOOK_LOANS.LOAN_ID AND CARD_NO LIKE '%"+FineGUI.cardNoName+"%' AND paid=FALSE AND Date_in IS NULL Group By Card_no;");
			

			while (rs.next()) {
				FineGUI.estFineAmt = rs.getDouble("SUM(fine_amt)");
				
		} 
			
			rs.close();
			conn.close();
			//System.out.println("Success");
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		
	}
	
	static public void payFine()
	{
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

			Statement stmt = conn.createStatement();

	
			stmt.execute("use library;");

			
			stmt.executeUpdate("UPDATE FINES,BOOK_LOANS SET fine_amt=0.00,paid=1 WHERE Card_no LIKE '%"+FineGUI.cardNoName+"%' AND BOOK_LOANS.loan_id=FINES.loan_id AND Date_in IS NOT NULL;");
			
	
			conn.close();
			//System.out.println("Success");
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		
	}
	
	static public void refresh()
	{
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

			
			Statement stmt = conn.createStatement();

			
			stmt.execute("use library;");

			
			stmt.executeUpdate("UPDATE FINES,BOOK_LOANS SET FINES.fine_amt=Datediff(SYSDATE(),BOOK_LOANS.Due_date)*0.25 WHERE BOOK_LOANS.loan_id=FINES.loan_id AND FINES.paid=0 AND Date_in IS NULL;");
			stmt.executeUpdate("UPDATE FINES,BOOK_LOANS SET FINES.fine_amt=Datediff(BOOK_LOANS.Date_in,BOOK_LOANS.Due_date)*0.25 WHERE BOOK_LOANS.loan_id=FINES.loan_id AND FINES.paid=0 AND Date_in IS NOT NULL;");
			stmt.executeUpdate("UPDATE FINES SET fine_amt=0.00 WHERE fine_amt<0.00");
	
			conn.close();
			//System.out.println("Success");
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		
	}
}

