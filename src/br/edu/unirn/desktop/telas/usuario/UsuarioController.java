package br.edu.unirn.desktop.telas.usuario;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Usuario;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import br.edu.unirn.desktop.utils.AppUtils;
import br.edu.unirn.desktop.utils.CommonStrings;
import br.edu.unirn.desktop.utils.MensagemUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class UsuarioController implements Initializable {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private TextField txtSenha;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Button btnExcluirConta;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();
        
        if (usuario == null) {
            exibirBtnExcluirConta(false);
            setLabelBtnSalvar(CommonStrings.CADASTRAR);
        } else {
            exibirBtnExcluirConta(true);
            setLabelBtnSalvar(CommonStrings.ATUALIZAR);
            
            txtNome.setText(usuario.getNome());
            txtEmail.setText(usuario.getEmail());
            txtUsuario.setText(usuario.getUsuario());
            txtSenha.setText(usuario.getSenha());
        }
    }    

    @FXML
    public void btnSalvarUsuario(ActionEvent event) {
        Usuario usuario = UsuarioSingleton.getInstancia().getUsuario();
        if (usuario != null) {
            usuario.setNome(txtNome.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setUsuario(txtUsuario.getText());
            usuario.setSenha(txtSenha.getText());
            
            UsuarioSingleton.getInstancia().setUsuario(usuario);
            OrganizadorDespesas.getUsuarioDao().atualizar(usuario);
            
            MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Usuário", "Usuário atualizado com sucesso!");
        } else {
            usuario = new Usuario();
            
            usuario.setNome(txtNome.getText());
            usuario.setEmail(txtEmail.getText());
            usuario.setUsuario(txtUsuario.getText());
            usuario.setSenha(txtSenha.getText());
            
            OrganizadorDespesas.getUsuarioDao().salvar(usuario);
            
            MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Usuário", "Usuário cadastrado com sucesso!");
            
            AppUtils.fecharTela(txtNome);
        }
    }
    
    @FXML
    public void btnExcluirConta(ActionEvent event) {
        try {
            OrganizadorDespesas.getUsuarioDao().remover(UsuarioSingleton.getInstancia().getUsuario());
            UsuarioSingleton.getInstancia().setUsuario(null);
            AppUtils.fecharTela(txtNome);
            MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Usuário", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Usuário", "Ocorreu algum erro ao excluir o usuário.");
        }
    }
    
    private void exibirBtnExcluirConta(boolean exibir) {
        btnExcluirConta.setVisible(exibir);
    }
    
    private void setLabelBtnSalvar(String label) {
        btnSalvar.setText(label);
    }
}
