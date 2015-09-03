package Func;

import java.util.Random;

public class Sudoku {
	public int a[][] = new int[9][9];
	
	public void generatePuzzle(){
        Random r = new Random();
        int i1=r.nextInt(8);
        int firstval = i1;
        int x = firstval;
        int v = 1;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if((x + j + v) <= 9){
                    a[i][j] = j + x + v;
                }
                else{
                    a[i][j] = j + x + v - 9;
                }
                if(a[i][j] == 10){
                    a[i][j] = 1;
                 //System.out.print(a[i][j]+" ");
                }
            }
            x += 3;
            if(x >= 9){
                x = x - 9;
             //System.out.println();
            }
            if(i == 2){
                v = 2;
                x = firstval;
            }
            if(i == 5){
                v = 3;
                x = firstval;
            }
        }
	}
}
