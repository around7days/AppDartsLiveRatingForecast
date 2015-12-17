package main;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.servlet.ServletException;

import main.bean.PlayDataInfoBean;
import main.common.Utils;
import main.logic.Logic;

import org.apache.log4j.Logger;

/**
 * Debugクラス
 * @author 7days
 */
public class Debug {

    /** logger */
    private static final Logger logger = Logger.getLogger(Debug.class);

    /**
     * デバッグ用
     * @param args 引数
     */
    public static void main(String[] args) {
        logger.info("--------------- start ---------------");
        try {

            new Debug().execute();
        } catch (Exception e) {
            logger.error("system err", e);
        }
        logger.info("---------------- end ----------------");
    }

    /**
     * メイン処理
     * @throws IOException
     * @throws ServletException
     */
    private void execute() throws IOException, ServletException {

        kabe0928();
        kabe0929();
        kabe0930();
        kabe1003();
        kabe1005();
        kabe1009();
        kabe1011();
        kabe1012();
        kabe1014();
        kabe1016();
        kabe1017();

        // zawa1011();
    }

    private void kabe0928() {
        System.out.println("★★★★★★★★★★kabe0928");

        // レーティング
        Double rating = 7.45;
        // レーティング（実績）
        Double ratingResult = 7.99;
        // ０１スタッツ
        Double stats01 = 66.18;
        // クリケットスタッツ
        Double statsCricket = 2.45;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 80.86, 81.29, 73.43, 92.14, 79.38, 82.29, 98.83, 57.33, 51.78 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 3.00, 3.00, 2.50, 3.67, 3.33 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);

    }

    private void kabe0929() {
        System.out.println("★★★★★★★★★★kabe0929");

        // レーティング
        Double rating = 7.99;
        // レーティング（実績）
        Double ratingResult = 7.75;
        // ０１スタッツ
        Double stats01 = 69.0;
        // クリケットスタッツ
        Double statsCricket = 2.55;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 68.00, 57.90, 57.33, 69.38, 64.00, 69.75, 61.78, 57.86 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.00, 2.10, 2.50, 2.54, 2.75, 2.15, 3.29, 2.56, 1.86 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe0930() {
        System.out.println("★★★★★★★★★★kabe0930");

        // レーティング
        Double rating = 7.75;
        // レーティング（実績）
        Double ratingResult = 7.59;
        // ０１スタッツ
        Double stats01 = 67.64;
        // クリケットスタッツ
        Double statsCricket = 2.51;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 55.56, 90.57, 52.86, 45.00, 56.30 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.30, 2.38 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1003() {
        System.out.println("★★★★★★★★★★kabe1003");

        // レーティング
        Double rating = 7.59;
        // レーティング（実績）
        Double ratingResult = 7.80;
        // ０１スタッツ
        Double stats01 = 66.41;
        // クリケットスタッツ
        Double statsCricket = 2.50;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 79.00, 86.71, 79.00, 53.45, 87.71, 73.00 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.78, 2.29, 2.50 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1005() {
        System.out.println("★★★★★★★★★★kabe1005");

        // レーティング
        Double rating = 7.80;
        // レーティング（実績）
        Double ratingResult = 7.94;
        // ０１スタッツ
        Double stats01 = 68.24;
        // クリケットスタッツ
        Double statsCricket = 2.50;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 81.86, 101.33 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.45, 1.89 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1009() {
        System.out.println("★★★★★★★★★★kabe1009");

        // レーティング
        Double rating = 7.94;
        // レーティング（実績）
        Double ratingResult = 7.92;
        // ０１スタッツ
        Double stats01 = 69.78;
        // クリケットスタッツ
        Double statsCricket = 2.48;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 76.25, 53.50, 73.25, 84.29 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 1.86, 2.22, 2.60 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1011() {
        System.out.println("★★★★★★★★★★kabe1011");

        // レーティング
        Double rating = 7.92;
        // レーティング（実績）
        Double ratingResult = 7.70;
        // ０１スタッツ
        Double stats01 = 70.06;
        // クリケットスタッツ
        Double statsCricket = 2.45;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 56.71, 50.00, 72.63, 49.57, 69.00, 48.14, 61.88, 78.14, 68.50 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.86 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1012() {
        System.out.println("★★★★★★★★★★kabe1012");

        // レーティング
        Double rating = 7.70;
        // レーティング（実績）
        Double ratingResult = 7.72;
        // ０１スタッツ
        Double stats01 = 67.95;
        // クリケットスタッツ
        Double statsCricket = 2.47;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 59.50, 71.75, 99.80, 74.50 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.10, 1.71, 2.20, 2.20 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1014() {
        System.out.println("★★★★★★★★★★kabe1014");

        // レーティング
        Double rating = 7.72;
        // レーティング（実績）
        Double ratingResult = 7.75;
        // ０１スタッツ
        Double stats01 = 69.07;
        // クリケットスタッツ
        Double statsCricket = 2.42;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 86.40, 81.14 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.25, 1.50, 2.22 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1016() {
        System.out.println("★★★★★★★★★★kabe1016");

        // レーティング
        Double rating = 7.75;
        // レーティング（実績）
        Double ratingResult = 7.62;
        // ０１スタッツ
        Double stats01 = 70.03;
        // クリケットスタッツ
        Double statsCricket = 2.37;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 73.63 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 1.36, 1.67, 2.70, 2.29, 2.40, 1.67 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    private void kabe1017() {
        System.out.println("★★★★★★★★★★kabe1017");

        // レーティング
        Double rating = 7.62;
        // レーティング（実績）
        Double ratingResult = 7.46;
        // ０１スタッツ
        Double stats01 = 70.15;
        // クリケットスタッツ
        Double statsCricket = 2.31;
        // ０１.......................1......2......3......4......5......6......7......8......9......10
        Double[] statsZeroOneList = { 64.56, 48.57, 80.86, 70.63, 51.89 };
        // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
        Double[] statsCricketList = { 2.67, 2.20, 2.11, 2.00, 2.09, 2.30 };

        // 判定
        judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    }

    // private void zawa1011() {
    // System.out.println("■■■■■■■■■■zawa1011");
    //
    // // レーティング
    // Double rating = 8.32;
    // // レーティング（実績）
    // Double ratingResult = 8.30;
    // // ０１スタッツ
    // Double stats01 = ;
    // // クリケットスタッツ
    // Double statsCricket = ;
    // // ０１.......................1......2......3......4......5......6......7......8......9......10
    // Double[] statsZeroOneList = { 55.00, 27.25, 54.63, 70.50, 83.63, 90.29, 73.88, 72.75, 82.57, 95.33, 84.33,
    // 81.67, 82.00, 86.33 };
    // // クリケット.................1.....2.....3.....4.....5.....6.....7.....8.....9.....10
    // Double[] statsCricketList = { 1.86 };
    //
    // // 判定
    // judge(rating, stats01, statsCricket, ratingResult, statsZeroOneList, statsCricketList);
    // }

    private void judge(Double rating,
                       Double stats01,
                       Double statsCricket,
                       Double ratingResult,
                       Double[] statsZeroOneList,
                       Double[] statsCricketList) {
        PlayDataInfoBean playDataInfo = new PlayDataInfoBean();
        playDataInfo.setRating(rating);
        playDataInfo.setStats01(stats01);
        playDataInfo.setStatsCricket(statsCricket);
        playDataInfo.setStats01List(Arrays.asList(statsZeroOneList));
        playDataInfo.setStatsCricketList(Arrays.asList(statsCricketList));

        Logic logic = new Logic();
        logic.ratingCalcY(playDataInfo);

        Double ratingNew = playDataInfo.getRatingNew();
        Double ratingError = Utils.roundHalfUp(ratingNew - ratingResult);

        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println("予想：" + rating + " → " + df.format(ratingNew));
        System.out.println("実際：" + rating + " → " + df.format(ratingResult));
        System.out.println("誤差：" + df.format(ratingError));
    }

}
