
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DHARSHITHA
 */
import java.sql.*;

public class DB {
    Connection con= null;
    public DB(){}
    public static Connection connector(){
        try {
        Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank_rmi?characterEncoding=latin1&useConfigs=maxPerformance","root","");
       return con;
     }catch(Exception e){
       return null;
    }
    }
    public static Connection connector2(){
        try{
     Class.forName("com.mysql.jdbc.Driver");
            Connection con2=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ceesinte_bank_rmi?characterEncoding=latin1&useConfigs=maxPerformance","root","");
            return con2;
        }
        catch(Exception e){
            return null;
        }
    }
    
}
