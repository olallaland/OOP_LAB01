package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.csvreader.CsvWriter;

import constant.Constants;
import player.ComputerPlayer;
import player.HumanPlayer;

public class Main {
	static Board b;
	public static void main(String args[]) {	
		//Prompt the player to enter the size of the chessboard.
		int size;
		while(true) {
			System.out.print("Please input the size of the board(an even number between 4 and 10): ");
			Scanner in = new Scanner(System.in);		
			try {
				size = in.nextInt();
			} catch(InputMismatchException e) {
				//�����벻�Ϸ����ַ������̴�С���ᱻ����ΪĬ��ֵ10
				//System.out.println("Invalid input! The size will be set to the default value 10.\n");
				size = 0;
			} 
			//������̴�С�Ƿ�Ϊ��4��10֮���ż��
			if(size > 0 && size % 2 == 0 && size >= 4 && size <= 10) {
				break;			
			}		
			System.out.println("Invalid input!");
		}
		
		b = new Board(size);
		b.printBoard();

		ComputerPlayer cp;
		HumanPlayer hp;
		ChessGame game;
		Move move = new Move(b.getBoardArr());
		
		//�����ѡ��������������ɫ
		System.out.print("\nWhich color of dose the computer play? \nPlease input w(for white) and b(for black): ");		
		Scanner in1 = new Scanner(System.in);
		String comColor = in1.next();
		switch(comColor) {
			
			case "w"://������ְ�������
				cp = new ComputerPlayer("white");
				hp = new HumanPlayer("black");
				game = new ChessGame(size, hp, cp);
				
				long startTime = System.currentTimeMillis();
				while(true) {
					if(move.isAbleToPlay(hp.getRepColor())) {
						if(!humanPlay(move, hp.getRepColor(), hp.getSymbol())) {
							game.setResult("Human gave up.");
							break;
						}					
						b.printBoard();
					} else if(!move.isAbleToPlay(hp.getRepColor()) && move.isAbleToPlay(cp.getRepColor())) {
						System.out.print("X player has no valid move. ");
					}					
					
					if(move.isAbleToPlay(cp.getRepColor())) {
						computerPlay(move, cp.getRepColor(), cp.getSymbol());
						b.printBoard();
					} else if(!move.isAbleToPlay(cp.getRepColor()) && move.isAbleToPlay(hp.getRepColor())) {
						System.out.print("O player has no valid move. ");
					}
					
					if(!move.isAbleToPlay(cp.getRepColor()) && !move.isAbleToPlay(hp.getRepColor())) {
						System.out.println("Both players have no valid move.");
						game.setResult(printResult(b.getBoardArr()));
						//printResult(b.getBoardArr());
						break;
					}
				}	
				
				long endTime = System.currentTimeMillis();
				game.setLastTime((endTime - startTime));
				//game.objectToStringArray();
				writeRecord(game.objectToStringArray(), Constants.LOG_PATH);
				break;
			
			case "b"://������ֺ�������
				cp = new ComputerPlayer("black");
				hp = new HumanPlayer("white");
				game = new ChessGame(size, cp, hp);
				
				long startTime2 = System.currentTimeMillis();
				while(true) {
					if(move.isAbleToPlay(cp.getRepColor())) {
						computerPlay(move, cp.getRepColor(), cp.getSymbol());
						b.printBoard();
					} else if(!move.isAbleToPlay(cp.getRepColor()) && move.isAbleToPlay(hp.getRepColor()))  {
						System.out.print("X player has no valid move. ");
					}
					
					if(move.isAbleToPlay(hp.getRepColor())) {
						if(!humanPlay(move, hp.getRepColor(), hp.getSymbol())) {
							game.setResult("Human gave up.");
							break;
						}
						b.printBoard();
					} else if(!move.isAbleToPlay(hp.getRepColor()) && move.isAbleToPlay(cp.getRepColor())) {
						System.out.print("O player has no valid move. ");
					}
					
					if(!move.isAbleToPlay(cp.getRepColor()) && !move.isAbleToPlay(hp.getRepColor())) {
						System.out.println("Both players have no valid move.");
						game.setResult(printResult(b.getBoardArr()));
						break;
					}
				}
				
				long endTime2 = System.currentTimeMillis();
				game.setLastTime((endTime2 - startTime2));
				//game.objectToStringArray();
				writeRecord(game.objectToStringArray(), Constants.LOG_PATH);
				break;
			case "t": //for test, two humanplayers play
				HumanPlayer hp2 = new HumanPlayer("black");
				hp = new HumanPlayer("white");
				while(true) {
					if(move.isAbleToPlay(hp2.getRepColor())) {
						if(!humanPlay(move, hp2.getRepColor(), hp2.getSymbol())) {
							break;
						}
						
						b.printBoard();
					} else {
						System.out.print("X player has no valid move. ");
					}
					
					if(move.isAbleToPlay(hp.getRepColor())) {
						if(!humanPlay(move, hp.getRepColor(), hp.getSymbol())) {
							break;
						}					
						b.printBoard();
					} else {
						System.out.print("O player has no valid move. ");
					}
					
					if(!move.isAbleToPlay(hp2.getRepColor()) && !move.isAbleToPlay(hp.getRepColor())) {
						System.out.println("Both players have no valid move.");
						printResult(b.getBoardArr());
						break;
					}
				}							
				break;
			default:
				System.out.println("Wrong input!");
		}
	}
	
	/*
	 * �������
	 */
	private static boolean humanPlay(Move move, String color, char symbol) {		
		char temp = (symbol == Constants.NO_CHESS_SYMBOL) ? Constants.NO_CHESS_SYMBOL : Constants.NO_CHESS_SYMBOL;
		System.out.print("Enter move for " + symbol + "(Row/Col): ");
		Scanner in = new Scanner(System.in);
		String nextStep = in.nextLine();
		if(move.isValid(nextStep, color)) {
			move.moveToNext(nextStep, color);
		} else {
			System.out.println("Invalid move.\nGame over.\n" + temp + " player wins.");
			return false;
		}
		
		return true;
	}
	
	/*
	 * ���������
	 */
	private static void computerPlay(Move move, String color, char symbol) {
		String nextPosForComp = move.calculateNextPos(color);
		System.out.println("Computer plays " + symbol + " at " + nextPosForComp + ".");
		move.moveToNext(nextPosForComp, color);
	}
	
	/*
	 * ����������ҵĲ��Ϸ�����λ�ý���������
	 * ����ڴ˴���ӡ����������������
	 */
	private static String printResult(Chessman[][] boardArr) {
		int oCount = 0;
		int xCount = 0;
		String result;
		for(int i = 1; i < boardArr.length; i++) {
			for(int j = 1; j < boardArr.length; j++) {
				if(boardArr[i][j].getContent() == Constants.NO_CHESS_SYMBOL) {
					oCount++;
				} else if(boardArr[i][j].getContent() == Constants.NO_CHESS_SYMBOL) {
					xCount++;
				}
			}
		}
		result = xCount  + " to " + oCount;
		System.out.println("Game over.");
		System.out.println("X : O = " + xCount + " : " + oCount);
		System.out.println(((xCount > oCount) ? "X" : "O") + " player wins.");
		return result;
	}	
	
	/*
	 * ��Ϸ�����󣬽���Ϸ����Լ������Ϣ�������־�ļ���
	 */
	public static void writeRecord(String[] array, String outputPath) {
		File outputFile = new File(outputPath);
		if(!outputFile.exists()) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(outputPath, true));
			CsvWriter out = new CsvWriter(bw, Constants.CSV_SEPARATOR);
			out.writeRecord(array);
			out.flush();
			bw.flush();
			out.close();
			bw.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
