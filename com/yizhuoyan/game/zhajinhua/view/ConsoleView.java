package com.yizhuoyan.game.zhajinhua.view;

import com.yizhuoyan.game.zhajinhua.model.CardsTypeModel;
import com.yizhuoyan.game.zhajinhua.util.ConsoleUtil;

public class ConsoleView {
	
	/**
	 * 读取用户大名
	 * @return
	 */
	public static String getPlayerName() {
		String name=null;
		while(name==null) {
			name=ConsoleUtil.readLine("请输入您的大名>");
		}
		return name;
	}
	
	/**
	 * 让用户下注
	 * @param haveShips 持有筹码数
	 * @return 下注筹码
	 */
	public static int getBetShips(int haveShips) {
		while(true) {
			String tip="您目前还有"+haveShips+"筹码，请输入你本次下注(输入0弃牌):50>";
			int ships=ConsoleUtil.readInt(tip, 50);
			if(ships<0) {
				System.out.println("下注筹码必须大于0");
				continue;
			}
			if(ships>haveShips) {
				System.out.println("你输入的筹码大于你已持有筹码数，是否梭哈?:y:>");
				if(readBoolean("y", true)) {
					return haveShips;
				}
				continue;
			}
			
			return ships;
		}
	}
	
	public static boolean getIsContinuePlay() {
		System.out.println("是否继续游戏?:y>");
		return readBoolean("y", true);
	}
	
	public static boolean readBoolean(String yes,boolean defaultValue) {
		String line=ConsoleUtil.readLine("");
		if(line==null) {
			return defaultValue;
		}
		return line.equalsIgnoreCase(yes);
	}
	
	
	
	/**
	 * 展示牌和具体牌型
	 * @param cards
	 */
	public static void showCards(String who,int[] cards) {
		System.out.print(who+"的牌是:");
		System.out.print(getCardsDescription(cards));
		System.out.print(" ");
		int type=CardsTypeModel.judgeCardsType(cards);
		System.out.println(getCardTypeDescription(type));
	}
	
	
	public static String getCardsDescription(int[] cards) {
		String result="";
		for(int i=0;i<cards.length;i++) {
			result+=getCardDescription(cards[i]);
		}
		return result;
	}
	/**
	 * 返回牌型描述
	 * @param type
	 * @return
	 */
	public static String getCardTypeDescription(int type) {
		String[] arr= {"235","散牌","对子","顺子","金花","顺金","炸弹"};
		return arr[type];
	}
	private static String getCardDescription(int card) {
		int color=card/100;
		int dot=card%100;
		return getColorDescription(color)+getDotDescription(dot);
	}
	private static String getColorDescription(int color) {
		return new String[] {"♦","♣","♥","♠"}[color-1];
	}
	private static String getDotDescription(int dot) {
		switch (dot) {
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";	
		default:
			return dot+"";
		}
	}
}
