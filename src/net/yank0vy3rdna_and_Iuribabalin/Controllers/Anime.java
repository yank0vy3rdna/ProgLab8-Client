package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.yank0vy3rdna_and_Iuribabalin.Dragon;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import net.yank0vy3rdna_and_Iuribabalin.StoredType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Anime {
    static final int WINDOW_X = 1000;
    static final int WINDOW_Y = 800;

    void draw_dragon(int x, int y, int size, int color){
        try {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            FileInputStream inputstream = new FileInputStream("images/"+ color +".png");
            Image image = new Image(inputstream);
            gc.drawImage(image, x,y,size,size);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @FXML
    private Canvas canvas;
    @FXML
    void initialize() {
        canvas.setHeight(WINDOW_Y);
        canvas.setWidth(WINDOW_X);
        TimerTask task = new TimerTask() {
            public void run() {
                canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                for(StoredType dragon : Main.collectionWorker.collection){
                    int color_owner_id = (int)(dragon.getOwner_id() % 7);
                    int x = dragon.getCoordinates().getX().intValue();
                    int y = (int)dragon.getCoordinates().getY();
                    int size = (int)(dragon.getWeight()/2);
                    double offset = System.currentTimeMillis() / 300.;
                    draw_dragon(x, (int) (y + Math.sin(offset)*20),size,color_owner_id);
                }
            }
        };
        Main.timer.schedule(task, 1000L, 100L);
        canvas.setOnMouseClicked(e -> {
            double mouse_x = e.getX();
            double mouse_y = e.getY();
            for(StoredType dragon : Main.collectionWorker.collection){
                int color_owner_id = (int)(dragon.getOwner_id() % 7);
                int x = dragon.getCoordinates().getX().intValue();
                int y = (int)dragon.getCoordinates().getY();
                int size = (int)(dragon.getWeight()/2);
                double offset = System.currentTimeMillis() / 300.;

                if(mouse_x > x && mouse_x < x + size && mouse_y > y +Math.sin(offset)*20&& mouse_y < y +Math.sin(offset)*20+size){

                    if (dragon.getOwner_id() == Main.owner_id) {
                        Main.dragon1 = (Dragon) Main.collectionWorker.get_by_id(dragon.getId());
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("DragonUpdate.fxml"));

                        try {
                            loader.load();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Auth error");
                        alert.setHeaderText("Auth error");
                        alert.setContentText("Access error");
                        alert.showAndWait().ifPresent(rs -> {});
                    }
                }
            }
        });
    }
}
