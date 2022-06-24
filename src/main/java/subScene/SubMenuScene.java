package subScene;

import java.time.format.TextStyle;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SubMenuScene extends SubScene {
    private Pane pane;
    private Background background;
    private Button cancelButton;
    private Button confirmButton;
    private Text text;
    public SubMenuScene() {
        super(new Group(),554,400);
        background = new Background(new BackgroundImage(new Image(SubMenuScene.class.getResourceAsStream("subMenu.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        pane = new Pane();
        pane.setBackground(background);
        setRoot(pane);
        setLayoutY(200);
        setLayoutX(400);
        setVisible(false);
        Background backgroundCancelBtn = new Background(new BackgroundImage(new Image(SubMenuScene.class.getResourceAsStream("cancelButton.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        cancelButton = new Button();
        cancelButton.setBackground(backgroundCancelBtn);
        cancelButton.setPrefWidth(100);
		cancelButton.setPrefHeight(100);
        cancelButton.setLayoutX(458);
        cancelButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
                setVisible(false);
			}
		});
        Background backgroundConfirmBtn = new Background(new BackgroundImage(new Image(SubMenuScene.class.getResourceAsStream("confirmButton.png")),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT));
        confirmButton = new Button();
        confirmButton.setBackground(backgroundConfirmBtn);
        confirmButton.setPrefWidth(100);
		confirmButton.setPrefHeight(100);
        confirmButton.setLayoutX(227);
        confirmButton.setLayoutY(260);
        text = new Text();
        text.setX(50);  
        text.setY(120);  
        text.setWrappingWidth(400);
        text.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,30));  
        text.setFill(Color.WHITE);
        text.setText("Bắt đầu 1 game mới sẽ mất hết dữ liệu của game cũ, bạn có muốn tiếp tục ?");  
        pane.getChildren().addAll(cancelButton,text,confirmButton);
    }
    public Button getConfirmButton() {
        return confirmButton;
    }
    public void setConfirmButton(Button confirmButton) {
        this.confirmButton = confirmButton;
    }
    
}