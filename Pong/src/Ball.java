import java.awt.Color;

public class Ball extends Sprite {

	private static final Color BALL_COLOR = Color.black;
	private static final int WIDTH = 25;
	private static final int HEIGHT = 25;
	
	public Ball(int panelWidth, int panelHeight) {
		setColor(BALL_COLOR);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setInitialPos(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
		resetToInitialPos();
	}
}
