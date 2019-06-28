package com.yizhuoyan.game.zhajinhua.util;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;

public class ConsoleUtil {
	/**
	* 获取控制台用户的一行输入
	*@param tip 输入提示
	*@param defaultValue 如果用户未输入时的默认值
	*@return 获取的输入
	*/
	public static String readLine(String tip,String defaultValue){
		System.out.println(tip);
		Console console=System.console();
		String line=null;
		if(console!=null) {
			line=console.readLine();
		}else {
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			try {
			line=in.readLine();
			}catch (Exception e) {}
		}
		if(line.length()==0){
			return defaultValue;
		}
		return line;
	}
	/**
	 * 获取控制台用户的一行输入
	 * @param tip 输入提示
	 * @return
	 */
	public static String readLine(String tip){
		return readLine(tip,null);
	}
	
	/**
	 * 从控制台读取一个整数
	 * @param tip
	 * @param defaultValue
	 * @return
	 */
	public static int readInt(String tip,int defaultValue) {
		int resultInt=defaultValue;
		String line=null;
		while(true) {
			line=readLine(tip);
			if(line==null) {
				return defaultValue;
			}
			try {
				resultInt=Integer.parseInt(line);
				return resultInt;
			}catch(NumberFormatException e) {
				System.out.println("输入错误，请输入整数");
			}
		}
	}
	
	public static double readDouble(String tip,double defaultValue) {
		double resultInt=defaultValue;
		String line=null;
		while(true) {
			line=readLine(tip);
			if(line==null) {
				return defaultValue;
			}
			try {
				resultInt=Double.parseDouble(line);
				return resultInt;
			}catch(NumberFormatException e) {
				System.out.println("输入错误，请输入小数");
			}
		}
	}
}
