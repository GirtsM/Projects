package Func;

import javax.swing.JLabel;

public class MyRunnable implements Runnable{
	JLabel label;
	
	Thread t = Thread.currentThread();
	
	public int i = 0;
	public int j = 0;
	
	public MyRunnable(JLabel label){
		this.label = label;
	}

	@Override
	public void run() {
		for(;;){
			i++;
			if(i == 60){
				i = 0;
				j++;
			}
			if(j > 0){
				label.setText(j + " m " + i + " s");
			}
			else{
				label.setText(i + " s");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}