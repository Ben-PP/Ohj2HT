package ArmAKerho;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * 
 * @author Karel Parkkola
 * @version 15.10.2021
 * TODO: Muista päivittää versio!
 *
 *Kontrolleri kerhoa kysyttäessä aukeavalle ikkunalle
 *
 */
public class KerhonNimiController implements ModalControllerInterface<String> {
    
    //Tekstikenttä johon kerhon nimi kirjoitetaan
    @FXML private TextField textVastaus;
    
    private String vastaus = null;
    
    
    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }
    
    
    
    @Override
    public String getResult() {
        return vastaus;
    }
    
    
    /**
     * Mitä tehdään kun dialogi ikkuna on avattu
     */
    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }
    
    
    /**
     * Mitä tapahtuu kun painetaan OK nappia
     * TODO: Testit
     */
    @FXML private void handleOK() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }

    
    /**
     * Mitä tapahtuu kun painetaan Cancel nappia
     */
    @FXML private void handleCancel() {
        ModalController.closeStage(textVastaus);
    }

    
    /**
     * Avaa ikkunan jossa kysytään kerhon nimi ja palautetaan kirjoitettu kerhon nimi tai null
     * @param modalityStage mille ollaan modaalisia. Jos null niin sovellukselle
     * @param oletus Oletusnimi joka näytetään
     * @return null jos painetaan Cancel, muuten kerhon nimi joka kirjoitettiin.
     * TODO: Testit
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                KerhonNimiController.class.getResource("KerhonNimiView.fxml"),
                "Kerho",
                modalityStage, oletus);
    }

}
