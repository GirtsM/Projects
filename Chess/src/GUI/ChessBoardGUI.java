package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Players.BlackPlayer;
import Players.WhitePlayer;

public class ChessBoardGUI extends JPanel{
	
	public WhitePlayer player1;
	public BlackPlayer player2;

	private short speletajaKarta = 1;
	private boolean gameOver = false;
	private boolean gameStarted = true;
	private Rectangle2D laukums;
	
	boolean sutit;
	
	public boolean local = true;
	private boolean server = false;
	private boolean client = false;

	private int dalijums = 600/8; //paredzets laukuma izveidei
	private String Box;
	public SpelesInfoPanelis spelesInfo;
   
	private BufferedReader in;
	private PrintWriter out;
    
	private String ipAddress;
	private String portNumber;
	private chessThread thread;
	private ServerSocket ServerSock;
	private Socket Sock;

	public ChessBoardGUI(SpelesInfoPanelis spelesInfo){
		
		this.spelesInfo = spelesInfo;
		this.setLayout(null);
		this.setSize(600, 600);
		this.setBackground(Color.WHITE);
		this.setLocation(30, 30);
		
		player1 = new WhitePlayer();
		player2 = new BlackPlayer();
		
		VelkKaulinu velk = new VelkKaulinu();
        KaulinsAizvilkts aizvilkts = new KaulinsAizvilkts();
        this.addMouseMotionListener(velk);
        this.addMouseListener(aizvilkts);
	
	}
	
	
	//##############################
	//Laukums
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for(int i = 0; i < 8; i = i + 2){
            for(int j = 0; j < 8; j = j + 2){
                g2.setColor(Color.BLACK);
                laukums = new Rectangle2D.Double(j * dalijums, (1 + i) * dalijums, dalijums, dalijums);
                g2.fill(laukums);
                laukums = new Rectangle2D.Double((1 + j) * dalijums, i * dalijums, dalijums, dalijums);
                g2.fill(laukums);
            }
        }
        
        /// Novietojam kaulinus
        Point pozicija;
        int postX;
        int postY;
        Image img;
        for (int i = 1; i <= 32; i++){
            if(i < 17){
                if(i == player2.getPanemtais()){
                	pozicija = player2.getPixelPoint(i);
                }
                 else{
                	 pozicija = player2.returnPoziciju(i);
                 }
                img = player2.returnImage(i);                
            }
            else{
                if(i == player1.getPanemtais()){
                	pozicija = player1.getPixelPoint(i);
                }
                else{
                	pozicija = player1.returnPoziciju(i);  
                }
                img = player1.returnImage(i);
            }
            
            if(i == player1.getPanemtais() || i == player2.getPanemtais()){
            	if((i > 8 && i < 17) || (i > 24 && i < 33)){
            		g2.drawImage(img, pozicija.x - 25, pozicija.y - 20, dalijums - 38, dalijums - 18, this);
            	}
            	else{
            		g2.drawImage(img, pozicija.x - 25, pozicija.y - 25, dalijums - 36, dalijums - 8, this);
            	}
            }
            else{
                postX = rinda(pozicija.x);
                postY = kol(pozicija.y);
                if((i > 8 && i < 17) || (i > 24 && i < 33)){
            		g2.drawImage(img, postX + 19, postY + 14, dalijums - 38, dalijums - 18, this);
                }
            	else{
            		g2.drawImage(img, postX + 19, postY + 4, dalijums - 36, dalijums - 8, this);
            	}
            } 
        }  
    }
	
	
	private int rinda(int r){
        int garums = 600; 
        return (r * garums / 8) - dalijums;
    }
	
    private int kol(int c){
        int platums = 600;
        return (c * platums / 8) - dalijums;
    }

    
    
    public void sakt(){
        player1 = new WhitePlayer();
        player2 = new BlackPlayer();
        speletajaKarta = 1;
        gameOver = false;
        
        
        /*
        if(server){
        	 try {
        		in.close();
        		out.close();
        		Sock.close();
        		ServerSock.close();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
        }
        else if( client){
        	 try {
        		in.close();
         		out.close();
 				Sock.close();
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
        }
        */
        
        local = true;
        server = false;
        client = false;
        repaint(); 
    }

    
    
    private class VelkKaulinu implements MouseMotionListener{ //klase, kas lauj vilkt kaulinu
		@Override
		public void mouseDragged(MouseEvent e){
			int x = e.getX();
            int y = e.getY();
            if(spelesTips(x,y)){
                repaint();
            }
		}
		
		@Override
		public void mouseMoved(MouseEvent arg0){
			// TODO Auto-generated method stub

		}
	}
    
    
	private boolean spelesTips(int x,int y){
        if((server == true || client == true) && gameStarted){
            if(server && speletajaKarta == 1){
                return getPoziciju(x,y);
            } 
            else if(client && speletajaKarta == 2){
                return getPoziciju(x,y);
            }
            else{
                return false;
            }
        }
        else{
            return getPoziciju(x,y);
        }
    }

	
	private boolean getPoziciju(int x, int y){  
		if(!gameOver && gameStarted){
            if((server && speletajaKarta == 1) || (local) || (client && speletajaKarta == 2)){
                int newX = x / dalijums;
                int newY = y / dalijums;
                newX++; //dalijums atgriez no 0 - 7, kaulinu ieraksta koordinates no 1-8. tapec ++
                newY++;
                
                if(newX > 8 || newY > 8 || newX < 1 || newY < 1){
                    repaint();
                    return false;
                }
                
                if(speletajaKarta == 1 && player1.getPanemtais() == -1){ //Player 1
                    for(int i = 17; i <= 32; i++){
                        Point p = player1.returnPoziciju(i);
                        if(p.x == newX && p.y == newY){
                            player1.setPanemtais(i); 
                            kadParvieto(x,y);
                            return true;
                        }
                    }
                }
                else if(speletajaKarta == 2 && player2.getPanemtais() == -1){ //Player 2
                    for(int i = 1; i <= 16; i++){
                        Point p = player2.returnPoziciju(i);
                        if(p.x == newX && p.y == newY){
                            player2.setPanemtais(i); 
                            kadParvieto(x,y);
                            return true;
                        }
                    }
                }
                
                else if(speletajaKarta == 1 && player1.getPanemtais() != -1){ //Player 1
                    kadParvieto(x,y);
                    return true;
                }
                else if(speletajaKarta == 2&& player2.getPanemtais() != -1){ //Player 2
                    kadParvieto(x,y);
                    return true;
                }
                
                player1.setPanemtais(-1);
                return false;
            }
		}
        return false;
    }

	private boolean kadParvieto(int x,int y){
        if(speletajaKarta == 1 && player1.getPanemtais() != -1){
        	player1.changePixel(x, y, player1.getPanemtais());
        	return true;
        }
        else if(speletajaKarta == 2 && player2.getPanemtais() !=- 1){
        	player2.changePixel(x, y, player2.getPanemtais());
        	return true;
        }
        return false;
    }

	
    private class KaulinsAizvilkts implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0){
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0){
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0){
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0){
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0){
			sutit = false;
			
			if(!gameOver){
				veiktGajienu();
			}
		}
    }
    
    
    
    //################# GAJIENA VEIKSANAS FUNKCIJA
    public void veiktGajienu(){
    	Point jaunaVieta, vecaVieta; //Point jauna kaulina pozicija, vecaa pozicija
		
		//#################################################
		if(player1.getPanemtais() != -1 ){// BALTIE KAULINI
			
			jaunaVieta = player1.getPixelPoint(player1.getPanemtais());
			jaunaVieta.x /= dalijums;
			jaunaVieta.y /= dalijums;
			jaunaVieta.x++;
			jaunaVieta.y++;
			Point tagad = player1.returnPoziciju(player1.getPanemtais());
			
			if(server || local){
				if(player1.getPanemtais() <= 32 && player1.getPanemtais() >= 25){
					for(int i = 1; i<=16 ; i++){
						vecaVieta = player2.returnPoziciju(i);
						if(vecaVieta.x == jaunaVieta.x && vecaVieta.y == jaunaVieta.y){
							if(player1.setRedzetsPawns(player1.getPanemtais(), vecaVieta))
								break;
						}
					}
				}

				if(!(jaunaVieta.x == tagad.x && jaunaVieta.y == tagad.y)){
					if(player1.parbauditGajienu(jaunaVieta, player1.getPanemtais())){ //ja nu gajiens ir neatlauts
						boolean prieksa = false; //zinos par to, ka kads kaulins prieksaa
						for(int i = 1; i <= 32; i++){
							if(player1.getPanemtais() != i){
								if(i < 17){
									prieksa = player1.parbauditCelu(jaunaVieta, player2.returnPoziciju(i), player1.getPanemtais());
								}
								else{
									prieksa = player1.parbauditCelu(jaunaVieta, player1.returnPoziciju(i), player1.getPanemtais());
								}
								if(prieksa == true){
									break;
								}
							}	
						}

						if(!prieksa && player1.laucinsAiznemts(jaunaVieta)){
							boolean nosist = false;
							boolean beidzGajienu = true;
							int nogalinats = -1;
							Point vecais = new Point();
							Point otrs = new Point();
							Point dayum = new Point();
							vecais.x = player1.returnPoziciju(player1.getPanemtais()).x;
							vecais.y = player1.returnPoziciju(player1.getPanemtais()).y;

							for (int k = 1; k < 17; k++){
								otrs = player2.returnPoziciju(k);
								if(jaunaVieta.x == otrs.x && jaunaVieta.y == otrs.y){
									int panemtais = player1.getPanemtais();
									if(panemtais > 24 && player1.returnRedzetsPawn(panemtais)){
										nosist = true;
										dayum.x = otrs.x;
										dayum.y = otrs.y;
										player2.killedKaulins(k);
									}
									else if( panemtais <= 24 ){
										nosist = true;
										dayum.x = otrs.x;
										dayum.y = otrs.y;
										player2.killedKaulins(k);
									}
									else{
										player1.changePoziciju(vecais, panemtais);
										beidzGajienu = false;
										break;
									}
									nogalinats = k;
									break;
								}
							}
							
							if(beidzGajienu){
								player1.changePoziciju(jaunaVieta, player1.getPanemtais());
							}		
							player1.chekotKarali(false);
							if(player1.parbauditKaralisChekots(player2)){
								//gadijumaa, ja karalis ir briesmaas, pec noteikumiem
								//nedrikst veikt gajienu, kas nepalidzes karalim izklut no briesmam ("SHAHS!")
								player1.changePoziciju(vecais, player1.getPanemtais());
								player1.chekotKarali(true);
								beidzGajienu = false;
							}
							if(nosist && player1.returnChekotKarali()){
								player2.changePoziciju(dayum, nogalinats);

							}
							if(!player1.returnChekotKarali()){
								if(player2.parbauditKaralisChekots(player1)){
									player2.chekotKarali(true);
									beidzGajienu = false;
									if(player2.checkMateGameOver(player1)){
										gameOver();
										Box = Integer.toString(player2.getPanemtais()) + Integer.toString(jaunaVieta.x) + Integer.toString(jaunaVieta.y);
										sutit = true;
									}
									else{
										Box = Integer.toString(player1.getPanemtais()) + Integer.toString(jaunaVieta.x) + Integer.toString(jaunaVieta.y);
										CheckStatus();
										sutit = true;
									}					
								}
								
								if(beidzGajienu){
									Box = Integer.toString(player1.getPanemtais()) + Integer.toString(jaunaVieta.x) + Integer.toString(jaunaVieta.y);
									changeTurn();
									sutit = true;
								}
							}
						}
					}
				}

				player1.setPanemtais(-1);
				repaint();
				if(sutit && (server || client)){
					sendMove();
				}
				
				if(gameOver){
                    JOptionPane.showConfirmDialog(null,"Sahs un Mats!\n Uzvareja baltie kaulini.","Spele Beigusies",JOptionPane.DEFAULT_OPTION);
                    spelesInfo.stopTimers();
                    
                    try {
						in.close();
						out.close();
						Sock.close();
						ServerSock.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		//######################################################
		else if(player2.getPanemtais() != -1){ // MELNIE KAULINI
            if(client || local){
            	
            	
                jaunaVieta=player2.getPixelPoint(player2.getPanemtais());
                jaunaVieta.x /= dalijums;
                jaunaVieta.y /= dalijums;
                jaunaVieta.x++;
                jaunaVieta.y++;
                Point present = player2.returnPoziciju(player2.getPanemtais());
                
                if(player2.getPanemtais() < 17 && player2.getPanemtais() > 8){
                    for(int i = 17; i < 33; i++){
                        vecaVieta = player1.returnPoziciju(i);
                        if(vecaVieta.x == jaunaVieta.x && vecaVieta.y == jaunaVieta.y){
                            if(player2.setRedzetsPawns(player2.getPanemtais(), vecaVieta)){
                                break;
                            }
                        }
                    }
                }

                if(!(jaunaVieta.x == present.x && jaunaVieta.y == present.y))
                    if(player2.parbauditGajienu(jaunaVieta, player2.getPanemtais())){
                        boolean prieksa = false;
                        for(int i = 1; i <= 32; i++){
                            if(player2.getPanemtais() != i){
                                if(i < 17){
                                	prieksa = player2.parbauditCelu(jaunaVieta, player2.returnPoziciju(i), player2.getPanemtais());
                                }
                                else{
                                	prieksa = player2.parbauditCelu(jaunaVieta, player1.returnPoziciju(i), player2.getPanemtais());
                                }
                                if(prieksa){
                                	break;
                                }
                            }
                        }
                        
                        for(int i = 1; i <= 16 && !prieksa; i++){
                            if(player2.getPanemtais() != i ){
                                if(prieksa == false){
                                    vecaVieta = player2.returnPoziciju(i);
                                    if(jaunaVieta.x == vecaVieta.x && jaunaVieta.y == vecaVieta.y){
                                    	prieksa = true;
                                        break; 
                                    }
                                }
                            }
                            if(prieksa){
                            	break;
                            }
                        }
                        if(!prieksa){
                            Point vecais = new Point();
                            vecais.x = player2.returnPoziciju(player2.getPanemtais()).x;
                            vecais.y = player2.returnPoziciju(player2.getPanemtais()).y;
                            
                            Point otrs = new Point();
                            Point dayum = new Point();
                            
                            boolean nosist = false;
                            boolean beidzGajienu = true;
                            int nogalinats = -1;

                            for(int k = 17; k < 33; k++){
                                otrs = player1.returnPoziciju(k);
                                if(jaunaVieta.x == otrs.x && jaunaVieta.y == otrs.y){
                                    int panemtais = player2.getPanemtais();

                                    if(panemtais > 8 && player2.returnRedzetsPawn(panemtais)){
                                    	nosist = true;
                                        dayum.x = otrs.x;
                                        dayum.y = otrs.y;
                                        player1.killedKaulins(k);
                                    }
                                    else if(panemtais <= 8){
                                    	nosist = true;
                                        dayum.x = otrs.x;
                                        dayum.y = otrs.y;
                                        player1.killedKaulins(k);
                                    }
                                    else{
                                    	beidzGajienu = false;
                                        player2.changePoziciju(vecais, panemtais);
                                    }
                                    nogalinats = k;
                                    break; 
                                } 
                            }
                            if(beidzGajienu){
                                player2.changePoziciju(jaunaVieta, player2.getPanemtais());
                            }
                            
                            player2.chekotKarali(false);
                            if(player2.parbauditKaralisChekots(player1)){
                                player2.changePoziciju(vecais, player2.getPanemtais());
                                player2.chekotKarali(true);
                                beidzGajienu = false;
                            }
                            if(nosist && player2.returnChekotKarali()){
                                player1.changePoziciju(dayum, nogalinats);
                            }
                            if(player2.returnChekotKarali()){
                                player2.changePoziciju(vecais, player2.getPanemtais());
                            }

                            if(!player2.returnChekotKarali()){
                                if(player1.parbauditKaralisChekots(player2)){
                                    player1.chekotKarali(true);
                                    beidzGajienu = false;

                                    if(player1.checkMateGameOver(player2)){
                                        Box = Integer.toString(player2.getPanemtais()) + Integer.toString(jaunaVieta.x) + Integer.toString(jaunaVieta.y);
                                        gameOver();
                                        sutit = true;
                                    }
                                    else{
                                        Box = Integer.toString(player2.getPanemtais()) + Integer.toString(jaunaVieta.x) + Integer.toString(jaunaVieta.y);
                                        CheckStatus();
                                        sutit = true;
                                    }
                                }

                                if(beidzGajienu) {
                                    Box = Integer.toString(player2.getPanemtais()) + Integer.toString(jaunaVieta.x) + Integer.toString(jaunaVieta.y);
                                    changeTurn();
                                    sutit = true;
                                }
                            }
                        }
                    }
                
                player2.setPanemtais(-1);
                repaint();
                if(sutit && (server || client)){
                	sendMove();   
                }
                if(gameOver){
                    JOptionPane.showConfirmDialog(null,"Sahs un Mats!\n Uzvareja melnie kaulini.","Spele Beigusies",JOptionPane.DEFAULT_OPTION);
                    spelesInfo.stopTimers();
                    try {
						in.close();
						out.close();
						Sock.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    
                }
            }
		}
    }
    

    private void gameOver(){
        gameOver = true;
    }

    private void CheckStatus(){
        if(speletajaKarta == 1){
        	speletajaKarta = 2;
        }
        else if(speletajaKarta == 2){
        	speletajaKarta = 1;
        }
    }

    private void changeTurn(){
        if(speletajaKarta == 1){
        	speletajaKarta = 2;
        }
        else if(speletajaKarta == 2){
        	speletajaKarta = 1;
        }
        spelesInfo.changeTurn();
    }

    private void sendMove(){
        out.print(Box);
        out.print("\r\n");
        out.flush();
    }

    public void startServer(String Ip,String Port){
    	spelesInfo.timer1.stop();
    	spelesInfo.timer2.stop();
    	
        thread = new chessThread();
        ipAddress = Ip;
        portNumber = Port;
        sakt();
        local = false;
        server = true;
        repaint();
        
        gameOver = true;
        
        try{
            ServerSock = new ServerSocket(Integer.parseInt(portNumber));
            Thread Server = new Thread(new Runnable(){
                public synchronized  void run(){
                    try{
                        Sock = ServerSock.accept();
                        spelesInfo.timer1.start();
                        in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
                        out = new PrintWriter(Sock.getOutputStream());
                        thread.start();
                        gameOver = false;
                    }
                    catch (IOException ex){
                        ex.printStackTrace();
                    }
                }
            });
            Server.start();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void startClient(String Ip,String Port){
    	spelesInfo.timer1.stop();
    	spelesInfo.timer2.stop();
    	
        thread = new chessThread();
        sakt();
        ipAddress = Ip;
        portNumber = Port;
        local = false;
        client = true;
        try{
            Sock = new Socket(ipAddress,Integer.parseInt(portNumber));
            spelesInfo.timer1.start();
            in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
            out = new PrintWriter(Sock.getOutputStream());
            thread.start();
        }
        catch(NumberFormatException | IOException ex){
           // ex.printStackTrace();
        }
    }

    private class chessThread extends Thread{
        public synchronized void run(){
            while(true){
                try{
                    Box = in.readLine(); 
                }
                catch (IOException ex){
                	
                    spelesInfo.timer1.stop();
                    spelesInfo.timer2.stop();
                    if(server)
                    	spelesInfo.speletajsIzgajis((byte)1);
                    else if(client)
                    	spelesInfo.speletajsIzgajis((byte)2);
                    
                    gameOver = true;
                    
                    break;// kad pazud connection, partrauc run funkciju, speele apstajas
                }
                if(Box != null){
                    int panemtais = Integer.parseInt(Box);
                    int newX = Integer.parseInt(Box);
                    int newY = Integer.parseInt(Box);

                    panemtais /= 100;
                    newX -= (panemtais * 100);
                    newX /= 10;
                    newY -= (panemtais * 100) + (newX * 10);

                    if(speletajaKarta == 1){
                        player1.setPanemtais(panemtais);
                        speletajaKarta = 2;
                        player1.changePoziciju(new Point(newX,newY),panemtais);
                        player2.killedKaulins((player1.getLaucinsAiznemtsIenaidnieks(new Point(newX,newY),player2)));
                        player2.chekotKarali(false);
                        
                        if(player2.parbauditKaralisChekots(player1)){ 
                        	player2.chekotKarali(true);
                            if(player2.checkMateGameOver(player1)){
                                gameOver();
                            }
                        }
                        player1.setPanemtais(-1);                    
                    }
                    else{
                    	player2.setPanemtais(panemtais);
                    	player2.changePoziciju(new Point(newX,newY),panemtais);
                    	player1.killedKaulins(player2.getLaucinsAiznemtsIenaidnieks(new Point(newX,newY),player1));
                    	speletajaKarta = 1;
                        player1.chekotKarali(false);
                        if(player1.parbauditKaralisChekots(player2)){
                        	player1.chekotKarali(true);
                            if(player1.checkMateGameOver(player2)){
                                gameOver();
                            }
                        }
                        player2.setPanemtais(-1);
                    }
                    repaint();
                }
                spelesInfo.changeTurn();
            }
        }
    }
}
