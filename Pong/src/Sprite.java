import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {
	
	private int xPos, yPos;
	private int xVelo, yVelo;
	private int width;
	private int height;
	private int initialXpos, initialYpos;
	
	private Color spriteColor;
	
	public int getXpos() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public int getXvelo() {
		return xVelo;
	}
	
	public int getYvelo() {
		return yVelo;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Color getColor() {
		return spriteColor;
	}
	
	public void setXpos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYpos(int yPos) {
		this.yPos = yPos;
	}
	
	public void setXpos(int newX, int panelWidth) {
	     xPos = newX;
	     if(xPos < 0) {
	    	 xPos = 0;
	     }
	     else if((xPos + width) > panelWidth) {
	    	 xPos = panelWidth - width;
	     }
	}
	
	public void setYpos(int newY, int panelHeight) {
	     yPos = newY;
	     if(yPos < 0) {
	    	 yPos = 0;
	     }
	     else if((yPos + height) > panelHeight) {
	    	 yPos = panelHeight - height;
	     }
	}
	
	public void setXvelo(int xVelo) {
		this.xVelo = xVelo;
	}
	
	public void setYvelo(int yVelo) {
		this.yVelo = yVelo;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setColor(Color spriteColor) {
		this.spriteColor = spriteColor;
	}
	
	public void setInitialPos(int initialX, int initialY) {
		initialXpos = initialX;
		initialYpos = initialY;
	}
	
	public void resetToInitialPos() {
		setXpos(initialXpos);
		setYpos(initialYpos);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(getXpos(), getYpos(), getWidth(), getHeight());
	}
}
