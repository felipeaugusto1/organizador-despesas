/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirn.desktop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author felipe
 */
public class FXMLMainController implements Initializable {
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private TextField txtSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void btnLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/listagemlancamentos/ListagemLancamentos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Organizador de Despesas");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void clickNovoCadastro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/novousuario/NovoUsuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Novo Usuário");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
