package com.dbproject.lms;

import java.sql.*;
public class Checkout {

	public static int flag=0;
static Connection conn = null;
	
	
	public static void check(){
		
		

		try {
	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

			Statement stmt = conn.createStatement();
    
			stmt.execute("use library;");

			
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CHECKER FROM BOOK_LOANS WHERE Card_No LIKE '%" +CheckoutGUI.cardNo+"%';");
			
			
			while (rs.next()) {
				
				CheckoutGUI.noOfCopies=rs.getInt("CHECKER");
				System.out.println("No of Copies"+ CheckoutGUI.noOfCopies);
		} 
			ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) AS COPIES FROM BOOK_COPIES WHERE Book_id LIKE '%"+CheckoutGUI.bookId+"%' AND Branch_id="+CheckoutGUI.branchId+";");
			while(rs1.next())
			{
				CheckoutGUI.copiesAvailable=rs1.getInt("COPIES");
				System.out.println("No of Copies"+ CheckoutGUI.copiesAvailable);
			}
			
			
			conn.close();
			System.out.println("Success!!");
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		
		if(CheckoutGUI.noOfCopies<3 && CheckoutGUI.copiesAvailable>0)
		{
			try
			{
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");
                Statement stmt = conn.createStatement();
	    
	
				stmt.execute("use library;");
				
				stmt.executeUpdate("INSERT INTO BOOK_LOANS (ISBN,BRANCH_ID,CARD_NO,DATE_OUT, DUE_DATE) VALUES ('"+CheckoutGUI.bookId+"',"+CheckoutGUI.branchId +",'"+CheckoutGUI.cardNo+"',SYSDATE(), ADDDATE(SYSDATE(),INTERVAL 14 DAY));");
				stmt.executeUpdate("INSERT INTO FINES (FINE_AMT,PAID) VALUES (0.00,FALSE);");
				ResultSet rs2=stmt.executeQuery("SELECT Copies_Available FROM BOOK_Copies WHERE Book_Id LIKE '%"+CheckoutGUI.bookId+"%' AND Branch_Id="+CheckoutGUI.branchId+";");
				rs2.next();
				int copies=rs2.getInt("Copies_Available")-1;
				stmt.executeUpdate("UPDATE BOOK_COPIES SET Copies_Available ="+copies+" WHERE Book_Id LIKE '%"+CheckoutGUI.bookId+"%' AND Branch_Id="+CheckoutGUI.branchId+";");
				conn.close();
				flag=1;
				System.out.println("Flag =" + flag);
				
				//System.out.println("Success!");
			}
			
			catch(SQLException ex)
			{
				System.out.println("Error:"+ex.getMessage());
			}
		}
	}

	
	
}
