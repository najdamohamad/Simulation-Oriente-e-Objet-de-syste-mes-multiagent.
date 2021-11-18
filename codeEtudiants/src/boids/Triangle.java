package boids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import gui.GraphicalElement;

public class Triangle implements GraphicalElement{
	   private Color drawColor;
	   private Color fillColor;
	   private int size;
	   private Point direction;
	   private int x;
	   private int y;

	   public Triangle(int x, int y, Point direction, Color drawColor, Color fillColor, int size){
	        this.x = x;
	        this.y = y;
	        this.drawColor = drawColor;
	        this.fillColor = fillColor;
	        this.direction=direction;
	        this.size = size;
	    }

	   public int getX() {
		   return x;
	    }

	   public int getY() {
	      return y;
	    }


	    @Override
	   public void paint(Graphics2D g2d){
	    	Point vecteurOrthogonal=new Point();
	    	Point v = new Point(direction.x,direction.y);
	    	int norme = (int) Math.sqrt(Math.pow(v.x,2)+Math.pow(v.y,2));
	    	if(v.x ==0 && v.y==0) {
	    		vecteurOrthogonal.x=1; vecteurOrthogonal.y=-1;
	    		v.x=1; v.y=1;
	    		norme=1;
	    	}
	    	
	    	else {
	    		vecteurOrthogonal.x=v.y;
	    		vecteurOrthogonal.y=-v.x;
	    	}
	        int x1 = x + size*(v.x/norme);
	        int y1 = y + size*(v.y/norme);
	        int x2 = x + (size/2)*(vecteurOrthogonal.x/norme);
	        int y2 = y + (size/2)*(vecteurOrthogonal.y/norme);
	        int x3 = x - (size/2)*(vecteurOrthogonal.x/norme);
	        int y3 = y + (size/2)*(vecteurOrthogonal.y/norme);
	        int[] xPoints = {x1, x2, x3};
	        int[] yPoints = {y1, y2, y3};
	        g2d.setColor(drawColor);
	        g2d.drawPolygon(xPoints, yPoints, 3);
	        g2d.setColor(fillColor);
	        g2d.fillPolygon(xPoints, yPoints, 3);

	    }

	    // @Override
	   public void translate(int dx, int dy) {
	      this.x += dx;
	      this.y += dy;
	    }

	   public String toString() {
	    return "";
	 }
}
