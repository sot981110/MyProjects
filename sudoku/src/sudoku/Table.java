package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Table {
	
	private int[][] table;
	
	public Table(){
		this.table = new int[9][9];
		for (int[] i : table) {
			i = new int[9];
			for (int j : i) {
				j = 0;
			}
		}
	}
	
	public Table(boolean random){
		this();
		if(random){
			Random rand = new Random();
			ArrayList<Integer> numbers;
			int next;
			for (int i = 0; i < 9; i++) {
				numbers = new ArrayList<Integer>();
				for (int j = 0; j < 9; j++) {
					next = Math.abs(rand.nextInt()%10);
					while(numbers.contains(next)){
						next = Math.abs(rand.nextInt()%10);
					}
					table[i][j] = next;
					numbers.add(next);
				}
			}
		}
	}
	
	public int getNumber(int x, int y){
		return this.table[x][y];
	}
	
	public void setNumber(int n, int x, int y){
		this.table[x][y] = n;
	}
	
	public boolean isSolved(){
		boolean isSolved = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(table[i][j] == 0){
					isSolved = false;
				}
			}
		}
		return isSolved;
	}
	
	public int numberOfValues(){
		int number = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(table[i][j] != 0){
					number++;
				}
			}
		}
		return number;
	}
	
	public int[] checkNumber(int n, int x, int y){
		int[] returnValueRow = checkNumberRow(n, x, y);
		int[] returnValueColoumn = checkNumberColoumn(n, x, y);
		int[] returnValueBox = checkNumberBox(n, x, y);
		int[] returnValue = new int[2];
		
		returnValue[0] = returnValueRow[0] + returnValueColoumn[0] + returnValueBox[0];
		returnValue[1] = returnValueRow[1] + returnValueColoumn[1] + returnValueBox[1];
		
		return returnValue;
	}
	
	public int[] checkNumberRow(int n, int x, int y){
		if(checkImpossibility(n, x, y)){
			int[] returnValue = {-1,-1};
			return returnValue;
		}
		int[] returnValue = new int[2];
		for (int i = 0; i < 9; i++) {
			if(table[x][i] == 0 && i != y){
				if(!checkImpossibility(n, x, i)){
					returnValue[1]++;
				}
				returnValue[0]++;
			}
		}
		return returnValue;
	}
	
	public int[] checkNumberColoumn(int n, int x, int y){
		if(checkImpossibility(n, x, y)){
			int[] returnValue = {-1,-1};
			return returnValue;
		}
		int[] returnValue = new int[2];
		for (int i = 0; i < 9; i++) {
			if(table[i][y] == 0 && i != x){
				if(!checkImpossibility(n, i, y)){
					returnValue[1]++;
				}
				returnValue[0]++;
			}
		}
		return returnValue;
	}
	
	public int[] checkNumberBox(int n, int x, int y){
		if(checkImpossibility(n, x, y)){
			int[] returnValue = {-1,-1};
			return returnValue;
		}
		int[] returnValue = new int[2];
		for (int i : checkBoxes(x)) {
			for (int j : checkBoxes(y)) {
				if(table[i][j] == 0 && !(i == x && j == y)){
					if(!checkImpossibility(n, i, j)){
						returnValue[1]++;
					}
					returnValue[0]++;
				}
			}
		}
		return returnValue;
	}
	
	public int[] checkBoxes(int x){
		int[] returnValue = new int[3];
		switch(x){
		case 0:
		case 1:
		case 2: 
			returnValue[0] = 0;
			returnValue[1] = 1;
			returnValue[2] = 2;
			break;
		case 3:
		case 4:
		case 5: 
			returnValue[0] = 3;
			returnValue[1] = 4;
			returnValue[2] = 5;
			break;
		case 6:
		case 7:
		case 8: 
			returnValue[0] = 6;
			returnValue[1] = 7;
			returnValue[2] = 8;
			break;
		}
		return returnValue;
	}
	
	public boolean checkImpossibility(int n, int x, int y){
		return checkImpossibilityRow(n, x, y) || checkImpossibilityColoumn(n, x, y) || checkImpossibilityBox(n, x, y);
	}
	
	public boolean checkImpossibilityRow(int n, int x, int y){
		for (int i = 0; i < 9; i++) {
			if(table[i][y] == n && i != x){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkImpossibilityColoumn(int n, int x, int y){
		for (int i = 0; i < 9; i++) {
			if(table[x][i] == n && i != y){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkImpossibilityBox(int n, int x, int y){
		for (int i : checkBoxes(x)) {
			for (int j : checkBoxes(y)) {
				if(table[i][j] == n && !(i == x && j == y))
					return true;
			}
		}
		return false;
	}
	
	public void drawTable(){
		System.out.println("/=====================================\\");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if(table[i][j] != 0){
					System.out.print(" " + table[i][j] + " ");
				}
				else{
					System.out.print("   ");
				}
				if(j == 2 || j == 5){
					System.out.print("||");
				}
				else{
					System.out.print("|");
				}
				
			}
			System.out.println();
			if(i != 8){
				if(i == 2 || i == 5){
					System.out.println("|=====================================|");
				}
				else{
					System.out.println("|-------------------------------------|");
				}
				
			}
			else{
				System.out.println("\\=====================================/");
			}
		}
	}
	
	public void drawTable(int x, int y){
		System.out.println("/=====================================\\");
		for (int i = 0; i < 9; i++) {
			System.out.print("|");
			for (int j = 0; j < 9; j++) {
				if(table[i][j] != 0){
					System.out.print(" " + table[i][j] + " ");
				}
				else if(i == x && j == y){
					System.out.print(" X ");
				}
				else{
					System.out.print("   ");
				}
				if(j == 2 || j == 5){
					System.out.print("||");
				}
				else{
					System.out.print("|");
				}
				
			}
			System.out.println();
			if(i != 8){
				if(i == 2 || i == 5){
					System.out.println("|=====================================|");
				}
				else{
					System.out.println("|-------------------------------------|");
				}
				
			}
			else{
				System.out.println("\\=====================================/");
			}
		}
	}
	
	public boolean checkSudoökuRule(){
		boolean isRight = true;
		for (int i = 0; i < table.length; i++) {
			isRight = isRight && checkSudokuRuleRow(i) && checkSudokuRuleColoumn(i);
		}
		return isRight;
	}
	
	public boolean checkSudokuRuleRow(int x){
		boolean isRight = true;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++) {
			if(table[x][i] > 0){
				if(numbers.contains(table[x][i])){
					isRight = false;
				}
				numbers.add(table[x][i]);
			}
		}
		
		return isRight;
		
	}
	
	public boolean checkSudokuRuleColoumn(int y){
		boolean isRight = true;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++) {
			if(table[i][y] > 0){
				if(numbers.contains(table[i][y])){
					isRight = false;
				}
				numbers.add(table[i][y]);
			}
		}
		
		return isRight;
		
	}
	
}
