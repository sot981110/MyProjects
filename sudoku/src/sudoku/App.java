package sudoku;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean booleanAnswer;
		int numberAnswer;
		Table previousTable;
		boolean randomGenerate;
		
		System.out.println("Random generate table? 0/1");
		numberAnswer = Integer.parseInt(input.next());
		booleanAnswer = (numberAnswer == 1);
		randomGenerate = booleanAnswer;
		
		Table table = new Table(randomGenerate);
		if(!randomGenerate){
			App.readTable(table, input);
		}
		
		if(table.numberOfValues() < 16){
			System.out.println("Warning! This table does NOT look solvable.");
		}
		
		table.drawTable();
		
		while(!table.isSolved()){
			System.out.println("Solver Type: ");
			System.out.println("0: Normal");
			System.out.println("1: Fast");
			
			numberAnswer = Integer.parseInt(input.next());
			booleanAnswer = (numberAnswer == 0);
			
			previousTable = table;
			int writtenNumbers = 0;
			
			if(booleanAnswer){
				System.out.println("Using normal solver: ");
				writtenNumbers = solveSudoku(table);
			}
			else{
				System.out.println("Fast solver selected. Please write a border value: ");
				while((numberAnswer = Integer.parseInt(input.next())) < 0 || 9 < numberAnswer ){
					System.out.println("Possible numbers are within 0 and 9");
				}
				System.out.println("Using fast solver with border " + numberAnswer + ":");
				writtenNumbers = solveSudokuFast(table, numberAnswer);
			}
			
			if(writtenNumbers == 0){
				System.out.println("Couldnt write any numbers.");
			}
			else{
				System.out.println("Wrote " + writtenNumbers + " to the table");
			}
			
			table.drawTable();
			
			System.out.println("Is the table still right? 0/1");
			if(!table.checkSudoökuRule()){
				System.out.println("(Table does NOT seem valid)");
			}
			
			numberAnswer = Integer.parseInt(input.next());
			booleanAnswer = (numberAnswer == 1);
			
			if(!booleanAnswer){
				table = previousTable;
				System.out.println("Reverted table.");
			}
			
			System.out.println("Stop the run? 0/1");
			numberAnswer = Integer.parseInt(input.next());
			booleanAnswer = (numberAnswer == 1);
			
			if(booleanAnswer){
				input.close();
				return;
			}
		}
		
		input.close();

	}
	
	public static void readTable(Table table, Scanner input){
		int number;
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				table.drawTable(i, j);
				System.out.println("Write a number for position (" + (i+1) + "," + (j+1) + "): ");
				while( (number = Integer.parseInt(input.next())) < 0 || 9 < number){
					System.out.println("Possible numbers are within 0 and 9");
				}
				table.setNumber(number, i, j);
			}
		}
	}
	
	public static int solveSudoku(Table table){
		int[] returnedValue = new int[2];
		int writtenNumbers = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(table.getNumber(i, j) == 0){
					for (int k = 1; k < 10; k++) {
						returnedValue = table.checkNumberRow(k, i, j);
						if(returnedValue[1] == 0){
							table.setNumber(k, i, j);
							System.out.print("Wrote number " + k + " into cell (" + (i+1) + "," + (j+1) + ") because of its row");
							writtenNumbers++;
						}
						returnedValue = table.checkNumberColoumn(k, i, j);
						if(returnedValue[1] == 0){
							if(table.getNumber(i, j) == k){
								System.out.print(" and coloumn");
							}
							else{
								table.setNumber(k, i, j);
								System.out.print("Wrote number " + k + " into cell (" + (i+1) + "," + (j+1) + ") because of its coloumn");
								writtenNumbers++;
							}
						}
						returnedValue = table.checkNumberBox(k, i, j);
						if(returnedValue[1] == 0){
							if(table.getNumber(i, j) == k){
								System.out.print(" and box");
							}
							else{
								table.setNumber(k, i, j);
								System.out.print("Wrote number " + k + " into cell (" + (i+1) + "," + (j+1) + ") because of its box");
								writtenNumbers++;
							}
						}
						if(table.getNumber(i, j) == k){
							System.out.println(".");
						}
					}
				}
			}
		}
		return writtenNumbers;
	}
	
	public static int solveSudokuFast(Table table, int border){
		int[][] returnedValues = new int[9][2];
		for (int[] i : returnedValues) {
			i = new int[2];
			for (int j = 0; j < i.length; j++) {
				i[j] = -1;
			}
		}
		int writtenNumbers = 0;
		double min;
		int minNumber;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(table.getNumber(i, j) == 0){
					for (int k = 1; k < 10; k++) {
						returnedValues[k-1] = table.checkNumber(k, i, j);
						min = returnedValues[0][1]/returnedValues[0][0];
						minNumber = 0;
						for (int m = 1; m < 9; m++) {
							if(returnedValues[m-1][0] != 0 && returnedValues[m-1][1]/returnedValues[m-1][0] < min){
								minNumber = m;
								min = returnedValues[m-1][1]/returnedValues[m-1][0];
							}
						}
						if(minNumber < (border/9)){
							table.setNumber(minNumber + 1, i, j);
							System.out.println("Wrote number " + (minNumber + 1)  + " into cell (" + (i+1) + "," + (j+1) + ") because of probability of " + min);
							writtenNumbers++;
						}
					}
				}
			}
		}
		return writtenNumbers;
	}

}
