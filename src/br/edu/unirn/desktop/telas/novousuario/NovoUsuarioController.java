package br.edu.unirn.desktop.telas.novousuario;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Usuario;
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
        try {
            Usuario usuario = new Usuario(txtNome.getText(), txtEmail.getText(), txtUsuario.getText(), txtSenha.getText());
        OrganizadorDespesas.getUsuarioDao().salvar(usuario);
        MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Novo Usuário", "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Novo Usuário", "Ocorreu algum erro ao realizar o cadastro.");
        }
        
    }
    
}
