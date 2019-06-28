package com.yizhuoyan.game.zhajinhua.model;

import java.util.Arrays;

public class CardsTypeModel {
	
	public static void main(String[] args) {
		int[] cards= {102,103,105};
		int type=judgeCardsType(cards);
		System.out.println("实际返回:"+type+";期望:"+4);
	}
	/**
	 * 判断三张牌的牌型
	 * 
	 * @param cards 三张牌
	 * @param 牌型    6=炸弹 5=顺金 (同花顺) 4=金花3=顺子 2=对子 1=散牌 0=235
	 */
	public static int judgeCardsType(int[] cards) {
		if (isPair(cards)) {
			return 2;
		}
		if (isSameColor(cards)) {
			if (isFlush(cards)) {
				return 5;
			}
			return 4;
		}

		if (isFlush(cards)) {
			return 5;
		}

		if (is235(cards)) {
			return 0;
		}
		if (isBomb(cards)) {
			return 6;
		}

		return 1;

	}

	private static boolean isBomb(int[] cards) {
		int dot1 = cards[0] % 100;
		int dot2 = cards[1] % 100;
		int dot3 = cards[2] % 100;
		return dot1 == dot2 && dot2 == dot3;
	}

	private static boolean isSameColor(int[] cards) {
		int color1 = cards[0] / 100;
		int color2 = cards[1] / 100;
		int color3 = cards[2] / 100;
		return color1 == color2 && color2 == color3;
	}

	private static boolean isFlush(int[] cards) {
		Arrays.sort(cards);
		int dot1 = cards[0] % 100;
		int dot2 = cards[1] % 100;
		int dot3 = cards[2] % 100;
		if(dot2 - dot1 == 1 && dot3 - dot2 == 1) {
			return true;
		}
		//判断AKQ
		if(dot1==1&&dot2==12&&dot3==13) {
			return true;
		}
				
		return false;
	}

	private static boolean isPair(int[] cards) {
		int dot1 = cards[0] % 100;
		int dot2 = cards[1] % 100;
		int dot3 = cards[2] % 100;
		if (dot1 == dot2 && dot2 != dot3) {
			return true;
		}
		if (dot1 == dot3 && dot2 != dot3) {
			return true;
		}
		if (dot2 == dot3 && dot2 != dot1) {
			return true;
		}
		return false;
	}

	private static boolean is235(int[] cards) {
		Arrays.sort(cards);
		int dot1 = cards[0] % 100;
		int dot2 = cards[1] % 100;
		int dot3 = cards[2] % 100;
		return dot1 == 2 && dot2 == 3 && dot3 == 5;
	}

}
