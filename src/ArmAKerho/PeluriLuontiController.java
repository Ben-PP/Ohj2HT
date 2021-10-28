package ArmAKerho;

import Kerho.Peluri;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * 
 * @author Karel Parkkola
 * @version 28.10.2021
 * TODO: Muista päivittää versio!
 * 
 * Kontrolleri pelureiden luonti-ikkunalle
 *
 */
public class PeluriLuontiController implements ModalControllerInterface<Peluri> {
    // TODO: Kaikki toiminnot joita pelaajan luomiseen tarvitaan
    
    @FXML private TextField nimi;

    
    @Override
    public Peluri getResult() {
        return peluri;
    }
    
    
    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }
    
    
    
    @Override
    public void setDefault(Peluri oletus) {
        // TODO Auto-generated method stub
        
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
    
    private Peluri peluri;
    
    /**
     * Tehdään uusi peluri joka voidaan lisätä kerhoon
     */
    private void uusiPeluri() {
        peluri = new Peluri();
        peluri.rekisteroi();
        peluri.taytaTestiTiedoilla(); // TODO: korvaa aikanaan oikeilla tiedoilla
        
        
        }
}
