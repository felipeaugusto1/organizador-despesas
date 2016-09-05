package br.edu.unirn.desktop.utils;

import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 *
 * @author felipe
 */
public class AppUtils {

    public static void fecharTela(Control elementoTela) {
        ((Stage) elementoTela.getScene().getWindow()).close();
    }
    
}
