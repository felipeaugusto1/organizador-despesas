package br.edu.unirn.desktop.telas.listagemlancamentos;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.modelos.Lancamento;
import br.edu.unirn.desktop.modelos.TipoLancamento;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import br.edu.unirn.desktop.utils.CommonStrings;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class ListagemLancamentosController implements Initializable {
    
    @FXML
    private TableView<Lancamento> tbLancamentos;
    
    @FXML
    private TextField txtDescricao;
    
    @FXML
    private TextField txtValor;
    
    @FXML
    private TableColumn<Lancamento, Date> tcData;
    
    @FXML
    private TableColumn<Lancamento, Double> tcValor;
    
    @FXML
    private TableColumn<Lancamento, Categoria> tcCategoria;
    
    @FXML
    private TableColumn<Lancamento, TipoLancamento> tcTipo;
    
    @FXML
    private TableColumn<Lancamento, String> tcDescricao;
    
    @FXML
    private ComboBox<Categoria> comboCategoria;
    
    @FXML
    private ComboBox<String> comboTipoLancamento;
    
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnSalvar;
    
    private Lancamento lancamento;
    private final String RECEITA = "Receita";
    private final String DESPESA = "Despesa";
    
    public ObservableList<Lancamento> observableListLancamento = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> tipoLancamentos = new ArrayList<>();
        tipoLancamentos.add(RECEITA);
        tipoLancamentos.add(DESPESA);
        
        comboCategoria.getItems().addAll(OrganizadorDespesas.getCategoriaDao().listarTodos());
        comboTipoLancamento.getItems().addAll(tipoLancamentos);
        
        lancamento = null;
        
        carregarListaLancamentos();
    }   
    
    private void carregarListaLancamentos() {
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoLancamento"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        atualizarLista();
    }
    
    private void atualizarLista() {
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
        observableListLancamento.clear();
        observableListLancamento.addAll(OrganizadorDespesas.getLancamentoDao().listarLancamentosPorUsuario(UsuarioSingleton.getInstancia().getUsuario()));
        tbLancamentos.setItems(observableListLancamento);
    }
    
    @FXML
    public void btnSalvarLancamento(ActionEvent event) {
        if (lancamento == null)
            lancamento = new Lancamento();
            
        lancamento.setDescricao(txtDescricao.getText());
        lancamento.setValor(Double.parseDouble(txtValor.getText()));
        lancamento.setTipoLancamento(comboTipoLancamento.getSelectionModel().getSelectedItem().equals(RECEITA) ? TipoLancamento.RECEITA : TipoLancamento.DESPESA);
        lancamento.setCategoria(comboCategoria.getSelectionModel().getSelectedItem());
        lancamento.setUsuario(UsuarioSingleton.getInstancia().getUsuario());
        
        if (lancamento.getId() == null)
            OrganizadorDespesas.getLancamentoDao().salvar(lancamento);
        else
            OrganizadorDespesas.getLancamentoDao().atualizar(lancamento);
            
        
        lancamento = null;
        atualizarLista();
    }
    
    @FXML
    public void btnListarCategorias(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/listagemcategorias/ListagemCategorias.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Novo Lançamento");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void selecionarLancamento(MouseEvent event) {
        exibirBtnDelete(true);
        setLabelBtnSalvar(CommonStrings.ATUALIZAR);
        
        lancamento = tbLancamentos.getSelectionModel().getSelectedItem();
        
        txtDescricao.setText(lancamento.getDescricao());
        txtValor.setText(String.valueOf(lancamento.getValor()));
        comboCategoria.getSelectionModel().select(lancamento.getCategoria());
        comboTipoLancamento.getSelectionModel().select(lancamento.getTipoLancamento() == TipoLancamento.RECEITA ? RECEITA : DESPESA);
    }
    
    @FXML
    public void limparCampos(ActionEvent event) {
        lancamento = null;
        txtDescricao.setText("");
        txtValor.setText("");
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
    }
    
    @FXML
    public void deletarLancamento(ActionEvent event) {
        OrganizadorDespesas.getLancamentoDao().remover(lancamento);
        lancamento = null;
        atualizarLista();
    }
    
    @FXML
    public void visualizarContaUsuario(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/usuario/Usuario.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Usuário");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void sairAplicacao(ActionEvent event) {
        //UsuarioSingleton.getInstancia().sairAplicacao();
    }
    
    private void exibirBtnDelete(boolean exibir) {
        btnDeletar.setVisible(exibir);
    }
    
    private void setLabelBtnSalvar(String label) {
        btnSalvar.setText(label);
    }
    
}
