import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	
	private final static Color BACKGROUND_COLOR = Color.white;
	private final static int TIMER_DELAY = 5;
	private final static int BALL_SPEED = 5;
	private final static int POINTS_TO_WIN = 3;
	
	int player1score = 0, player2score = 0;
	Player gameWinner;
	GameState gameState = GameState.INITIALISING;
	Ball ball;
	Paddle paddle1, paddle2;
	
	public void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
	}

	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
			Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		 	g2d.setStroke(dashed);
		 	g2d.setPaint(Color.black);
		 	g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		 	g2d.dispose();
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.getColor());
		g.fillRect(sprite.getXpos(), sprite.getYpos(), sprite.getWidth(), sprite.getHeight());
	}
	
	private void paintScores(Graphics g) {
		int xPadding = 100;
		int yPadding = 100;
		int fontSize = 50;
		Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
		Font winFont = new Font("Serif", Font.BOLD, (fontSize-(fontSize / 2)) -3);
		String p1score = Integer.toString(player2score);
		String p2score = Integer.toString(player1score);
		String pWin = "Congratulations, you won!";
		if(gameWinner == Player.One) {
			g.setFont(winFont);
			g.drawString(pWin, getWidth()-((xPadding * 3) + 20), yPadding + (yPadding / 2));
		}
		if(gameWinner == Player.Two) {
			g.setFont(winFont);
			g.drawString(pWin, xPadding, yPadding + (yPadding / 2));
		}
		g.setFont(scoreFont);
		g.drawString(p1score, xPadding, yPadding);
		g.drawString(p2score, getWidth()-xPadding, yPadding);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if(gameState != GameState.INITIALISING) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
		}
	}
	@Override
	public void keyTyped(KeyEvent event) {
	}
	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.setYvelo(-8);
		}
		else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.setYvelo(+8);
		}
		if(event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.setYvelo(-8);
		}
		else if(event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.setYvelo(+8);
		}
	}
	@Override
	public void keyReleased(KeyEvent event) {
		if((event.getKeyCode() == KeyEvent.VK_UP) || (event.getKeyCode() == KeyEvent.VK_DOWN)) {
			paddle2.setYvelo(0);
		}
		if((event.getKeyCode() == KeyEvent.VK_W) || (event.getKeyCode() == KeyEvent.VK_S)) {
			paddle1.setYvelo(0);
		}
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
	}

	public PongPanel() {
		setBackground(BACKGROUND_COLOR);
		Timer gameTimer = new Timer(TIMER_DELAY, this);
		gameTimer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	private void update() {
		switch(gameState) {
			case INITIALISING: {
				createObjects();
				gameState = GameState.PLAYING;
				ball.setXvelo(BALL_SPEED);
				ball.setYvelo(BALL_SPEED);
				break;
			}
			case PLAYING: {
				moveObject(ball);
				moveObject(paddle1);
				moveObject(paddle2);
				checkWallBounce();
				checkPaddleBounce();
				checkWin();
				break;
			}
			case GAMEOVER: {
				break;
			}
		}
	}
	
	public void moveObject(Sprite object) {
		object.setXpos(object.getXpos() + object.getXvelo(), getWidth());
		object.setYpos(object.getYpos() + object.getYvelo(), getHeight());
	}
	
	public void resetBall(Sprite object) {
		object.resetToInitialPos();
	}
	
	private void checkWallBounce() {
		if(ball.getXpos() <= 0) {
			addScore(Player.One);
			resetBall(ball);
		}
		else if(ball.getXpos() >= getWidth() - ball.getWidth()) {
			addScore(Player.Two);
			resetBall(ball);
		}
		if((ball.getYpos() <= 0) || (ball.getYpos() >= getHeight() - ball.getHeight())) {
			ball.setYvelo(-ball.getYvelo());
		}
	}
	
	private void checkPaddleBounce() {
		if(ball.getXvelo() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
			ball.setXvelo(BALL_SPEED);
		}
		if(ball.getXvelo() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
			ball.setXvelo(-BALL_SPEED);
		}
	}
	
	private void addScore(Player player) {
		if(player == Player.One) {
			player1score++;
		}
		else if(player == Player.Two) {
			player2score++;
		}
	}
	
	private void checkWin() {
		if(player1score >= POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GAMEOVER;
		}
		if(player2score >= POINTS_TO_WIN) {
			gameWinner = Player.Two;
			gameState = GameState.GAMEOVER;
		}
	}
}
