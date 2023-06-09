package com.lbjb2.jdbcPgm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterationDBOperation {
	InputStreamReader inputStreamReader=new InputStreamReader(System.in);		//To Read the Input From the User
	BufferedReader breader=new BufferedReader(inputStreamReader);
	
	Registeration r=new Registeration();
	
	public static Connection getDbConnection() throws ClassNotFoundException,SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcpgm", "root", "1234");
		return con;
	}
	
	void InsertData() throws IOException
	{
		System.out.println("Enter the Name ");
		r.setName(breader.readLine());
		System.out.println("Enter the Department ");
		r.setDept(breader.readLine());
		System.out.println("Enter the Email Id ");
		r.setEmailid(breader.readLine());
		System.out.println("Enter the Mobile Number ");
		r.setMobileNo(breader.readLine());
		try
		{
			Connection conn1=getDbConnection();
			PreparedStatement ps=conn1.prepareStatement("insert into regsiteration (name,dept,emailid,mobileNo) values (?,?,?,?)");
			ps.setString(1, r.getName());
			ps.setString(2, r.getDept());
			ps.setString(3, r.getEmailid());
			ps.setString(4, r.getMobileNo());
			int insertQuery=ps.executeUpdate();
			if(insertQuery>0)
			{
				System.out.println("Inserted Successfully");
			}
			else
			{
				System.out.println("Error in Inserting the Values");
			}
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	
		
	}
	public void reteriveData()throws ClassNotFoundException,SQLException
	{
		Connection con=getDbConnection();
		
		PreparedStatement ps1=con.prepareStatement("select * from regsiteration");
		ResultSet rs=ps1.executeQuery();
		while(rs.next())
		{
			System.out.println("Id is "+rs.getInt("id")+" Name is "+rs.getString("name")+" Department is "+rs.getString("dept"));
		}
		
	}
	public void reteriveParticularData()throws IOException,ClassNotFoundException,SQLException
	{
		this.reteriveData();
		System.out.println("In the Above Record Which Record u want to View in Details");
		int RecordsNo=Integer.parseInt(breader.readLine());
		Connection con=getDbConnection();
		PreparedStatement ps=con.prepareStatement("select * from regsiteration where id=?");
		ps.setInt(1, RecordsNo);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			System.out.println("**************** Student Complete Details***************");
			System.out.println("Student Register Number is ------------>"+rs.getInt("id"));
			System.out.println("Student Name is            ------------>"+rs.getString("name"));
			System.out.println("Student Email id is        ------------>"+rs.getString("emailid"));
			System.out.println("Student Mobile Number is   ------------>"+rs.getString("mobileNo"));
			System.out.println("Student Department is      ------------>"+rs.getString("dept"));
			
		}
		
		
	}

	
	
	
	
	public void UserChoice() throws IOException,ClassNotFoundException,SQLException
	{
		int options;
		System.out.println(" 1.Insert \n 2.Delete \n 3.Update \n 4.Reterive\n 5.Reterive Particular Student Details");
		System.out.println("Enter your Choice ");
		options=Integer.parseInt(breader.readLine());
		
		switch (options) {
		case 1:
			this.InsertData();
			break;
		case 2:
		System.out.println("Comming Soon");
			break;
		case 3:
			System.out.println("Comming Soon");
			break;
		case 4:
			this.reteriveData();
			break;
		case 5:
			this.reteriveParticularData();
			break;

		default:
			System.out.println("Please Provide proper Input");
		}
	}
	public static void main(String[] args) throws IOException,ClassNotFoundException,SQLException {
		RegisterationDBOperation rdbo=new RegisterationDBOperation();
		
		rdbo.UserChoice();
		
	
	}
	

}
