package player;

import constant.Constants;

public class Player {
	protected String repColor;//����������ɫ
	protected char symbol;    //������������������ϴ���ķ���
	protected String identity;//������(����/����)
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
