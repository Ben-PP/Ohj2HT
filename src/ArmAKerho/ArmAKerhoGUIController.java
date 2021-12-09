package ArmAKerho;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.Desktop;
import java.io.File;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Kanta.RegularExpression;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;

import Kerho.Kerho;
import Kerho.Paiva;
import Kerho.Peluri;

/**
 * Pääikkunan kontrolleri
 * 
 * @author Karel Parkkola
 * @version 15.10.2021
 * @version 15.11.2021
 * @version 28.11.2021
 * @version 29.11.2021
 * @version 1.12.2021
 * @version 9.12.2021
 */
public class ArmAKerhoGUIController implements Initializable {
    
    //Hakukenttä johon voidaan kirjoittaa
    @FXML private TextField hakuSana;
    @FXML private ComboBoxChooser<String> hakuEhto;
    
    //Lista luoduista pelureista vasemmassa laidassa
    @FXML private ListChooser<Peluri> chooserPelurit;
    
    //Valitun pelurin tiedot
    @FXML private TextField nimiTxtField;
    @FXML private TextField pNimiTxtField;
    @FXML private TextField tTilaTxtField;
    @FXML private TextField puhNumtxtField;
    @FXML private Label maanantaiLabel;
    @FXML private Label tiistaiLabel;
    @FXML private Label keskiviikkoLabel;
    @FXML private Label torstaiLabel;
    @FXML private Label perjantaiLabel;
    @FXML private Label lauantaiLabel;
    @FXML private Label sunnuntaiLabel;
    
    //Kerhon nimi. Oletus on valmiiksi kirjoitettuna kenttään.
    private String kerhonnimi = "Anna kerhon nimi!";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    /**
     * Avaa uuden kerhon
     */
    @FXML
    private void handleAvaa() {
        if(kerho.onkoMuutettu()) {
            if(Dialogs.showQuestionDialog("Poistutaanko?", "Kerhossa on tallentamattomia muutoksia!\nHaluatko varmasti poistua?", "Kyllä", "Ei")) {
                alusta();
                avaa();
            }
        } else {
            alusta();
            avaa();
        }
    }
    
    
    
    @FXML
    private void handleHae() {
        etsi();
    }
    
    
    
    @FXML
    private void handleMuutaHakuEhto() {
        hae(ensimmainenPeluri(), muutaHakuEhto());
    }
    
    
    /**
     * Antaa kerhon jäsenten tiedot tulostettavassa muodossa
     */
    @FXML
    private void handleTulosta() {
        String tulostus = tulostettavaksi();
        TulostusController.tulosta(tulostus);
    } 

    
    
    /**
     * Lisää uuden jäsenen
     */
    @FXML
    private void handleUusiPeluri() {
        //avaa pelurin luonti-ikkunan
        Peluri peluri = ModalController.showModal(ArmAKerhoGUIController.class.getResource("PeluriLuontiGUIView.fxml"), "Peluri", null, null);
        if (peluri == null) return;
        lisaaPeluri(peluri);
    }
    
    
    /**
     * Muokkaa vanhan jäsenen tietoja
     */
    @FXML
    private void handleMuokkaaPeluria() {
        if(peluriKohdalla == null) return;
        
        String[] muokatut = new String[4];
        muokatut = ModalController.<String[],PeluriMuokkausController>showModal(PeluriMuokkausController.class.getResource("PeluriDialogView.fxml"), "Tiedot", null, muokatut,ctrl->ctrl.alusta(peluriKohdalla));
        muokkaaTietoja(muokatut);
    }
    
    
    /**
     * Poistaa jäsenen
     */
    @FXML
    private void handlePoistaPeluri() {
        if(Dialogs.showQuestionDialog("Poistetaanko?", "Haluatko varmasti poistaa pelurin?", "Kyllä", "Ei")) {
            poistaPeluri();
        }
    }

    
    
    
    /**
     * Avaa ikkunan jossa voidaan muokata pelaajan peliaikoja
     * @param event ei käytössä
     */
    @FXML
    private void handleAikaMuokkaus() {
        if(peluriKohdalla == null) return;
        
        //Avaa aikamuokkausikkunan
        ajat = ModalController.<String[][],AikaMuokkausController>showModal(AikaMuokkausController.class.getResource("aikaMuokkausGUIView.fxml"), "Tiedot", null, ajat,ctrl->ctrl.setAjat(kerho.getPaivat(peluriKohdalla)));

        if (ajat[0][0] == null) return;
        muokkaaAikoja(ajat);
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
    
    private String hakuTapa = "nimi";
    
    
    /**
     * Alustaa pääikkunan
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
        
        File kerhot = new File("kerhot");
        if(!kerhot.exists()) kerhot.mkdir();
        
        kerho = new Kerho();
    }
    
    /**
     * Lisää pelurin kerhoon
     * @param peluri Peluri joka lisätään
     */
    private void lisaaPeluri(Peluri peluri) {
            kerho.lisaa(peluri);
            hae(peluri.getPeluriId(), true);
    }
    
    
    /**
     * Poistaa valitun pelurin
     */
    private void poistaPeluri() {
        if (peluriKohdalla == null) return;
        kerho.poistaPeluri(peluriKohdalla.getPeluriId());
        hae(1, true);
    }
    
    
    /**
     * Näyttää listalta valitun pelurin tiedot
     */
    private void naytaPeluri() {
        peluriKohdalla = chooserPelurit.getSelectedObject();
        if (peluriKohdalla == null) return;
        
        //Asetetaan Pelurin tiedot tekstikenttiin.
        nimiTxtField.setText(peluriKohdalla.getNimi());
        pNimiTxtField.setText(peluriKohdalla.getPNimi());
        tTilaTxtField.setText(Integer.toString(peluriKohdalla.getTTila()));
        puhNumtxtField.setText(peluriKohdalla.getPuh());
        
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
            } else if (p.getAlkuAika(false).equals("00") && p.getAlkuAika(true).equals("00") && p.getLoppuAika(false).equals("00") && p.getLoppuAika(true).equals("00")) {
                paivatLabels.get(i).setText("Ei käy!");
            }else {
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
     */
    protected void lueTiedosto(String nimi) {
        
        kerho.lueTiedostosta("kerhot/"+nimi+"/nimet.dat", "kerhot/"+nimi+"/pelipaivat.dat");
        hae(1, true);
        kerhonnimi = nimi;
        setTitle("Kerho - " + kerhonnimi);
    }
    
    
    /**
     * Luo uuden kerhon kansiorakenteen
     * @param nimi kerhon nimi
     * @example
     * <pre name="test">
     *  #import java.io.File;
     *  #import ArmAKerho.ArmAKerhoGUIController;
     *  
     *  ArmAKerhoGUIController ctrl = new ArmAKerhoGUIController();
     *  ctrl.luoTiedostotTest("testi");
     *  
     *  File nimet = new File("kerhot/testi/nimet.dat");
     *  File nimetBak = new File("kerhot/testi/nimet.bak");
     *  File pelipaivat = new File("kerhot/testi/pelipaivat.dat");
     *  File pelipaivatBak = new File("kerhot/testi/pelipaivat.bak");
     *  
     *  nimet.exists() === true;
     *  nimetBak.exists() === true;
     *  pelipaivat.exists() === true;
     *  pelipaivatBak.exists() === true;
     *  
     *  nimet.delete();
     *  nimetBak.delete();
     *  pelipaivat.delete();
     *  pelipaivatBak.delete();
     *  File testi = new File("kerhot/testi");
     *  testi.delete();
     * </pre>
     */
    private void luoTiedostot(String nimi) {
        File paa = new File("kerhot/"+nimi);
        paa.mkdir();
        File pelurit = new File("kerhot/"+nimi+"/nimet.dat");
        File peluritBak = new File("kerhot/"+nimi+"/nimet.bak");
        File pelipaivat = new File("kerhot/"+nimi+"/pelipaivat.dat");
        File pelipaivatBak = new File("kerhot/"+nimi+"/pelipaivat.bak");
        try {
            pelurit.createNewFile();
            peluritBak.createNewFile();
            pelipaivat.createNewFile();
            pelipaivatBak.createNewFile();
        } catch (IOException e) {
            System.err.println("Tiedostoja ei voitu luoda. "+e.getMessage());
        }
        
    }
    
    
    /**
     * Metodi tiedostonluonnin testaamista varten
     * @param nimi kerhon nimi
     */
    public void luoTiedostotTest(String nimi) {
        luoTiedostot(nimi);
    }
    
    
    /**
     * Tallentaa kaikki tiedot
     */
    private void tallenna() {
        kerho.tallenna("kerhot/"+kerhonnimi+"/nimet", "kerhot/"+kerhonnimi+"/pelipaivat");
    }

    
    /**
     * Kysytään tiedoston nimi ja luetaan kyseinen tiedosto
     * @return true jos onnistui, false jos epäonnistui
     */
    public boolean avaa() {
        boolean valmis = false;
        String uusinimi = "";
        do {
            uusinimi = KerhonNimiController.kysyNimi(null, kerhonnimi);
            if (uusinimi == null) return false;
            if (uusinimi.equals("") || uusinimi.equals("Anna kerhon nimi!")) {
                Dialogs.showMessageDialog("Anna kerhon nimi!");
                continue;
            }
            
            File tied = new File("kerhot/"+uusinimi);
            
            if (!tied.exists()) {
                if (Dialogs.showQuestionDialog("Luodaanko uusi kerho", "Kerhoa "+uusinimi+" ei ole olemassa.\nTahdotko luoda uuden kerhon?", "Kyllä", "Ei")) {
                    luoTiedostot(uusinimi);
                }else continue;
            }
            valmis = true;
        } while (!valmis);
        
        lueTiedosto(uusinimi);
        kerhonnimi = uusinimi;
        return true;
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma TIM:issä erillisessä selaimessa.
     */
    private void avustus() {
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
     * Palauttaa kerhon pelurit tulostettavassa muodossa
     * @return Kerhon pelurit tulostettavassa muodossa
     */
    private String tulostettavaksi() {
        return kerho.tulosta();
    }
    
    
    /**
     * Asetetaan käytettävä kerho
     * @param kerho jota käytetään
     */
    public void setKerho(Kerho kerho) {
        this.kerho = kerho;
    }
    
    
    /**
     * Palauttaa tiedon onko tietoja muutettu tallentamisen jälkeen
     * @return true jos on tallentamattomia tietoja, muuten false
     */
    public boolean onkoMuutettu() {
        return kerho.onkoMuutettu();
    }
    
    
    /**
     * Päivittää peluri listan
     * @param jnro jäsennumero
     * @param nimella true jos halutaan näyttää Pelureiden nimet ja false jos pelaajanimet
     */
    private void hae(int jnro, Boolean nimella) {
        chooserPelurit.clear();
        int index = 0;
        for (int i = 0; i < kerho.getPelureita(); i++) {
            Peluri peluri = kerho.getPeluri(i);
            if (peluri.getPeluriId() == jnro) index = i;
            if (nimella) {
                chooserPelurit.add(peluri.getNimi(), peluri);
            } else {
                chooserPelurit.add("_"+peluri.getPNimi(), peluri);
            }
        }
        chooserPelurit.setSelectedIndex(index);
    }
    
    
    /**
     * Päivittää peluri listan
     * @param jnro jäsennumero
     * @param nimella true jos halutaan näyttää Pelureiden nimet ja false jos pelaajanimet
     */
    private void hae(ArrayList<Peluri> lista, int jnro, Boolean nimella) {
        chooserPelurit.clear();
        int index = 0;
        for (int i = 0; i < lista.size(); i++) {
            Peluri peluri = lista.get(i);
            if (peluri.getPeluriId() == jnro) index = i;
            if (nimella) {
                chooserPelurit.add(peluri.getNimi(), peluri);
            } else {
                chooserPelurit.add("_"+peluri.getPNimi(), peluri);
            }
        }
        chooserPelurit.setSelectedIndex(index);
    }
    
    
    /**
     * Etsii hakusanaa vastaavat pelurit ja näyttää heidät
     */
    private void etsi() {
        
        ArrayList<Peluri> lista = new ArrayList<Peluri>();
        switch (hakuTapa) {
        case "P-Nimi":
            for (int i = 0; i < kerho.getPelureita(); i++) {
                Peluri peluri = kerho.getPeluri(i);
                if (RegularExpression.regulaariExp(hakuSana.getText(), peluri.getPNimi(), true)) {
                    lista.add(peluri);
                }
            }
            hae(lista, ensimmainenPeluri(), false);
            break;

        default:
            for (int i = 0; i < kerho.getPelureita(); i++) {
                Peluri peluri = kerho.getPeluri(i);
                if (RegularExpression.regulaariExp(hakuSana.getText(), peluri.getNimi(), true)) {
                    lista.add(peluri);
                }
            }
            hae(lista, ensimmainenPeluri(), true);
            break;
        }
    }
    
    
    /**
     * Muuttaa haetaanko pelureita nimen vai pelaajanimen mukaan
     * @return true jos nimen mukaan ja false jos pelaajanimen mukaan
     */
    private boolean muutaHakuEhto() {
        hakuTapa = hakuEhto.getSelectedText();
        if(hakuTapa.equals("Nimi")) return true;
        return false;
    }
    
    
    /**
     * Selvittää ja palauttaa pelurin jolla on kerhon pienin jäsennumero
     * @return Kerhon pienin jäsennumero
     */
    private int ensimmainenPeluri() {
        int id = kerho.getPeluri(kerho.getPelureita()-1).getPeluriId();
        for (int i = kerho.getPelureita()-1; i >= 0; i--) {
            if (kerho.getPeluri(i).getPeluriId() < id) id = kerho.getPeluri(i).getPeluriId();
        }
        return id;
    }
    
    
    /**
     * Muokkaa pelurin aikoja
     * @param aikaTaulukko Taulukko josta ajat luetaan. String[0=maanantai][0=AlkuTunti]
     */
    private void muokkaaAikoja(String[][] aikaTaulukko) {
        kerho.muokkaaAikoja(peluriKohdalla.getPeluriId(), aikaTaulukko);
        hae(peluriKohdalla.getPeluriId(), true);
    }
    
    
    /**
     * Muokkaa pelurin tiedot
     * @param tiedot taulukko tiedoista [nimi, pnimi, ttila, puh]
     */
    private void muokkaaTietoja(String[] tiedot) {
        kerho.muokkaaTietoja(peluriKohdalla.getPeluriId(), tiedot);
        hae(peluriKohdalla.getPeluriId(), true);
    }
}