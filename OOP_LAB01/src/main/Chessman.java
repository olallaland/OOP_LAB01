package main;

public class Chessman {
	char content;
	String color;
	int row;
	int col;
	Chessman west;
	Chessman northwest;
	Chessman north;
	Chessman northeast;
	Chessman east;
	Chessman southwest;
	Chessman south;
	Chessman southeast;
	boolean visited;
	int score;

	public Chessman(char content, int row, int col) {
		this.content = content;
		this.row = row;
		this.col = col;
	}
	public Chessman() {
		this.content = ' ';
	}

	public char getContent() {
		return content;
	}

	public void setContent(char content) {
		this.content = content;
	}

	public Chessman getDirection(String direction, Chessman[][] boardArr) {
		try {
			switch (direction) {
			case "west":
				return boardArr[row][col - 1];
			case "northwest":
				return boardArr[row - 1][col - 1];
			case "north":
				return boardArr[row - 1][col];
			case "northeast":
				return boardArr[row - 1][col + 1];
			case "east":
				return boardArr[row][col + 1];
			case "southwest":
				return boardArr[row + 1][col - 1];
			case "south":
				return boardArr[row + 1][col];
			case "southeast":
				return boardArr[row + 1][col + 1];
			default:
				System.out.println("wrong dirction");
				return boardArr[row][col];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.out.println("out bound");
			return new Chessman();
		}
	}
	
	public Chessman getWest(Chessman[][] boardArr) {
		return boardArr[row][col - 1];
	}
	public Chessman getNorthwest(Chessman[][] boardArr) {
		return boardArr[row - 1][col - 1];
	}
	public Chessman getNorth(Chessman[][] boardArr) {
		return boardArr[row - 1][col];
	}
	public Chessman getNortheast(Chessman[][] boardArr) {
		return boardArr[row - 1][col + 1];
	}
	public Chessman getEast(Chessman[][] boardArr) {
		return boardArr[row][col + 1];
	}
	public Chessman getSouthwest(Chessman[][] boardArr) {
		return boardArr[row + 1][col - 1];
	}
	public Chessman getSouth(Chessman[][] boardArr) {
		return boardArr[row + 1][col];
	}
	public Chessman getSoutheast(Chessman[][] boardArr) {
		return boardArr[row + 1][col + 1];
	}	
	
}
