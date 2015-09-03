package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SpelesInfoPanelis extends JPanel implements ActionListener{
	
	private JTextField speletajs1, speletajs2;
	Timer timer1, timer2;
	private long seconds1, seconds2;
	private JLabel taimeris1, taimeris2, spelesIlgums, domaGajienu;
	 
	public SpelesInfoPanelis(){
		this.setSize(290, 580);
		this.setLocation(600, 600);
		this.setLayout(null);
		this.setBackground(Color.GRAY);
		
		taimeris1 = new JLabel();
		taimeris1.setBounds(129, 30, 100, 20);
		taimeris1.setForeground(Color.white);
		taimeris1.setText("0s");

		taimeris2 = new JLabel();
		taimeris2.setBounds(129, 80, 100, 20);
		taimeris2.setForeground(Color.white);
		taimeris2.setText("0s");
		
		spelesIlgums = new JLabel();
		spelesIlgums.setBounds(30, 130, 200, 20);
		spelesIlgums.setForeground(Color.white);
		spelesIlgums.setText("Speles ilgums:     0s");

		domaGajienu = new JLabel("domaa ");
		domaGajienu.setForeground(Color.orange);

		timer1 = new Timer(1000, this );
		timer2 = new Timer(1000, this );
		
		speletajs1 = new JTextField("Baltie kaulini");
		speletajs1.setDisabledTextColor(Color.BLACK);
		speletajs1.setEnabled(false);
		speletajs1.setBounds(30, 30, 83, 20);
		
		speletajs2 = new JTextField("Melnie kaulini");
		speletajs2.setBounds(30, 80, 83, 20);
		speletajs2.setDisabledTextColor(Color.BLACK);
		speletajs2.setEnabled(false);
		
		this.add(speletajs1);
		this.add(speletajs2);
		this.add(taimeris1);
		this.add(taimeris2);
		this.add(spelesIlgums);
		this.add(domaGajienu);
	}
	
	public void changeTurn(){
		if(speletajs2.getBackground() == Color.orange ){		
			speletajs1.setBackground(Color.orange);
			taimeris1.setForeground(Color.orange);
			speletajs2.setBackground(Color.white);
			taimeris2.setForeground(Color.white);

			domaGajienu.setLocation(175, 30);
			domaGajienu(seconds1);
			
			timer1.start();
			timer2.stop();
		}
		else{
			speletajs1.setBackground(Color.white);
			taimeris1.setForeground(Color.white);
			speletajs2.setBackground(Color.orange);
			taimeris2.setForeground(Color.orange);

			domaGajienu.setLocation(175, 80);
			domaGajienu( seconds2 );
			
			timer2.start();
			timer1.stop();
		}
	}

	public void speletajsIzgajis( byte kaarta ){
		if(kaarta == 1){	
			speletajs1.setBackground(Color.white);
			taimeris1.setForeground(Color.white);
			speletajs2.setBackground(Color.red);
			taimeris2.setForeground(Color.red);

			domaGajienu.setLocation(175, 80);
			domaGajienu.setForeground(Color.red);
			domaGajienu.setText("Speletajs pametis speli!");
			
			timer2.stop();
		}
		else{
			speletajs2.setBackground(Color.white);
			taimeris2.setForeground(Color.white);
			speletajs1.setBackground(Color.red);
			taimeris1.setForeground(Color.red);

			domaGajienu.setLocation(175, 30);
			domaGajienu.setForeground(Color.red);
			domaGajienu.setText("Speletajs pametis speli!");
			
			timer1.stop();
		}
	}
	
	
	public void saktJaunuLocal(){
		saktJaunu();
		timer1.start();
		timer2.stop();
	}
	
	public void stopTimers(){
		timer1.stop();
		timer2.stop();
	}

	public void saktJaunu(){
		speletajs1.setBackground(Color.orange);
		taimeris1.setForeground(Color.orange);
		domaGajienu.setBounds(175, 30, 200, 20);
		
		speletajs2.setBackground(Color.white);
		taimeris2.setForeground(Color.white);
		
		taimeris1.setText("0s");
		taimeris2.setText("0s");
		spelesIlgums.setText("Speles ilgums:     0s");
		
		seconds1 = 0;
		seconds2 = 0;
	}

	private void domaGajienu(long seconds){
		domaGajienu.setForeground(Color.orange);
		
		if(seconds %4 == 0){
			domaGajienu.setText("domaa ....");
		}
		else if(seconds %4 == 1){
			domaGajienu.setText("domaa .");
		}
		else if(seconds %4 == 2){
			domaGajienu.setText("domaa ..");
		}
		else{
			domaGajienu.setText("domaa ...");
		}
	}
	
	private void spelesIlgumaTaimeris(long s1, long s2){
		if(s1 + s2 < 60){ //sekundes
			spelesIlgums.setText( "Speles ilgums:     " + (s1 + s2) + "s");
		}
		else if ((s1 + s2) < 60 * 60){ //minutes un sekundes
			spelesIlgums.setText( "Speles ilgums:     " + (s1 + s2) / 60 + "m " + ((s1 + s2) - (s1 + s2) / 60 * 60) + "s");
		}
		else{ // stundas 
			spelesIlgums.setText( "Speles ilgums:     " + ((s1 + s2) / 3600) + "h " + ((s1 + s2) / 60 - (s1 + s2) / 3600 * 60 ) + "m " + ((s1 + s2) - (s1 + s2) / 60 * 60) + "s");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(timer1)){
			seconds1++;
			if(seconds1 < 60){ //sekundes
				taimeris1.setText(seconds1 + "s");
			}
			else if(seconds1 < 60 * 60){ //minutes un sekundes
				taimeris1.setText(seconds1 / 60 + "m " + (seconds1 - seconds1 / 60 * 60) + "s");
			}
			else{ // stundas 
				taimeris1.setText((seconds1 / 3600) + "h " + (seconds1 / 60 - seconds1 / 3600 * 60 ) + "m " + (seconds1 - seconds1 / 60 * 60) + "s");
			}
			
			domaGajienu( seconds1 );
			spelesIlgumaTaimeris( seconds1, seconds2 );

		}
		else if(e.getSource().equals(timer2)){
			seconds2++;
			if(seconds2 < 60){ //sekundes
				taimeris2.setText( seconds2 + "s");
			}
			else if(seconds2 < 60 * 60){ //minutes un sekundes
				taimeris2.setText(seconds2 / 60 + "m " + (seconds2 - seconds2 / 60 * 60) + "s");
			}
			else{ //stundas 
				taimeris2.setText((seconds2 / 3600) + "h " + (seconds2 / 60 - seconds2 / 3600 * 60) + "m " + (seconds2 - seconds2 / 60 * 60) + "s");
			}
			
			domaGajienu(seconds2);
			spelesIlgumaTaimeris(seconds1, seconds2);
		}
	}	
}
