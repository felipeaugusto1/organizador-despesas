package br.edu.unirn.desktop.telas.listagemlancamentos;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.modelos.Lancamento;
import br.edu.unirn.desktop.modelos.TipoLancamento;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class ListagemLancamentosController implements Initializable {
    
    @FXML
    private TableView tbLancamentos;
    
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
    
    public ObservableList<Lancamento> observableListLancamento = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarListaLancamentos();
    }   
    
    private void carregarListaLancamentos() {
        tcData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tcCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("tipoLancamento"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        observableListLancamento.addAll(OrganizadorDespesas.getLancamentoDao().listarTodos());
        tbLancamentos.setItems(observableListLancamento);
    }
    
    @FXML
    public void btnNovoLancamento(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/unirn/desktop/telas/novolancamento/NovoLancamento.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Novo Lançamento");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void btnListarCategorias(ActionEvent event) {
        
    }
    
    @FXML
    public void btnVisualizarCategorias(ActionEvent event) {
        
    }
    
}
