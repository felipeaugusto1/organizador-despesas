package br.edu.unirn.desktop.telas.novolancamento;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.dao.LancamentoDao;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.modelos.Lancamento;
import br.edu.unirn.desktop.modelos.TipoLancamento;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author felipe
 */
public class NovoLancamentoController implements Initializable {
    
    @FXML
    private TextArea txtDescricao;
    
    @FXML
    private TextField txtValor;
    
    @FXML
    private RadioButton rbReceita;
    
    @FXML
    private RadioButton rbDespesa;
    
    @FXML
    private ComboBox<Categoria> comboCategoria;
    
    @FXML
    private ToggleGroup grupo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categoria> categorias = OrganizadorDespesas.getCategoriaDao().listarTodos();
        //categorias.add(new Categoria("cat 1"));
        //categorias.add(new Categoria("cat 2"));
        //categorias.add(new Categoria("cat 3"));
        
        
        
        comboCategoria.getItems().addAll(categorias);
    }    
    
    @FXML
    public void btnSalvarLancamento(ActionEvent event) {
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao(txtDescricao.getText());
        lancamento.setValor(Double.parseDouble(txtValor.getText()));
        lancamento.setTipoLancamento(grupo.getSelectedToggle().toString().equalsIgnoreCase("receita") ? TipoLancamento.RECEITA : TipoLancamento.DESPESA);
        lancamento.setCategoria(comboCategoria.getSelectionModel().getSelectedItem());
        
        System.out.println("categoria: " +comboCategoria.getSelectionModel().getSelectedItem());
        
        OrganizadorDespesas.getLancamentoDao().salvar(lancamento);
    }
}
