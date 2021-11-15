package ArmAKerho;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;

import Kerho.Kerho;
import Kerho.Paiva;
import Kerho.Peluri;
import Kerho.SailoException;

/**
 * Pääikkunan kontrolleri
 * 
 * @author Karel Parkkola
 * @version 15.10.2021
 * @version 15.11.2021
 */
public class ArmAKerhoGUIController implements Initializable {
    
    //Hakukenttä johon voidaan kirjoittaa
    @FXML private TextField hakuSana;
    
    //Lista luoduista pelureista vasemmassa laidassa
    @FXML private ListChooser<Peluri> chooserPelurit;
    
    //Valitun pelurin tiedot
    @FXML private TextField nimiTxtField;
    @FXML private TextField pNimiTxtField;
    @FXML private TextField tTilaTxtField;
    @FXML private Label maanantaiLabel;
    @FXML private Label tiistaiLabel;
    @FXML private Label keskiviikkoLabel;
    @FXML private Label torstaiLabel;
    @FXML private Label perjantaiLabel;
    @FXML private Label lauantaiLabel;
    @FXML private Label sunnuntaiLabel;
    
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
        //TODO: Toimivaksi: Tulostaminen FXML
        TulostusController.tulosta(null);
    } 

    
    
    /**
     * Lisää uuden jäsenen
     */
    @FXML
    private void handleUusiPeluri() {
        //avaa pelurin luonti-ikkunan
        Peluri peluri = ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriLuontiGUIView.fxml"), "Peluri", null, null);
        
        lisaaPeluri(peluri);
    }
    
    
    /**
     * Muokkaa vanhan jäsenen tietoja
     * TODO: Testit: Pelurin muokkaus FXML
     */
    @FXML
    private void handleMuokkaaPeluria() {
        //TODO: Toimivaksi: Pelurin muokkaus FXML
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriDialogView.fxml"), "Peluri", null, "");
    }
    
    
    /**
     * Poistaa jäsenen
     * TODO: Testit: Pelurin poisto
     */
    @FXML
    private void handlePoistaPeluri() {
        // TODO: Toimivaksi: Pelurin poisto FXML
        Dialogs.showMessageDialog("Vielä ei osata poistaa jäsentä");
    }

    
    
    
    /**
     * Avaa ikkunan jossa voidaan muokata pelaajan peliaikoja
     * @param event ei käytössä
     * TODO: Testit: Aikojen muokkaus FXML
     */
    @FXML
    private void handleAikaMuokkaus() {
        if(peluriKohdalla == null) return;
        
        //Avaa aikamuokkausikkunan
        ajat = ModalController.<String[][],AikaMuokkausController>showModal(AikaMuokkausController.class.getResource("aikaMuokkausGUIView.fxml"), "Tiedot", null, ajat,ctrl->ctrl.setAjat(kerho.getPaivat(peluriKohdalla)));
        
        //TODO: Poista: Sys.out.println debuggausta varten FXML
        //tulostus debuggausta varten=========================
        for (int i = 0; i < ajat.length; i++) {
            for(int a = 0; a < ajat[i].length; a++) {
                System.out.print(ajat[i][a]+",");
            }
            System.out.println();
        }
        //taas toiminnallista================================
        
        muokkaaAikoja(ajat);
    }
    
    
    /**
     * Tallentaa ja sitten sulkee ohjelman
     */
    @FXML
    private void handleLopeta() {
        //TODO: Lopuksi: Muokkaa tarvittaessa lopetus FXML
        tallenna();
        Platform.exit();
    }
    
    
    /**
     * Tallentaa kaikki tiedot
     * TODO: Testit: Tallentaminen FXML
     */
    @FXML
    private void handleTallenna() {
        //TODO: Toimivaksi: Tallentaminen FXML
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
        // TODO: Päivitä: Tietoja-ikkuna FXML
        ModalController.showModal(ArmAKerhoGUIController.class.getResource("AboutView.fxml"), "Kerho", null, "");
    }
    
    
     // --------------------------------------------------------------------------------------------------------
     // -------Koodin sisäiset asiat----------------------------------------------------------------------------
     // --------------------------------------------------------------------------------------------------------
    
    //Kerho jota käsitellään
    private Kerho kerho;
    /**
     * Peluri joka on valittu chooserboxissa
     */
    private Peluri peluriKohdalla;
    /**
     * Labelit joihin päivitetään pelurin peliajat 0=maanantai
     */
    private ArrayList<Label> paivatLabels;
    /**
     * Taulukko pelurin ajoista [viikonpäivä][ah,am,lh,lm]
     */
    private String[][] ajat;
    
    
    /**
     * Alustaa pääikkunan
     * TODO: Testit: Pääikkunan alustus
     */
    private void alusta() {
        paivatLabels = new ArrayList<Label>(List.of(
                maanantaiLabel,
                tiistaiLabel,
                keskiviikkoLabel,
                torstaiLabel,
                perjantaiLabel,
                lauantaiLabel,
                sunnuntaiLabel
                ));
        
        chooserPelurit.clear();
        chooserPelurit.addSelectionListener(e -> naytaPeluri());
    }
    
    /**
     * Lisää pelurin kerhoon
     * @param peluri Peluri joka lisätään
     * TODO: Testit: Pelurin lisääminen kerhoon
     */
    private void lisaaPeluri(Peluri peluri) {
        try {
            kerho.lisaa(peluri);
            hae(peluri.getPeluriId());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden pelurin luomisessa " + e.getMessage());
            return;
        }
    }
    
    
    /**
     * Näyttää listalta valitun pelurin tiedot
     * TODO: Testit: Pelurin tietojen näyttäminen
     */
    private void naytaPeluri() {
        peluriKohdalla = chooserPelurit.getSelectedObject();
        if (peluriKohdalla == null) return;
        
        //Asetetaan Pelurin tiedot tekstikenttiin.
        nimiTxtField.setText(peluriKohdalla.getNimi());
        pNimiTxtField.setText(peluriKohdalla.getPNimi());
        tTilaTxtField.setText(Integer.toString(peluriKohdalla.getTTila()));
        
        //Lisätään pelurin peliajat oikeille paikoille.
        
        //Jos Pelurilla ei ole asetettu päiviä, näkyy vakiona "Ei käy!"
        List<Paiva> pelurinPaivat = kerho.getPaivat(peluriKohdalla);
        if(pelurinPaivat.size() <= 0) {
            for(int i = 0; i < paivatLabels.size(); i++) {
                paivatLabels.get(i).setText("Ei käy!");
            }
            return;
        }
        //Kirjoitetaan pelurin peliajat labeleihin
        for(int i = 0; i < paivatLabels.size(); i++) {
            Paiva p = pelurinPaivat.get(i);
            //Jos jokin päivän kellonajoista on tyhjä, ei kyseinen päivä käy.
            if(p.getAlkuAika(false) == null || p.getAlkuAika(true) == null || p.getLoppuAika(false) == null || p.getLoppuAika(true) == null) {
                paivatLabels.get(i).setText("Ei käy!");
            } else {
                paivatLabels.get(i).setText(p.getAlkuAika(false)+":"+p.getAlkuAika(true)+" - "+p.getLoppuAika(false)+":"+p.getLoppuAika(true));
            }
        }
    }
    
    
    /**
     * Asettaa ikkunan titlen
     * @param title Stringi joka tulee titleksi
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuSana).setTitle(title);
    }

    
    /**
     * Lukee kerhon tiedot tiedostosta
     * @param nimi Kerhon nimi
     * TODO: Testit: Tiedostosta lukeminen
     */
    protected void lueTiedosto(String nimi) {
        kerhonnimi = nimi;
        setTitle("Kerho - " + kerhonnimi);
        String virhe = "Ei osata lukea vielä";  // TODO: Toimivaksi: Tiedostosta lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    
    /**
     * Tallentaa kaikki tiedot
     * TODO: Testit: Tallentaminen
     */
    private void tallenna() {
     // TODO: Toimivaksi: Tiedostoon tallentaminen
     Dialogs.showMessageDialog("Vielä ei osata tallentaa");
    }

    
    /**
     * Kysytään tiedoston nimi ja luetaan kyseinen tiedosto
     * @return true jos onnistui, false jos epäonnistui
     * TODO: Testit: Alkuikkunan avaus
     */
    public boolean avaa() {
        // TODO: Toimivaksi: Kerhon valitseminen nimen pohjalta ensimmäiseksi
        String uusinimi = KerhonNimiController.kysyNimi(null, kerhonnimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma TIM:issä erillisessä selaimessa.
     */
    private void avustus() {
        // TODO: Päivitä: TIM Suunnitelma
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
    
    
    /**
     * Muokkaa pelurin aikoja
     * @param aikaTaulukko Taulukko josta ajat luetaan. String[0=maanantai][0=AlkuTunti]
     *TODO: Testit: Aikojen muokkaus
     */
    private void muokkaaAikoja(String[][] aikaTaulukko) {
        //Poistaa kaikki pelurin päivät paivat luokasta
        kerho.poistaPelurinPaivat(peluriKohdalla.getPeluriId());
        //Tekee uudet päivät pelurille
        for (int i = 0; i < aikaTaulukko.length; i++) {
            Paiva pv = new Paiva(peluriKohdalla.getPeluriId(), aikaTaulukko[i][0], aikaTaulukko[i][1], aikaTaulukko[i][2], aikaTaulukko[i][3], i);
            kerho.lisaa(pv);
        }
        hae(peluriKohdalla.getPeluriId());
    }
}