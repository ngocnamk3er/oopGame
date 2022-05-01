package main;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import map.MapManager;
import mapinteraction.MapInteractionManager;

public class MakeMainScene {
    private Scene mainScene;
    private Canvas canvas;
    private Canvas canvasbg;
    private GraphicsContext gc;
    private GraphicsContext gcbg;
    // private SetKeyBoardInputs setKeyBoardInputs;
    private MapManager mapManager;
    public MakeMainScene() {
        canvas=new Canvas(1344,768);
        canvasbg=new Canvas(1344,768);

        gc=canvas.getGraphicsContext2D();
        gcbg=canvasbg.getGraphicsContext2D();

        mapManager=new MapManager(gcbg);
        mapManager.render();

        StackPane stackPane=new StackPane();
        stackPane.getChildren().add(canvasbg);
        stackPane.getChildren().add(canvas);

        mainScene = new Scene(stackPane,1344,768);
        new GameLoop(new MapInteractionManager(gc,mapManager.getMapData(),this)).start();
    }
    public Scene getMainScene() {
        return mainScene;
    }
    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

}
