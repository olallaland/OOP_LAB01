package main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

public class Test {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个整数：");
		int length = input.nextInt();// 输入一个整数
		System.out.println("请输入一个字符或者字符串：");
		String str = input.next();// 输入一个字符串
		System.out.println("输入的字符串是：" + str);
		input.close();// 关闭输入的流，释放内存
		
		Stack<Integer> s = new Stack<Integer>();
		s.add(1);
		s.add(2);
		System.out.println(s.isEmpty());
		s.clear();
		System.out.println(s.isEmpty());
		
		//我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        System.out.println(createdate);
        
        File outputFile = new File("log/data.txt");
		if(!outputFile.exists()) {
			outputFile.mkdirs();
		}
	}
}