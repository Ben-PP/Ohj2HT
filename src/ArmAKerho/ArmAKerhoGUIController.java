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
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;

import Kerho.Kerho;
import Kerho.Peluri;
import Kerho.SailoException;

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
    @FXML private ListChooser<Peluri> chooserPelurit;
    @FXML private TextField nimiTxtField;
    @FXML private TextField pNimiTxtField;
    @FXML private TextField tTilaTxtField;
    
    //Kerhon nimi. Oletus on valmiiksi kirjoitettuna kenttään.
    private String kerhonnimi = "ArmA Kerho";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
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
        
        Peluri peluri = ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriLuontiGUIView.fxml"), "Peluri", null, null);

        try {
            
            //Koittaa avata pelurin luonti ikkunan ja tehdä annettujen tietojen pohjalta uuden pelurin ja lopuksi lisää sen kerhoon
            kerho.lisaa(peluri);
            hae(peluri.getPeluriId());
            
        } catch (SailoException e) {
            
            Dialogs.showMessageDialog("Ongelmia uuden pelurin luomisessa " + e.getMessage());
            return;
        }
    }
    
    
    /**
     * Muokkaa vanhan jäsenen tietoja
     */
    @FXML
    private void handleMuokkaaPeluria() {
        // TODO: Tämä hyvä modaalisena?
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriDialogView.fxml"), "Peluri", null, "");
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
     // -------Koodin sisäiset asiat----------------------------------------------------------------------------
     // --------------------------------------------------------------------------------------------------------
    
    private Kerho kerho;
    
    
    /**
     * Alustaa pääikkunan
     */
    private void alusta() {
        chooserPelurit.clear();
        chooserPelurit.addSelectionListener(e -> naytaPeluri());
    }
    
    
    /**
     * Näyttää listalta valitun pelurin tiedot
     */
    private void naytaPeluri() {
        Peluri peluriKohdalla = chooserPelurit.getSelectedObject();
        
        if (peluriKohdalla == null) return;
        
        nimiTxtField.setText(peluriKohdalla.getNimi());
        pNimiTxtField.setText(peluriKohdalla.getPNimi());
        tTilaTxtField.setText(Integer.toString(peluriKohdalla.getTTila()));
    }
    
    
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
    
    
    /**
     * Asetetaan käytettävä kerho
     * @param kerho jota käytetään
     */
    public void setKerho(Kerho kerho) {
        this.kerho = kerho;
    }
    
    
    /**
     * Päivittää peluri listan
     * @param jnro jäsennumero
     */
    private void hae(int jnro) {
        chooserPelurit.clear();
        
        int index = 0;
        for (int i = 0; i < kerho.getPelureita(); i++) {
            Peluri peluri = kerho.getPeluri(i);
            if (peluri.getPeluriId() == jnro) index = i;
            chooserPelurit.add(peluri.getNimi(), peluri);
        }
        chooserPelurit.setSelectedIndex(index);
    }
}