package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;

public class Model {
	
	Connection con = null;
	ResultSet res = null;
	PreparedStatement pstmt = null;
    String url = "jdbc:oracle:thin:@//localhost:1521/XE";
    String user = "SYSTEM";
    String pass = "system";
    ArrayList al1 = new ArrayList();

String name;
String accountNumber;
int balance;
String cid;
String pw;
String email;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(String accountNumber) {
	this.accountNumber = accountNumber;
}

public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}

public String getCid() {
	return cid;
}
public void setCid(String cid) {
	this.cid = cid;
}

public String getPw() {
	return pw;
}
public void setPw(String pw) {
	this.pw = pw;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


String rAccountNumber;
String rPassword;
int rBalance;
int transferAmount;
int newBalance;
int executeUpdateVal1;
int executeUpdateVal2;
int ministatementUpdateVAL;

String formattedTime;
ArrayList al = new ArrayList();


public ArrayList getAl() {
	return al;
}
public void setAl(ArrayList al) {
	this.al = al;
}

public int getTransferAmount() {
	return transferAmount;
}
public String getFormattedTime() {
	return formattedTime;
}
public void setFormattedTime(String formattedTime) {
	this.formattedTime = formattedTime;
}
public void setTransferAmount(int transferAmount) {
	this.transferAmount = transferAmount;
}
public String getrAccountNumber() {
	return rAccountNumber;
}
public void setrAccountNumber(String rAccountNumber) {
	this.rAccountNumber = rAccountNumber;
}
public String getrPassword() {
	return rPassword;
}
public void setrPassword(String rPassword) {
	this.rPassword = rPassword;
}
public int getrBalance() {
	return rBalance;
}
public void setrBalance(int rBalance) {
	this.rBalance = rBalance;
}

public boolean setRecieverDetailsToSession() {
	String q = "select * from receiver_bank_details where accountnumber = ?";
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setString(1, rAccountNumber);
		res = pstmt.executeQuery();
		
		if(res.next()==true) {
			rAccountNumber = res.getString(2);
			rBalance = res.getInt(3);
			return true;
		}else {
			return false;
		}
	} catch (SQLException e) {}
	return false;
	
}



public void rNewBal() {
	int receiverNewBalance = rBalance + transferAmount;
	String q = "update receiver_bank_details set balance = ? where accountnumber = ?";
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setInt(1, receiverNewBalance);
		pstmt.setString(2, rAccountNumber);
		executeUpdateVal2 = pstmt.executeUpdate();
		
	} catch (SQLException e) {}
	
}

Model(){
	
		try {
			DriverManager.registerDriver(new OracleDriver());
			con = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {}
	
}


public boolean changePassword() {
	String q = "UPDATE BANK_DETAILS SET PASSWORD = ? WHERE accountnumber = ?";
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setString(1, pw);
		pstmt.setString(2, accountNumber);
		int updatePasswordVAL = pstmt.executeUpdate();
		if(updatePasswordVAL > 0) {
			return true;
		}else {return false;}
	}catch(Exception e) {}
	return false;
	
}

public boolean login() {
	String q = "select * from bank_details where CUSTOMERID=? and PASSWORD=?";
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setString(1, cid);
		pstmt.setString(2, pw);
		res = pstmt.executeQuery();
		
		if(res.next()==true) {
			accountNumber = res.getString(2);
			balance = res.getInt(3);
			return true;
		}else {
			return false;
		}
	} catch (SQLException e) {}
	return false;
	
	}
public boolean checkBalance() {
	String q = "select * from bank_details where accountnumber = ?";
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setString(1, accountNumber);
		
		res = pstmt.executeQuery();
		if(res.next()==true) {
			balance=res.getInt(3);
			return true;
		}else return false;
	}catch(Exception e) {}
	return false;
}

public boolean transferMoney() {
	
	try {con.setAutoCommit(false);}catch(SQLException e2) {}
	
	String query = "select * from bank_details where accountnumber = ?"; 
	try {
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, accountNumber);
		res=pstmt.executeQuery();
		if(res.next()==true) {
			balance = res.getInt(3);
		}
	} catch (SQLException e1) {}
	
	newBalance = balance - transferAmount;

	String q = "UPDATE bank_details SET balance = ? WHERE accountnumber = ?";
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setInt(1, newBalance);
		pstmt.setString(2, accountNumber);
		executeUpdateVal1 = pstmt.executeUpdate();
		
		if(executeUpdateVal1>0) {
			rNewBal();
				
			System.out.println(executeUpdateVal1+executeUpdateVal2);
			ministatementUpdateVAL = executeUpdateVal1+executeUpdateVal2;
			con.setAutoCommit(true);
			if(ministatementUpdateVAL==2) {	
				miniStatement();
				
			}
			return true;
		}else {return false;}
	} catch (SQLException e) {}return false;
}

public void miniStatement() {
    
	String qMiniStatement = "INSERT INTO MINI VALUES(?,?,?,?)";
	System.out.println("MiniStatement Generation Started");
	System.out.println(accountNumber);
	System.out.println(rAccountNumber);
	System.out.println(transferAmount);
	System.out.println(formattedTime);
	try {
		pstmt = con.prepareStatement(qMiniStatement);
		pstmt.setString(1, accountNumber);
		pstmt.setString(2, rAccountNumber);
		pstmt.setInt(3, transferAmount);
		pstmt.setString(4, formattedTime);
		int val=pstmt.executeUpdate();
		System.out.println("MiniStatement Generated"+" "+val);
	} catch (SQLException e) {e.printStackTrace();}
}


String fromAcNo;
String toAcNo;
int miniAmount;
String time;

public boolean viewMiniStatement() {
	String q = "select * from mini where FROMACCOUNTNUMBER = ?";
	
	try {
		pstmt = con.prepareStatement(q);
		pstmt.setString(1,accountNumber );
		res=pstmt.executeQuery();
		
		while(res.next()==true) {
			fromAcNo = res.getString(1);
			al.add(fromAcNo);
			toAcNo = res.getString(2);
			al.add(toAcNo);
			miniAmount = res.getInt(3);
			al.add(miniAmount);
			time = res.getString(4);
			al.add(time);
			}
	} catch (SQLException e) {}
	return true;
}
public ArrayList getMiniSbyAcNo() {
	String q = "select * from mini where TOACCOUNTNUMBER = ?";
	System.out.println("model method"+rAccountNumber);
	try {
		pstmt=con.prepareStatement(q);
		pstmt.setString(1,rAccountNumber);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			fromAcNo = res.getString(1);
			al1.add(fromAcNo);
			toAcNo = res.getString(2);
			al1.add(toAcNo);
			miniAmount = res.getInt(3);
			al1.add(miniAmount);
			time = res.getString(4);
			al1.add(time);
			System.out.println("array list add done on method");
			}
		for(Object element:al1) {
			System.out.println(element);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return al1;
}

}

