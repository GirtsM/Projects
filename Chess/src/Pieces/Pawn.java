package Pieces;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Pawn {
	
	private int X, Y;
	private Image bilde;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Point point;
	private Point pixelPoint = new Point();
	private boolean pakustinats = false;
	private boolean redzets = false;
    
    public Pawn(String icon, int sakumaX, int sakumaY){
    	bilde = toolkit.getImage(icon);
		X = sakumaX;
		Y = sakumaY;
		point = new Point();
		point.x = X;
		point.y = Y;
    }
    
    public String getPakustinats(){
    	String log;
    	if(pakustinats == false){
    		log = "1";
    	}
    	else{
    		log = "0";
    	}
    	return log;
    }
    
    public void setPakustinats(boolean kustiba){
    	pakustinats = kustiba;
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
        
        pakustinats = true;
        redzets = false;
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
	
	public boolean possibleMove(int x, int y, String typeColor){
        if(typeColor.equals("black")){
            if((y - 1 == Y) && (x == X)){
                return true;
            }
            else if(((y-2 == Y) && (x == X)) && !pakustinats){
                return true;
            }
            else if(((y - 1 == Y && x + 1 == X) || (y - 1 == Y && x - 1 == X)) && redzets){
                return true;
            }
            else{
            	return false;
            }
        }
        
        else if(typeColor.equals("white")) {
            if((y + 1 == Y) && (x == X)){
                return true;
            }
            else if(((y + 2 == Y) && (x == X)) && !pakustinats){
                return true;
            }
            else if((((y + 1 == Y) && (x + 1 == X)) || (y + 1 == Y && x - 1 == X)) && redzets){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
	}

	public void setPixels(int newpixelX, int newpixelY){
        pixelPoint.x = newpixelX;
        pixelPoint.y = newpixelY;
    }

    public Point getpixelPoint(){
        return pixelPoint;
    }

    public boolean piecePrieksa(int x, int y, Point other, String typeColor ){
        if(Y - y == 2 || Y - y == -2){
            if((typeColor.equals("black"))){
                if(((y - 1 == other.y) && (x == other.x)) && !pakustinats){
                    return true;
                }
                else{
                	return false;
                }
            }
            
            else  if(typeColor.equals("white")){
                if(((y + 1 == other.y) && (x == other.x) && !pakustinats)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
    
    public void setRedzets(boolean newBoolean){
    	redzets = newBoolean;
    }
    
    public boolean returnRedzets(){
        return redzets;
    }
    
    public boolean setRedzetsCheck(Point newP,String Color){
    	redzets = false;
        if((Color.equals("black"))) {
            if(((newP.y - 1 == Y && newP.x + 1 == X) || (newP.y - 1 == Y && newP.x - 1 == X))){
            	redzets = true;
                return true;
            }
            else{
            	return false;
            }
        }
        else if(Color.equals("white")){
            if(((newP.y + 1 == Y && newP.x + 1 == X) || (newP.y + 1 == Y && newP.x - 1 == X))){
            	redzets = true;
                return true;
            }
            else{
            	return false;
            }
        }
        return false;
    }
}
