import java.awt.Color;

public class Paddle extends Sprite {
	
	private static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 100;
	private static final Color PADDLE_COLOR = Color.red;
	private static final int EDGE_DISTANCE = 40;
	private int xPos;
	
	public Paddle(Player player, int panelWidth, int panelHeight) {
		setWidth(PADDLE_WIDTH);
		setHeight(PADDLE_HEIGHT);
		setColor(PADDLE_COLOR);
		if(player == Player.One) {
			xPos = EDGE_DISTANCE;
		}
		else {
			xPos = (panelWidth - EDGE_DISTANCE - getWidth());
		}
		setInitialPos(xPos, (panelHeight / 2) - (getHeight() / 2));
		resetToInitialPos();
	}
}
