package Pieces;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Knight {
	
	private int X, Y;
	private Image bilde;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Point point;
	private Point pixelPoint = new Point();
    
    public Knight(String icon, int sakumaX, int sakumaY){
    	bilde = toolkit.getImage(icon);
		X = sakumaX;
		Y = sakumaY;
		point = new Point();
		point.x = X;
		point.y = Y;
    }
    
    public String getVieta(){
    	String log;
    	log = X + "|" + Y;
    	return log;
    }

    public Image getBilde(){
    	return bilde;
    }

	public void setPoint(Point newPoint){
        point.x = newPoint.x;
        point.y = newPoint.y;
        
        X = point.x;
        Y = point.y;
	}

	public void setNewX(int newX){
		X = newX;
		point.x = X;
	}
	
	public void setNewY(int newY){
		Y = newY;
		point.y = Y;
	}
	
	public Point returnPoziciju(){
		return (Point)point.clone();
    }
	
	public boolean possibleMove(int x, int y){
		if((x + 1 == X) && (y + 2 == Y)
			||(x + 1 == X) && (y - 2 == Y)
			||(x - 1 == X) && (y + 2 == Y)
			||(x - 1 == X) && (y - 2 == Y)
			||(x + 2 == X) && (y + 1 == Y)
		    ||(x + 2 == X) && (y - 1 == Y)
		    ||(x - 2 == X) && (y + 1 == Y)
		    ||(x - 2 == X) && (y - 1 == Y)) 
			return true;
		else{
			return false;
		}
	}

	public void setPixels(int newpixelX, int newpixelY){
        pixelPoint.x = newpixelX;
        pixelPoint.y = newpixelY;
    }

    public Point getpixelPoint(){
        return pixelPoint;
    }
}
