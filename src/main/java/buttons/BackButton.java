package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import main.MainStage;

public class BackButton extends Button {
	private Background background;

	public BackButton(MainStage mainStage) {
		setPrefWidth(48);
		setPrefHeight(48);
		background = new Background(
				new BackgroundImage(
						new Image(BackButton.class.getResourceAsStream("back.png")), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT,
						BackgroundPosition.DEFAULT,
						BackgroundSize.DEFAULT));
		setBackground(background);
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.backScene();
			}
		});
	}
}
