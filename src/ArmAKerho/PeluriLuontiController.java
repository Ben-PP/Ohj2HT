package ArmAKerho;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;

import Kerho.Peluri;

/**
 * Kontrolleri pelureiden luonti-ikkunalle
 * 
 * @author Karel Parkkola
 * @version 28.10.2021
 * @version 15.11.2021
 * FIXME: Toimivaksi: Oikeilla tiedoilla toimivaksi
 */
public class PeluriLuontiController implements ModalControllerInterface<Peluri> {
    
    //Ikkunan sulkemista varten
    @FXML private TextField nimi;

    
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
        uusiPeluri();
        ModalController.closeStage(nimi);
    }
    
    
    /**
     * Sulkee ikkunan ja eikä tallenna pelurin tietoja
     */
    @FXML
    public void handlePeruuta() {
        ModalController.closeStage(nimi);
    }
    
    
    //------------------------------------------------------------------
    // Koodin sisäiset asiat--------------------------------------------
    //------------------------------------------------------------------
    
    /**
     * Peluri joka luodaan ja lopuksi palautetaan
     */
    private Peluri peluri;
    
    /**
     * Tehdään uusi peluri joka voidaan lisätä kerhoon
     * TODO: Testit: Pelurin luominen
     */
    private void uusiPeluri() {
        peluri = new Peluri();
        peluri.rekisteroi();
        peluri.taytaTestiTiedoilla(); // TODO: Toimivaksi: Pelurin luominen oikeilla tiedoilla
        
        
        }
}
