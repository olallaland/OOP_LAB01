package main;

import java.util.Stack;

import constant.Constants;

public class Move {
	Chessman[][] boardArr;
	boolean isValid = false;
	String[] directions = { "west", "northwest", "north", "northeast", "east", "southwest", "south", "southeast" };

	public Move(Chessman[][] boardArr) {
		this.boardArr = boardArr;
	}

	public Chessman[][] getBoardArr() {
		return boardArr;
	}

	public void setBoardArr(int row, int col, char content) {
		boardArr[row][col].setContent(content);
	}

	public void moveToNext(String position, String color) {
		Chessman chess = getChess(position);
		if (chess.getContent() == Constants.NO_CHESS_SYMBOL) {
			switch (color) {
			case "white":
				chess.setContent(Constants.WHITE_CHESS_SYMBOL);
				reverseChess(chess, color, "reverse", Constants.WHITE_CHESS_SYMBOL);
				break;
			case "black":
				chess.setContent(Constants.BLACK_CHESS_SYMBOL);
				reverseChess(chess, color, "reverse", Constants.BLACK_CHESS_SYMBOL);
				break;
			default:
				break;
			}
		}
	}

	/*
	 * ��ת�м������
	 */
	private int reverseChess(Chessman chess, String color, String sign, char curContent) {
		int score = 0;// ���㵱ǰ�������º�ķ�ֵ
		Chessman tempChess;
		// char curContent = chess.getContent();
		char temp = (curContent == Constants.WHITE_CHESS_SYMBOL) ? Constants.BLACK_CHESS_SYMBOL
				: Constants.WHITE_CHESS_SYMBOL;
		Stack<Chessman> chessToBeReversed = new Stack<Chessman>();// ���Ҫ����ת������
		for (int i = 0; i < directions.length; i++) {
			// System.out.println(directions[i]);
			Chessman neighborChess = chess.getDirection(directions[i], boardArr);
			if (neighborChess.getContent() == temp) {
				chessToBeReversed.add(chess.getDirection(directions[i], boardArr));
				tempChess = neighborChess.getDirection(directions[i], boardArr);
				while (true) {
					if (tempChess.getContent() == curContent) {
						break;
					} else if (tempChess.getContent() == temp) {
						chessToBeReversed.add(tempChess);
						tempChess = tempChess.getDirection(directions[i], boardArr);
					} else {
						// System.out.println("failure direction: " + directions[i]);
						chessToBeReversed.clear();
						break;
					}
				}
				if (!chessToBeReversed.isEmpty()) {
					for (Chessman cm : chessToBeReversed) {
						// System.out.println(cm.row + "/" + cm.col);
						if (sign.equals("reverse")) {
							cm.setContent(curContent);
						}
						score++;
					}
					chessToBeReversed.clear();
				}
			} else {
				// System.out.println(directions[i]);
			}
		}
		// System.out.println("current score: " + score);
		return score;
	}

	/*
	 * ����������һ����λ��
	 */
	public String calculateNextPos(String color) {
		String pos = "z";
		int score = 0;
		char temp = (color == "black") ? Constants.BLACK_CHESS_SYMBOL : Constants.WHITE_CHESS_SYMBOL;
		for (int i = 1; i < boardArr.length; i++) {
			for (int j = 1; j < boardArr.length; j++) {
				if (isValid(indexToPosition(i, j), color)) {
					// System.out.println(indexToPosition(i, j));
					int reverseNumber = reverseChess(boardArr[i][j], color, "calculate", temp);
					// System.out.println(reverseNumber + " : " + indexToPosition(i, j) );
					if (score < reverseNumber) {
						score = reverseNumber;
						pos = indexToPosition(i, j);
					} else if (score == reverseNumber && pos.compareTo(indexToPosition(i, j)) > 0) {
						// System.out.println(pos.compareTo(indexToPosition(i, j)));
						pos = indexToPosition(i, j);
					}
					// System.out.println(pos);
				}
			}
		}
		// System.out.println(pos);
		return pos;

	}

	/*
	 * �ж�����λ���Ƿ�Ϸ�
	 */
	public boolean isValid(String position, String color) {
		Chessman chess;
		try {
			chess = getChess(position);
		} catch (Exception e) {
			return false;
		}

		char curContent = chess.getContent();

		// �ظ����Ӻ�Խ������
		if (curContent != Constants.NO_CHESS_SYMBOL) {
			// System.out.println(curContent);
			return false;
		} else {
			for (int i = 0; i < directions.length; i++) {
				// System.out.println(directions[i]);
				if (curDirectionWorks(chess, directions[i], color)) {
					// System.out.println(directions[i]);
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * ���������λ���ַ������ظ�λ�õ�Chessman����
	 */
	private Chessman getChess(String position) {
		Chessman chess;
		char[] rowCol = position.toCharArray();
		if (rowCol.length != 2) {
			throw new RuntimeException("invalid input!");
		}
		int row = rowCol[0] - 96;
		int col = rowCol[1] - 96;
		try {
			chess = boardArr[row][col];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("invalid input!");
		}
		return chess;
	}

	/*
	 * �������������������е�����������λ���ַ���
	 */
	private String indexToPosition(int row, int col) {
		String pos = "";
		char cRow = (char) (row + 96);
		char cCol = (char) (col + 96);
		pos = cRow + "" + cCol;
		return pos;
	}

	/*
	 * �жϵ�ǰ����λ�õ�ĳ�������Ƿ�Ϸ�
	 */
	private boolean curDirectionWorks(Chessman chess, String direction, String curColor) {
		Chessman neighborChess = chess.getDirection(direction, boardArr);
		// ����Ҫ���õ����ӵķ���
		char toBePlaced = (curColor == "white") ? Constants.WHITE_CHESS_SYMBOL : Constants.BLACK_CHESS_SYMBOL;
		// �뼴��Ҫ���õ�������ɫ�෴�����ӵķ���
		char temp = (toBePlaced == Constants.WHITE_CHESS_SYMBOL) ? Constants.BLACK_CHESS_SYMBOL
				: Constants.WHITE_CHESS_SYMBOL;
		char neighborContent = neighborChess.getContent();

		// Ҫ���õ��������ڽӵ�������ɫ�෴����Ҫ����
		if (neighborContent == temp) {
			// System.out.println(neighborChess.row + "/" + neighborChess.col);
			neighborChess = neighborChess.getDirection(direction, boardArr);
			while (neighborChess.getContent() == Constants.WHITE_CHESS_SYMBOL
					|| neighborChess.getContent() == Constants.BLACK_CHESS_SYMBOL) {
				if (neighborChess.getContent() == toBePlaced) {
					// System.out.println(neighborChess.row + "/" + neighborChess.col);
					return true;
				} else {
					neighborChess = neighborChess.getDirection(direction, boardArr);
				}
			}
			return false;
		} else {
			return false;
		}
		// return isWork;
	}

	/*
	 * �жϵ�ǰ����Ƿ��п������ӵ����̸�
	 */
	public boolean isAbleToPlay(String repColor) {
		for (int i = 1; i < boardArr.length; i++) {
			for (int j = 1; j < boardArr.length; j++) {
				if (boardArr[i][j].getContent() == Constants.NO_CHESS_SYMBOL
						&& isValid(indexToPosition(i, j), repColor)) {
					return true;
				}
			}
		}
		return false;
	}

}
