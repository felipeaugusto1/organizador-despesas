package br.edu.unirn.desktop.telas.listagemcategorias;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import br.edu.unirn.desktop.utils.CommonStrings;
import br.edu.unirn.desktop.utils.MensagemUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class ListagemCategoriasController implements Initializable {

    @FXML
    private TableView<Categoria> tvCategorias;
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TableColumn<Categoria, String> tcNome;
    
    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnSalvar;
    
    private Categoria categoria;
    public ObservableList<Categoria> observableListCategoria = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoria = null;
        carregarListaCategorias();
        
        tvCategorias.setPlaceholder(new Label("Nenhuma categoria cadastrada."));
    }  
    
    private void carregarListaCategorias() {
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        atualizarLista();
    }
    
    private void atualizarLista() {
        limparCampos();
        observableListCategoria.clear();
        observableListCategoria.addAll(OrganizadorDespesas.getCategoriaDao().buscarCategoriasPorUsuario(UsuarioSingleton.getInstancia().getUsuario()));
        tvCategorias.setItems(observableListCategoria);
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
    }
    
    @FXML
    public void btnSalvarCategoria(ActionEvent event) {
        try {
            String nomeCategoria = txtNome.getText();
            nomeCategoria = nomeCategoria.replaceAll(" ", "");
            
            if (!nomeCategoria.isEmpty()) {
                if (categoria == null)
                    categoria = new Categoria();
            
                categoria.setNome(txtNome.getText());
                categoria.setUsuario(UsuarioSingleton.getInstancia().getUsuario());

                if (categoria.getId() == null) {
                    MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Categoria", "Categoria cadastrada com sucesso!");
                    OrganizadorDespesas.getCategoriaDao().salvar(categoria);
                } else {
                    MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Categoria", "Categoria atualizada com sucesso!");
                    OrganizadorDespesas.getCategoriaDao().atualizar(categoria);
                }

                categoria = null;

                atualizarLista();
            } else {
                MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Categoria", "Por favor, informe o nome da categoria.");
            }
        } catch (Exception e) {
            MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Categoria", "Ocorreu um erro ao cadastrar a categoria.");
        }
    }
    
    @FXML
    public void selecionarCategoria(MouseEvent event) {
        categoria = tvCategorias.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            txtNome.setText(categoria.getNome());
            
            exibirBtnDelete(true);
            setLabelBtnSalvar(CommonStrings.ATUALIZAR);
        }
    }
    
    @FXML
    public void btnLimparCampos(ActionEvent event) {
        limparCampos();
    }
    
    private void limparCampos() {
        categoria = null;
        txtNome.setText("");
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
    }
    
    @FXML
    public void btnDeletarCategoria(ActionEvent event) {
        try {
            OrganizadorDespesas.getCategoriaDao().remover(categoria);
            categoria = null;
            atualizarLista();
            exibirBtnDelete(false);
            MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Categoria", "Categoria deletada com sucesso!");
        } catch (Exception e) {
            MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Categoria", "Não é possível deletar esta categoria, pois existe(m) lançamento(s) associado(s) a ela.");
        }
    }
    
    private void exibirBtnDelete(boolean exibir) {
        btnDeletar.setVisible(exibir);
    }
    
    private void setLabelBtnSalvar(String label) {
        btnSalvar.setText(label);
    }
}
