package ArmAKerho;

import java.util.ArrayList;
import java.util.List;

import Kerho.Paiva;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * Kontrolleri aika muokkaus ikkunalle
 * @author Karel
 * @version 9.11.2021
 * @version 15.11.2021
 * TODO: Kommentit koko dokumenttiin
 */
public class AikaMuokkausController implements ModalControllerInterface<String[][]> {
    
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
    
    
    @Override
    public String[][] getResult() {
        return ajatPalautus;
    }

    @Override
    public void handleShown() {
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
    }

    @Override
    public void setDefault(String[][] oletus) {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * Ajetaan kun painetaan ok nappia
     */
    @FXML
    public void handleOk() {
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
    
    //Taulukko päivistä joka palautetaan kun poistutaan 0= maanantai 6= sunnuntai
    
    private ArrayList<ArrayList<TextField>> ajat = new ArrayList<>();
    private String[][] ajatPalautus = new String[7][4];
    
    private void lisaaAjat() {
        for (int i = 0; i < ajatPalautus.length; i++) {
            for (int a = 0; a < ajatPalautus[i].length; a++) {
                ajatPalautus[i][a] = ajat.get(i).get(a).getText();
            }
        }
    }
}
