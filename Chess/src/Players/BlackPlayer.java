package Players;

import java.awt.Image;
import java.awt.Point;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class BlackPlayer {
	
	public Pawn[] pawn = new Pawn[8];
	public Rook rook1, rook2;
	public Knight knight1, knight2;
	public Bishop bishop1, bishop2;
	public Queen queen;
	public King king;
	
	private int izveletais;
	private int panemtaisKaulins = -1;
	private boolean karalisIrChekots = false;
	private String kraasa = "black";
	
	private Point citsPoint;
	private int aizsargaa;
		
	public BlackPlayer(){
		String fileSeparator = new String(System.getProperty("file.separator"));
		
		int j = 1;
        for(int i = 0; i <= 7; i++, j++){
            pawn[i] = new Pawn("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackPawn.png",j,2);
        }
        
        rook1 = new Rook("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackRook.png", 1,1);
        rook2 = new Rook("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackRook.png", 8,1);
        
        knight1 = new Knight("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackKnight.png",2,1);
        knight2 = new Knight("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackKnight.png",7,1);
        
        bishop1 = new Bishop("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackBishop.png",3,1);
        bishop2 = new Bishop("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackBishop.png",6,1);
        
        queen = new Queen("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackQueen.png",4,1);
        
        king = new King("src" + fileSeparator + "BlackPlayerPieces" + fileSeparator + "BlackKing.png",5,1);
	}
	
	public int getIzveletais(){
    	return izveletais;
    }

    public void setPanemtais(int i ){
        panemtaisKaulins = i;
    }
    
    public int getPanemtais(){
        return panemtaisKaulins;
    }

    public boolean varParvietot(int x,int y){
        return true;
    }
    
    public void chekotKarali(boolean check){
        karalisIrChekots = check;
    }
    
    public boolean returnChekotKarali(){
        return karalisIrChekots;
    }

	public Point returnPoziciju(int i){
        switch(i){
            case 1:return rook1.returnPoziciju();
            case 2:return rook2.returnPoziciju();
            case 3:return knight1.returnPoziciju();
            case 4:return knight2.returnPoziciju();
            case 5:return bishop1.returnPoziciju();
            case 6:return bishop2.returnPoziciju();
            case 7:return queen.returnPoziciju();
            case 8:return king.returnPoziciju();
            case 9:return pawn[0].returnPoziciju();
            case 10:return pawn[1].returnPoziciju();
            case 11:return pawn[2].returnPoziciju();
            case 12:return pawn[3].returnPoziciju();
            case 13:return pawn[4].returnPoziciju();
            case 14:return pawn[5].returnPoziciju();
            case 15:return pawn[6].returnPoziciju();
            case 16:return pawn[7].returnPoziciju();
        }
        return new Point(-1,-1);
    }

    public Image returnImage(int i){
        switch(i){
            case 1: return rook1.getBilde();
            case 2: return rook2.getBilde();
            case 3: return knight1.getBilde();
            case 4: return knight2.getBilde();
            case 5: return bishop1.getBilde();
            case 6: return bishop2.getBilde();
            case 7: return queen.getBilde();
            case 8: return king.getBilde();
            case 9: return pawn[0].getBilde();
            case 10: return pawn[1].getBilde();
            case 11: return pawn[2].getBilde();
            case 12: return pawn[3].getBilde();
            case 13: return pawn[4].getBilde();
            case 14: return pawn[5].getBilde();
            case 15: return pawn[6].getBilde();
            case 16: return pawn[7].getBilde();
        }
        return null;
    }
    
    public void changePoziciju(Point punkts,int i){
        switch(i){
            case 1: rook1.setPoint(punkts);break;
            case 2: rook2.setPoint(punkts);break;
            case 3: knight1.setPoint(punkts);break;
            case 4: knight2.setPoint(punkts);break;
            case 5: bishop1.setPoint(punkts);break;
            case 6: bishop2.setPoint(punkts);break;
            case 7: queen.setPoint(punkts);break;
            case 8: king.setPoint(punkts);break;
            case 9: pawn[0].setPoint(punkts);break;
            case 10: pawn[1].setPoint(punkts);break;
            case 11: pawn[2].setPoint(punkts);break;
            case 12: pawn[3].setPoint(punkts);break;
            case 13: pawn[4].setPoint(punkts);break;
            case 14: pawn[5].setPoint(punkts);break;
            case 15: pawn[6].setPoint(punkts);break;
            case 16: pawn[7].setPoint(punkts);break;
        }
    }

    public void changePixel(int newPixelX,int newPixelY,int i){
        switch(i){
            case 1: rook1.setPixels(newPixelX,newPixelY);break;
            case 2: rook2.setPixels(newPixelX,newPixelY);break;
            case 3: knight1.setPixels(newPixelX,newPixelY);break;
            case 4: knight2.setPixels(newPixelX,newPixelY);break;
            case 5: bishop1.setPixels(newPixelX,newPixelY);break;
            case 6: bishop2.setPixels(newPixelX,newPixelY);break;
            case 7: queen.setPixels(newPixelX,newPixelY);break;
            case 8: king.setPixels(newPixelX,newPixelY);break;
            case 9: pawn[0].setPixels(newPixelX,newPixelY);break;
            case 10: pawn[1].setPixels(newPixelX,newPixelY);break;
            case 11: pawn[2].setPixels(newPixelX,newPixelY);break;
            case 12: pawn[3].setPixels(newPixelX,newPixelY);break;
            case 13: pawn[4].setPixels(newPixelX,newPixelY);break;
            case 14: pawn[5].setPixels(newPixelX,newPixelY);break;
            case 15: pawn[6].setPixels(newPixelX,newPixelY);break;
            case 16: pawn[7].setPixels(newPixelX,newPixelY);break;
        }
    }
    
    public Point getPixelPoint(int i){
        switch(i){
            case 1: return rook1.getpixelPoint();
            case 2: return rook2.getpixelPoint();
            case 3: return knight1.getpixelPoint();
            case 4: return knight2.getpixelPoint();
            case 5: return bishop1.getpixelPoint();
            case 6: return bishop2.getpixelPoint();
            case 7: return queen.getpixelPoint();
            case 8: return king.getpixelPoint();
            case 9: return pawn[0].getpixelPoint();
            case 10: return pawn[1].getpixelPoint();
            case 11: return pawn[2].getpixelPoint();
            case 12: return pawn[3].getpixelPoint();
            case 13: return pawn[4].getpixelPoint();
            case 14: return pawn[5].getpixelPoint();
            case 15: return pawn[6].getpixelPoint();
            case 16: return pawn[7].getpixelPoint();
        }
        return null;
    }
    
    public boolean parbauditGajienu(Point newP,int i){
        switch(i){
            case 1 :return rook1.possibleMove(newP.x,newP.y);
            case 2: return rook2.possibleMove(newP.x,newP.y);
            case 3: return knight1.possibleMove(newP.x,newP.y);
            case 4: return knight2.possibleMove(newP.x,newP.y);
            case 5: return bishop1.possibleMove(newP.x,newP.y);
            case 6: return bishop2.possibleMove(newP.x,newP.y);
            case 7: return queen.possibleMove(newP.x,newP.y);
            case 8: return king.possibleMove(newP.x,newP.y);
            case 9: return pawn[0].possibleMove(newP.x,newP.y,kraasa);
            case 10: return pawn[1].possibleMove(newP.x,newP.y,kraasa);
            case 11: return pawn[2].possibleMove(newP.x,newP.y,kraasa);
            case 12: return pawn[3].possibleMove(newP.x,newP.y,kraasa);
            case 13: return pawn[4].possibleMove(newP.x,newP.y,kraasa);
            case 14: return pawn[5].possibleMove(newP.x,newP.y,kraasa);
            case 15: return pawn[6].possibleMove(newP.x,newP.y,kraasa);
            case 16: return pawn[7].possibleMove(newP.x,newP.y,kraasa);
        }
        return false;
    }
    
    public boolean setRedzetsPawns(int i,Point P){
        switch(i){
            case 9: return pawn[0].setRedzetsCheck(P,kraasa);
            case 10: return pawn[1].setRedzetsCheck(P,kraasa);
            case 11: return pawn[2].setRedzetsCheck(P,kraasa);
            case 12: return pawn[3].setRedzetsCheck(P,kraasa);
            case 13: return pawn[4].setRedzetsCheck(P,kraasa);
            case 14: return pawn[5].setRedzetsCheck(P,kraasa);
            case 15: return pawn[6].setRedzetsCheck(P,kraasa);
            case 16: return pawn[7].setRedzetsCheck(P,kraasa);
        }
        return false;
    }
    
    public boolean returnRedzetsPawn(int i){
        switch(i){
            case 9: return pawn[0].returnRedzets();
            case 10: return pawn[1].returnRedzets();
            case 11: return pawn[2].returnRedzets();
            case 12: return pawn[3].returnRedzets();
            case 13: return pawn[4].returnRedzets();
            case 14: return pawn[5].returnRedzets();
            case 15: return pawn[6].returnRedzets();
            case 16: return pawn[7].returnRedzets();
        }
        return false;
    }
    
    public boolean parbauditCelu(Point newP,Point pozicijaNoCitaPunkta,int i){
        switch(i){
            case 1: return rook1.piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta);
            case 2: return rook2.piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta);
            case 5: return bishop1.piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta);
            case 6: return bishop2.piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta);
            case 7: return queen.piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta);
            case 9: return pawn[0].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 10: return pawn[1].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 11: return pawn[2].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 12: return pawn[3].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 13: return pawn[4].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 14: return pawn[5].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 15: return pawn[6].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
            case 16: return pawn[7].piecePrieksa(newP.x,newP.y,pozicijaNoCitaPunkta,kraasa);
        }
        return false;
    }
    
    public boolean killedKaulins(int i){
        Point out = new Point(20,20);
        switch(i){
            case 1: rook1.setPoint(out);return true;
            case 2: rook2.setPoint(out);return true;
            case 3: knight1.setPoint(out);return true;
            case 4: knight2.setPoint(out);return true;
            case 5: bishop1.setPoint(out);return true;
            case 6: bishop2.setPoint(out);return true;
            case 7: queen.setPoint(out);return true;
            case 8: return false;
            case 9: pawn[0].setPoint(out);return true;
            case 10: pawn[1].setPoint(out);return true;
            case 11: pawn[2].setPoint(out);return true;
            case 12: pawn[3].setPoint(out);return true;
            case 13: pawn[4].setPoint(out);return true;
            case 14: pawn[5].setPoint(out);return true;
            case 15: pawn[6].setPoint(out);return true;
            case 16: pawn[7].setPoint(out);return true;
        }
        return false;
    }
    
    public boolean parbauditKing(Point p1,Point p2,int i){
        switch(i){
            case 1: return rook1.checkKing(p1.x,p1.y,p2);
            case 2: return rook2.checkKing(p1.x,p1.y,p2);
            case 3: return knight1.possibleMove(p1.x,p1.y);
            case 4: return knight2.possibleMove(p1.x,p1.y);
            case 5: return bishop1.checkKing(p1.x,p1.y,p2);
            case 6: return bishop2.checkKing(p1.x,p1.y,p2);
            case 7: return queen.checkKing(p1.x,p1.y,p2);
            case 9: return pawn[0].possibleMove(p1.x,p1.y,kraasa);
            case 10: return pawn[1].possibleMove(p1.x,p1.y,kraasa);
            case 11: return pawn[2].possibleMove(p1.x,p1.y,kraasa);
            case 12: return pawn[3].possibleMove(p1.x,p1.y,kraasa);
            case 13: return pawn[4].possibleMove(p1.x,p1.y,kraasa);
            case 14: return pawn[5].possibleMove(p1.x,p1.y,kraasa);
            case 15: return pawn[6].possibleMove(p1.x,p1.y,kraasa);
            case 16: return pawn[7].possibleMove(p1.x,p1.y,kraasa);
        }
        return false;
    }

    public boolean parbauditKaralisChekots(WhitePlayer white){ //parbauditKaralisChekots
        Point kingPozicija = king.returnPoziciju();
        boolean prieksa = false;

        // saak parbaudit, vai karalis chekots
        for(int i = 17; i < 33; i++){
            if(i < 25){
                if(white.parbauditGajienu(kingPozicija,i)){ 
                	prieksa = true;
                    for(int j = 1; j < 33; j++){
                        if(j < 17){
                            if(white.parbauditCelu(kingPozicija,returnPoziciju(j),i)){
                                //kaut kas ir prieksaa, tapec nevar veitk gajienu pret karali
                            	prieksa = false;   
                            }
                        }
                        else{
                            if(j != 8){
                                if(white.parbauditCelu(kingPozicija,white.returnPoziciju(j),i)){
                                	prieksa = false;
                                //kaut kas ir prieksaa, tapec nevar veitk gajienu pret karali
                                }
                            }
                        }                  
                    }
                    if(prieksa){    
                        break;
                    }  
                }
            }
            else{
                // un prieks Pawns
                if(white.setRedzetsPawns(i,kingPozicija)){
                    break; 
                }
            }
            if(i == 32){
            	return false; 
            }
        }
        return true;
    }
    
    public boolean checkMateGameOver(WhitePlayer Enemy){
        if(!kingGajieni(Enemy)){
            panemtaisKaulins =- 1;
            return false;
        }
        else if(!rookGajieni(Enemy,rook1)){
        	panemtaisKaulins =- 1;
            return false;
        }
        else if(!rookGajieni(Enemy,rook2)){
        	panemtaisKaulins =- 1;
            return false;
        }
        else if(!bishopGajieni(Enemy,bishop1)){
        	panemtaisKaulins =- 1;
            return false;
        }
        else if(!bishopGajieni(Enemy,bishop2)){
        	panemtaisKaulins =- 1;
            return false;
        }
        else if(!knightGajieni(Enemy,knight1)){
        	panemtaisKaulins =- 1;
            return false;
        }
        else if(!knightGajieni(Enemy,knight2)){
        	panemtaisKaulins =- 1;
            return false;
        }
        
        else if(!queenGajieni(Enemy)){
        	panemtaisKaulins =- 1;
            return false;
        }
        
        for(int i = 0; i <= 7; i++){
        	panemtaisKaulins = 9 + i;
            if(!pawnGajieni(Enemy,pawn[i])){
            	panemtaisKaulins =- 1;
                return false;
            }
        }
        panemtaisKaulins =- 1;
        return true;
    }

    public boolean laucinsAiznemts(Point newP){
        Point vecaPozicija;
        for(int i = 1; i <= 16; i++){
            if(getPanemtais() != i){
                //Parbauda, vai melnie kaulini aiznem laucinu
                //Ja ta, tad nevar parvietot uz so laucinu, jo vienada krasa!!
            	vecaPozicija = returnPoziciju(i);
                if(newP.x == vecaPozicija.x && newP.y == vecaPozicija.y){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean laucinsAiznemtsIenaidnieks(Point newP,WhitePlayer enemy){
        Point vecaPozicija;
        for(int i = 17; i <= 32; i++){
            vecaPozicija=enemy.returnPoziciju(i);
            if(newP.x == vecaPozicija.x && newP.y == vecaPozicija.y){
                return false;
            }
        }
        return true;
    }

    public int getLaucinsAiznemtsIenaidnieks(Point newP,WhitePlayer enemy){
        Point vecaPozicija;
        for(int i = 17; i <= 32; i++){
            vecaPozicija = enemy.returnPoziciju(i);
            if(newP.x == vecaPozicija.x && newP.y == vecaPozicija.y){
                return i;
            }
        }
        return -1;
    }

    public boolean aizsargatKing(WhitePlayer enemy, Point newP){
        for(int i = 17; i <= 32; i++){
            citsPoint = enemy.returnPoziciju(i);
            if(citsPoint.x == newP.x && citsPoint.y == newP.y){
                aizsargaa = i;
                enemy.killedKaulins(i);
                return true;
            }
        }
        return false;
    }

    public boolean parbauditCelu(WhitePlayer enemy, Point newP){
        boolean prieksa = false;

        for(int i = 1; i <= 32; i++){
            if(panemtaisKaulins != i){
                if(i < 17){
                	prieksa = parbauditCelu(newP,enemy.returnPoziciju(i),panemtaisKaulins);
                }
                else{
                	prieksa = parbauditCelu(newP,returnPoziciju(i),panemtaisKaulins);
                }
                if(prieksa == true){
                	return false;
                }
            }
        }
        return true;
    }

    public boolean kingGajieni(WhitePlayer enemy){
        boolean nogalinats = false;
        Point punkts = new Point();
        Point parbaudit = new Point();
        panemtaisKaulins = 8;
        
        punkts = king.returnPoziciju();
        
        int x = punkts.x;
        int y = punkts.y;

        if(x + 1 <= 8){
            king.setNewX(x + 1);
            king.setNewY(y);
            parbaudit.x = x + 1;
            parbaudit.y = y;

            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                king.setPoint(punkts);
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(y + 1 <= 8){
            king.setNewX(x);
            king.setNewY(y+1);
            parbaudit.x = x;
            parbaudit.y = y + 1;
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts( parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                king.setPoint(punkts);
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(y - 1 > 0){
            king.setNewX(x);
            king.setNewY(y - 1);
            
            parbaudit.x = x;
            parbaudit.y = y - 1;
            
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                king.setPoint(punkts);
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(x - 1 > 0){
            king.setNewX(x - 1);
            king.setNewY(y);
            
            parbaudit.x = x - 1;
            parbaudit.y = y;
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
		                if(nogalinats){
		                    enemy.changePoziciju(citsPoint,aizsargaa);
		                    nogalinats = false;
		                }
	                king.setPoint(punkts);
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(y - 1 > 0 && x - 1 > 0){
            king.setNewX(x - 1);
            king.setNewY(y - 1);
            
            parbaudit.x = x - 1;
            parbaudit.y = y - 1;
            
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                king.setPoint(punkts);
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(y + 1 <= 8 && x + 1 <= 8){
            king.setNewX(x + 1);
            king.setNewY(y + 1);
            
            parbaudit.x = x + 1;
            parbaudit.y = y + 1;
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                king.setPoint(punkts);
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(y - 1 > 0 && x + 1 <= 8){
            
            king.setNewX(x + 1);
            king.setNewY(y - 1);
            
            parbaudit.x = x + 1;
            parbaudit.y = y - 1;
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                king.setPoint(punkts);
	                return false;
                }
            }
        }
        king.setPoint(punkts);
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        if(y + 1 <= 8 && x - 1 > 0){   
            king.setNewX(x - 1);
            king.setNewY(y + 1);
            
            parbaudit.x = x - 1;
            parbaudit.y = y + 1;
            if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                nogalinats = true;
            }
            if(laucinsAiznemts(parbaudit)){
                if(!parbauditKaralisChekots(enemy)){
	                if(nogalinats){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    nogalinats = false;
	                }
	                king.setPoint(punkts);
	                return false;
                }
            }
        }
        
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        
        
        king.setPoint(punkts);
        return true;
    }

    public  boolean bishopGajieni(WhitePlayer enemy,Bishop bishop){
        boolean nogalinats = false;
        Point punkts = new Point();
        Point parbaudit = new Point();
        
        punkts = bishop.returnPoziciju();
        
        if(bishop == bishop1){
        	panemtaisKaulins = 5;
        }
        else{
        	panemtaisKaulins = 6;
        }
        
        if(punkts.x != 20){
            for(int x = punkts.x, y = punkts.y; x >= 1 && y <= 8; x--, y++){
                bishop.setNewX(x);
                bishop.setNewY(y);
                parbaudit.x = x;
                parbaudit.y = y;
                if(parbauditCelu(enemy, punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        bishop.setPoint(punkts);
	                        return false;
                        }
                    }
                }
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            for(int x = punkts.x, y = punkts.y; y >= 1 && x <= 8; x++ ,y--){
                bishop.setNewX(x);
                bishop.setNewY(y);
                parbaudit.x = x;
                parbaudit.y = y;
                if(parbauditCelu(enemy, punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        bishop.setPoint(punkts);
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                } 
            }
            bishop.setPoint(punkts);
        }
        bishop.setPoint(punkts);

        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        return true;
    }

    public boolean knightGajieni(WhitePlayer enemy,Knight knight){
        Point punkts = new Point();
        boolean nogalinats = false;
        punkts = knight.returnPoziciju();
       
        Point parbaudit = new Point();
        
        if(knight == knight1){
        	panemtaisKaulins = 3;
        }
        else{
        	panemtaisKaulins = 4;
        }
        
        int x = punkts.x;
        int y = punkts.y;
        
        if(x != 20){
            if(x + 1 <= 8 && y + 1 <= 8){
                knight.setNewX(x + 1);
                knight.setNewY(y + 2);
                parbaudit.x = x + 1;
                parbaudit.y = y + 2;
                
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    knight.setPoint(punkts);
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    return false;
                    }
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            
            if(x + 1 <= 8 && y - 2 >= 1){
                knight.setNewX(x + 1);
                knight.setNewY(y - 2);
                parbaudit.x = x + 1;
                parbaudit.y = y - 2;
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            
            if(x + 2 <= 8 && y + 1 <= 8){
                knight.setNewX(x + 2);
                knight.setNewY(y + 1);
                parbaudit.x = x + 2;
                parbaudit.y = y + 1;
                
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            if(x + 2 <= 8 && y - 1 >= 1){
                knight.setNewX(x + 2);
                knight.setNewY(y - 1);
                parbaudit.x = x + 2;
                parbaudit.y = y - 1;
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            
            if(x - 1 >= 1 && y + 2 <= 8){
                knight.setNewX(x - 1);
                knight.setNewY(y + 2);
                parbaudit.x = x - 1;
                parbaudit.y = y + 2;
                
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            
            if(x - 1 >= 1 && y - 2 >= 1){
                knight.setNewX(x - 1);
                knight.setNewY(y - 2);
                parbaudit.x = x - 1;
                parbaudit.y = y - 2;
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            
            if(x - 2 >= 1 && y + 1 <= 8){
                knight.setNewX(x - 2);
                knight.setNewY(y + 1);
                parbaudit.x = x - 2;
                parbaudit.y = y + 1;
                
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                    nogalinats = true;
                }
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            
            if(x - 2 >= 1 && y - 1 >= 1){
                knight.setNewX(x - 2);
                knight.setNewY(y - 1);
                parbaudit.x = x - 2;
                parbaudit.y = y - 1;
                
                if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))) {
                    nogalinats = true;
                }
                
                if(laucinsAiznemts(parbaudit)){
                    if(!parbauditKaralisChekots(enemy)){
	                    if(nogalinats){
	                        enemy.changePoziciju(citsPoint,aizsargaa);
	                        nogalinats = false;
	                    }
	                    knight.setPoint(punkts);
	                    return false;
                    }
                }
            }
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
        }
        knight.setPoint(punkts);
        return true;
    }

    
    public boolean queenGajieni(WhitePlayer enemy){
        boolean nogalinats = false;
        Point punkts = new Point();
        punkts = queen.returnPoziciju();
        Point parbaudit = new Point();
        panemtaisKaulins = 7;
        
        if(punkts.x != 20){
            for(int x = punkts.x, y = punkts.y; x >= 1 && y <= 8; x--, y++){
                queen.setNewX(x);
                queen.setNewY(y);
                parbaudit.x = x;
                parbaudit.y = y;
                if(parbauditCelu(enemy, punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        queen.setPoint(punkts);
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                }
            }
            if(nogalinats) {
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            for(int x = punkts.x, y = punkts.y; y >= 1 && x <= 8; x++, y--){
                queen.setNewX(x);
                queen.setNewY(y);
                parbaudit.x = x;
                parbaudit.y = y;
                if(parbauditCelu(enemy, punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        queen.setPoint(punkts);
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                }
            }
            queen.setPoint(punkts);
            parbaudit.x = punkts.x;
            parbaudit.y = punkts.y;
            
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }

            for(int i = 1; i <= 8; i++){
                queen.setNewX(i);
                parbaudit.x = i;
                if(parbauditCelu(enemy, punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        queen.setNewX(punkts.x);
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                }
            }
            queen.setNewX(punkts.x);
            parbaudit.x = punkts.x;
            
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }

            for(int i = 1; i <= 8; i++){
                queen.setNewY(i);
                parbaudit.y = i;
                if(parbauditCelu(enemy, punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        queen.setNewY(punkts.y);
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                }
            }
            queen.setNewY(punkts.y);
        }

        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        return true;
    }


    
    public boolean rookGajieni(WhitePlayer enemy,Rook rook){
        boolean nogalinats = false;
        Point punkts = new Point();
        Point parbaudit = new Point();

        if(rook == rook1){
        	panemtaisKaulins = 1;
        }
        else{
        	panemtaisKaulins = 2;
        }

        punkts = rook.returnPoziciju();
        int x1 = punkts.x;
        int y1 = punkts.y;
        parbaudit.y = y1;

        if(x1 != 20){
            for(int i = 1; i <= 8; i++){
                rook.setNewX(i);
                parbaudit.x = i;
                if(parbauditCelu(enemy,punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))){
                        nogalinats = true;
                    }
                    
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        rook.setNewX(punkts.x);
	                        rook.setNewY(punkts.y);
	                        
	                        if(nogalinats){
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                }
            }
            rook.setNewX(punkts.x);
            parbaudit.x = punkts.x;
            
            if(nogalinats){
                enemy.changePoziciju(citsPoint,aizsargaa);
                nogalinats = false;
            }
            for(int i = 1; i <= 8; i++){
                rook.setNewY(i);
                parbaudit.y = i;
                if(parbauditCelu(enemy,punkts)){
                    if(aizsargatKing(enemy,returnPoziciju(panemtaisKaulins))) {
                        nogalinats = true;
                    }
                    if(laucinsAiznemts(parbaudit)){
                        if(!parbauditKaralisChekots(enemy)){
	                        rook.setNewX(punkts.x);
	                        rook.setNewY(punkts.y);
	                        
	                        if(nogalinats) {
	                            enemy.changePoziciju(citsPoint,aizsargaa);
	                            nogalinats = false;
	                        }
	                        return false;
                        }
                    }
                }
                
                if(nogalinats){
                    enemy.changePoziciju(citsPoint,aizsargaa);
                    nogalinats = false;
                } 
            }
            rook.setNewY(punkts.y);
        }
        
        if(nogalinats){
            enemy.changePoziciju(citsPoint,aizsargaa);
            nogalinats = false;
        }
        rook.setNewX(punkts.x);
        rook.setNewY(punkts.y);
        return true;
    }
    
    public boolean pawnGajieni(WhitePlayer enemy, Pawn pawn){
        Point punkts = new Point();
        punkts = pawn.returnPoziciju();
        Point parbaudit = new Point();
        parbaudit.x = punkts.x;
        parbaudit.y = punkts.y;

        if(punkts.x != 20){
            if(pawn.possibleMove(punkts.x, punkts.y + 2, kraasa) && punkts.y + 2 >= 1){
                pawn.setNewY(punkts.y + 2);
                parbaudit.y = punkts.y + 2;
                
                if(laucinsAiznemts(parbaudit)){
                    if(laucinsAiznemtsIenaidnieks(parbaudit, enemy)){
	                    if(!parbauditKaralisChekots(enemy)){
	                        pawn.setPoint(punkts);
	                        return false;
	                    }
                    }
                }
            }
            if(pawn.possibleMove(punkts.x, punkts.y + 1, kraasa) && punkts.y + 1 >= 1){
                pawn.setNewY(punkts.y + 1);
                parbaudit.y = punkts.y + 1;

                if(laucinsAiznemts(parbaudit)){
                    if(laucinsAiznemtsIenaidnieks(parbaudit, enemy)){
	                    if(!parbauditKaralisChekots(enemy)){
	                        pawn.setPoint(punkts);
	                        return false;
	                    }
                    }
                }
            }
            
            if(laucinsAiznemtsIenaidnieks(new Point(punkts.x - 1, punkts.y + 1), enemy)){
                if(aizsargatKing(enemy,new Point(punkts.x - 1, punkts.y + 1))){
	                if(!parbauditKaralisChekots(enemy)){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    pawn.setPoint(punkts);
	                    return false;
	                }
	                enemy.changePoziciju(citsPoint,aizsargaa);
                }
            }
            if(!laucinsAiznemtsIenaidnieks(new Point(punkts.x + 1, punkts.y + 1), enemy)){
                if(aizsargatKing(enemy,new Point(punkts.x + 1, punkts.y + 1))){
	                if(!parbauditKaralisChekots(enemy)){
	                    enemy.changePoziciju(citsPoint,aizsargaa);
	                    pawn.setPoint(punkts);
	                    return false;
	                }
	                enemy.changePoziciju(citsPoint,aizsargaa);
                }
            }
        }
        pawn.setPoint(punkts);
        return true;
    }
}
