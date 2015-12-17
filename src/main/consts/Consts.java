package main.consts;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Constsクラス
 * @author 7days
 */
public class Consts {
    /**
     * ゲームタイプ
     */
    public enum GameType {
        /** 01 */
        ZeroOne,
        /** クリケット */
        Cricket;
    }

    /**
     * 計算対象ゲーム種類（カウントアップ）
     */
    public final static List<String> gameTypeCountUp = Arrays.asList("COUNT-UP");

    /**
     * 計算対象ゲーム種類（０１）
     */
    public final static List<String> gameTypeZeroOne = Arrays.asList("301", "501", "701", "701", "901", "1101", "1501");

    /**
     * 計算対象ゲーム種類（クリケット）
     */
    public final static List<String> gameTypeCricket = Arrays.asList("STANDARD CRICKET");

    /**
     * RATING換算表 01 (stats → rating)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final static TreeMap<Double, Double> rating01Table = new TreeMap() {
        {
            // 初期化
            put(0.0, 1.00);
            put(40.0, 2.00);
            put(45.0, 3.00);
            put(50.0, 4.00);
            put(55.0, 5.00);
            put(60.0, 6.00);
            put(65.0, 7.00);
            put(70.0, 8.00);
            put(75.0, 9.00);
            put(80.0, 10.00);
            put(85.0, 11.00);
            put(90.0, 12.00);
            put(95.0, 13.00);
            put(102.0, 14.00);
            put(109.0, 15.00);
            put(116.0, 16.00);
            put(123.0, 17.00);
            put(130.0, 18.00);
        };
    };

    /**
     * RATING換算表 クリケット (stats → rating)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final static TreeMap<Double, Double> ratingCricketTable = new TreeMap() {
        {
            // 初期化
            put(0.00, 1.00);
            put(1.30, 2.00);
            put(1.50, 3.00);
            put(1.70, 4.00);
            put(1.90, 5.00);
            put(2.10, 6.00);
            put(2.30, 7.00);
            put(2.50, 8.00);
            put(2.70, 9.00);
            put(2.90, 10.00);
            put(3.10, 11.00);
            put(3.30, 12.00);
            put(3.50, 13.00);
            put(3.75, 14.00);
            put(4.00, 15.00);
            put(4.25, 16.00);
            put(4.50, 17.00);
            put(4.75, 18.00);
        };
    };

    /**
     * STATS換算表 01 (rating → stats)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final static TreeMap<Double, Double> stats01Table = new TreeMap() {
        {
            // 初期化
            put(1.00, 0.0);
            put(2.00, 40.0);
            put(3.00, 45.0);
            put(4.00, 50.0);
            put(5.00, 55.0);
            put(6.00, 60.0);
            put(7.00, 65.0);
            put(8.00, 70.0);
            put(9.00, 75.0);
            put(10.00, 80.0);
            put(11.00, 85.0);
            put(12.00, 90.0);
            put(13.00, 95.0);
            put(14.00, 102.0);
            put(15.00, 109.0);
            put(16.00, 116.0);
            put(17.00, 123.0);
            put(18.00, 130.0);
        };
    };

    /**
     * STATS換算表 クリケット (rating → stats)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final static TreeMap<Double, Double> statsCricketTable = new TreeMap() {
        {
            // 初期化
            put(1.00, 0.00);
            put(2.00, 1.30);
            put(3.00, 1.50);
            put(4.00, 1.70);
            put(5.00, 1.90);
            put(6.00, 2.10);
            put(7.00, 2.30);
            put(8.00, 2.50);
            put(9.00, 2.70);
            put(10.00, 2.90);
            put(11.00, 3.10);
            put(12.00, 3.30);
            put(13.00, 3.50);
            put(14.00, 3.75);
            put(15.00, 4.00);
            put(16.00, 4.25);
            put(17.00, 4.50);
            put(18.00, 4.75);
        };
    };
}
