package ArmAKerho;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Tulostuksen hoitava kontrolleri
 * 
 * @author Karel
 * @version 15.10.2021
 * @version 15.11.2021
 * TODO: Toimivaksi: Tulostus controller
 */
public class TulostusController implements ModalControllerInterface<String>{
    
    //Alue johon tulostettava teksti kirjoitetaan
    @FXML TextArea tulostusAlue;
    
    
    @Override
    public String getResult() {
        return null;
    } 
    
    
    @Override
    public void setDefault(String oletus) {
        if ( oletus == null ) return;
        tulostusAlue.setText(oletus);
    }
    
    
    @Override
    public void handleShown() {
        //
    }
    
    
    
    /**
     * Sulkee ikkunan
     */
    @FXML private void handleOK() {
        ModalController.closeStage(tulostusAlue);
    }

    /**
     * Tulostaa tiedot
     */
    @FXML private void handleTulosta() {
        // TODO: Toimivaksi: Tulostaminen controller FXML
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teskti
     * TODO: Testit: Tulostamisen testit
     */
    public static void tulosta(String tulostus) {
        ModalController.showModeless(TulostusController.class.getResource("TulostusGUIView.fxml"),
                "Tulostus", tulostus);
    }

}
