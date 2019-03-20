package main;

import constant.Constants;

public class Board {
	public int rowNum;
	public int colNum;
	private Chessman[][] boardArr;
	
	//初始化棋盘
	public Board(int size) {
		this.rowNum = size;
		this.colNum = size;
		boardArr = new Chessman[rowNum + 1][colNum + 1];
		boardArr[0][0] = new Chessman(' ', 0, 0);
		char rowTitle = 96;
		char colTitle = 96;
		for(int i = 0; i <= rowNum; i++) {
			
			for(int j = 0; j <= colNum; j++) {
				if(i == 0) {
					boardArr[i][j] = new Chessman(rowTitle, i, j);
					rowTitle++;
				} else {
					boardArr[i][j] = new Chessman(Constants.NO_CHESS_SYMBOL, i, j);
				}
			}
			boardArr[i][0] = new Chessman(colTitle, i, 0);
			colTitle ++;
		}
		boardArr[0][0] = new Chessman(' ', 0, 0);
		int half = size / 2;
		boardArr[half][half].setContent(Constants.WHITE_CHESS_SYMBOL);
		boardArr[half][half + 1].setContent(Constants.BLACK_CHESS_SYMBOL);
		boardArr[half + 1][half].setContent(Constants.BLACK_CHESS_SYMBOL);
		boardArr[half + 1][half + 1].setContent(Constants.WHITE_CHESS_SYMBOL);	
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	
	public void setBoardArr(int row, int col, char content) {
		//boardArr = new Node[rowNum + 1][colNum + 1];
		boardArr[row][col].setContent(content);
	}
	
	//打印棋盘
	public void printBoard() {
		for(int i = 0; i <= rowNum; i++) {
			for(int j = 0; j <= colNum; j++) {
				try
				{
					System.out.print(boardArr[i][j].getContent() + " ");
				}catch(NullPointerException e)
				{
				System.out.println("发生异常的原因为 :"+e.getMessage());
				}			
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public Chessman[][] getBoardArr(){
		return boardArr;
	}

	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
