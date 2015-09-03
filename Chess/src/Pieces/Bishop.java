package Pieces;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class Bishop {
	
	private int X, Y;
	private Image bilde;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Point point;
	private Point pixelPoint = new Point();
    
    public Bishop(String icon, int sakumaX, int sakumaY){
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
        if((x - y) == (X - Y)){
            return true;
        }
        else if((x + y) == (X + Y)){
            return true;  
        }
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

    public boolean piecePrieksa(int x, int y,Point other){
        int j = y;
        int i = x;

        if((x - y) == (X - Y)){
            if(x > X && y > Y){
                while((j != Y + 1) && (i != X + 1)){
                    j--;
                    i--;
                    if((other.y == j) && (other.x == i)){
                        return true;
                    }
                }
            }

            else if(x < X && y < Y){
                while((j != Y - 1) && (i != X - 1)){
	                j++;
	                i++;
	                if((other.y == j) && (other.x == i)){
	                    return true;
	                }  
                }
            }
        }
        
        else if((x + y) == (X + Y)){
            if((X < i) && (Y > j)){
                while((j != Y - 1) && (i != X + 1)){
                    j++;
                    i--;

                    if((other.y == j) && (other.x == i)){
                        return true;
                    } 
                } 
            }

            else if((X > i) && (Y < j)){
                while((j != X + 1) && (i != X - 1)){
                    j--;
                    i++;
                    
                    if((other.y == j) && (other.x == i)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean checkKing(int x, int y, Point other){
        int j = y;
        int i = x;

        if((x - y) == (X - Y)){
            if(x > X && y > Y){
                while((j != Y) && (i != X)){
                    j--;
                    i--;
                    
                    if((other.y == j) && (other.x == i)){
                        return true;
                    }
                }
            }

            else if(x < X && y < Y){
                while((j != Y) && (i != X)){
	                j++;
	                i++;

	                if((other.y == j) && (other.x == i)){
	                    return true;
	                }
                }
            }
        }
        
        else if((x + y) == (X + Y)){
            if((X < i) && (Y > j)){
                while((j != Y) && (i != X)){
                    j++;
                    i--;

                    if((other.y == j) && (other.x == i)){
                        return true;
                    } 
                } 
            }

            else  if((X > i) && (Y < j)){
                while((j != X) && (i != X)){
                    j--;
                    i++;
                    
                    if((other.y ==j ) && (other.x == i)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
