/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unirn.desktop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author felipe
 */
public class DateUtils {
    
    public static String formatarData(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }
    
}
