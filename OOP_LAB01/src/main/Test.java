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
		System.out.println("������һ��������");
		int length = input.nextInt();// ����һ������
		System.out.println("������һ���ַ������ַ�����");
		String str = input.next();// ����һ���ַ���
		System.out.println("������ַ����ǣ�" + str);
		input.close();// �ر�����������ͷ��ڴ�
		
		Stack<Integer> s = new Stack<Integer>();
		s.add(1);
		s.add(2);
		System.out.println(s.isEmpty());
		s.clear();
		System.out.println(s.isEmpty());
		
		//��Ҫ��ȡ��ǰ������
        Date date = new Date();
        //����Ҫ��ȡ��ʲô����ʱ��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        //��ȡString���͵�ʱ��
        String createdate = sdf.format(date);
        System.out.println(createdate);
        
        File outputFile = new File("log/data.txt");
		if(!outputFile.exists()) {
			outputFile.mkdirs();
		}
	}
}