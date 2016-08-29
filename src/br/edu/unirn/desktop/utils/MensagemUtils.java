package br.edu.unirn.desktop.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author felipe
 */
public class MensagemUtils {
    
    public static void exibirMensagem(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText("Organizador de Despesas");
        alert.setContentText(mensagem);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                
            }
        });
    }
    
}
