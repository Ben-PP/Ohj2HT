package ArmAKerho;

import Kerho.Paiva;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TestiController implements ModalControllerInterface<Paiva[]>{
    
    @Override
    public Paiva[] getResult() {
        return paivat;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Paiva[] oletus) {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * Ajetaan kun painetaan ok nappia
     */
    @FXML
    public void handleOk() {
        //

    }
    
    
    /**
     * Sulkee ikkunan tallentamatta
     */
    @FXML
    public void handleCancel() {
        //
    }

    //===============================================================================
    //================Ei FXML========================================================
    //===============================================================================
    
    //Taulukko päivistä 0= maanantai 6= sunnuntai
    private Paiva[] paivat = new Paiva[7];
    
    
    private String[] paivaNimet = {"Maanantai", "Tiistai", "Keskiviikko", "Torstai", "Perjantai", "Lauantai", "Sunnuntai"};
    
}
