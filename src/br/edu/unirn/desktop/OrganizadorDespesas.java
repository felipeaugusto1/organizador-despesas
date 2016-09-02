package br.edu.unirn.desktop;

import br.edu.unirn.desktop.dao.CategoriaDao;
import br.edu.unirn.desktop.dao.LancamentoDao;
import br.edu.unirn.desktop.dao.UsuarioDao;
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
    
    static {
        lancamentoDao = new LancamentoDao();
        categoriaDao = new CategoriaDao();
        usuarioDao = new UsuarioDao();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
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
    
}
