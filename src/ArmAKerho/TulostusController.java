package ArmAKerho;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.ohj2.Mjonot;

import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Tulostuksen hoitava kontrolleri
 * 
 * @author Karel
 * @version 15.10.2021
 * @version 15.11.2021
 * @version 7.12.2021
 * FIXME: Tulostus kuntoon tai pois, paskaa...
 */
public class TulostusController implements ModalControllerInterface<String>{
    
    //Alue johon tulostettava teksti kirjoitetaan
    @FXML private TextArea tulostusAlue;
    
    private static String tulostus;
    
    
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
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printer(tulostus));
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                for (int i = 0; i < 3; i++) {
                    job.print();
                }
            } catch (PrinterException e) {
                System.err.println("Tulostaminen epäonnistui! "+ e.getMessage());
            }
        }
    }
    
    
    private static class Printer implements Printable {
        
        private StringBuilder tulostettava;
        private ArrayList<String> lines = new ArrayList<String>();
        private int linesPerPage = 3 * 15;
        
        public Printer(String tulostus) {
            this.tulostettava = new StringBuilder(tulostus);
            while (tulostettava.length() != 0) {
                lines.add(rivi(tulostettava));
            }
        }
        
        
        @Override
        public int print(Graphics graphics, PageFormat pageFormat,
                int pageIndex) throws PrinterException {
            if (pageIndex * linesPerPage >= lines.size()) {
                return NO_SUCH_PAGE;
            }
            
            //StringBuilder apu = new StringBuilder(tulostettava);
            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            int x = 150;
            int y = 50;
            
            for (int i = linesPerPage * pageIndex; i < lines.size() && i < linesPerPage; i++) {
                graphics.drawString(lines.get(i), x, y);
                y += 15;
            }

            return PAGE_EXISTS;
        }
        
        
        private String rivi(StringBuilder sb) {
            String rivi = Mjonot.erota(sb, '\n');
            return rivi;
        }
    }
    
    
    /**
     * Näyttää tulostusalueessa tekstin
     * @param tulostettava tulostettava teskti
     * TODO: Testit: Tulostamisen testit
     */
    public static void tulosta(String tulostettava) {
        tulostus = tulostettava;
        System.out.println(tulostus);
        ModalController.showModeless(TulostusController.class.getResource("TulostusGUIView.fxml"),
                "Tulostus", tulostus);
    }

}
