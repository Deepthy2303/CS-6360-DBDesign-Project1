#!/bin/bash


DB_USER=$2
DB_PASS=$3
DB=$1
echo 'logging in'
mysql -u $DB_USER --password=$DB_PASS << EOF

CREATE DATABASE IF NOT EXISTS $DB;
exit
EOF
cp book_copies.csv /mysql/data/ $DB
cp books.csv /mysql/data/ $DB
cp borrowers.csv /mysql/data/ $DB
cp library_branch.csv /mysql/data/ $DB
cp book_loans.csv /mysql/data/ $DB

mysql -u $DB_USER --password=$DB_PASS << EOF
use $DB;
CREATE TABLE BOOK(ISBN CHAR(20) NOT NULL, Title VARCHAR(500) NOT NULL);
LOAD DATA INFILE 'books.csv' INTO TABLE BOOK FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7) set ISBN=@col1 , Title = @col3;
ALTER TABLE BOOK ADD UNIQUE INDEX id_index(ISBN);

CREATE TABLE LIBRARY_BRANCH (Branch_id INT(11) NOT NULL, Branch_name VARCHAR(20) NOT NULL, Address VARCHAR(50) NOT NULL, PRIMARY KEY(Branch_id));
LOAD DATA INFILE 'library_branch.csv' INTO TABLE LIBRARY_BRANCH FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3) set Branch_id=@col1 , Branch_name = @col2, Address = @col3;

CREATE TABLE BOOK_COPIES(Book_id CHAR(20) NOT NULL, Branch_id INT(11) NOT NULL, No_of_copies INT(11) NOT NULL, copies_available INT(11));
UPDATE library.book_copies SET copies_available=no_of_copies;
ALTER TABLE BOOK_COPIES ADD CONSTRAINT id_fk4 FOREIGN KEY (Book_id) REFERENCES BOOK(ISBN);
set foreign_key_checks = 0;
LOAD DATA INFILE 'book_copies.csv' INTO TABLE BOOK_COPIES FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3) set Book_id=@col1 , Branch_id = @col2, No_of_copies = @col3;
set foreign_key_checks = 1;
ALTER TABLE BOOK_COPIES ADD UNIQUE INDEX id_indexx(Book_id,Branch_id);
ALTER TABLE BOOK_COPIES ADD CONSTRAINT id_fk2 FOREIGN KEY (Branch_id) REFERENCES LIBRARY_BRANCH(Branch_id);

CREATE TABLE BORROWER(Card_no VARCHAR(20) NOT NULL, SSN VARCHAR(11) NOT NULL,Fname VARCHAR(20) NOT NULL, Lname VARCHAR(20) NOT NULL, Address VARCHAR(255) NOT NULL, Phone CHAR(20) NOT NULL,PRIMARY KEY(Card_no));
LOAD DATA INFILE 'borrowers.csv' INTO TABLE BORROWER FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7,@col8,@col9) set Card_no=@col1 ,SSN=@col2, Fname = @col3 , Lname = @col4, Address = CONCAT(@col6,@col7,@col8) , Phone = @col9;

CREATE TABLE BOOK_LOANS(Loan_Id INT(11) NOT NULL AUTO_INCREMENT, ISBN CHAR(20) NOT NULL, Branch_id INT NOT NULL, Card_no VARCHAR(20) NOT NULL, Date_out datetime, Due_date datetime, Date_in datetime, PRIMARY KEY(Loan_id));
LOAD DATA INFILE 'book_loans.csv' INTO TABLE BOOK_LOANS FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7) set Loan_id=@col1, ISBN=@col2 , Branch_id = @col3 , Card_no = @col4 , Date_out = @col5 , Due_date = @col6 , Date_in = @col7;
ALTER TABLE BOOK_LOANS ADD CONSTRAINT id_fkA FOREIGN KEY (ISBN) REFERENCES BOOK(ISBN);
ALTER TABLE BOOK_LOANS ADD CONSTRAINT id_fkB FOREIGN KEY (Branch_id) REFERENCES LIBRARY_BRANCH(Branch_id);
ALTER TABLE BOOK_LOANS ADD CONSTRAINT id_fkC FOREIGN KEY (Card_no) REFERENCES BORROWER(Card_no);

CREATE TABLE FINES(Loan_id INT(11),fine_amt DECIMAL(5,2),paid BOOLEAN);
ALTER TABLE FINES ADD CONSTRAINT id_fkD FOREIGN KEY (loan_id) REFERENCES BOOK_LOANS(loan_id);
LOAD DATA INFILE 'book_loans.csv' INTO TABLE FINES FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7) set loan_id=@col1;
ALTER TABLE FINES ADD UNIQUE INDEX id_index(loan_id);
UPDATE FINES SET fine_amt=0.00 WHERE fine_amt<0.00;
#ALTER TABLE BOOK ADD UNIQUE INDEX id_index(ISBN);

CREATE TABLE AUTHORS(Author_ID INT(11) NOT NULL AUTO_INCREMENT, Fullname VARCHAR(255) NOT NULL, PRIMARY KEY(Author_ID));
LOAD DATA INFILE 'books.csv' INTO TABLE AUTHORS FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7) set Fullname=@col4;


CREATE TABLE BOOK_AUTHORS
(
ISBN VARCHAR(13) NOT NULL,
AUTHOR_ID INT(11) NOT NULL AUTO_INCREMENT,
constraint BOOK_AUTHORS_PK
PRIMARY KEY(ISBN, AUTHOR_ID),

CONSTRAINT BOOK_AUTHORS_FK1
FOREIGN KEY(ISBN)
REFERENCES BOOK(ISBN)
ON UPDATE CASCADE
ON DELETE CASCADE,
constraint BOOK_AUTHORS_FK2
FOREIGN KEY(AUTHOR_ID)
REFERENCES AUTHORS(AUTHOR_ID)
ON UPDATE CASCADE
ON DELETE CASCADE
);
set foreign_key_checks = 0;
LOAD DATA INFILE 'books.csv' INTO TABLE BOOK_AUTHORS FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n' (@col1,@col2,@col3,@col4,@col5,@col6,@col7) set ISBN=@col1;
set foreign_key_checks = 1;

exit

ok "Database created"

EOF
