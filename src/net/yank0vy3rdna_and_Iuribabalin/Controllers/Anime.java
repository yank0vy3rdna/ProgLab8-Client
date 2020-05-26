package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
                for(StoredType dragon : Main.collectionWorker.collection){
                    int color_owner_id = (int)(dragon.getOwner_id() % 7);
                    int x = dragon.getCoordinates().getX().intValue();
                    int y = (int)dragon.getCoordinates().getY();
                    int size = (int)(dragon.getWeight()/2);
                    draw_dragon(x,y,size,color_owner_id);
                }
            }
        };
        Main.timer.schedule(task, 0L, 1000L);
    }
}
