

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Target01
 */
import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.*;
class BankImpl implements Bank {
    BankImpl() throws RemoteException {
    }
    Connection con, con2;
    
    
    public  List<Customer> getCustomers(String uname) {
        List<Customer> list = new ArrayList<Customer>();

        try{
            con = DB.connector();
            con2 = DB.connector2();
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/bank_rmi?characterEncoding=latin1&useConfigs=maxPerformance","root","");
//            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from customer where USERNAME = '"+uname+"'");
            //PreparedStatement ps=con.prepareStatement("select * from customer");
            //ResultSet rs=ps.executeQuery();

            while(rs.next()){
                Customer c=new Customer();
                c.setAcc_no(rs.getInt(1));
                c.setFirstname(rs.getString(2));
                c.setLastname(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setAmount(rs.getFloat(5));
                c.setUsername(rs.getString(6));
                list.add(c);
                
            }
            stmt.close();
            if(rs==null){
             Statement stmt1=con2.createStatement();
             ResultSet rs1=stmt1.executeQuery("select * from customer where USERNAME = '"+uname+"'");
           
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return list;
    }
    
    public String saveCustomer(String fname, String lname, String email, float amount, String uname, String passwd) {
        
            try{
                 con = DB.connector();
                 con2 = DB.connector2();
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/bank_rmi?characterEncoding=latin1&useConfigs=maxPerformance","root","");
//            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
               //PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES(103, 'Sumit', 'Mittal', 28)");
               String sql = "INSERT INTO customer(FIRSTNAME,LASTNAME,EMAIL,AMOUNT,USERNAME,PASSWORD)"+"VALUES('"+fname+"','"+lname+"','"+email+"','"+amount+"','"+uname+"','"+passwd+"')";
                //int rs = ps.executeUpdate();
                if(stmt.executeUpdate(sql)>0){
                    Statement stmt2=con2.createStatement();
               //PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES(103, 'Sumit', 'Mittal', 28)");
               String sql2 = "INSERT INTO customer(FIRSTNAME,LASTNAME,EMAIL,AMOUNT,USERNAME,PASSWORD)"+"VALUES('"+fname+"','"+lname+"','"+email+"','"+amount+"','"+uname+"','"+passwd+"')";
               stmt2.executeUpdate(sql);
                }
                
                stmt.close();
            
            }
            catch (Exception e){
                System.out.println(e);
            }
        
        
        return "";
    }
    
    public String loginUser (String uname, String Pword) {
        
        try{
             con = DB.connector();
             con2 = DB.connector2();
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/bank_rmi?characterEncoding=latin1&useConfigs=maxPerformance","root","");
//            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from customer where USERNAME = '"+uname+"' && PASSWORD = '"+Pword+"'");
            //PreparedStatement ps=con.prepareStatement("select * from customer");
            //ResultSet rs=ps.executeQuery();
            
            if (rs.next() == false) {
                stmt.close();
                 Statement stmt3=con2.createStatement();
                ResultSet rs2=stmt3.executeQuery("select * from customer where USERNAME = '"+uname+"' && PASSWORD = '"+Pword+"'");
                return "Fail";
               
            }
            else {
                stmt.close();
                return "Pass";
            }
            
            
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return "fail";
    }
    
    public String DepositsCust(int accno, float amount) {
        
        try{
             con = DB.connector();
             con2 = DB.connector2();
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/bank_rmi?characterEncoding=latin1&useConfigs=maxPerformance","root","");
//            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
               PreparedStatement ps = con.prepareStatement("update customer set AMOUNT = '"+amount+"' WHERE ACC_NO = '"+accno+"'");
                
                int rs = ps.executeUpdate();
                System.out.println("rsout "+rs);
                if(rs>0){
                    
                    System.out.println("rsin "+rs);
                 Statement stmt4=con2.createStatement();
                 PreparedStatement ps1 = con2.prepareStatement("update customer set AMOUNT = '"+amount+"' WHERE ACC_NO = '"+accno+"'");
                 ps1.executeUpdate();
                }
                stmt.close();
            
        }
        catch (Exception e){
            System.out.println(e);
        }
       
        return "Success";
    }
    
}
