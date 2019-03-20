package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import player.Player;

public class ChessGame {
	String startTime;   //记录这局游戏开始的时间
	long lastTime;      //记录这局游戏持续的时间
	int boardSize;      //记录当前游戏棋盘的大小
	Player repBlack;    //记录当前下黑棋的玩家身份
	Player repWhite;    //记录当前下白棋的玩家身份
	String result = ""; //记录这局游戏的结果
	
	/*
	 * 当游戏开始时，初始化这局游戏的相关信息
	 */
	public ChessGame(int boardSize, Player repBlack, Player repWhite) {
		startTime = getStartTime();
		this.boardSize = boardSize;
		this.repBlack = repBlack;
		this.repWhite = repWhite;
	}
	
	/*
	 * 得到当前时间作为游戏开始的时间
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
	 * 将对象转为String类型数组，以便于输出到日志文件中
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
