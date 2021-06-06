package janken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Janken {

	public static void main(String[] args) throws IOException, InterruptedException {
		/*
		 * じゃんけんアプリのアプリケーションクラス
		 * じゃんけんアプリの要件定義
		 * ・グーチョキパーの3種類を使用する
		 * ・２人対戦
		 * ・勝敗を表示する
		 * ・対戦回数をカウントする
		 * ・好きなだけ再戦ができる。飽きたらプログラムを終了できるように作成する
		 *
		 */
		//フィールド
		final int SAME = 0;
		final int WIN = 1;
		final int LOSE = 2;
		final String[] hand = { "グー", "チョキ", "パー" };
		String enemy = "相手";
		String name = "Player";
		String temp = null;
		char c = ' ';
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//カウンタ変数
		int winCnt = 0;
		int loseCnt = 0;
		int sameCnt = 0;

		int[][] table = { //二次元配列で勝敗テーブルを定義する
				{ SAME, WIN, LOSE },
				{ LOSE, SAME, WIN },
				{ WIN, LOSE, SAME }
		};


		//以下メイン
		System.out.println("じゃんけんプログラムを開始します");
		//プレイヤー名取得
		while (c != 'y') {
			System.out.println("あなたの名前を教えてください");
			temp = br.readLine();
			if (temp.length() == 0) //未入力の場合初期値を反映させるためif判定させる
				temp = name;
			System.out.println(
					"あなたの名前は" + temp + "さんでよろしいでしょうか。？(Y/N)");
			c = (char) (System.in.read());
			System.in.skip(1000);
			while (c != 'y' && c != 'n') {
				System.out.println("不正な入力です。(Y/N)を入力してください");
				c = (char) (System.in.read());
				System.in.skip(1000);
			}
		}
		name = temp; //ループを抜けたら名前が確定するためtempをnameに代入する

		//じゃんけんプログラム開始(自分の処理）
		while (true) {
			System.out.println("対戦します。何を出しますか？");
			int input = -1;
			while (!(input >= 0 && input <= 2)) {
				System.out.println("グー:0 チョキ: 1 パー:2 ");
				input = Character.digit(System.in.read(), 10);
				System.in.skip(1000);
				if (!(input >= 0 && input <= 2))
					System.out.println(
							"入力された値が不正です。正しい数値を入力してください。");
			}
			System.out.println(name + "さんは" + hand[input] + "を出しました。");

			//（相手の処理）
			int point = (int) (Math.random() * 3);
			Thread.sleep(2000); //２秒待機
			System.out.println(enemy + "は" + hand[point] + "を出しました");

			//判定
			if (table[input][point] == SAME) {
				System.out.println("あいこ です");
				sameCnt++;
				continue; //あいこだった場合は再戦させる
			} else if (table[input][point] == WIN) {
				System.out.println("あなたは 勝ち です！");
				winCnt++;
			} else if (table[input][point] == LOSE) {
				System.out.println("あなたは 負け ました。。。");
				loseCnt++;
			}

			//再戦処理
			System.out.println("もう一度じゃんけんしますか？(Y/N)");
			System.out.println(
					"現在：" + winCnt + "勝  " + loseCnt + "敗  引き分け" + sameCnt + "回");
			c = (char) (System.in.read());
			System.in.skip(1000);
			while (c != 'y' && c != 'n') {
				System.out.println("不正な入力です。(Y/N)を入力してください");
				c = (char) (System.in.read());
				System.in.skip(1000);
			}
			if (c == 'n') {
				System.out.println("プログラムを終了します。");
				break; //無限ループを抜けてプログラムを終了する
			}
		}
	}

}
