package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.text.NumberFormatter;

public class ChessGUI extends JFrame implements ActionListener{

	private JMenuBar menuBar;
	private JMenu iespejas;
	private JMenu jaunaSpele;
	private JMenuItem saktLocal, saktServer, saktClient, saglabatSpeli, ieladetSpeli, iziet;
	private JPanel spelesLaukums;
	private SpelesInfoPanelis spelesInfo;
	private ChessBoardGUI sahaLauks;
	private String kolonnas;
	private JLabel[] colLabels, rowLabels;
	
	private JaunaSpeleServer jaunaSpeleServer;
	private JaunaSpeleClient jaunaSpeleClient;
	
	private JFileChooser saglabat, ieladet;

	public ChessGUI(){
		this.setLayout(null);
		this.setBounds(20, 20, 1000, 688);
		this.setResizable(false); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Chess");

		spelesInfo = new SpelesInfoPanelis();
		spelesInfo.setBounds(this.getWidth() - 340, 20, 340, this.getHeight() - 20);
		this.add(spelesInfo);
		
		sahaLauks = new ChessBoardGUI(spelesInfo);

		jaunaSpele = new JMenu("Jauna Spele");
		
		saglabatSpeli = new JMenuItem("Saglabat Speli");
		saglabatSpeli.addActionListener(this);
		
		ieladetSpeli = new JMenuItem("Ieladet Speli");
		ieladetSpeli.addActionListener(this);
		
		iziet = new JMenuItem("Iziet");
		iziet.addActionListener(this);
		
		saktLocal = new JMenuItem("Sakt Speli Lokali");
		saktLocal.addActionListener(this);
		jaunaSpele.add(saktLocal);
		
		saktServer = new JMenuItem("Sakt Speli Ka Serveris");
		saktServer.addActionListener(this);
		jaunaSpele.add(saktServer);
		
		saktClient = new JMenuItem("Sakt Speli Ka Klients");
		saktClient.addActionListener(this);
		jaunaSpele.add(saktClient);

		iespejas = new JMenu("Iespejas");
		iespejas.add(jaunaSpele);
		iespejas.add(saglabatSpeli);
		iespejas.add(ieladetSpeli);
		iespejas.add(iziet);
		
		menuBar = new JMenuBar();
		menuBar.add(iespejas);
		menuBar.setBounds(this.getWidth() - 340, 0, 340, 20);
		this.add(menuBar);

		spelesLaukums = new JPanel();
		spelesLaukums.setLayout(null);
		spelesLaukums.setLocation(0, 0);
		spelesLaukums.setSize(660, 660);
		spelesLaukums.setBackground(Color.darkGray);
		
		this.add(spelesLaukums);	
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(saktLocal)){
			sakt();
        	sahaLauks.spelesInfo.saktJaunuLocal();
		}
		if(e.getSource().equals(saktServer)){
			jaunaSpeleServer = new JaunaSpeleServer();
			jaunaSpeleServer.setVisible(true);
		}
		if(e.getSource().equals(saktClient)){
			jaunaSpeleClient = new JaunaSpeleClient();
			jaunaSpeleClient.setVisible(true);
		}
		if(e.getSource().equals(saglabatSpeli)){
			if(sahaLauks.local == true){
				saglabat = new JFileChooser();
				int returnVal = saglabat.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					String test = "";
					for(int i = 0; i < 8; i++){
						test += sahaLauks.player1.pawn[i].getVieta() + "|";
						test += sahaLauks.player1.pawn[i].getPakustinats() + "|";
					}
					test += sahaLauks.player1.rook1.getVieta() + "|";
					test += sahaLauks.player1.rook2.getVieta() + "|";
					
					test += sahaLauks.player1.knight1.getVieta() + "|";
					test += sahaLauks.player1.knight2.getVieta() + "|";
					
					test += sahaLauks.player1.bishop1.getVieta() + "|";
					test += sahaLauks.player1.bishop2.getVieta() + "|";
					
					test += sahaLauks.player1.queen.getVieta() + "|";
					test += sahaLauks.player1.king.getVieta();
					
					test += "\r\n";
					
					for(int i = 0; i < 8; i++){
						test += sahaLauks.player2.pawn[i].getVieta() + "|";
						test += sahaLauks.player2.pawn[i].getPakustinats() + "|";
					}
					test += sahaLauks.player2.rook1.getVieta() + "|";
					test += sahaLauks.player2.rook2.getVieta() + "|";
	
					test += sahaLauks.player2.knight1.getVieta() + "|";
					test += sahaLauks.player2.knight2.getVieta() + "|";
	
					test += sahaLauks.player2.bishop1.getVieta() + "|";
					test += sahaLauks.player2.bishop2.getVieta() + "|";
	
					test += sahaLauks.player2.queen.getVieta() + "|";
					test += sahaLauks.player2.king.getVieta();
					
					try(FileWriter fw = new FileWriter(saglabat.getSelectedFile()+".txt")){
					    fw.write(test.toString());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            }
			}
			else{
				JOptionPane.showMessageDialog(null, "Var saglabat tikai lokalu speli!");
			}
		}
		if(e.getSource().equals(ieladetSpeli)){
			if(sahaLauks.local == true){
				ieladet = new JFileChooser();
				int returnVal = ieladet.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
	                File file = ieladet.getSelectedFile();
	                Reader reader;
	                try{
						reader = new FileReader(file);
						BufferedReader buffReader = new BufferedReader(reader);
						String s = "";
						int reize = 1;
						while((s = buffReader.readLine()) != null){
							StringTokenizer tokenizer = new StringTokenizer(s, "|");
							if(reize == 1){
								for(int i = 0; i < 8; i++){
									int pawnX = Integer.parseInt(tokenizer.nextToken());
									int pawnY = Integer.parseInt(tokenizer.nextToken());
									int pawnKustiba = Integer.parseInt(tokenizer.nextToken());
									sahaLauks.player1.pawn[i].setNewX(pawnX);
									sahaLauks.player1.pawn[i].setNewY(pawnY);
									if(pawnKustiba == 1){
										sahaLauks.player1.pawn[i].setPakustinats(false);
									}
									else{
										sahaLauks.player1.pawn[i].setPakustinats(true);
									}
								}
								
								int rook1X = Integer.parseInt(tokenizer.nextToken());
								int rook1Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.rook1.setNewX(rook1X);
								sahaLauks.player1.rook1.setNewY(rook1Y);
								
								int rook2X = Integer.parseInt(tokenizer.nextToken());
								int rook2Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.rook2.setNewX(rook2X);
								sahaLauks.player1.rook2.setNewY(rook2Y);
								
								int knight1X = Integer.parseInt(tokenizer.nextToken());
								int knight1Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.knight1.setNewX(knight1X);
								sahaLauks.player1.knight1.setNewY(knight1Y);
								
								int knight2X = Integer.parseInt(tokenizer.nextToken());
								int knight2Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.knight2.setNewX(knight2X);
								sahaLauks.player1.knight2.setNewY(knight2Y);
								
								int bishop1X = Integer.parseInt(tokenizer.nextToken());
								int bishop1Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.bishop1.setNewX(bishop1X);
								sahaLauks.player1.bishop1.setNewY(bishop1Y);
								
								int bishop2X = Integer.parseInt(tokenizer.nextToken());
								int bishop2Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.bishop2.setNewX(bishop2X);
								sahaLauks.player1.bishop2.setNewY(bishop2Y);
								
								int queenX = Integer.parseInt(tokenizer.nextToken());
								int queenY = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.queen.setNewX(queenX);
								sahaLauks.player1.queen.setNewY(queenY);
								
								int kingX = Integer.parseInt(tokenizer.nextToken());
								int kingY = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player1.king.setNewX(kingX);
								sahaLauks.player1.king.setNewY(kingY);
	
								reize = 2;
							}
							else{
								for(int i = 0; i < 8; i++){
									int pawnX = Integer.parseInt(tokenizer.nextToken());
									int pawnY = Integer.parseInt(tokenizer.nextToken());
									int pawnKustiba = Integer.parseInt(tokenizer.nextToken());
									sahaLauks.player2.pawn[i].setNewX(pawnX);
									sahaLauks.player2.pawn[i].setNewY(pawnY);
									if(pawnKustiba == 1){
										sahaLauks.player2.pawn[i].setPakustinats(false);
									}
									else{
										sahaLauks.player2.pawn[i].setPakustinats(true);
									}
								}
								
								int rook1X = Integer.parseInt(tokenizer.nextToken());
								int rook1Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.rook1.setNewX(rook1X);
								sahaLauks.player2.rook1.setNewY(rook1Y);
								
								int rook2X = Integer.parseInt(tokenizer.nextToken());
								int rook2Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.rook2.setNewX(rook2X);
								sahaLauks.player2.rook2.setNewY(rook2Y);
								
								int knight1X = Integer.parseInt(tokenizer.nextToken());
								int knight1Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.knight1.setNewX(knight1X);
								sahaLauks.player2.knight1.setNewY(knight1Y);
								
								int knight2X = Integer.parseInt(tokenizer.nextToken());
								int knight2Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.knight2.setNewX(knight2X);
								sahaLauks.player2.knight2.setNewY(knight2Y);
								
								int bishop1X = Integer.parseInt(tokenizer.nextToken());
								int bishop1Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.bishop1.setNewX(bishop1X);
								sahaLauks.player2.bishop1.setNewY(bishop1Y);
								
								int bishop2X = Integer.parseInt(tokenizer.nextToken());
								int bishop2Y = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.bishop2.setNewX(bishop2X);
								sahaLauks.player2.bishop2.setNewY(bishop2Y);
								
								int queenX = Integer.parseInt(tokenizer.nextToken());
								int queenY = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.queen.setNewX(queenX);
								sahaLauks.player2.queen.setNewY(queenY);
								
								int kingX = Integer.parseInt(tokenizer.nextToken());
								int kingY = Integer.parseInt(tokenizer.nextToken());
								sahaLauks.player2.king.setNewX(kingX);
								sahaLauks.player2.king.setNewY(kingY);
							}
						}
					}
	                catch (IOException e1){
						e1.printStackTrace();
					}
	            }
				sahaLauks.repaint();
			}
			else{
				JOptionPane.showMessageDialog(null, "Speles ieladi var veikt tikai lokalaja versija!");
			}
		}
		
		if(e.getSource().equals(iziet)){
			 System.exit(0);
		}
	}

	public void pievienoKolonnasRindas_nosaukumi(){
		kolonnas = new String("ABCDEFGH");
		colLabels = new JLabel[8];
		rowLabels = new JLabel[8];
		
		for(int i = 0; i < 8; i++){
			colLabels[i] = new JLabel(kolonnas.substring(i, i + 1), SwingConstants.CENTER);
			colLabels[i].setBounds(30 + (600 / 8)*i, 10, 600 / 8, 10);
			colLabels[i].setForeground(Color.white);

			rowLabels[i] = new JLabel((8-i) + "");
			rowLabels[i].setBounds(12, 60 + (600 / 8)*i, 30, 10);
			rowLabels[i].setForeground(Color.white);

			spelesLaukums.add(colLabels[i]);
			spelesLaukums.add(rowLabels[i]);
		}
	
	}

	private class JaunaSpeleServer extends JDialog implements ActionListener{
	    
		JButton button1;
	    JButton button2;
	    JPanel panel;
	    JLabel ipAddress, port, punkts1, punkts2, punkts3;
	    JFormattedTextField portField;
	    JFormattedTextField ipField1, ipField2, ipField3, ipField4;

	    public JaunaSpeleServer(){
	    	this.setSize(300, 200);
	        this.setLocation(300, 150);
	        this.setTitle("Serveris");
	        
	        panel = new JPanel();
	        panel.setSize(300, 300);
	        panel.setLayout(null);

	        ipAddress = new JLabel("IP adrese:");
	        ipAddress.setSize(100, 30);
	        ipAddress.setLocation(20, 10);
	        panel.add(ipAddress);

	        NumberFormatter ipFormat = new NumberFormatter();  
	        ipFormat.setMinimum(new Integer(0));  
	        ipFormat.setMaximum(new Integer(255));
	        
	        NumberFormatter portFormat = new NumberFormatter();  
	        portFormat.setMinimum(new Integer(0));
	        portFormat.setMaximum(new Integer(65535)); 
	        
	        ipField1 = new JFormattedTextField(ipFormat);
	        ipField1.setValue(new Integer(127));
	        ipField1.setLocation(110, 10);
	        ipField1.setSize(30, 30);
	        panel.add(ipField1);
	        
	        punkts1 = new JLabel(".");
	        punkts1.setSize(10, 30);
	        punkts1.setLocation(140, 10);
	        panel.add(punkts1);
	        
	        ipField2 = new JFormattedTextField(ipFormat);
	        ipField2.setValue(new Integer(0));
	        ipField2.setLocation(150, 10);
	        ipField2.setSize(30, 30);
	        panel.add(ipField2);
	        
	        punkts2 = new JLabel(".");
	        punkts2.setSize(10, 30);
	        punkts2.setLocation(180, 10);
	        panel.add(punkts2);
	        
	        ipField3 = new JFormattedTextField(ipFormat);
	        ipField3.setValue(new Integer(0));
	        ipField3.setLocation(190, 10);
	        ipField3.setSize(30, 30);
	        panel.add(ipField3);
	        
	        punkts3 = new JLabel(".");
	        punkts3.setSize(10, 30);
	        punkts3.setLocation(220, 10);
	        panel.add(punkts3);
	        
	        ipField4 = new JFormattedTextField(ipFormat);
	        ipField4.setValue(new Integer(1));
	        ipField4.setLocation(230, 10);
	        ipField4.setSize(30, 30);
	        panel.add(ipField4);
	        
	        port = new JLabel("Ports:");
	        port.setSize(100, 30);
	        port.setLocation(20, 50);
	        panel.add(port);
	        
	        portField = new JFormattedTextField(portFormat);
	        portField.setColumns(3);
	        portField.setValue(new Integer(9001));
	        portField.setLocation(110, 50);
	        portField.setSize(50, 30);
	        panel.add(portField);
	        
	        button1 = new JButton("Sakt");
	        button1.setSize(100, 30);
	        button1.setLocation(30, 110);
	        button1.addActionListener(this);
	        panel.add(button1);
	        
	        button2 = new JButton("Atcelt");
	        button2.setSize(100, 30);
	        button2.setLocation(150, 110);
	        button2.addActionListener(this);
	        panel.add(button2);

	        Container cp = getContentPane();
	        cp.add(panel);
	    }

		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource().equals(button1)){
				startServer();
		        sahaLauks.spelesInfo.saktJaunu();
		        dispose();
			}
			if(e.getSource().equals(button2)){
		        dispose();
			}	
		}
		
		public String getIp(){
			String log;
			log = ipField1.getValue() + "." + ipField2.getValue() + "." + ipField3.getValue() + "." + ipField4.getValue();
	        return log;
	    }
	    
	    public String getPort(){
	    	String log;
	    	log = portField.getValue() + "";
	        return log;
	    }
	}
	
	private class JaunaSpeleClient extends JDialog implements ActionListener{
	    
		JButton button1;
	    JButton button2;
	    JPanel panel;
	    JLabel ipAddress, port, punkts1, punkts2, punkts3;
	    JFormattedTextField portField;
	    JFormattedTextField ipField1, ipField2, ipField3, ipField4;

	    public JaunaSpeleClient(){
	    	this.setSize(300, 200);
	        this.setLocation(300, 150);
	        this.setTitle("Serveris");
	        
	        panel = new JPanel();
	        panel.setSize(300, 300);
	        panel.setLayout(null);

	        ipAddress = new JLabel("IP adrese:");
	        ipAddress.setSize(100, 30);
	        ipAddress.setLocation(20, 10);
	        panel.add(ipAddress);

	        NumberFormatter ipFormat = new NumberFormatter();  
	        ipFormat.setMinimum(new Integer(0));  
	        ipFormat.setMaximum(new Integer(255));
	        
	        NumberFormatter portFormat = new NumberFormatter();  
	        portFormat.setMinimum(new Integer(0));
	        portFormat.setMaximum(new Integer(65535)); 
	        
	        ipField1 = new JFormattedTextField(ipFormat);
	        ipField1.setValue(new Integer(127));
	        ipField1.setLocation(110, 10);
	        ipField1.setSize(30, 30);
	        panel.add(ipField1);
	        
	        punkts1 = new JLabel(".");
	        punkts1.setSize(10, 30);
	        punkts1.setLocation(140, 10);
	        panel.add(punkts1);
	        
	        ipField2 = new JFormattedTextField(ipFormat);
	        ipField2.setValue(new Integer(0));
	        ipField2.setLocation(150, 10);
	        ipField2.setSize(30, 30);
	        panel.add(ipField2);
	        
	        punkts2 = new JLabel(".");
	        punkts2.setSize(10, 30);
	        punkts2.setLocation(180, 10);
	        panel.add(punkts2);
	        
	        ipField3 = new JFormattedTextField(ipFormat);
	        ipField3.setValue(new Integer(0));
	        ipField3.setLocation(190, 10);
	        ipField3.setSize(30, 30);
	        panel.add(ipField3);
	        
	        punkts3 = new JLabel(".");
	        punkts3.setSize(10, 30);
	        punkts3.setLocation(220, 10);
	        panel.add(punkts3);
	        
	        ipField4 = new JFormattedTextField(ipFormat);
	        ipField4.setValue(new Integer(1));
	        ipField4.setLocation(230, 10);
	        ipField4.setSize(30, 30);
	        panel.add(ipField4);
	        
	        port = new JLabel("Ports:");
	        port.setSize(100, 30);
	        port.setLocation(20, 50);
	        panel.add(port);
	        
	        portField = new JFormattedTextField(portFormat);
	        portField.setColumns(3);
	        portField.setValue(new Integer(9001));
	        portField.setLocation(110, 50);
	        portField.setSize(50, 30);
	        panel.add(portField);
	        
	        button1 = new JButton("Sakt");
	        button1.setSize(100, 30);
	        button1.setLocation(30, 110);
	        button1.addActionListener(this);
	        panel.add(button1);
	        
	        button2 = new JButton("Atcelt");
	        button2.setSize(100, 30);
	        button2.setLocation(150, 110);
	        button2.addActionListener(this);
	        panel.add(button2);

	        Container cp = getContentPane();
	        cp.add(panel);
	    }

		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource().equals(button1)){
				dispose();
		        startClient();
		        sahaLauks.spelesInfo.saktJaunu();
		        //dispose();
			}
			if(e.getSource().equals(button2)){
		        dispose();
			}	
		}
		
		public String getIp(){
			String log;
			log = ipField1.getValue() + "." + ipField2.getValue() + "." + ipField3.getValue() + "." + ipField4.getValue();
	        return log;
	    }
	    
	    public String getPort(){
	    	String log;
	    	log = portField.getValue() + "";
	        return log;
	    }
	    
	}

	private void startServer(){
        sahaLauks.startServer(jaunaSpeleServer.getIp(), jaunaSpeleServer.getPort());
        spelesLaukums.add(sahaLauks);
		pievienoKolonnasRindas_nosaukumi();
		spelesLaukums.repaint();
        setTitle("Servera speletajs"); 
    }
	
	private void startClient(){
    	sahaLauks.startClient(jaunaSpeleClient.getIp(), jaunaSpeleClient.getPort());
    	spelesLaukums.add(sahaLauks);
		pievienoKolonnasRindas_nosaukumi();
		spelesLaukums.repaint();
        setTitle("Klienta speletajs");
    }
    
	private void sakt(){
        sahaLauks.sakt();
        spelesLaukums.add(sahaLauks);
		pievienoKolonnasRindas_nosaukumi();
		spelesLaukums.repaint();
		setTitle("Local speletaji");
    }
}
