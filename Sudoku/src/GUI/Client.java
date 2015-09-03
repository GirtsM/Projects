package GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Func.MyRunnable;
import Func.Sudoku;

public class Client extends JFrame implements ActionListener, KeyListener{
	
	ArrayList<JPanel> panelList = new ArrayList<JPanel>();
	ArrayList<ArrayList<JTextField>> fieldList = new ArrayList<ArrayList<JTextField>>();

	JPanel panel, panel2;
	JTextField field;
	JButton button1, button2, button3, button4;
	JLabel label1, label2;
	MyRunnable myRunnable;
	int i = 0;
	Sudoku sudoku;

    Socket socket;
    InputStream inputStream;
	OutputStream outputStream;
	
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	
	Thread t;
	Runnable runnable;
	
	int sudokuPuzzle[][] = new int[9][9];
	int gotPuzzle[][] = new int[9][9];

	
	public Client() throws UnknownHostException, IOException{
		this.setLayout(null);
		this.setBounds(0, 0, 800, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel(new GridLayout(3,3));
		panel.setBounds(100,80,500,500);
		this.add(panel);

		for(int i = 0; i < 9; i++){
			panel2 = new JPanel(new GridLayout(3,3));
			panel2.setBounds(0,0,200,200);
			panel2.setBorder(BorderFactory.createLineBorder(Color.black));
			ArrayList<JTextField> fieldList2 = new ArrayList<JTextField>();
			for(int j = 0; j < 9; j++){
				field = new JTextField();
				field.setHorizontalAlignment(JTextField.CENTER );
				field.setFont(new Font("Serif", Font.BOLD, 18));
				field.addKeyListener(this);
				fieldList2.add(field);
				panel2.add(field);
			}
			fieldList.add(fieldList2);
			panelList.add(panel2);
			panel.add(panel2);
		}
		
        	
		button1 = new JButton("Nodzest minejumus");
		button1.setBounds(60,600,166,30);
		button1.addActionListener(this);
		this.add(button1);
		
		button2 = new JButton("Apstadinat laiku");
		button2.setBounds(266,600,166,30);
		button2.addActionListener(this);
		this.add(button2);
		
		button3 = new JButton("Parbaudit minejumus");
		button3.setBounds(472,600,166,30);
		button3.addActionListener(this);
		this.add(button3);
		
		button4 = new JButton("Jauna spele");
		button4.setBounds(600,200,166,30);
		button4.addActionListener(this);
		this.add(button4);
		
		label1 = new JLabel("Laiks: ");
		label1.setBounds(620,250,50,30);
		this.add(label1);
		
		label2 = new JLabel();
		label2.setBounds(670,250,100,30);
		this.add(label2);
		
		socket = new Socket("localhost", 9898);
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
		
		objectInputStream = new ObjectInputStream(inputStream);
		objectOutputStream = new ObjectOutputStream(outputStream);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button3)){
			int tuksums = 0;
			for(int i = 0; i < fieldList.size(); i++){
				for(int j = 0; j < fieldList.get(i).size(); j++){
					if(fieldList.get(i).get(j).getText().equals("")){
						tuksums = 1;
					}
				}
			}
			
			if(tuksums == 1){
				JOptionPane.showMessageDialog(null, "Ludzu aizpildit visus laukus");
			}
			else{
				ArrayList<Integer> puzzle = new ArrayList<Integer>();
				if(i == 1){
					t.suspend();
					i = 2;
				}
				for(int i = 0; i < fieldList.size(); i++){
					for(int j = 0; j < fieldList.get(i).size(); j++){
						sudokuPuzzle[i][j] = Integer.parseInt(fieldList.get(i).get(j).getText());
					}
				}
				puzzle.clear();
				for(int i = 0; i < 9; i++){
					for(int j = 0; j < 9; j++){
						puzzle.add(sudokuPuzzle[i][j]);
					}
				}
				int minutes = myRunnable.j;
				int sekundes = myRunnable.i;
				int laiks = minutes * 60 + sekundes;
				puzzle.add(laiks);

				try {
					socket = new Socket("localhost", 9898);
					inputStream = socket.getInputStream();
					outputStream = socket.getOutputStream();
					
					objectInputStream = new ObjectInputStream(inputStream);
					objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(puzzle);
					t.resume();
					i = 1;
					puzzle.clear();
	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource().equals(button4)){
			if(i == 2){

				myRunnable.i = 0;
				myRunnable.j = 0;
				t.resume();
				i = 1;

			}
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					fieldList.get(i).get(j).setText("");
					fieldList.get(i).get(j).setEditable(true);
				}
			}
			
			try {
				objectOutputStream.writeObject("puzzle");
				ArrayList<Integer> puzzle = new ArrayList<Integer>();
				puzzle = (ArrayList<Integer>) objectInputStream.readObject();

				int k = 0;
				for(int i = 0; i < 9; i++){
            		for(int j = 0; j < 9; j++){
            			gotPuzzle[i][j] = puzzle.get(k);
            			k++;
            		}
            	}
				puzzle.clear();

			} catch (IOException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					if(gotPuzzle[i][j] == 0){
						fieldList.get(i).get(j).setText("");
					}
					else{
						fieldList.get(i).get(j).setText(gotPuzzle[i][j]+"");
						fieldList.get(i).get(j).setEditable(false);
					}
				}
			}

			this.repaint();
			this.revalidate();
			
			if(i == 1){
				myRunnable.i = 0;
				myRunnable.j = 0;
			}
			if(i == 0){
				i++;
				myRunnable = new MyRunnable(label2);
				t = new Thread(myRunnable);
				t.start();
			}
		}
		if(e.getSource().equals(button1)){
			for(int i = 0; i < fieldList.size(); i++){
				for(int j = 0; j < fieldList.get(i).size(); j++){
					if(fieldList.get(i).get(j).isEditable()){
						fieldList.get(i).get(j).setText("");
					}
				}
			}
		}
		
		if(e.getSource().equals(button2)){
			if(i == 1){
				t.suspend();
				i = 2;
			}
			else if(i == 2){
				t.resume();
				i = 1;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < fieldList.size(); i++){
			for(int j = 0; j < fieldList.get(i).size(); j++){
				if(e.getSource().equals(fieldList.get(i).get(j))){
					if(!(e.getKeyChar()>=49 && e.getKeyChar()<=57 || e.getKeyChar() == 8)){
						JOptionPane.showMessageDialog(null, "Drikst ievadit no 1 lidz 9!");
						fieldList.get(i).get(j).setText("");
					}
					else{
						if(!(fieldList.get(i).get(j).getText().equals("") || e.getKeyChar() == 8)){
							String temp = fieldList.get(i).get(j).getText();
							if(temp.length() > 0){
								JOptionPane.showMessageDialog(null, "Drikst ievadit no 1 lidz 9!");
								fieldList.get(i).get(j).setText("");
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
