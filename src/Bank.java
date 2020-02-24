

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
import java.util.*;

interface Bank extends Remote{
    public List<Customer> getCustomers(String uname)throws RemoteException;
    public String saveCustomer(String fname, String lname, String email, float amount, String uname, String passwd) throws RemoteException;
    public String loginUser(String uname, String Pword) throws RemoteException;
    //public String DepositsCust(float amount) throws RemoteException;
    public String DepositsCust(int accno, float amount) throws RemoteException;
    
}
