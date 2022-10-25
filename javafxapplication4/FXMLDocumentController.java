/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafxapplication4.custom.*;  // IMPORT TO PACKAGE STO OPOIO UPARXEI H NUMBERTEXTFIELD POU KANEI VALIDATION SE PAIDIO POU 8ELOUME NA EXEI MONO ARH8MOUS

public class FXMLDocumentController implements Initializable {
    
    // OLA TA VASIKA KOMATIA POU A3IOPOIOUNTAI APO TON CONTROLLER
    
    @FXML
    private TextArea txt_description;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_surname;

    @FXML
    private TextField txt_address;

    @FXML
    private NumberTextField txt_telephone; // H NUMBERTEXTFIELD SE XRHSH

    @FXML
    private CheckBox check_firearms;

    @FXML
    private CheckBox check_police;

    @FXML
    private CheckBox check_ambulance;
    
    
    
    @FXML
    private TableView<emergency> table_users;
    
    @FXML
    private TableColumn<emergency,LocalDate> col_date;

    @FXML
    private TableColumn<emergency, String> col_name;

    @FXML
    private TableColumn<emergency, String> col_surname;

    @FXML
    private TableColumn<emergency, String> col_address;

    @FXML
    private TableColumn<emergency, String> col_telephone;

    @FXML
    private TableColumn<emergency, String> col_description;
    
     @FXML
    private TableColumn<emergency, String> col_firearms;

    @FXML
    private TableColumn<emergency, String> col_police;

    @FXML
    private TableColumn<emergency, String> col_ambulance;
    
    
    ObservableList<emergency> listE;  // H LISTA STHN OPOIA PERNOUNTAI TA STOIXEIA APO THN VASH MAS KAI USTERA EMFANIZONTAI 
    
    int index= -1; // INDEX POU XRHSIMOPOIEITAI GIA THN LEITOURGIA EPILOGHS KAI EPE3ERGASIAS MIAS GRAMMHS APO THN LISTA
    
    Connection conn = null; //
    
    ResultSet rs = null;  //
    
    PreparedStatement pst = null;  //ANTIKEIMENO PREPAREDSTATEMENT POU 8A XRHSIMOPOIH8EI GIA THN EKTELESH QUERIES STHN VASH MAS
    
    static LocalDate date = LocalDate.now(); // H STATIKH METAVLITH LOCALDATE STHN OPOIA PERNAME THN TWRINH HMEROMHNIA H OPOIA KAI APO8HKEUETAI STHN VASH KA8E FORA POU PERNAME MIA EISAGWGH
    
    
    // H ME8ODOS POU XRHSIMOPOIEITAI GIA NA EISAGOUME KAINOURIA EGKRAFH KA8E FORA POU PATAME TO KOUMPI ADD
    public void Add_emergency ()
    {
        
        conn= mysqlconnect.ConnectDb();
        String sql = "insert into emergency (em_date,em_name,em_surname,em_address,em_telephone,em_description,em_firearms,em_police,em_ambulance) values(?,?,?,?,?,?,?,?,?) ";
     
        try {
            
            //8ETONTAS STO STATEMENT PARAPANW TIS TIMES APO TA TEXTFIELNDS KAI TA CHECKBOXES
            pst = conn.prepareStatement(sql);
            pst.setString(1, date.toString());
            pst.setString(2, txt_name.getText());
            pst.setString(3, txt_surname.getText());
            pst.setString(4, txt_address.getText());
            pst.setString(5, txt_telephone.getText());
            pst.setString(6, txt_description.getText());
            
            if (check_firearms.isSelected())
            {
                pst.setString(7, "true");
            }
            else
            {
                pst.setString(7, "false");
            }
            if (check_police.isSelected())
            {
                pst.setString(8, "true");
            }
            else
            {
                pst.setString(8, "false");
            }
            if (check_ambulance.isSelected())
            {
                pst.setString(9, "true");
            }
            else
            {
                pst.setString(9, "false");
            }
            pst.execute(); //EKTELONTAS TO STATEMENT
            JOptionPane.showMessageDialog(null, "Emergency Added successfully.");
            UpdateTable(); // EPISTROFH THS ANAVA8MISMENHS VASHS
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    
   // H ME8ODOS ME THN OPOIA KA8E FORA POU PATAME MIA GRAMMH THS LISTAS GEMIZEI TA TEXTFIELDS KAI TA COMBOBOXES ME TIS EISODOUS THS GRAMMHS
    @FXML
    public void getSelected (MouseEvent event){
        // INDEX GIA THN GRAMMH THS LISTAS 
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index<= -1){
            
            return ;
        }
        //PAIRNONTAS STA TEXTFIELDS TIS TIMES APO THN GRAMMH THS LISAS POU PATH8HKE
    txt_name.setText(col_name.getCellData(index).toString());
    txt_surname.setText(col_surname.getCellData(index).toString());
    txt_address.setText(col_address.getCellData(index).toString());
    txt_telephone.setText(col_telephone.getCellData(index).toString());
    txt_description.setText(col_description.getCellData(index).toString());
    //CHECKARONTAS TA CHECKBOXES ANALOGA ME TIS TIMES THS VASHS ( OTA EINAI TRUE H ANTISTOIXH STULH TO CHECKAREI ALLIWS OXI)
    if (col_firearms.getCellData(index).toString().equals("true"))
    {
        check_firearms.setSelected(true);
    }
    else
    {
        check_firearms.setSelected(false);
    }
    
    if (col_police.getCellData(index).toString().equals("true"))
    {
        check_police.setSelected(true);
    }
    else
    {
        check_police.setSelected(false);
    }
    if (col_ambulance.getCellData(index).toString().equals("true"))
    {
        check_ambulance.setSelected(true);
    }
    else
    {
        check_ambulance.setSelected(false);
    }
    }

    // H ME8ODOS POU XRHSIMOPOIEITAI SE SUNDIASMO ME THN GET SELECTED GIA EPE3ERGASIA MIAS GRAMMHS APO THN VASH 
    @FXML
    public void Edit ()
    {
        try{
            
            index = table_users.getSelectionModel().getSelectedIndex();
            emergency temp = table_users.getItems().get(index);  //PROSWRINO OBJECT POU XRHSIMOPOIEITAI GIA NA PAROUME TO ID THS SUGKEKRIMENHS GRAMMHS ARGOTERA
            
            
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_name.getText();
            String value2 = txt_surname.getText();
            String value3 = txt_address.getText();
            String value4 = txt_telephone.getText();
            String value5 = txt_description.getText();
            String value6,value7,value8;
            if (check_firearms.isSelected())
            {
                value6="true";
            }
            else
            {
                value6="false";
            }
            if (check_police.isSelected())
            {
                value7="true";
            }
            else
            {
                value7="false";
            }
            if (check_ambulance.isSelected())
            {
                value8="true";
            }
            else
            {
                value8="false";
            }
            
            String sql = "update emergency set em_name= '"+value1+
                    "', em_surname= '"+value2+
                    "', em_address= '"+value3+
                    "', em_telephone= '"+value4+
                    "', em_description= '"+value5+
                    "', em_firearms= '"+value6+
                    "', em_police= '"+value7+
                    "', em_ambulance= '"+value8+
                    "' where em_id = '"+temp.getID()+"' " ;
            //OPWS FAINETAI PANW TO TEMP A3IOPOIEITAI GIA THN EURESH TOU ID STHN SUGKEKRIMENH GRAMMH 
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Updated.");
            
            //EMFANISH TOU ANAVA8MISMENOU TABLE ANALOGA ME THN EPILOGH POU EXEI GINEI STO COMBOBOX
            if(comb.getValue()=="All emergencys")
        {
            UpdateTable();
        }
            else if(comb.getValue()=="Firearms")
        {
            UpdateTableFromCombobox("em_firearms");
        }
            else if(comb.getValue()=="Police")
        {
            UpdateTableFromCombobox("em_police");
        }
            else if(comb.getValue()=="Ambulance")
        {
            UpdateTableFromCombobox("em_ambulance");
        }
            else
            {
                UpdateTable();
            }
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    //DIAGRAFH EPILEGMENHS GRAMMHS ME THN GETSELECTED
    @FXML
    public void deleteRow()
    {
        emergency temp = table_users.getItems().get(index); // OPWS KAI STO EDIT, XRHSH PROSWRINOU ANTIKEIMENOU GIA THN SUGKEKRIMENH GRAMMH GIA APOKTHSH PROSVASHS STO ID
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from emergency where em_id = '"+temp.getID()+"' ";
        try{
        pst = conn.prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Row Deleted.");
        UpdateTable();
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    //TO COMBOBOX KAI OI TIMES POU 8A DIA8ETEI
    @FXML
    private ComboBox<String> comb;
    
    ObservableList<String> listC = FXCollections.observableArrayList("All emergencys","Firearms","Police","Ambulance");
    
    
    //H TA3INOMHSH POU GINETAI KA8E FORA POU KAPOIOS DIALEGEI MIA TIMH APO TO COMBOBOX
    
    public void listAfterCombobox(ActionEvent event)
    {
        if(comb.getValue()=="All emergencys") // EMFANIZEI OLH THN LISTA ME TA EMERGENCYS
        {
            UpdateTable();
        }
        if(comb.getValue()=="Firearms")//EMFANIZEI TIS GRAMMES STIS OPOIES DIALEXTHKE TO FIERARMS
        {
            UpdateTableFromCombobox("em_firearms");
        }
        if(comb.getValue()=="Police")//EMFANIZEI TIS GRAMMES STIS OPOIES DIALEXTHKE TO POLICE
        {
            UpdateTableFromCombobox("em_police");
        }
        if(comb.getValue()=="Ambulance")//EMFANIZEI TIS GRAMMES STIS OPOIES DIALEXTHKE TO AMBULANCE
        {
            UpdateTableFromCombobox("em_ambulance");
        }
    }
    
    @FXML
    private Button addBtn;

    @FXML 
    private Button updateBtn;
    
    @FXML
    private Button deleteBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comb.setItems(listC);
        UpdateTable();
        
        // VALIDATION POU FRONTIZEI TO KOUMPI ADD NA EINAI DISABLED MEXRI O XRHSTHS NA EISAGEI TIMES STA PAIDIA POU FAINONTAI PARAKATW
        addBtn.disableProperty().bind(txt_name.textProperty().isEmpty()
        .or(txt_surname.textProperty().isEmpty())
        .or(txt_address.textProperty().isEmpty())
        .or(txt_telephone.textProperty().isEmpty())); 
        
        // VALIDATION POU FRONTIZEI TO KOUMPI UPDATE NA EINAI DISABLED MEXRI O XRHSTHS NA EISAGEI TIMES STA PAIDIA POU FAINONTAI PARAKATW
        updateBtn.disableProperty().bind(txt_name.textProperty().isEmpty()
        .or(txt_surname.textProperty().isEmpty())
        .or(txt_address.textProperty().isEmpty())
        .or(txt_telephone.textProperty().isEmpty())); 
        
        // VALIDATION POU FRONTIZEI TO KOUMPI DELETE NA EINAI DISABLED MEXRI O XRHSTHS NA EISAGEI TIMES STA PAIDIA POU FAINONTAI PARAKATW
        deleteBtn.disableProperty().bind(txt_name.textProperty().isEmpty()
        .or(txt_surname.textProperty().isEmpty())
        .or(txt_address.textProperty().isEmpty())
        .or(txt_telephone.textProperty().isEmpty())); 
        
    }    
    
    
    // ANAVA8MISH THS LISTAS OTAN 8ELOUME NA EPISTREFEI OLA TA STOIXEIA THS VASHS
     public void UpdateTable()
    {
        col_date.setCellValueFactory(new PropertyValueFactory<emergency,LocalDate>("date"));
        col_name.setCellValueFactory(new PropertyValueFactory<emergency,String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<emergency,String>("surname"));
        col_address.setCellValueFactory(new PropertyValueFactory<emergency,String>("address"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<emergency,String>("telephone"));
        col_description.setCellValueFactory(new PropertyValueFactory<emergency,String>("description"));
        col_firearms.setCellValueFactory(new PropertyValueFactory<emergency,String>("firearms"));
        col_police.setCellValueFactory(new PropertyValueFactory<emergency,String>("police"));
        col_ambulance.setCellValueFactory(new PropertyValueFactory<emergency,String>("ambulance"));
        
        listE = mysqlconnect.getDatausers();
        table_users.setItems(listE);
        
    }
     
     //H ANAVA8MISH THS LISTAS POU GINETAI KA8E FORA POU EKTELEITAI MIA LEITOURGEIA KAI UPARXEI EPILEGMENH MIA TIMH STO COMBOBOX ETSI WSTE NA MHN ALLAZEI TIS TIMES POU FAINONTAI
     public void UpdateTableFromCombobox(String s)
     {
         col_date.setCellValueFactory(new PropertyValueFactory<emergency,LocalDate>("date"));
        col_name.setCellValueFactory(new PropertyValueFactory<emergency,String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<emergency,String>("surname"));
        col_address.setCellValueFactory(new PropertyValueFactory<emergency,String>("address"));
        col_telephone.setCellValueFactory(new PropertyValueFactory<emergency,String>("telephone"));
        col_description.setCellValueFactory(new PropertyValueFactory<emergency,String>("description"));
        col_firearms.setCellValueFactory(new PropertyValueFactory<emergency,String>("firearms"));
        col_police.setCellValueFactory(new PropertyValueFactory<emergency,String>("police"));
        col_ambulance.setCellValueFactory(new PropertyValueFactory<emergency,String>("ambulance"));
        
        listE = mysqlconnect.getDatausersByCombobox(s);
        table_users.setItems(listE);
     }
     
     
}
