package player;

import constant.Constants;

public class Player {
	protected String repColor;//玩家下棋的颜色
	protected char symbol;    //玩家所持棋子在棋盘上代表的符号
	protected String identity;//玩家身份(电脑/人类)
	public char getSymbol() {
		return symbol;
	}
	public String getRepColor() {
		return repColor;
	}
	public String getIdentity() {
		return identity;
	}
	protected void setSymbol() {
		symbol = (repColor == "black") ? Constants.BLACK_CHESS_SYMBOL : Constants.BLACK_CHESS_SYMBOL;
	}
	
}
