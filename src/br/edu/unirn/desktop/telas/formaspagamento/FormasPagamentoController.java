package br.edu.unirn.desktop.telas.formaspagamento;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.FormaPagamento;
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
public class FormasPagamentoController implements Initializable {

    @FXML
    private TableView<FormaPagamento> tvFormasPagamento;
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TableColumn<FormaPagamento, String> tcNome;
    
    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnSalvar;
    
    public ObservableList<FormaPagamento> observableListFormasPagamento = FXCollections.observableArrayList();
    
    private FormaPagamento formaPagamento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formaPagamento = null;
        carregarListaFormasPagamento();
        
        tvFormasPagamento.setPlaceholder(new Label("Nenhuma forma de pagamento cadastrada."));
    }    
    
    private void carregarListaFormasPagamento() {
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        atualizarLista();
    }
    
    private void atualizarLista() {
        limparCampos();
        observableListFormasPagamento.clear();
        observableListFormasPagamento.addAll(OrganizadorDespesas.getFormaPagamentoDao().buscarFormasPagamentoPorUsuario(UsuarioSingleton.getInstancia().getUsuario()));
        tvFormasPagamento.setItems(observableListFormasPagamento);
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
    }
    
    @FXML
    public void btnSalvarFormaPagamento(ActionEvent event) {
        try {
            String forma = txtNome.getText();
            forma = forma.replaceAll(" ", "");
            
            if (!forma.isEmpty()) {
                if (formaPagamento == null)
                    formaPagamento = new FormaPagamento();
            
                formaPagamento.setNome(txtNome.getText());
                formaPagamento.setUsuario(UsuarioSingleton.getInstancia().getUsuario());

                if (formaPagamento.getId() == null) {
                    MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Forma de Pagamento", "Forma de pagamento cadastrada com sucesso!");
                    OrganizadorDespesas.getFormaPagamentoDao().salvar(formaPagamento);
                } else {
                    MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Forma de Pagamento", "Forma de pagamento atualizada com sucesso!");
                    OrganizadorDespesas.getFormaPagamentoDao().atualizar(formaPagamento);
                }

                formaPagamento = null;

                atualizarLista();
            } else {
                MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Forma de Pagamento", "Por favor, informe o nome da forma de pagamento.");
            }
            
        } catch (Exception e) {
            MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Forma de Pagamento", "Ocorreu um erro ao cadastrar a Forma de pagamento.");
        }
    }
    
    @FXML
    public void selecionarFormaPagamento(MouseEvent event) {
        formaPagamento = tvFormasPagamento.getSelectionModel().getSelectedItem();
        
        if (formaPagamento != null) {
            txtNome.setText(formaPagamento.getNome());
            
            exibirBtnDelete(true);
            setLabelBtnSalvar(CommonStrings.ATUALIZAR);
        }
    }
    
    @FXML
    public void btnDeletarFormaPagamento(ActionEvent event) {
        try {
            OrganizadorDespesas.getFormaPagamentoDao().remover(formaPagamento);
            formaPagamento = null;
            atualizarLista();
            exibirBtnDelete(false);
            MensagemUtils.exibirMensagem(Alert.AlertType.CONFIRMATION, "Forma de pagamento", "Forma de pagamento deletada com sucesso!");
        } catch (Exception e) {
            MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Forma de pagamento", "Não é possível deletar esta Forma de pagamento, pois existe(m) lançamento(s) associado(s) a ela.");
        }
    }
    
    @FXML
    public void btnLimparCampos(ActionEvent event) {
        limparCampos();
    }
    
    private void limparCampos() {
        formaPagamento = null;
        txtNome.setText("");
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
    }
    
    private void exibirBtnDelete(boolean exibir) {
        btnDeletar.setVisible(exibir);
    }
    
    private void setLabelBtnSalvar(String label) {
        btnSalvar.setText(label);
    }
    
}
