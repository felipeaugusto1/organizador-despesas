package br.edu.unirn.desktop;

import br.edu.unirn.desktop.modelos.Usuario;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import br.edu.unirn.desktop.utils.AppUtils;
import br.edu.unirn.desktop.utils.MensagemUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
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
    private PasswordField txtSenha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void btnLogin(ActionEvent event) {
        try {
            Usuario u = OrganizadorDespesas.getUsuarioDao().login(txtUsuario.getText(), txtSenha.getText());
            
            if (u != null) {
                UsuarioSingleton.getInstancia().setUsuario(u);
                
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/listagemlancamentos/ListagemLancamentos.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Organizador de Despesas");
                stage.setScene(new Scene(root));
                stage.show();
                
                AppUtils.fecharTela(txtSenha);
            } else {
                MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Usuário Inválido", "Usuario ou senha inválidos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void clickNovoCadastro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/usuario/Usuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Novo Usuário");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
