package br.edu.unirn.desktop.telas.listagemlancamentos;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.modelos.FormaPagamento;
import br.edu.unirn.desktop.modelos.Lancamento;
import br.edu.unirn.desktop.modelos.TipoLancamento;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import br.edu.unirn.desktop.utils.AppUtils;
import br.edu.unirn.desktop.utils.CommonStrings;
import br.edu.unirn.desktop.utils.MensagemUtils;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private TableColumn<Lancamento, FormaPagamento> tcFormaPagamento;
    
    @FXML
    private ComboBox<Categoria> comboCategoria;
    
    @FXML
    private ComboBox<FormaPagamento> comboFormaPagamento;
    
    @FXML
    private ComboBox<String> comboTipoLancamento;
    
    @FXML
    private Button btnDeletar;
    
    @FXML
    private Button btnSalvar;
    
    @FXML
    private Label txtSaldo;
    
    private Lancamento lancamento;
    public ObservableList<Lancamento> observableListLancamento = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> tipoLancamentos = new ArrayList<>();
        tipoLancamentos.add(TipoLancamento.RECEITA.getValor());
        tipoLancamentos.add(TipoLancamento.DESPESA.getValor());
        
        comboTipoLancamento.getItems().addAll(tipoLancamentos);
        comboTipoLancamento.getSelectionModel().select(tipoLancamentos.get(0));
        selecionarTipoLancamento();
        
        atualizarListaCategorias();
        atualizarListaFormasPagamento();
        
        lancamento = null;
        
        tbLancamentos.setPlaceholder(new Label("Nenhum lançamento cadastrado."));
        
        carregarListaLancamentos();
    }   
    
    private void carregarListaLancamentos() {
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoLancamento"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));
        
        atualizarLista();
    }
    
    private void atualizarLista() {
        List<Lancamento> lancamentos = OrganizadorDespesas.getLancamentoDao().listarLancamentosPorUsuario();
        
        txtSaldo.setText(String.valueOf(calcularSaldo(lancamentos)));
        limparCampos();
        exibirBtnDelete(false);
        setLabelBtnSalvar(CommonStrings.CADASTRAR);
        observableListLancamento.clear();
        observableListLancamento.addAll(lancamentos);
        tbLancamentos.setItems(observableListLancamento);
    }
    
    private double calcularSaldo(List<Lancamento> lancamentos) {
        double saldo = 0;
        
        for (Lancamento l : lancamentos) {
            if (l.getTipoLancamento().equals(TipoLancamento.RECEITA.getValor()))
                saldo += l.getValor();
            else
                saldo -= l.getValor();
        }
        
        return saldo;
    }
    
    @FXML
    public void btnSalvarLancamento(ActionEvent event) {
        String descricao = txtDescricao.getText();
        descricao = descricao.replaceAll(" ", "");
        
        String valor = txtValor.getText();
        valor = valor.replaceAll(" ", "");
        
        Categoria categoria = comboCategoria.getSelectionModel().getSelectedItem();
        FormaPagamento formaPagamento = comboFormaPagamento.getSelectionModel().getSelectedItem();
        
        String tipoLancamento = comboTipoLancamento.getSelectionModel().getSelectedItem();
        
        if (!descricao.isEmpty() && !valor.isEmpty() && categoria != null && ((tipoLancamento.equals(TipoLancamento.DESPESA.getValor()) && formaPagamento != null) || tipoLancamento.equals(TipoLancamento.RECEITA.getValor()))) {
            if (lancamento == null)
                lancamento = new Lancamento();

            try {
                lancamento.setDescricao(txtDescricao.getText());
                lancamento.setValor(Double.parseDouble(txtValor.getText()));
                lancamento.setTipoLancamento(tipoLancamento.equals(TipoLancamento.RECEITA.getValor()) ? TipoLancamento.RECEITA : TipoLancamento.DESPESA);
                lancamento.setCategoria(comboCategoria.getSelectionModel().getSelectedItem());
                lancamento.setUsuario(UsuarioSingleton.getInstancia().getUsuario());
                lancamento.setFormaPagamento(tipoLancamento.equals(TipoLancamento.RECEITA.getValor()) ? null : comboFormaPagamento.getSelectionModel().getSelectedItem());

                if (lancamento.getId() == null)
                    OrganizadorDespesas.getLancamentoDao().salvar(lancamento);
                else
                    OrganizadorDespesas.getLancamentoDao().atualizar(lancamento);

                lancamento = null;
                atualizarLista();
            } catch (NumberFormatException e) {
                MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Lançamento", "Digite o valor corretamente. Ex: 100 ou 100.00");
            }
        } else {
            MensagemUtils.exibirMensagem(Alert.AlertType.ERROR, "Lançamento", "Por favor, preencha todos os campos corretamente.");
        }
    }
    
    @FXML
    public void btnListarCategorias(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/listagemcategorias/ListagemCategorias.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Categorias");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void btnListarFormasPagamento(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/formaspagamento/FormasPagamento.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Formas de Pagamento");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void selecionarLancamento(MouseEvent event) {
        lancamento = tbLancamentos.getSelectionModel().getSelectedItem();
        
        if (lancamento != null) {
            txtDescricao.setText(lancamento.getDescricao());
            txtValor.setText(String.valueOf(lancamento.getValor()));
            comboCategoria.getSelectionModel().select(lancamento.getCategoria());
            comboTipoLancamento.getSelectionModel().select(lancamento.getTipoLancamento());
            comboFormaPagamento.getSelectionModel().select(lancamento.getFormaPagamento());
            
            exibirBtnDelete(true);
            setLabelBtnSalvar(CommonStrings.ATUALIZAR);
        }
    }
    
    @FXML
    private void selecionarTipoLancamento() {
        String tipoLancamento = comboTipoLancamento.getSelectionModel().getSelectedItem();
        
        if (tipoLancamento.equals(TipoLancamento.RECEITA.getValor()))
            comboFormaPagamento.setDisable(true);
        else
            comboFormaPagamento.setDisable(false);
    }
    
    @FXML
    public void limparCampos(ActionEvent event) {
        limparCampos();
    }
    
    private void limparCampos() {
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
    public void btnVisualizarRelatorioMensal(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/relatoriogastos/RelatorioGastos.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Relatório de Gastos Mensal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void sairAplicacao(ActionEvent event) {
        UsuarioSingleton.getInstancia().setUsuario(null);
        AppUtils.fecharTela(txtDescricao);
        
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/FXMLMain.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Categorias");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void exibirBtnDelete(boolean exibir) {
        btnDeletar.setVisible(exibir);
    }
    
    private void setLabelBtnSalvar(String label) {
        btnSalvar.setText(label);
    }
    
    private void atualizarListaCategorias() {
        List<Categoria> categorias = OrganizadorDespesas.getCategoriaDao().buscarCategoriasPorUsuario();
        
        comboCategoria.getItems().clear();
        comboCategoria.getItems().addAll(categorias);
            
        if (categorias.size() > 0)
            comboCategoria.getSelectionModel().select(categorias.get(0));
    }
    
    private void atualizarListaFormasPagamento() {
        List<FormaPagamento> formasPagamento = OrganizadorDespesas.getFormaPagamentoDao().buscarFormasPagamentoPorUsuario();
        
        comboFormaPagamento.getItems().clear();
        comboFormaPagamento.getItems().addAll(formasPagamento);
            
        if (formasPagamento.size() > 0)
            comboFormaPagamento.getSelectionModel().select(formasPagamento.get(0));
    }
    
    @FXML
    public void atualizarListaCategorias(MouseEvent event) {
        atualizarListaCategorias();
    }
    
    @FXML
    public void atualizarListaFormasPagamento(MouseEvent event) {
        atualizarListaFormasPagamento();
    }
    
}
