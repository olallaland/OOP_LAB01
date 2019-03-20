package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import player.Player;

public class ChessGame {
	String startTime;   //��¼�����Ϸ��ʼ��ʱ��
	long lastTime;      //��¼�����Ϸ������ʱ��
	int boardSize;      //��¼��ǰ��Ϸ���̵Ĵ�С
	Player repBlack;    //��¼��ǰ�º����������
	Player repWhite;    //��¼��ǰ�°����������
	String result = ""; //��¼�����Ϸ�Ľ��
	
	/*
	 * ����Ϸ��ʼʱ����ʼ�������Ϸ�������Ϣ
	 */
	public ChessGame(int boardSize, Player repBlack, Player repWhite) {
		startTime = getStartTime();
		this.boardSize = boardSize;
		this.repBlack = repBlack;
		this.repWhite = repWhite;
	}
	
	/*
	 * �õ���ǰʱ����Ϊ��Ϸ��ʼ��ʱ��
	 */
	private String getStartTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String strTime = sdf.format(date);
		return strTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime / 1000;
		//this.lastTime = lastTime;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	/*
	 * ������תΪString�������飬�Ա����������־�ļ���
	 */
	public String[] objectToStringArray() {
		String[] array = new String[6];
        array[0] = startTime;
        array[1] = lastTime + "";
        array[2] = boardSize + "*" + boardSize;
        array[3] = repBlack.getIdentity();
        array[4] = repWhite.getIdentity();
        array[5] = result;
        
        for(int i = 0; i < array.length; i++) {
        	System.out.print(array[i] + ", ");
        }
        return array;
	}
}
