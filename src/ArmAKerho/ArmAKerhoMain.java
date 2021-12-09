package ArmAKerho;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

import Kerho.Kerho;


/**
 * @author Karel Parkkola
 * @version 15.10.2021
 * @version 15.11.2021
 * @version 1.12.2021
 * @version 7.12.2021
 * Pääohjelma kerhon käynnistämiseksi
 *
 */
public class ArmAKerhoMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        //Koitetaan avata pääikkuna
        try {
            
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("ArmAKerhoGUIView.fxml"));
            final Pane root = ldr.load();
            final ArmAKerhoGUIController kerhoCtrl = (ArmAKerhoGUIController)ldr.getController();
            
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("armakerho.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kerho Velho");
            
            Kerho kerho = new Kerho();
            kerhoCtrl.setKerho(kerho);
            
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(kerhoCtrl.onkoMuutettu()) {
                        Alert a = new Alert(AlertType.CONFIRMATION);
                        a.setTitle("Suljetaanko?");
                        a.setHeaderText("Muutoksia ei ole tallennettu!\nHaluatko varmasti sulkea tallentamatta?");
                        a.getButtonTypes().clear();
                        ButtonType ei = new ButtonType("Ei!", ButtonData.CANCEL_CLOSE);
                        ButtonType kylla = new ButtonType("Kyllä", ButtonData.OK_DONE);
                        a.getButtonTypes().add(ei);
                        a.getButtonTypes().add(kylla);
                        a.showAndWait().ifPresent(response -> {
                            if (response == kylla) {
                                Platform.exit();
                            } else event.consume();
                        });
                    }
                    
                }
            });
            
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