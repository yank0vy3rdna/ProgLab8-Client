package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import net.yank0vy3rdna_and_Iuribabalin.Dragon;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import net.yank0vy3rdna_and_Iuribabalin.StoredType;

import java.util.LinkedList;

public class Anime {
    static final int WINDOW_X = 1000;
    static final int WINDOW_Y = 800;
    @FXML
    private Canvas canvas;
    @FXML
    void initialize() {
        System.out.println("lol");
        canvas.setHeight(WINDOW_Y);
        canvas.setWidth(WINDOW_X);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        LinkedList<Color> colors = new LinkedList<>();
        for(StoredType dragon : Main.collectionWorker.collection){
            int color_owner_id = (int)(dragon.getOwner_id() % 7);
            gc.setFill(Color.rgb(255*(color_owner_id / 4), 255*((color_owner_id / 2) % 2), 255*(color_owner_id % 2)));
            gc.fillRect(dragon.getCoordinates().getX().intValue(),(int)dragon.getCoordinates().getY(),(int)(dragon.getWeight()/2),(int)(dragon.getWeight()/2));
        }
    }

    void onShow(){
    }
}
