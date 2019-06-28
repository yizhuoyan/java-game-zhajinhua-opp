package com.yizhuoyan.game.zhajinhua.model;

import java.util.Arrays;

/**
 * 比大小
 * 
 * @author Administrator
 *
 */
public class CardsCompareModel {
	
	public static void main(String[] args) {
		int[] cards1= {102,112,105};
		int[] cards2= {102,101,104};
		
		int result=compare(cards1, cards2);
		System.out.println(result);
	}
	/**
	 * 比大小
	 * 
	 * @param cards1
	 * @param cards2
	 * @return 如果是正数，则1>2 如果是负数，则2>1 如果是0，则表示相等
	 */
	public static int compare(int[] cards1, int[] cards2) {
		// 1先判断牌型，再比大小
		int type1 = CardsTypeModel.judgeCardsType(cards1);
		int type2 = CardsTypeModel.judgeCardsType(cards2);
		if (type1 != type2) {
			return type1 - type2;
		}
		// 2牌型相等
		// 6=炸弹 5=顺金 (同花顺) 4=金花3=顺子 2=对子 1=散牌 0=235
		switch (type1) {
		case 6:
			return compareBomn(cards1, cards2);
		case 5:
			return compareStraitFlush(cards1, cards2);
		case 4:
			return compareSameColor(cards1, cards2);
		case 3:
			return compareFlush(cards1, cards2);
		case 2:
			return comparePair(cards1, cards2);
		case 1:
			return compareNormal(cards1, cards2);
		case 0:
			return compare235(cards1, cards2);
		}
		return 0;
	}

	private static int compareBomn(int[] cards1, int[] cards2) {
		int dot1 = cards1[0] % 100;
		int dot2 = cards2[0] % 100;
		return dot1 - dot2;
	}

	private static int compareStraitFlush(int[] cards1, int[] cards2) {
		return compareFlush(cards1, cards2);
	}

	
	private static int compareSameColor(int[] cards1, int[] cards2) {
		return compareNormal(cards1, cards2);
	}

	private static int compareFlush(int[] cards1, int[] cards2) {
		
		return compareNormal(cards1, cards2);
	}

	private static void sortCards(int[] cards) {
		for(int j=1;j<cards.length;j++) {
			for(int i=0;i<cards.length-j;i++) {
				int dotA=cards[i]%100;
				int dotB=cards[i+1]%100;
				if(compareDot(dotA,dotB)>0) {
					int temp=cards[i];
					cards[i]=cards[i+1];
					cards[i+1]=temp;
				}
			}
		}
	}
	
	private static int compareDot(int dotA,int dotB) {
		if(dotA==1) {
			if(dotB==1) {
				return 0;
			}
			return 1;
		}
		if(dotB==1) {
			if(dotA==1) {
				return 0;
			}
			return -1;
		}
		return dotA-dotB;
	}
	private static int comparePair(int[] cards1, int[] cards2) {
		sortCards(cards1);
		sortCards(cards2);
		int pair1Dot=cards1[1]%100;
		int pair2Dot=cards2[1]%100;
		if(pair1Dot!=pair2Dot) {
			return pair1Dot-pair2Dot;
		}
		int single1=getPairCardsSingleCard(cards1);
		int single2=getPairCardsSingleCard(cards2);
		return single1-single2;
	}
	private static int getPairCardsSingleCard(int[] cards) {
		int dot1=cards[0]%100;
		int dot2=cards[1]%100;
		int dot3=cards[2]%100;
		if(dot1!=dot2) {
			return dot1;
		}
		return dot3;
	}

	private static int compareNormal(int[] cards1, int[] cards2) {
		sortCards(cards1);
		sortCards(cards2);
		int dot1C = cards1[2] % 100;
		int dot2C = cards2[2] % 100;
		if (dot1C != dot2C) {
			return compareDot(dot1C, dot2C);
		}

		int dot1B = cards1[1] % 100;
		int dot2B = cards2[1] % 100;
		if (dot1B != dot2B) {
			return compareDot(dot1B,dot2B);
		}

		int dot1A = cards1[0] % 100;
		int dot2A = cards2[0] % 100;
		return compareDot(dot1A,dot2A);
	}

	private static int compare235(int[] cards1, int[] cards2) {
		return 0;
	}

}
