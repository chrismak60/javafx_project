/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


// H KLASH POU XRHSIOMPOIEITAI GIA THN EPAFH THS VASHS MAS ME THN EFARMOGH MAS
public class mysqlconnect {
    
    Connection conn = null;
          
    
    
    public static Connection ConnectDb() // H ME8ODOS MAS GIA THN SUNDESH THS EFARMOGHS MAS ME THN VASH 
    {
        
        try {
            Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost/ergasia","root","");
            // JOptionPane.showMessageDialog(null,"Connection established!");  //GIA DEBUGGING
            return conn;
        }
        catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                    return null;
                }
      
    }
    
    
    public static ObservableList<emergency> getDatausers() //H ME8ODOS POU EPISTREFEI OLA TA STOIXEIA THS VASHS MAS SE MIA LISTA
    {
        
        Connection conn = ConnectDb();
        
        ObservableList<emergency> list = FXCollections.observableArrayList();
        try {
           PreparedStatement ps = conn.prepareStatement("select * from emergency");
           ResultSet rs = ps.executeQuery();
           
           while (rs.next())
           {
               list.add(new emergency(Integer.parseInt(rs.getString("em_id")),LocalDate.parse(rs.getString("em_date")),rs.getString("em_name"),rs.getString("em_surname"),rs.getString("em_address"),rs.getString("em_telephone"),rs.getString("em_description"),rs.getString("em_firearms"),rs.getString("em_police"),rs.getString("em_ambulance")));
           } 
        }catch (Exception ex){
            
        }
        
        
        return list;
    }
    
    
    // H ME8ODOS POU EPISTREFEI TA STOIXEIA THS VASHS MAS ANALOGA ME TO TI EPILE3OME STO COMBOBOX TWN EKTAKTWN ANAGKWN 
    public static ObservableList<emergency> getDatausersByCombobox( String s) 
            
        {
        Connection conn = ConnectDb();
        
        ObservableList<emergency> list = FXCollections.observableArrayList();
        try {
           PreparedStatement ps = conn.prepareStatement("select * from emergency where "+s+" = 'true'");
           ResultSet rs = ps.executeQuery();
           
           while (rs.next())
           {
               list.add(new emergency(Integer.parseInt(rs.getString("em_id")),LocalDate.parse(rs.getString("em_date")),rs.getString("em_name"),rs.getString("em_surname"),rs.getString("em_address"),rs.getString("em_telephone"),rs.getString("em_description"),rs.getString("em_firearms"),rs.getString("em_police"),rs.getString("em_ambulance")));
           } 
        }catch (Exception ex){
            
        }
        
        
        return list;
    }
    
    
}
