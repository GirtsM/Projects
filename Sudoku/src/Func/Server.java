package Func;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Server{
	public static int sentToClient[][] = new int[9][9];

	public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(9898);

        try {
            while(true){
                new TheServer(listener.accept()).start();      
            }
        } 
        finally{
            listener.close();
        }
        
	}
	
	public static class TheServer extends Thread{
        Socket socket;
        InputStream inputStream;
		OutputStream outputStream;
		
		ObjectOutputStream objectOutputStream;
		ObjectInputStream objectInputStream;
		
		Object object; 

        public int testPuzzle[][] = new int[9][9];
        int pareizi = 0;
    	ArrayList<Integer> laikaList = new ArrayList<Integer>();
    	
    	int minutes, sekundes;
    	int laiks;

        public TheServer(Socket socket) {
            this.socket = socket;
        }
        
    	public void puzzle(){
    		Sudoku sudoku = new Sudoku();
    		sudoku.generatePuzzle();

    		for(int i = 0; i < 9; i++){
    			for(int j = 0; j < 9; j++){
    				sentToClient[i][j] = sudoku.a[i][j];
    			}
    		}
    	}
    	
    	public void testSudoku(){
    		for(int i = 0; i < 9; i++){
    			for(int j = 0; j < 9; j++){
    				if(!(sentToClient[i][j] == testPuzzle[i][j])){
    					pareizi++;
    				}
    			}
    		}
    	}
    	
    	public String rezulats(){
			File file = new File("top.txt");		
			Reader reader;
			try {
				reader = new FileReader(file);
				BufferedReader buffReader = new BufferedReader(reader);
				String s = "";	
				try {
					while((s = buffReader.readLine()) != null){
						StringTokenizer tokenizer = new StringTokenizer(s, "");
						laikaList.add(Integer.parseInt(tokenizer.nextToken()));

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int laiks = minutes * 60 + sekundes;
			laikaList.add(laiks);
			Collections.sort(laikaList);
			
			int vieta = vietaTop();
			String log;
			if(vieta > 100){
				log = "Pareizi! Ar laiku " + minutes + "m " + sekundes + "s esat ierindojies arpus TOP100";
				
			}
			else{
				log = "Pareizi! Ar laiku " + minutes + "m " + sekundes + "s esat ierindojies " + vieta + ". vieta!";
			}
			File file2 = new File("top.txt");
			PrintWriter printwriter;
			try {
				printwriter = new PrintWriter(file2);
				int k = 0;
				for(int i = 0; i < laikaList.size() && k < 100; i++){
					if(k > 0){
						if(!(laikaList.get(i) == laikaList.get(i - 1))){
							printwriter.print(laikaList.get(i));
							printwriter.println("");
							k++;
						}
					}
					else{
						printwriter.print(laikaList.get(i));
						printwriter.println("");
						k++;
					}
				}
				printwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return log;
    	}

    	public int vietaTop(){
    		int laiks = minutes * 60 + sekundes;
    		int vieta = 1;
    		
    		for(int i = 0; i < laikaList.size(); i++){
    			if(laiks > laikaList.get(i)){
    				vieta++;
    			}
    		}
    		return vieta;
    	}
    	
        public void run() {
            try {
            	
            	inputStream = socket.getInputStream();
        		outputStream = socket.getOutputStream();
        		
        		objectOutputStream = new ObjectOutputStream(outputStream);
        		objectInputStream = new ObjectInputStream(inputStream);
        		
                while (true) {
                	try{
	                	object = objectInputStream.readObject();
	                	
	                	if(object.equals("puzzle")){
	                    	puzzle();
	                    	ArrayList<Integer> puzzle = new ArrayList<Integer>();
	                    	
	                    	int mixedPuzzle[][] = new int[9][9];
	                    	Random r = new Random();
	                    	int skaitli = 0;
	                    	while(skaitli < 79){
		                    	for(int i = 0; i < 9; i++){
		                    		for(int j = 0; j < 9; j++){
		                    			int i1=r.nextInt(9);
		                    			if(j == i1 && mixedPuzzle[i][j] != sentToClient[i][j]){
		                    				mixedPuzzle[i][j] = sentToClient[i][j];
		                    				skaitli++;
		                    			}
		                    		}
		                    	}
	                    	}
	
	                    	for(int i = 0; i < 9; i++){
	                    		for(int j = 0; j < 9; j++){
	                    			puzzle.add(mixedPuzzle[i][j]);
	                    		}
	                    	}
	
	                    	objectOutputStream.writeObject(puzzle);
	                    	puzzle.clear();
	                    }
	                    else{
	                    	ArrayList<Integer> puzzle2 = new ArrayList<Integer>();
	        				puzzle2 = (ArrayList<Integer>) object;
	
	        				int k = 0;
	        				for(int i = 0; i < 9; i++){
	                    		for(int j = 0; j < 9; j++){
	                    			testPuzzle[i][j] = puzzle2.get(k);
	                    			k++;
	                    		}
	                    	}
	        				laiks = puzzle2.get(k);
	        				while(laiks > 59){
	        					laiks -= 60;
	        					minutes++;
	        				}
	        				sekundes = laiks;
	        				testSudoku();
	        				String info;
	        				if(pareizi == 0){
	        					info = rezulats();
	        				}
	        				else{
	        					info = "Nepareizi";
	        					
	        				}
	        				JOptionPane.showMessageDialog(null, info);
	                    }
                	}catch(IOException e){
                	}
                }
                
            } catch (IOException | ClassNotFoundException e){
            	// TODO Auto-generated catch block
				e.printStackTrace();
            }
            
        }
    }
}