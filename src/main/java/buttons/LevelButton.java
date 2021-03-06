package buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import main.MainStage;

public class LevelButton extends Button {
	private int levelValue;

	public LevelButton(String text, MainStage mainStage, Background backgroundLevelButton) {
		setText(text);
		levelValue = Integer.parseInt(text) - 1;
		setPrefWidth(80);
		setPrefHeight(80);
		setBackground(backgroundLevelButton);
		setFont(Font.loadFont(LevelButton.class.getResourceAsStream("m6x11.ttf"), 25));
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainStage.nextScene(levelValue);
			}
		});
	}

	public int getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(int levelValue) {
		this.levelValue = levelValue;
	}

}
