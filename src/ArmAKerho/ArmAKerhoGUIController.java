package ArmAKerho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Karel
 * @version 28.9.2021
 *
 */
public class ArmAKerhoGUIController {
    
    @FXML
    private TextField kerhonNimiTxtField;
    
    @FXML
    private void AvaaPaaIkkuna(ActionEvent event) {
        try {
            if (kerhonNimiTxtField.getText().equals(new String("kyllä"))) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ArmAKerhoGUIView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Kerhon nimi");
                stage.show();
                return;
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eiKerhoaGUIView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Ei kerhoa");
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}