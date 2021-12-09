package ArmAKerho;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import Kanta.RegularExpression;
import Kerho.Peluri;

/**
 * Kontrolleri pelureiden luonti-ikkunalle
 * 
 * @author Karel Parkkola
 * @version 28.10.2021
 * @version 15.11.2021
 * @version 29.11.2021
 * @version 9.12.2021
 */
public class PeluriLuontiController implements ModalControllerInterface<Peluri> {
    
    //Ikkunan sulkemista varten
    @FXML private TextField nimiTextField;
    @FXML private TextField pelaajaNimiTextField;
    @FXML private TextField tallennusTilaTextField;
    @FXML private TextField puhNumTextField;
    

    
    @Override
    public Peluri getResult() {
        return peluri;
    }
    
    
    
    @Override
    public void handleShown() {
        //
    }
    
    
    
    @Override
    public void setDefault(Peluri oletus) {
        //
    }
    
    
    /**
     * Hoitaa pelaajien lisäyksen
     */
    @FXML
    public void handleLisaa() {
        if (!tarkasta()) return;
        uusiPeluri();
        ModalController.closeStage(nimiTextField);
    }
    
    
    /**
     * Sulkee ikkunan ja eikä tallenna pelurin tietoja
     */
    @FXML
    public void handlePeruuta() {
        ModalController.closeStage(nimiTextField);
    }
    
    
    //------------------------------------------------------------------
    // Koodin sisäiset asiat--------------------------------------------
    //------------------------------------------------------------------
    
    /**
     * Peluri joka luodaan ja lopuksi palautetaan
     */
    private Peluri peluri;
    
    
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
        if(RegularExpression.regulaariExp("^\s*$", puhNumTextField.getText(), true)) puhNumTextField.setText("");
        if(!RegularExpression.regulaariExp("[^0-9\s\\+]", puhNumTextField.getText(), true)) {
            puhNumTextField.setStyle(null);
        } else {
            ok = false;
            puhNumTextField.setStyle("-fx-background-color: #F55959");
        }
        
        return ok;
    }
    
    
    /**
     * Tehdään uusi peluri joka voidaan lisätä kerhoon
     */
    private void uusiPeluri() {
        peluri = new Peluri(nimiTextField.getText(), pelaajaNimiTextField.getText(), tallennusTilaTextField.getText(), puhNumTextField.getText());
        peluri.rekisteroi();
        }
}
