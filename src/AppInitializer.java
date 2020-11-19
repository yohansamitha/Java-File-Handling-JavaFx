import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AppInitializer extends Application {
    public String mainFilePath = "C:\\Users\\Public\\FileHandlingThogakade";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashBoardForm.fxml"));
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/CustomerForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Customer Controller Page");
        primaryStage.show();
        new Thread(() -> {
            File file = new File(mainFilePath);
            System.out.println(file.exists()+" file exists");
            if (!file.exists()){
                boolean mkdir = file.mkdir();
                System.out.println(mkdir);
            }else{
                System.out.println("file exists");
            }
        }).start();
//        t1.start();
    }
}
