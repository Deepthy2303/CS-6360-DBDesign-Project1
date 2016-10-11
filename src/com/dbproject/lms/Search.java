package com.dbproject.lms;

import java.sql.*;
public class Search {

	static Connection conn = null;
	
	public static int checkedOut;
	public static void nameIsbn(){
		
		

		try {
	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");


			Statement stmt = conn.createStatement();
    
			stmt.execute("use library;");

			int i =0;
			ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.ISBN LIKE"+"'%"+SearchGUI.isbn+"%'AND Fullname LIKE"+"'%"+SearchGUI.fullname+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
	
			while (rs.next()) {
				
				
				SearchGUI.row[i][0] = rs.getString("Book.ISBN");
				SearchGUI.row[i][1] = rs.getString("BOOK.Title");
				SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
				SearchGUI.row[i][3] = rs.getString("Book_Copies.Branch_id");
				SearchGUI.row[i][4] = rs.getString("Library_Branch.Branch_name");
				SearchGUI.row[i][5] = rs.getInt("No_of_copies");
				SearchGUI.row[i][6] = rs.getInt("copies_available");

				i++;
		} 
			
			rs.close();
			conn.close();
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
	}


public static void nameTitle(){
		
		

		try {
	
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

		
			Statement stmt = conn.createStatement();
    
			stmt.execute("use library;");

			int i =0;
			
			ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.Title LIKE"+"'%"+SearchGUI.booktitle+"%'AND Fullname LIKE"+"'%"+SearchGUI.fullname+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
	
			while (rs.next()) {
				
				
				SearchGUI.row[i][0] = rs.getString("Book.ISBN");
				SearchGUI.row[i][1] = rs.getString("BOOK.Title");
				SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
				SearchGUI.row[i][3] = rs.getString("Book_Copies.Branch_id");
				SearchGUI.row[i][4] = rs.getString("Library_Branch.Branch_name");
				SearchGUI.row[i][5] = rs.getInt("No_of_copies");
				SearchGUI.row[i][6] = rs.getInt("copies_available");

				i++;
		} 
			
			rs.close();
			conn.close();
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
	}

public static void isbnTitle(){
	
	

	try {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

	
		Statement stmt = conn.createStatement();

		stmt.execute("use library;");

		int i =0;
		
		ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.ISBN LIKE"+"'%"+SearchGUI.isbn+"%'AND Book.Title LIKE"+"'%"+SearchGUI.booktitle+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
		
		while (rs.next()) {
			
			
			SearchGUI.row[i][0] = rs.getString("Book.ISBN");
			SearchGUI.row[i][1] = rs.getString("BOOK.Title");
			SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
			SearchGUI.row[i][3] = rs.getString("Book_Copies.Branch_id");
			SearchGUI.row[i][4] = rs.getString("Library_Branch.Branch_name");
			SearchGUI.row[i][5] = rs.getInt("No_of_copies");
			SearchGUI.row[i][6] = rs.getInt("copies_available");

			i++;
	} 
		
		rs.close();
		conn.close();
	}
	catch(SQLException ex) {
		System.out.println("Error in connection: " + ex.getMessage());
	}
}

public static void fullname(){
	
	

	try {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

	
		Statement stmt = conn.createStatement();


		stmt.execute("use library;");

		int i =0;
		
		ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Fullname LIKE"+"'%"+SearchGUI.fullname+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
		
		while (rs.next()) {
			
			
			SearchGUI.row[i][0] = rs.getString("Book.ISBN");
			SearchGUI.row[i][1] = rs.getString("BOOK.Title");
			SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
			SearchGUI.row[i][3] = rs.getString("Book_Copies.Branch_id");
			SearchGUI.row[i][4] = rs.getString("Library_Branch.Branch_name");
			SearchGUI.row[i][5] = rs.getInt("No_of_copies");
			SearchGUI.row[i][6] = rs.getInt("copies_available");

			i++;
	} 
		
		rs.close();
		conn.close();
	}
	catch(SQLException ex) {
		System.out.println("Error in connection: " + ex.getMessage());
	}
}

public static void isbn(){
	
	

	try {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

		Statement stmt = conn.createStatement();
//System.out.println("Hello");

		stmt.execute("use library;");

		int i =0;
		System.out.println("hey");
		ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.ISBN LIKE"+"'%"+SearchGUI.isbn+"%'AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
		System.out.println("hi");
		
		while (rs.next()) {
			
			System.out.println(rs.getString("Book.Title"));
			SearchGUI.row[i][0] = rs.getString("Book.ISBN");
			SearchGUI.row[i][1] = rs.getString("BOOK.Title");
			SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
			SearchGUI.row[i][3] = rs.getString("Book_Copies.Branch_id");
			SearchGUI.row[i][4] = rs.getString("Library_Branch.Branch_name");
			SearchGUI.row[i][5] = rs.getInt("No_of_copies");
			SearchGUI.row[i][6] = rs.getInt("copies_available");

			i++;
	} 
		
		rs.close();
		conn.close();
	}
	catch(SQLException ex) {
		System.out.println("Error in connection: " + ex.getMessage());
	}
}

public static void bookTitle(){
	
	

	try {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

		Statement stmt = conn.createStatement();

	
		stmt.execute("use library;");

		int i =0;
		
		ResultSet rs = stmt.executeQuery("SELECT BOOK.ISBN,BOOK.TITLE,AUTHORS.FULLNAME,BOOK_COPIES.BRANCH_ID,library_branch.branch_name,no_of_copies,copies_available FROM book,authors,book_authors,book_copies,library_branch WHERE Book.Title LIKE"+"'%"+SearchGUI.booktitle+"%' AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND BOOK.Isbn LIKE CONCAT('%',BOOK_COPIES.Book_id,'%') AND Library_Branch.Branch_id=Book_Copies.Branch_Id AND BOOK.Isbn=BOOK_AUTHORS.Isbn AND book_authors.Author_ID=AUTHORS.Author_Id;");
		
		while (rs.next()) {
			
			SearchGUI.row[i][0] = rs.getString("Book.ISBN");
			SearchGUI.row[i][1] = rs.getString("BOOK.Title");
			SearchGUI.row[i][2] = rs.getString("Authors.Fullname");
			SearchGUI.row[i][3] = rs.getString("Book_Copies.Branch_id");
			SearchGUI.row[i][4] = rs.getString("Library_Branch.Branch_name");
			SearchGUI.row[i][5] = rs.getInt("No_of_copies");
			SearchGUI.row[i][6] = rs.getInt("copies_available");

			i++;
	} 
		
		rs.close();
		conn.close();
	}
	catch(SQLException ex) {
		System.out.println("Error in connection: " + ex.getMessage());
	}
}

public static void check(){
	
	

	try {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "loveulord");

		Statement stmt = conn.createStatement();

		stmt.execute("use library;");

		
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS CHECKED FROM BOOK_LOANS, BOOK_COPIES, LIBRARY_BRANCH WHERE BOOK_LOANS.Branch_id =" + SearchGUI.BranchID + " AND BOOK_COPIES.Book_id LIKE CONCAT('%',"+SearchGUI.BookID+",'%') AND BOOK_LOANS.Branch_Id=Library_Branch.Branch_id AND BOOK_COPIES.Branch_Id=Library_branch.Branch_Id AND Date_in='1000-10-10';");
		

		while (rs.next()) {
			
			checkedOut=rs.getInt("CHECKED");
			
	} 
		
		rs.close();
		conn.close();
		System.out.println("Success");
	}
	catch(SQLException ex) {
		System.out.println("Error in connection: " + ex.getMessage());
	}
}



public static void main(String args[])
{
	
}
	
}
