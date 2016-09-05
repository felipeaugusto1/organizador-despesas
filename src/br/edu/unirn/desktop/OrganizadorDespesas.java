package br.edu.unirn.desktop;

import br.edu.unirn.desktop.dao.CategoriaDao;
import br.edu.unirn.desktop.dao.FormaPagamentoDao;
import br.edu.unirn.desktop.dao.LancamentoDao;
import br.edu.unirn.desktop.dao.UsuarioDao;
import br.edu.unirn.desktop.modelos.Categoria;
import br.edu.unirn.desktop.singleton.UsuarioSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author felipe
 */
public class OrganizadorDespesas extends Application {
    
    private static LancamentoDao lancamentoDao;
    private static CategoriaDao categoriaDao;
    private static UsuarioDao usuarioDao;
    private static FormaPagamentoDao formaPagamentoDao;
    
    static {
        lancamentoDao = new LancamentoDao();
        categoriaDao = new CategoriaDao();
        usuarioDao = new UsuarioDao();
        formaPagamentoDao = new FormaPagamentoDao();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        
        //inserirCategorias();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    private void inserirCategorias() {
        if (getCategoriaDao().buscarCategoriaPessoal() == null) {
            getCategoriaDao().salvar(new Categoria("Pessoal", UsuarioSingleton.getInstancia().getUsuario()));
            getCategoriaDao().salvar(new Categoria("Transporte", UsuarioSingleton.getInstancia().getUsuario()));
            getCategoriaDao().salvar(new Categoria("Alimentação", UsuarioSingleton.getInstancia().getUsuario()));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static CategoriaDao getCategoriaDao() {
        return categoriaDao;
    }

    public static LancamentoDao getLancamentoDao() {
        return lancamentoDao;
    }

    public static UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public static FormaPagamentoDao getFormaPagamentoDao() {
        return formaPagamentoDao;
    }
    
}
