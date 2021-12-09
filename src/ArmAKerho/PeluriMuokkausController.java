package ArmAKerho;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import Kanta.RegularExpression;
import Kerho.Peluri;


/**
 * Käsittelee Pelurin tietojen muokkausikkunan tapahtumat
 * @author Karel
 * @version 29.11.2021
 * @version 1.12.2021
 * @version 9.12.2021
 */
public class PeluriMuokkausController implements ModalControllerInterface<String[]> {
    
    @FXML TextField nimiTextField;
    @FXML TextField pelaajaNimiTextField;
    @FXML TextField tallennusTilaTextField;
    @FXML TextField puhNumTextField;
    

    @Override
    public String[] getResult() {
        return muutetut;
    }
    

    @Override
    public void handleShown() {
        //
    }
    

    @Override
    public void setDefault(String[] oletus) {
        //
    }
    
    
    /**
     * Kun ok nappia painetaan
     */
    @FXML
    public void handleOk() {
        if(!tarkasta()) return;
        keraaTiedot();
        ModalController.closeStage(nimiTextField);
    }
    
    
    /**
     * Kun peruuta nappia painetaan
     */
    @FXML
    public void handleCancel() {
        ModalController.closeStage(nimiTextField);
    }
    
    //===================================================================================
    //=====================Ei FXML=======================================================
    //===================================================================================
    
    
    private String[] muutetut = new String[4];
    
    
    /**
     * kerää tiedot textFieldeistä taulukkoon
     */
    private void keraaTiedot() {
        muutetut[0] = nimiTextField.getText();
        muutetut[1] = pelaajaNimiTextField.getText();
        muutetut[2] = tallennusTilaTextField.getText();
        muutetut[3] = puhNumTextField.getText();
    }
    
    
    /**
     * Tarkastaa kenttien oikeellisuuden
     * @return true jos kentät ovat kunnossa, false jos ei
     */
    private boolean tarkasta() {
        boolean ok = true;
        
        //Tarkastaa Nimi-kentän. regulaariExp antaa true vain jos kentää ei sisällä mitään muita merkkejä kuin
        //luetellut. Case insesitive.
        if (!RegularExpression.regulaariExp("[^abcdefghijklmnopqrstuvwxyzoäöå\s-]|^\s*$", nimiTextField.getText(), true)) {
            nimiTextField.setStyle(null);
        } else {
            ok = false;
            nimiTextField.setStyle("-fx-background-color: #F55959");
        }
        
        //Tarkastaa pelaaja-nimi-kentän. Täytyy sisältää vähintään 1 merkin. Välilyönti ei kelpaa.
        //Myös | merkki hylätään sillä sitä käytetään tiedostoissa.
        if (!RegularExpression.regulaariExp("^\s*$|[\\|]", pelaajaNimiTextField.getText(), true)) {
            pelaajaNimiTextField.setStyle(null);
        } else {
            ok = false;
            pelaajaNimiTextField.setStyle("-fx-background-color: #F55959");
        }
        
        //Tarkastaa T-tila-kentän. Saa ainoastaan sisältää kokonaisnumeroita tai olla tyhjä
        if(RegularExpression.regulaariExp("^\s*$", tallennusTilaTextField.getText(), true)) tallennusTilaTextField.setText("");
        if (!RegularExpression.regulaariExp("[^0-9]", tallennusTilaTextField.getText(), true)) {
            tallennusTilaTextField.setStyle(null);
        } else {
            ok = false;
            tallennusTilaTextField.setStyle("-fx-background-color: #F55959");
        }
        
        //Tarkastaa puhelin kentän. Saa sisältää numeroita, välilyöntejä ja + merkin.
        if(RegularExpression.regulaariExp("\s+$", puhNumTextField.getText(), true)) puhNumTextField.setText("");
        if(!RegularExpression.regulaariExp("[^0-9\s\\+]", puhNumTextField.getText(), true)) {
            puhNumTextField.setStyle(null);
        } else {
            ok = false;
            puhNumTextField.setStyle("-fx-background-color: #F55959");
        }
        
        return ok;
    }
    
    
    /**
     * Alustaa ikkunan
     * @param peluri peluri jolta tiedot otetaan
     */
    public void alusta(Peluri peluri) {
        String nimi = peluri.getNimi();
        nimiTextField.setText(nimi);
        muutetut[0] = nimi;
        
        String pNimi = peluri.getPNimi();
        pelaajaNimiTextField.setText(pNimi);
        muutetut[1] = pNimi;
        
        String tTila;
        if(tallennusTilaTextField.getText() != null) {
            tTila = Integer.toString(peluri.getTTila());
            tallennusTilaTextField.setText(tTila);
            muutetut[2] = tTila;
        } else {
            tallennusTilaTextField.setText("0");
            muutetut[2] = "0";
        }
        
        String puh = peluri.getPuh();
        puhNumTextField.setText(puh);
    }

}
