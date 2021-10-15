package ArmAKerho;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;

/**
 * @author Karel Parkkola
 * @version 15.10.2021
 * TODO: Muista päivittää versio!
 * 
 * Pääikkunan kontrolleri
 *
 */
public class ArmAKerhoGUIController implements Initializable {
    
    //Hakukenttä johon voidaan kirjoittaa
    @FXML private TextField hakuSana;
    
    //Kerhon nimi. Oletus on valmiiksi kirjoitettuna kenttään.
    private String kerhonnimi = "ArmA Kerho";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //      
    }
    
    
    /**
     * Avaa uuden kerhon
     */
    @FXML
    private void handleAvaa() {
        avaa();
    }
    
    
    /**
     * Antaa kerhon jäsenten tiedot tulostettavassa muodossa
     */
    @FXML
    private void handleTulosta() {
        TulostusController.tulosta(null);
    } 

    
    
    /**
     * Lisää uuden jäsenen
     */
    @FXML
    private void handleUusiPeluri() {
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriLuontiGUIView.fxml"), "Jäsen", null, "");
        // TODO: Mieti kumpi on parempi, todennäköisesti modal?
        /**
        try {
            FXMLLoader ldr = new FXMLLoader(ArmAKerhoGUIController.class.getResource("PeluriLuontiGUIView.fxml"));
            Parent root1 = ldr.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Lisää peluri");
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        */
    }
    
    
    /**
     * Muokkaa vanhan jäsenen tietoja
     */
    @FXML
    private void handleMuokkaaPeluria() {
        // TODO: Tämä hyvä modaalisena?
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriDialogView.fxml"), "Jäsen", null, "");
    }
    
    
    /**
     * Poistaa jäsenen
     * TODO: Testit
     */
    @FXML
    private void handlePoistaPeluri() {
        // TODO: Pelurin poisto ominaisuus
        Dialogs.showMessageDialog("Vielä ei osata poistaa jäsentä");
    }

    
    
    
    /**
     * Avaa ikkunan jossa voidaan muokata pelaajan peliaikoja
     * @param event ei käytössä
     * TODO: Testit
     */
    @FXML
    private void handleAikaMuokkaus() {
        // TODO: Aikamuokkaus toimivaksi, tämäkin hyvä modaalisena?
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("aikaMuokkausGUIView.fxml"), "Ajat", null, "");
        /**
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("aikaMuokkausGUIView.fxml"));
            Parent root1 = (Parent) ldr.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Peliajat");
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        */
    }
    
    
    /**
     * Tallentaa ja sitten sulkee ohjelman
     */
    @FXML
    private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    /**
     * Tallentaa kaikki tiedot
     */
    @FXML
    private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Avaa harkkatyön TIM suunnitelman
     */
    @FXML
    private void handleApua() {
        avustus();
    }
    
    
    /**
     * Avaa ikkunan jossa tietoja sovelluksesta
     */
    @FXML
    private void handleTietoja() {
        // TODO: Muista päivitellä
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("AboutView.fxml"), "Kerho", null, "");
    }
    
    
     // --------------------------------------------------------------------------------------------------------
     // -------Koodin sisäiset aliohjelmat--------------------------------------------------------------------
     // --------------------------------------------------------------------------------------------------------
    
    /**
     * Asettaa ikkunan titlen
     * @param title Stringi joka tulee titleksi
     * TODO: Testit
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuSana).setTitle(title);
    }

    
    /**
     * Lukee kerhon tiedot tiedostosta
     * @param nimi Kerhon nimi
     * TODO: Testit
     */
    protected void lueTiedosto(String nimi) {
        kerhonnimi = nimi;
        setTitle("Kerho - " + kerhonnimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    
    /**
     * Tallentaa kaikki tiedot
     * TODO: Testit
     */
    private void tallenna() {
     // TODO: Tiedoston tallentaminen
     Dialogs.showMessageDialog("Vielä ei osata tallentaa");
    }

    
    /**
     * Kysytään tiedoston nimi ja luetaan kyseinen tiedosto
     * @return true jos onnistui, false jos epäonnistui
     * TODO: Testit
     */
    public boolean avaa() {
        // TODO: Tämä toimivaksi
        String uusinimi = KerhonNimiController.kysyNimi(null, kerhonnimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa.
     */
    private void avustus() {
        // TODO: Muista myös päivitellä timiä!
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021s/ht/kabepark");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }


}