package GUI;

public class Main {
	
	private ChessGUI gui;
	
	public Main(){
		gui = new ChessGUI();
		gui.setVisible(true);
	}
	
	public static void main(String[] args){
		new Main();
	}
}
