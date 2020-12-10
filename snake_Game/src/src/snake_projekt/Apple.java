package snake_projekt;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	
	private int xcoor, ycoor, width, height;
	
	public Apple(int xcoor, int ycoor, int titleSize) {
this.xcoor = xcoor;
this.ycoor = ycoor;
width = titleSize;
height = titleSize;
}

public void tick() {
	
}
public void draw(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect(xcoor * width, ycoor * height, width, height);
}

public int getXcoor() {
	return xcoor;
}

public void setXcoor(int xcoor) {
	this.xcoor = xcoor;
}

public int getYcoor() {
	return ycoor;
}

public void setYcoor(int ycoor) {
	this.ycoor = ycoor;
}

}