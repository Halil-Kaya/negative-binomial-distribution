/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author hlk
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private ScatterChart<?, ?> scatterChart;

    @FXML
    private Button buton;

    @FXML
    private TextField basariSayisi;

    @FXML
    private TextField denemeSayisi;

    @FXML
    private TextField basariOlasiligi;

    @FXML
    private Text ex;

    @FXML
    private Text variance;

    @FXML
    private Text ss;


    static double combination(double n, double r) {
        return fact(n) / (fact(r) * fact(n - r));
    }

    static double fact(double n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res = res * i;
        }
        return res;
    }

    XYChart.Series basarili = new XYChart.Series();
    XYChart.Series basarisiz = new XYChart.Series();

    public void pxHesapla() {
        basarili.getData().clear();
        basarisiz.getData().clear();
        double basaris = Double.parseDouble(basariSayisi.getText());
        double denemes = Double.parseDouble(denemeSayisi.getText());
        double basario = Double.parseDouble(basariOlasiligi.getText());

        for (double i = 0; i < denemes; i++) {
            String px = "" + (combination(denemes - 1, i - 1) * Math.pow(basario, i) * Math.pow(1 - basario, denemes - i));
            if (i < basaris) {
                basarili.getData().add(new XYChart.Data((i + 1), formatliGoster(px)));
            } else if (i >= basaris) {
                basarisiz.getData().add(new XYChart.Data((i + 1), formatliGoster(px)));
            }
        }
        scatterChart.getData().add(basarili);
        scatterChart.getData().add(basarisiz);
    }

    public void exHesapla() {
        double basaris = Double.parseDouble(basariSayisi.getText());
        double basario = Double.parseDouble(basariOlasiligi.getText());
        String exDegeri = "" + basaris / basario;
        

        ex.setText("μ=E(X)= " + formatliGoster(exDegeri));
    }

    public double formatliGoster(String sayi) {

        String formatliSayi = "";
        int uzunluk = sayi.length();
        for (int j = 0; j < uzunluk; j++) {

            if (j < sayi.length()) {

                if (sayi.charAt(j) == '.') {
                    uzunluk = j + 3;
                }
                formatliSayi += sayi.charAt(j);
            }
        }
        
        return Double.valueOf(formatliSayi);
        
    }

    public void varHesapla() {
        double basaris = Double.parseDouble(basariSayisi.getText());
        double basario = Double.parseDouble(basariOlasiligi.getText());
        String varDegeri =""+ (basaris * (1 - basario)) / Math.pow(basario, 2);
        
        variance.setText("σ2=Var(X)= " + formatliGoster(varDegeri));
    }

    public void standartSapmaHesapla() {
        double basaris = Double.parseDouble(basariSayisi.getText());
        double basario = Double.parseDouble(basariOlasiligi.getText());
        double varDegeri = (basaris * (1 - basario)) / Math.pow(basario, 2);
        String ssDegeri =""+ Math.sqrt(varDegeri);
        
        ss.setText("σ=SD(X)= " + formatliGoster(ssDegeri));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        basarili.setName("Başarılı");
        basarisiz.setName("Başarısız");

        buton.setOnMouseClicked(event -> {

            pxHesapla();
            exHesapla();
            varHesapla();
            standartSapmaHesapla();
            
        });

    }

}
