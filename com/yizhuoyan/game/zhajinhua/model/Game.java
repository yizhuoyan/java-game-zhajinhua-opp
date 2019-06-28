package com.yizhuoyan.game.zhajinhua.model;

import com.yizhuoyan.game.zhajinhua.view.ConsoleView;

public class Game {
	
	public static void begin() {
		System.out.println("欢迎加入炸金花游戏V1.0");
		//1 游戏初始化
		//1.1 创建一副牌
		int[] porker=createPorker();
		//1.2 读取玩家姓名
		String playerName=ConsoleView.getPlayerName();
		//1.3 分配初始筹码
		int playerChips=1000;
		int computerChips=1000;
		//2 进行游戏
		while(true) {
			System.out.println("-------------------------------");
			//2.0 统计筹码
			System.out.println(playerName+"您目前持有筹码数为:"+playerChips);
			//2.1 洗牌
			System.out.print("开始洗牌......");
			shuffleCards(porker);
			System.out.println("完成");
			//2.2 扣除基础筹码20
			System.out.print("扣除基础筹码100，");
			playerChips-=100;
			computerChips-=100;
			System.out.println("您目前还持有:"+playerChips);
			
			// 当前桌面上的筹码总数
			int tableChips=200;
			
			//2.2 发牌
			System.out.println("开始发牌");
			int[] playerCards=dealCards(porker,0,3);
			int[] computerCards=dealCards(porker,3,6);
			//2.3 给玩家展示牌
			ConsoleView.showCards("您",playerCards);
			//2.4 让玩家下组
			int playerBet=ConsoleView.getBetShips(playerChips);
			if(playerBet==0) {
				//弃牌
				System.out.println("你已选择弃牌，重新开始");
				continue;
			}
			//减去已下注金额
			playerChips-=playerBet;
			computerChips-=playerBet;
			System.out.println("您已下注"+playerBet+",你目前筹码数:"+playerChips);
			tableChips+=playerBet;
			tableChips+=playerBet;
			//2.5 比大小，显示输赢
			//2.5.1 展示电脑的牌
			ConsoleView.showCards("电脑",computerCards);
			int result=CardsCompareModel.compare(playerCards, computerCards);
			if(result>0) {
				System.out.println(playerName+"恭喜您，你胜利了");
				playerChips+=tableChips;
			}else if(result<0) {
				System.out.println(playerName+"很抱歉，你输了");
				computerChips+=tableChips;
			}else {
				System.out.println("平局");				
			}	
			
			
			//3 进行游戏状态判断 	
			if(playerChips<=0) {
				System.out.println("您倾家荡产了，游戏结束");
				break;
			}else if(computerChips<=0) {
				System.out.println("您把电脑赢的倾家荡产了，游戏结束");
				break;
			}else {
				//询问是否下一轮
				if(!ConsoleView.getIsContinuePlay()) {
					//退出循环
					break;
				}
			}
		}
		
		System.out.println("欢迎再次使用!");
		
	}
	

	/**
	 * 洗牌
	 */
	public static void shuffleCards(int[] porker) {
		for (int i = porker.length; i-- > 0;) {
			int ranIndex = (int) (Math.random() * porker.length);
			porker[0] = porker[ranIndex] + (porker[ranIndex] = porker[0]) * 0;
		}
	}
	/**
	 * 发牌 [1,2,3,4,5,6] 1~3 ==>[2,3]
	 */

	public static int[] dealCards(int[] arr, int begin, int end) {
		int[] result = new int[end - begin];
		int index = 0;
		for (int i = begin; i < end; i++) {
			result[index++] = arr[i];
		}
		return result;
	}

	/**
	 * 返回一副扑克牌 一个3位整数表示一张牌，百位表示花色 十位和个位表示点数 4xx 黑桃 3xx 红桃 2xx 梅花 1xx 方块 A=1 2.。 J=11
	 * Q=12 K=13 黑桃5=405 大王=999 小王=888
	 * 
	 * @return
	 */
	private static int[] createPorker() {
		int[] porker = new int[52];
		int i = 0;
		for (int color = 100; color <= 400; color += 100) {
			for (int dot = 14; dot-- > 1;) {
				porker[i++] = color + dot;
			}
		}
		//porker[i++] = 888;
		//porker[i++] = 999;
		return porker;

	}
}
