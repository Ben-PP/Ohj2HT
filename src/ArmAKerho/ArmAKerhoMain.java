package ArmAKerho;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Karel Parkkola
 * @version 15.10.2021
 * TODO: Muista päivittää versio!
 * 
 * Pääohjelma kerhon käynnistämiseksi
 *
 */
public class ArmAKerhoMain extends Application {
    
    // TODO: Testit?
    @Override
    public void start(Stage primaryStage) {
        
        //Koitetaan avata pääikkuna
        try {
            
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("ArmAKerhoGUIView.fxml"));
            final Pane root = ldr.load();
            final ArmAKerhoGUIController kerhoCtrl = (ArmAKerhoGUIController)ldr.getController();
            // TODO: Alla olevan pointti?
            //final ArmAKerhoGUIController armakerhoCtrl = (ArmAKerhoGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("armakerho.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kerho Velho");
            primaryStage.show();
            
            //Avaa KerhonNimiView ikkunan kerhon nimen kysymiseksi
            if(!kerhoCtrl.avaa() ) Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}