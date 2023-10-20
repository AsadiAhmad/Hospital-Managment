package Project1;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

public class FilesAndTools {

    public synchronized void audioPlayer (String fileName) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    File myFile = new File(fileName);
                    AudioInputStream myAudio = AudioSystem.getAudioInputStream(myFile);
                    Clip myClip = AudioSystem.getClip();
                    myClip.open(myAudio);
                    myClip.start();
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException error) {
                    error.printStackTrace();
                }
            }
        }).start();
    }

    public void createNotificationPage (String notification, String fileName, String title, int sizeX, int sizeY) {
        Stage primaryStage = new Stage ();
        // Image Source
        InputStream input= getClass().getResourceAsStream(fileName);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Label label = new Label(notification);
        // Set Image
        label.setGraphic(imageView);
        label.setFont(new Font("Arial", 20));
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.getChildren().add(label);
        Scene scene = new Scene(root, sizeX, sizeY);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int existFile (String fileName) {
        File myFile = new File (fileName);
        int result;
        if (myFile.exists ()) {
        result = 1; // file exist
        }
        else {
        result = 0; // file not exist
        }
        return result;
    }

    public int deleteFile (String fileName) {
        File myFile = new File (fileName);
        int result;
        if (myFile.delete()) {
            result = 0; // delete file doesnt have error
        }
        else {
            result = 1; // delete file have error
        }
        return result;
    }

    public int ifExistThenDelete (String fileName) {
        int result = 0;
        if (existFile (fileName) == 1) {
            result = deleteFile (fileName);
        }
        return result;
    }

    public int emptyTextFieldCheck (String[] text) {
        int result = 0;
        for (String counter : text) {
            if (counter.length() == 0) {
                result++;
            }
        }
        return result;
    }
}
