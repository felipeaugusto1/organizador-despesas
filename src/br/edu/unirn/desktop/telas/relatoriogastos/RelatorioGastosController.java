package br.edu.unirn.desktop.telas.relatoriogastos;

import br.edu.unirn.desktop.OrganizadorDespesas;
import br.edu.unirn.desktop.modelos.Lancamento;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

public class RelatorioGastosController implements Initializable {

    @FXML
    private AreaChart areaChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Lancamento> lancamentos = OrganizadorDespesas.getLancamentoDao().listarLancamentosDoTipoDespesa();
        
        XYChart.Series<Integer, Double> gastos = new XYChart.Series();
        gastos.setName("Gastos do mês");
        
        Calendar cal = Calendar.getInstance();
        
        lancamentos.stream().forEach((lancamento) -> {
            cal.setTime(lancamento.getDataAsDate());
            gastos.getData().add(new XYChart.Data(cal.get(Calendar.DAY_OF_MONTH), lancamento.getValor()));
        });
        
        areaChart.getData().addAll(gastos);
    }    
    
}
