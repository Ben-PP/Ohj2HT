package ArmAKerho;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Karel
 * @version 28.9.2021
 *
 */
public class ArmAKerhoMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("AlkuIkkunaGUIView.fxml"));
            final Pane root = ldr.load();
            //final ArmAKerhoGUIController armakerhoCtrl = (ArmAKerhoGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("armakerho.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Kerho Velho");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}