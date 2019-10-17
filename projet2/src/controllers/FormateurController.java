/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Formateurs;


public class FormateurController implements Initializable {

       @FXML
    private TextField txtsearch;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
        @FXML
    private TextField email;
    @FXML
    private TextField tele;

    @FXML
    private Button btnSave3,btnupf,btnsaveupf,delform,btnsearch,logout3,cours3,speci3,etud3,settingsf;
    @FXML
    Label lblsa;

    @FXML
    TableView tbl2;
    
            @FXML
    private ComboBox<String>  specx;

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement;
    Connection connection;
   

    public FormateurController() {
        connection = (Connection) ConnectionUtil.conDB();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        (btnsaveupf).setVisible(false);
        
        
        
        specx.getItems().add(" choisir le cours");
         try {
            
             
           
             
            
            // Execute query and store result in a resultset
            String SQL1 ="SELECT id, nom   FROM cours ";
                     ResultSet rs = connection.createStatement().executeQuery(SQL1);
            while (rs.next()) {
                
                //get string from db,whichever way 
                  specx.getItems().add(rs.getString("id")+"-"+rs.getString("nom"));
                
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }


            fetColumnList();
            fetRowList();
 
        
    

    }
    

    @FXML
    private void HandleEvents(MouseEvent event) throws SQLException, IOException {
   
        //check if not empty
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || tele.getText().isEmpty()) {
            lblsa.setTextFill(Color.TOMATO);
            lblsa.setText("Entrer toutes les  detailes");
        } 
        else {
         saveData();
        }
        


    }
    
    
    
        private String saveData() {

        try {
            String st = "INSERT INTO formateurs ( nom,prenom,email,telephone,id_c) VALUES (?,?,?,?,?)";
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1, nom.getText());
            preparedStatement.setString(2, prenom.getText());
            preparedStatement.setString(3, email.getText());
            preparedStatement.setString(4, tele.getText());
            String a=specx.getValue().toString();
              System.out.println(a);
            String spe=a.substring(0,1);
          
            preparedStatement.setString(5,spe);
            preparedStatement.executeUpdate();
            lblsa.setTextFill(Color.GREEN);
            lblsa.setText("Formateur ajouter avec succes");

            fetRowList();
            //clear fields
            clearFields();
            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblsa.setTextFill(Color.TOMATO);
            lblsa.setText(ex.getMessage());
            return "Exception";
        }
    }

    
    
    
     @FXML
    private void deletef(MouseEvent event) throws SQLException, IOException {  
        String p_nom;
                        if (event.getSource() ==delform) {
                     p_nom=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[0];
             String id= p_nom.substring(1,p_nom.length()-1);
             System.out.println(id);
     		String state ="DELETE FROM formateurs WHERE id="+(Integer.parseInt(id)+"");  
                 preparedStatement = (PreparedStatement) connection.prepareStatement(state);
                preparedStatement.executeUpdate(); 
              lblsa.setTextFill(Color.GREEN);
            lblsa.setText("formateur supprimer avec success");
            fetRowList();
            clearFields();
             
         }
      
    }
    
    
    
     @FXML
    private void update(MouseEvent event) throws SQLException, IOException {  
       String p_nom,no,p_id,id,pre,p_prenom,ema,p_email,p_tel,tel,p_cou,cou; 
       
     if (event.getSource() ==btnupf)
     
     {
               
    
      p_nom=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[1];
      no=p_nom.substring(0,p_nom.length()-1);
      System.out.println(no);
      nom.setText(no);
      
      p_prenom=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[2];
      pre=p_prenom.substring(0,p_prenom.length()-1);
      System.out.println(pre);
      prenom.setText(pre);
      
       p_email=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[3];
      ema=p_email.substring(0,p_email.length()-1);
      System.out.println(ema);
      email.setText(ema);
      
      p_tel=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[4];
      tel=p_tel.substring(0,p_tel.length()-1);
      System.out.println(tel);
      tele.setText(tel);
      
      p_cou=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[5];
      cou=p_cou.substring(0,p_cou.length()-1);
    
      specx.setValue(cou);

            
      
     (btnSave3).setVisible(false);
     (btnSave3).setDisable(true);
         (btnsaveupf).setVisible(true);
     }
            
     
     
     
     
       if (event.getSource() ==btnsaveupf) {
                       p_id=tbl2.getSelectionModel().getSelectedItem().toString().split(" ")[0];
        id=p_id.substring(1,p_id.length()-1);
      System.out.println(id);
                  
                     
                  
                try {
            
   String st="UPDATE formateurs set nom=?,prenom=?,email=?,telephone=?,id_c=? WHERE id="+(Integer.parseInt(id)+""); 
         
            preparedStatement = (PreparedStatement) connection.prepareStatement(st);
            preparedStatement.setString(1,nom.getText());
            preparedStatement.setString(2,prenom.getText());
            preparedStatement.setString(3,email.getText());
            preparedStatement.setString(4,tele.getText());
            String a=specx.getValue().toString();
            String spe=a.substring(0,1);
            System.out.println(spe);
            preparedStatement.setString(5,spe);
            System.out.println(st);
            preparedStatement.executeUpdate();
            lblsa.setTextFill(Color.GREEN);
            lblsa.setText("formateurs modifier avec succes");

           fetRowList();
            //clear fields
            clearFields();
            (btnsaveupf).setVisible(false);
          
            (btnSave3).setDisable(false);
           (btnSave3).setVisible(true);
                

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            lblsa.setTextFill(Color.TOMATO);
            lblsa.setText(ex.getMessage());
            
                 }
         
         
      
    }
    }
    
    
    
    
    
    
    
    
          @FXML
    private void nav(MouseEvent event) throws SQLException, IOException {  
     if (event.getSource() ==logout3) {
       Stage stage = (Stage) logout3.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setTitle("DEVTECH Login");

        stage.show();   
        
     }
        else if (event.getSource() ==cours3) {
       Stage stage2 = (Stage) cours3.getScene().getWindow();
        stage2.close();
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/Cours.fxml"));
       Scene scene2 = new Scene(root2);
       stage2.setScene(scene2);
       stage2.setTitle("DEVTECH Cours");

        stage2.show();   
        
    }
     
             else if (event.getSource() ==speci3) {
       Stage stage2 = (Stage) speci3.getScene().getWindow();
        stage2.close();
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/specialite.fxml"));
       Scene scene2 = new Scene(root2);
       stage2.setScene(scene2);
       stage2.setTitle("DEVTECH SPECIALITE");

        stage2.show();   
        
    }
                 else if (event.getSource() ==etud3) {
       Stage stage2 = (Stage) etud3.getScene().getWindow();
        stage2.close();
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/OnBoard.fxml"));
       Scene scene2 = new Scene(root2);
       stage2.setScene(scene2);
       stage2.setTitle("DEVTECH ETUDIANT");

        stage2.show();   
        
    }
                      else if (event.getSource() ==settingsf) {
       Stage stage2 = (Stage) settingsf.getScene().getWindow();
        stage2.close();
        Parent root2 = FXMLLoader.load(getClass().getResource("/fxml/Admin.fxml"));
       Scene scene2 = new Scene(root2);
       stage2.setScene(scene2);
       stage2.setTitle("DEVTECH ETUDIANT");

        stage2.show();   
        
    }
    }
     
    
     
    private void clearFields() {
        nom.clear();
        prenom.clear();
         email.clear();
          tele.clear();
 
  
    }
    

 

    private ObservableList<ObservableList> data;
    String SQL = "SELECT f.id,f.nom,f.prenom,f.email,f.telephone,c.nom from Formateurs f ,cours c where f.id_c=c.id";

    //only fetch columns
    public void fetColumnList() {
              
                  TableColumn col = new TableColumn<Formateurs,String>("ID");
              
                  TableColumn col2 = new TableColumn<Formateurs,String>("Nom ");
                  
                  TableColumn col3 = new TableColumn<Formateurs,String>("Prenom ");
          
                  TableColumn col4 = new TableColumn<Formateurs,String>("Email");
                  TableColumn col5 = new TableColumn<Formateurs,String>("Téléphone");
                 TableColumn col6 = new TableColumn<Formateurs,String>("Formé");
                  

        try {
            ResultSet rs = connection.createStatement().executeQuery(SQL);


            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;

                
                
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(0).toString());
                    }
                });
                
                      col2.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(1).toString());
                    }
                });
                                            col3.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(2).toString());
                    }
                });                      col4.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(3).toString());
                    }
                });                      col5.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(4).toString());
                    }
                });  
                
                  col6.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(5).toString());
                    }
                });      
  }
           
                tbl2.getColumns().addAll(col,col2,col3,col4,col5,col6);
             
               

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());

        }
    }


    //fetches rows and data from the list
    public void fetRowList() {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                //Iterate Row
                ObservableList row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            tbl2.setItems(data);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }



}
