package br.edu.unirn.desktop.telas.novousuario;

import br.edu.unirn.desktop.utils.MensagemUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class NovoUsuarioController implements Initializable {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private TextField txtSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void btnCadastrarUsuario(ActionEvent event) {
        MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Novo Usuário", "Usuário cadastrado com sucesso!");
    }
    
}
