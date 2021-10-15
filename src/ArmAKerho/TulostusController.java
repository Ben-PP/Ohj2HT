package ArmAKerho;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * @author Karel
 * @version 15.10.2021
 * TODO: Muista päivittää versio!
 *
 * Tulostuksen hoitava kontrolleri
 *
 */
public class TulostusController implements ModalControllerInterface<String>{

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
    
    
    /**
     * Mitä tehdään kun dialogi on näytetty
     */
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
        // TODO: Tulostus toimimaan
        Dialogs.showMessageDialog("Ei osata vielä tulostaa");
    }
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostus tulostettava teskti
     * TODO: Testit
     */
    public static void tulosta(String tulostus) {
        ModalController.showModeless(TulostusController.class.getResource("TulostusGUIView.fxml"),
                "Tulostus", tulostus);
    }

}
