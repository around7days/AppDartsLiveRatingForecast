package main.common;

import static main.consts.Consts.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

import main.consts.Consts.GameType;

/**
 * Utilsクラス
 * @author 7days
 */
public class Utils {

    /**
     * スタッツをレーティングに変換
     * @param statsType スタッツタイプ
     * @param stats スタッツ
     * @return レーティング
     */
    public static Double convertStatsToRating(GameType statsType,
                                              Double stats) {
        if (GameType.ZeroOne == statsType) {
            // ０１
            return convertStatsToRating(stats, rating01Table);
        } else {
            // クリケット
            return convertStatsToRating(stats, ratingCricketTable);
        }
    }

    /**
     * スタッツをレーティングに変換
     * @param stats スタッツ
     * @param statsToRatingTable レーティング表
     * @return レーティング
     */
    private static Double convertStatsToRating(Double stats,
                                               TreeMap<Double, Double> statsToRatingTable) {
        /*
         * RATING換算表に値が存在する場合
         */
        if (statsToRatingTable.containsKey(stats)) {
            // RATING換算表からレーティングを取り出す
            return statsToRatingTable.get(stats);
        }

        /*
         * RATING換算表の最大値よりスタッツが大きい場合
         */
        if (statsToRatingTable.lastKey() < stats) {
            // RATING換算表の最大値をレーティングとする
            return statsToRatingTable.lastEntry().getValue();
        }

        /*
         * RATING換算表の範囲内の場合
         */
        // スタッツがレーティングのどの範囲かを調べる
        Double beginStats = statsToRatingTable.lowerKey(stats);
        Double endStats = statsToRatingTable.higherKey(stats);

        // 基準となるレーティングを取得する
        Double beginRating = statsToRatingTable.get(beginStats);

        // 差分のレーティングを計算
        Double rating = beginRating + ((stats - beginStats) / (endStats - beginStats));

        // 小数点第３位を四捨五入して返却
        return Utils.roundHalfUp(rating);
    }

    /**
     * レーティングをスタッツに変換
     * @param gameType ゲームタイプ
     * @param rating レーティング
     * @return スタッツ
     */
    public static Double convertRatingToStats(GameType gameType,
                                              Double rating) {
        if (GameType.ZeroOne == gameType) {
            // ０１
            return convertRatingToStats(rating, stats01Table);
        } else {
            // クリケット
            return convertRatingToStats(rating, statsCricketTable);
        }
    }

    /**
     * レーティングをスタッツに変換
     * @param rating レーティング
     * @param ratingToStatsTable スタッツ表
     * @return スタッツ
     */
    private static Double convertRatingToStats(Double rating,
                                               TreeMap<Double, Double> ratingToStatsTable) {
        /*
         * RATING換算表に値が存在する場合
         */
        if (ratingToStatsTable.containsKey(rating)) {
            // RATING換算表からレーティングを取り出す
            return ratingToStatsTable.get(rating);
        }

        /*
         * RATING換算表の範囲内の場合
         */
        // スタッツがレーティングのどの範囲かを調べる
        Double beginRating = ratingToStatsTable.lowerKey(rating);
        Double endRating = ratingToStatsTable.higherKey(rating);

        // 基準となるスタッツを取得する
        Double beginStats = ratingToStatsTable.get(beginRating);
        Double endStats = ratingToStatsTable.get(endRating);

        // 差分のスタッツを計算
        Double stats = beginStats + ((endStats - beginStats) * (rating - beginRating));

        // 小数点第３位を四捨五入して返却
        return Utils.roundHalfUp(stats);
    }

    /**
     * 指定された小数点で四捨五入<br>
     * （小数点第１位を四捨五入する場合はscaleに0を指定）
     * @param d 数値
     * @return レーティング
     */
    public static Double roundHalfUp(Double d) {
        return roundHalfUp(d, 2);
    }

    /**
     * 指定された小数点で四捨五入<br>
     * （小数点第１位を四捨五入する場合はscaleに0を指定）
     * @param d 数値
     * @param scale 桁数
     * @return レーティング
     */
    public static Double roundHalfUp(Double d,
                                     int scale) {
        return new BigDecimal(d).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 指定された小数点で切り捨て<br>
     * （小数点第１位を四捨五入する場合はscaleに0を指定）
     * @param d 数値
     * @return レーティング
     */
    public static Double roundDown(Double d) {
        return roundDown(d, 2);
    }

    /**
     * 指定された小数点で切り捨て<br>
     * （小数点第１位を四捨五入する場合はscaleに0を指定）
     * @param d 数値
     * @param scale 桁数
     * @return レーティング
     */
    public static Double roundDown(Double d,
                                   int scale) {
        return new BigDecimal(d).setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 平均を求める<br>
     * @param doubleList 数値
     * @return 平均
     */
    public static Double average(List<Double> doubleList) {
        if (doubleList == null || doubleList.isEmpty()) return 0.00;

        Double sum = 0.0;
        for (Double d : doubleList) {
            sum += d;
        }
        Double avg = sum / doubleList.size();
        return roundHalfUp(avg);
    }

    /**
     * 合計を求める<br>
     * @param doubleList 数値
     * @return 合計
     */
    public static Double sum(List<Double> doubleList) {
        if (doubleList == null || doubleList.isEmpty()) return 0.00;

        Double sum = 0.0;
        for (Double d : doubleList) {
            sum += d;
        }
        return roundHalfUp(sum);
    }
}
