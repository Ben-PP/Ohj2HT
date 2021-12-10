package ArmAKerho;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import Kerho.Paiva;

import Kanta.RegularExpression;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;


/**
 * Kontrolleri aika muokkaus ikkunalle
 * @author Karel
 * @version 9.11.2021
 * @version 15.11.2021
 * @version 28.11.2021
 * @version 29.11.2021
 * @version 9.12.2021
 */
public class AikaMuokkausController implements ModalControllerInterface<String[][]> {
    
    //textfieldit joihin pelaajan peliajat kirjoitetaan
    @FXML private TextField maAlkuH;
    @FXML private TextField maAlkuM;
    @FXML private TextField maLoppuH;
    @FXML private TextField maLoppuM;
    @FXML private TextField tiAlkuH;
    @FXML private TextField tiAlkuM;
    @FXML private TextField tiLoppuH;
    @FXML private TextField tiLoppuM;
    @FXML private TextField keAlkuH;
    @FXML private TextField keAlkuM;
    @FXML private TextField keLoppuH;
    @FXML private TextField keLoppuM;
    @FXML private TextField toAlkuH;
    @FXML private TextField toAlkuM;
    @FXML private TextField toLoppuH;
    @FXML private TextField toLoppuM;
    @FXML private TextField peAlkuH;
    @FXML private TextField peAlkuM;
    @FXML private TextField peLoppuH;
    @FXML private TextField peLoppuM;
    @FXML private TextField laAlkuH;
    @FXML private TextField laAlkuM;
    @FXML private TextField laLoppuH;
    @FXML private TextField laLoppuM;
    @FXML private TextField suAlkuH;
    @FXML private TextField suAlkuM;
    @FXML private TextField suLoppuH;
    @FXML private TextField suLoppuM;
    
    @FXML private HBox maanantai;
    @FXML private HBox tiistai;
    @FXML private HBox keskiviikko;
    @FXML private HBox torstai;
    @FXML private HBox perjantai;
    @FXML private HBox lauantai;
    @FXML private HBox sunnuntai;
    
    @FXML private Label virheLabel1;
    @FXML private Label virheLabel2;

    
    @Override
    public String[][] getResult() {
        return ajatPalautus;
    }
    
    
    @Override
    public void handleShown() {
        //tehdään 2 ulotteinen lista textfieldeistä helpottamaan käsittelyä
        ajat.add(new ArrayList<TextField>(List.of(
                maAlkuH,
                maAlkuM,
                maLoppuH,
                maLoppuM
                )));
        
        ajat.add(new ArrayList<TextField>(List.of(
                tiAlkuH,
                tiAlkuM,
                tiLoppuH,
                tiLoppuM
                )));
        
        ajat.add(new ArrayList<TextField>(List.of(
                keAlkuH,
                keAlkuM,
                keLoppuH,
                keLoppuM
                )));
        
        ajat.add(new ArrayList<TextField>(List.of(
                toAlkuH,
                toAlkuM,
                toLoppuH,
                toLoppuM
                )));
        
        ajat.add(new ArrayList<TextField>(List.of(
                peAlkuH,
                peAlkuM,
                peLoppuH,
                peLoppuM
                )));
        
        ajat.add(new ArrayList<TextField>(List.of(
                laAlkuH,
                laAlkuM,
                laLoppuH,
                laLoppuM
                )));
        
        ajat.add(new ArrayList<TextField>(List.of(
                suAlkuH,
                suAlkuM,
                suLoppuH,
                suLoppuM
                )));
        
        paivat.add(maanantai);
        paivat.add(tiistai);
        paivat.add(keskiviikko);
        paivat.add(torstai);
        paivat.add(perjantai);
        paivat.add(lauantai);
        paivat.add(sunnuntai);
        
        //Alustetaan aikamuokkausikkunan arvot joko null tai ennestään olevalle arvolle
        for (int i = 0; i < pelurinAjat.length; i++) {
            for (int a = 0; a < pelurinAjat[i].length; a++) {
                String aika = pelurinAjat[i][a];
                ajat.get(i).get(a).setText(aika);
            }
        }
    }

    
    @Override
    public void setDefault(String[][] oletus) {
        //ei käytetä
    }
    
    
    /**
     * Ajetaan kun painetaan ok nappia
     */
    @FXML
    public void handleOk() {
        
        if (!tarkasta()) return;
        lisaaAjat();
        
        ModalController.closeStage(keAlkuH);
    }
    
    
    /**
     * Sulkee ikkunan tallentamatta
     */
    @FXML
    public void handleCancel() {
        ModalController.closeStage(keAlkuH);
    }

    
    //===============================================================================
    //================Ei FXML========================================================
    //===============================================================================
    
    
    //2 ulotteinen lista textfieldeistä
    private ArrayList<ArrayList<TextField>> ajat = new ArrayList<>();
    
    //2 ulotteinen taulukko ajoista jotka palautetaan kun ikkuna suljetaan
    private String[][] ajatPalautus = new String[7][4];
    
    // 2 ulotteinen taulukko pelurin ajoista joilla ikkuna alustetaan
    private String[][] pelurinAjat = new String[7][4];
    
    private ArrayList<HBox> paivat = new ArrayList<HBox>();
    
    
    /**
     * Lisää ajat ajatPalautus taulukkoon textfieldeistä
     */
    private void lisaaAjat() {
        for (int i = 0; i < ajatPalautus.length; i++) {
            for (int a = 0; a < ajatPalautus[i].length; a++) {
                ajatPalautus[i][a] = ajat.get(i).get(a).getText();
                if(ajatPalautus[i][a] == "") ajatPalautus[i][a] = null;
            }
        }
    }
    
    
    /**
     * Asettaa pelurinAjat taulukkoon ajat joilla ikkuna alustetaan
     * @param paivat Lista pelurin päivistä
     */
    public void setAjat(List<Paiva> paivat) {
        for (int i = 0; i < paivat.size(); i++) {
            pelurinAjat[i][0] = paivat.get(i).getAlkuAika(false);
            pelurinAjat[i][1] = paivat.get(i).getAlkuAika(true);
            pelurinAjat[i][2] = paivat.get(i).getLoppuAika(false);
            pelurinAjat[i][3] = paivat.get(i).getLoppuAika(true);
        }
    }
    
    
    /**
     * Tarkistaa ovatko kaikki aikakentät oikeassa muodossa
     * @return true jos kaikki kentät ovat oikein, muuten false
     */
    private boolean tarkasta() {
        String p;

        boolean ok = true;
        boolean ok2 = true;
        
        int paiva = 0;
        
        for (ArrayList<TextField> lista : ajat) {
            
            int alkuAikaH = 00;
            int alkuAikaM = 00;
            int loppuAikaH = 00;
            int loppuAikaM = 00;
            
            int i = 1;
            for (TextField txtField : lista) {
                
                if(txtField.getText().equals("")) txtField.setText("00");
                String txt = txtField.getText();
                
                if (RegularExpression.regulaariExp("[^0-9]", txt, true)) {
                    ok = false;
                    txtField.setStyle("-fx-background-color: #F55959;");
                    System.out.println("NoMatch");
                    virheLabel1.setText("Anna ajat \"HH:MM\"!");
                    virheLabel1.setStyle("-fx-background-color: #F55959; -fx-font-size: 14; -fx-font-weight: bold");
                    txtField.setText("00");
                } else {
                    txtField.setStyle(null);
                }
                
                //Selvittää onko kyseessä tunti vai minuutti
                if (RegularExpression.regulaariExp("H", txtField.getId(), true)) {
                    p = "^0[0-9]$|^1[0-9]$|^2[0-4]$";
                    if (i == 1) alkuAikaH = Integer.parseInt(txtField.getText().toString());
                    if (i == 3) loppuAikaH = Integer.parseInt(txtField.getText().toString());
                } else {
                    p = "^[0-5][0-9]$";
                    if (i == 2) alkuAikaM = Integer.parseInt(txtField.getText().toString());
                    if (i == 4) loppuAikaM = Integer.parseInt(txtField.getText().toString());
                }
                
                //Tutkii onko annettu kellonaika laillinen
                if (RegularExpression.regulaariExp(p, txt, true)) {
                    txtField.setStyle(null);
                    System.out.println("Match");
                } else if (RegularExpression.regulaariExp("^0$", txt, true)) {
                    txtField.setText("00");
                } else if (RegularExpression.regulaariExp("^[1-9]$", txt, true)) {
                    txtField.setText(0+txt);
                } else {
                    ok = false;
                    txtField.setStyle("-fx-background-color: #F55959;");
                    System.out.println("NoMatch");
                    virheLabel1.setText("Anna ajat \"HH:MM\"!");
                    virheLabel1.setStyle("-fx-background-color: #F55959; -fx-font-size: 14; -fx-font-weight: bold");
                }
                i++;
            }
            int onkoEnnen = tarkastaAika(alkuAikaH, alkuAikaM, loppuAikaH, loppuAikaM);
            if(onkoEnnen == 1) {
                virheLabel2.setText("Loppuaika on ennen alkua!");
                virheLabel2.setStyle("-fx-background-color: #F55959; -fx-font-size: 14; -fx-font-weight: bold");
                paivat.get(paiva).setStyle("-fx-background-color: #F55959");
                ok2 = false;
            }
            if(onkoEnnen <= 0) paivat.get(paiva).setStyle(null);
            paiva++;
        }
        if (ok) virheLabel1.setText("");
        if (ok2) virheLabel2.setText("");
        
        if(ok && ok2) return true;
        return false;
    }
    
    
    /**
     * Tarkastaa onko alkuaika ennen loppuaikaa
     * @param aH alku tunti
     * @param aM alku minuutti
     * @param lH loppu tunti
     * @param lM loppu minuutti
     * @return -1 jos alkuaika on ennen loppuaikaa, 0 jos ovat samat ja 1 jos alkuaika on loppuajan jälkeen
     */
    private int tarkastaAika(int aH, int aM, int lH, int lM) {
        if (aH == lH && aM == lM) return 0;
        if (aH < lH || (aH == lH && aM <= lM)) return -1;
        
        return 1;
    }
}
