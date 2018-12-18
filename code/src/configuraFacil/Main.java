package configuraFacil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryWindow) throws Exception{

        //new presentation
        URL url = getClass().getResource("presentation/views/login.fxml");
        Parent sceneroot = FXMLLoader.load(url);

        //new scene
        Scene scene = new Scene(sceneroot, 300, 275);
        //scene.setFill(Color.TRANSPARENT);

        //start window with scene
        primaryWindow.setOnCloseRequest(e->onClose());
        //primaryWindow.initStyle(StageStyle.TRANSPARENT);
        primaryWindow.setScene(scene);
        primaryWindow.show();
    }

    private void onClose(){
        System.out.println("closed");
        //save
    }


    public static void main(String[] args) {
        launch(args);
    }
}
