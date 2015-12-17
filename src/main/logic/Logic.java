package main.logic;

import static main.consts.Consts.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.base.JsopHttpClient;
import main.bean.PlayDataInfoBean;
import main.bean.UserInfoBean;
import main.common.Utils;
import main.consts.Consts.GameType;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Logicクラス
 * @author 7days
 */
public class Logic {

    /** logger */
    private static final Logger logger = Logger.getLogger(Logic.class);

    // /**
    // * ログイン画面にアクセス
    // * @param httpClient
    // * @return 結果[true:正常 false:異常]
    // * @throws IOException
    // */
    // public boolean accsessDartsLive(JsopHttpClient httpClient) throws IOException {
    //
    // /*
    // * 接続
    // */
    // // コネクションの生成
    // httpClient.connect("https://card.dartslive.com/t/login.jsp");
    //
    // // 接続＆HTMLの取得
    // Document document = httpClient.get();
    //
    // // 接続チェック
    // String title = document.select("title").text();
    // if (!title.equals("ログイン | DARTSLIVE")) {
    // // タイトルが「ログイン | DARTSLIVE」に一致しない場合
    // // 異常
    // return false;
    // }
    //
    // // 正常
    // return true;
    // }

    /**
     * ログイン処理
     * @param httpClient httpClient
     * @param userInfo ユーザー情報
     * @return 結果[true:正常 false:異常]
     * @throws IOException IOException
     */
    public boolean login(JsopHttpClient httpClient,
                         UserInfoBean userInfo) throws IOException {
        /*
         * 接続
         */
        // コネクションの生成
        httpClient.connect("https://card.dartslive.com/t/doLogin.jsp");

        // パラメータ設定
        Map<String, String> param = new HashMap<>();
        param.put("i", userInfo.getCardId()); // カードID
        param.put("p", userInfo.getPassword()); // パスワード
        httpClient.getConnection().data(param);

        // 接続（POST通信）
        Document document = httpClient.get(Method.POST);

        // 接続チェック
        String title = document.select("title").text();
        if (!title.startsWith("DARTSLIVE")) {
            // タイトルが「DARTSLIVE」で始まらない場合
            // 異常
            return false;
        }

        /*
         * 結果取得
         */
        // ユーザー名の取得 ※class="playerName"内の<a>タグから値を取得
        String userNm = document.select(".playerName a").text();
        userInfo.setUserNm(userNm);
        logger.debug("ユーザー名：" + userNm);

        // 正常
        return true;
    }

    /**
     * プレイデータ取得
     * @param httpClient httpClient
     * @param playDataInfo プレイデータ情報
     * @return 結果[true:正常 false:異常]
     * @throws IOException IOException
     */
    public boolean playData(JsopHttpClient httpClient,
                            PlayDataInfoBean playDataInfo) throws IOException {
        /*
         * 接続
         */
        // コネクションの生成
        httpClient.connect("https://card.dartslive.com/t/play/index.jsp");

        // 接続＆HTMLの取得
        Document document = httpClient.get();

        // 接続チェック
        String title = document.select("title").text();
        if (!title.equals("プレイデータ")) {
            // タイトルが「プレイデータ」ではない場合
            // 異常
            return false;
        }

        /*
         * 結果取得
         */
        // レーティングの取得
        Double rating = Double.parseDouble(document.select("#refValue").text());
        logger.debug("★rating：" + rating);
        playDataInfo.setRating(rating);

        // 01スタッツ（new）の取得
        Double stats01New = Double.parseDouble(document.select(".statsAvg .stats01").text().split(" ")[0]);
        playDataInfo.setStats01New(stats01New);

        // クリケットスタッツ（new）の取得
        Double statsCricketNew = Double.parseDouble(document.select(".statsAvg .statsCri").text().split(" ")[0]);
        playDataInfo.setStatsCricketNew(statsCricketNew);

        // カウントアップスタッツ（new）の取得
        Double statsCountUpNew = Double.parseDouble(document.select(".statsAvg .statsPra").text());
        playDataInfo.setStatsCountUpNew(statsCountUpNew);

        // 01スタッツ（前日比）の取得
        Double stats01Diff = Double.parseDouble(document.select(".statsBefore .stats01").text());
        playDataInfo.setStats01Diff(stats01Diff);

        // クリケットスタッツ（前日比）の取得
        Double statsCricketDiff = Double.parseDouble(document.select(".statsBefore .statsCri").text());
        playDataInfo.setStatsCricketDiff(statsCricketDiff);

        // 正常
        return true;
    }

    /**
     * 本日の実績データ取得
     * @param httpClient httpClient
     * @param playDataInfo プレイデータ情報
     * @return 結果[true:正常 false:異常]
     * @throws IOException IOException
     */
    public boolean todayData(JsopHttpClient httpClient,
                             PlayDataInfoBean playDataInfo) throws IOException {
        /*
         * 接続
         */
        // コネクションの生成
        httpClient.connect("https://card.dartslive.com/t/playdata.jsp");

        // 接続＆HTMLの取得
        Document document = httpClient.get();

        // 接続チェック
        String title = document.select("title").text();
        if (!title.equals("今日・昨日のプレイ結果")) {
            // タイトルが「今日・昨日のプレイ結果」ではない場合
            // 異常
            return false;
        }
        if (!document.select("#today .notPlaying").isEmpty()) {
            // プレイデータが存在しない場合
            // 正常
            return true;
        }

        /*
         * 結果取得
         */
        // // 01スタッツ（今日）の取得
        // Double stats01Today = Double.parseDouble(document.select("#today #score .score_avg .score_01").text());
        // playDataInfo.setStats01Today(stats01Today);
        //
        // // クリケットスタッツ（今日）の取得
        // Double statsCricketToday = Double.parseDouble(document.select("#today #score .score_avg .score_cri").text());
        // playDataInfo.setStatsCricketToday(statsCricketToday);

        // resultTitleゲームタイトル
        // resultLi ゲーム結果
        String cssSelecter = "#today ul[class=result]>*";
        Iterator<Element> ite = document.select(cssSelecter).listIterator();
        String gameType = "";
        while (ite.hasNext()) {
            Element ele = ite.next();

            /*
             * class=resultTitleの場合、ゲーム種類の取得
             */
            if (ele.classNames().contains("resultTitle")) {
                // ゲーム種類の取得
                gameType = ele.text();
                logger.debug("ゲーム種類：" + gameType);
                continue;
            }

            /*
             * class=resultLiの場合、自身のstatsを取得してプレイデータに格納
             */
            if (ele.classNames().contains("resultLi")) {
                // 対戦者情報の取得
                Elements usersEle = ele.select("ul[class=gameList]>li");
                // 自身のstatsを取得
                Double stats = Double.parseDouble(usersEle.select("div[class=point own]").text());

                if (gameTypeZeroOne.contains(gameType) && usersEle.size() >= 2) {
                    // ゲームの種類が０１、且つ、対戦者数が２人以上の場合
                    playDataInfo.addStats01List(stats);

                } else if (gameTypeCricket.contains(gameType) && usersEle.size() >= 2) {
                    // ゲームの種類がクリケット、且つ、対戦者数が２人以上の場合
                    playDataInfo.addStatsCricketList(stats);

                } else if (gameTypeCountUp.contains(gameType)) {
                    // ゲームの種類がカウントアップ
                    playDataInfo.addStatsCountUpList(stats);

                } else {
                    // それ以外はスルー
                    continue;
                }
                logger.debug("スタッツ：" + stats);
            }

        }

        // 逆順に並び替え（プレイ順）
        Collections.reverse(playDataInfo.getStatsCountUpList());
        Collections.reverse(playDataInfo.getStats01List());
        Collections.reverse(playDataInfo.getStatsCricketList());

        // 正常
        return true;
    }

    /**
     * レーティング計算（メイン）<br>
     * ※多少の誤差は気にしない為、面倒なのでBigDecimalは使わない
     * @param playDataInfo プレイデータ情報
     */
    public void ratingCalc(PlayDataInfoBean playDataInfo) {
        // レーティング合計の宣言
        Double ratingSum = 0.00;
        // ゲーム数の宣言
        int gameCnt = 0;

        // ０１スタッツをレーティングに変換して加算
        for (Double stats : playDataInfo.getStats01List()) {
            ratingSum += Utils.convertStatsToRating(GameType.ZeroOne, stats);
            gameCnt++;
        }

        // クリケットスタッツをレーティングに変換して加算
        for (Double stats : playDataInfo.getStatsCricketList()) {
            ratingSum += Utils.convertStatsToRating(GameType.Cricket, stats);
            gameCnt++;
        }

        // ゲーム数が60回未満の場合は前日実績のレーティングを加算
        Double rating = playDataInfo.getRating();
        while (60 > gameCnt) {
            ratingSum += rating;
            gameCnt++;
        }

        // レーティング（new）の計算
        Double ratingNew = Utils.roundHalfUp(ratingSum / gameCnt);
        playDataInfo.setRatingNew(ratingNew);
        logger.debug("★new rating：" + ratingNew);
    }

    /**
     * レーティング計算（メイン）<br>
     * ※多少の誤差は気にしない為、面倒なのでBigDecimalは使わない
     * @param playDataInfo プレイデータ情報
     */
    public void ratingCalcX(PlayDataInfoBean playDataInfo) {

        // ゲーム数の宣言
        int gameCnt01 = 0;
        int gameCntCricket = 0;

        // レーティングの取得
        Double rating = playDataInfo.getRating();

        // ０１スタッツの合計を算出
        Double stats01Sum = 0.00;
        for (Double stats01 : playDataInfo.getStats01List()) {
            stats01Sum += stats01;
            gameCnt01++;
        }
        // ゲーム数が30回未満の場合は前日実績のスタッツを加算
        Double stats01 = Utils.convertRatingToStats(GameType.ZeroOne, rating);
        while (30 > gameCnt01) {
            stats01Sum += stats01;
            gameCnt01++;
        }

        // クリケットスタッツの合計を算出
        Double statsCricketSum = 0.00;
        for (Double statsCricket : playDataInfo.getStatsCricketList()) {
            statsCricketSum += statsCricket;
            gameCntCricket++;
        }
        // ゲーム数が30回未満の場合は前日実績のスタッツを加算
        Double statsCricket = Utils.convertRatingToStats(GameType.Cricket, rating);
        while (30 > gameCntCricket) {
            statsCricketSum += statsCricket;
            gameCntCricket++;
        }

        // レーティング（new）の計算
        Double stats01Avg = Utils.roundHalfUp(stats01Sum / gameCnt01);
        Double rating01New = Utils.convertStatsToRating(GameType.ZeroOne, stats01Avg);
        logger.debug("★new rating01     ：" + rating01New);

        Double statsCricketAvg = Utils.roundHalfUp(statsCricketSum / gameCntCricket);
        Double ratingCricketNew = Utils.convertStatsToRating(GameType.Cricket, statsCricketAvg);
        logger.debug("★new ratingCricket：" + ratingCricketNew);

        Double ratingNew = (rating01New + ratingCricketNew) / 2;
        ratingNew = Utils.roundHalfUp(ratingNew);

        playDataInfo.setRatingNew(ratingNew);
        logger.debug("★new rating       ：" + ratingNew);

    }

    /**
     * レーティング計算（メイン）<br>
     * ※多少の誤差は気にしない為、面倒なのでBigDecimalは使わない
     * @param playDataInfo プレイデータ情報
     */
    public void ratingCalcY(PlayDataInfoBean playDataInfo) {

        // ゲーム数の宣言
        int gameCnt01 = 0;
        int gameCntCricket = 0;

        // レーティングの取得
        Double rating = playDataInfo.getRating();
        Double rating01Ave = Utils.convertStatsToRating(GameType.ZeroOne, playDataInfo.getStats01());
        Double ratingCricketAve = Utils.convertStatsToRating(GameType.Cricket, playDataInfo.getStatsCricket());
        Double rate01 = (rating01Ave / (rating01Ave + ratingCricketAve)) + 0.5;
        Double rateCricket = (ratingCricketAve / (rating01Ave + ratingCricketAve)) + 0.5;
        Double rating01 = Utils.roundHalfUp(rating * rate01);
        Double ratingCricket = Utils.roundHalfUp(rating * rateCricket);

        // ０１スタッツの合計を算出
        Double stats01Sum = 0.00;
        for (Double stats01 : playDataInfo.getStats01List()) {
            stats01Sum += stats01;
            gameCnt01++;
        }
        // ゲーム数が30回未満の場合は前日実績のスタッツを加算
        Double stats01 = Utils.convertRatingToStats(GameType.ZeroOne, rating01);
        while (30 > gameCnt01) {
            stats01Sum += stats01;
            gameCnt01++;
        }

        // クリケットスタッツの合計を算出
        Double statsCricketSum = 0.00;
        for (Double statsCricket : playDataInfo.getStatsCricketList()) {
            statsCricketSum += statsCricket;
            gameCntCricket++;
        }
        // ゲーム数が30回未満の場合は前日実績のスタッツを加算
        Double statsCricket = Utils.convertRatingToStats(GameType.Cricket, ratingCricket);
        while (30 > gameCntCricket) {
            statsCricketSum += statsCricket;
            gameCntCricket++;
        }

        // レーティング（new）の計算
        Double stats01Avg = Utils.roundHalfUp(stats01Sum / gameCnt01);
        Double rating01New = Utils.convertStatsToRating(GameType.ZeroOne, stats01Avg);
        logger.debug("★new rating01     ：" + rating01New);

        Double statsCricketAvg = Utils.roundHalfUp(statsCricketSum / gameCntCricket);
        Double ratingCricketNew = Utils.convertStatsToRating(GameType.Cricket, statsCricketAvg);
        logger.debug("★new ratingCricket：" + ratingCricketNew);

        Double ratingNew = (rating01New + ratingCricketNew) / 2;
        ratingNew = Utils.roundHalfUp(ratingNew);

        playDataInfo.setRatingNew(ratingNew);
        logger.debug("★new rating       ：" + ratingNew);

    }

    /**
     * レーティング計算（その他）<br>
     * ※多少の誤差は気にしない為、面倒なのでBigDecimalは使わない
     * @param playDataInfo プレイデータ情報
     */
    public void otherCalc(PlayDataInfoBean playDataInfo) {

        // レーティング（前日比）の計算
        {
            Double ratingNew = playDataInfo.getRatingNew();
            Double rating = playDataInfo.getRating();
            Double ratingDiff = Utils.roundHalfUp(ratingNew - rating);
            playDataInfo.setRatingDiff(ratingDiff);
        }

        // ０１レーティングの計算
        {
            Double stats01New = playDataInfo.getStats01New();
            Double stats01Diff = playDataInfo.getStats01Diff();
            Double stats01 = Utils.roundHalfUp(stats01New - stats01Diff);
            Double rating01New = Utils.convertStatsToRating(GameType.ZeroOne, stats01New);
            Double rating01 = Utils.convertStatsToRating(GameType.ZeroOne, stats01);
            Double rating01Diff = Utils.roundHalfUp(rating01New - rating01);

            playDataInfo.setStats01(stats01);
            playDataInfo.setRating01(rating01);
            playDataInfo.setRating01New(rating01New);
            playDataInfo.setRating01Diff(rating01Diff);
        }

        // クリケットレーティングの計算
        {
            Double statsCricketNew = playDataInfo.getStatsCricketNew();
            Double statsCricketDiff = playDataInfo.getStatsCricketDiff();
            Double statsCricket = Utils.roundHalfUp(statsCricketNew - statsCricketDiff);
            Double ratingCricketNew = Utils.convertStatsToRating(GameType.Cricket, statsCricketNew);
            Double ratingCricket = Utils.convertStatsToRating(GameType.Cricket, statsCricket);
            Double ratingCricketDiff = Utils.roundHalfUp(ratingCricketNew - ratingCricket);

            playDataInfo.setStatsCricket(statsCricket);
            playDataInfo.setRatingCricket(ratingCricket);
            playDataInfo.setRatingCricketNew(ratingCricketNew);
            playDataInfo.setRatingCricketDiff(ratingCricketDiff);
        }

        // ０１レーティング（今日）の計算
        {
            Double stats01Today = Utils.average(playDataInfo.getStats01List());
            Double rating01Today = Utils.convertStatsToRating(GameType.ZeroOne, stats01Today);
            playDataInfo.setStats01Today(stats01Today);
            playDataInfo.setRating01Today(rating01Today);
        }

        // クリケットレーティング（今日）の計算
        {
            Double statsCricketToday = Utils.average(playDataInfo.getStatsCricketList());
            Double ratingCricketToday = Utils.convertStatsToRating(GameType.Cricket, statsCricketToday);
            playDataInfo.setStatsCricketToday(statsCricketToday);
            playDataInfo.setRatingCricketToday(ratingCricketToday);
        }
    }

    /**
     * Json形式に変換
     * @param userInfo ユーザー情報
     * @param playDataInfo プレイデータ情報
     * @return json
     */
    public String convertJson(UserInfoBean userInfo,
                              PlayDataInfoBean playDataInfo) {

        // Json変換対象をマップに格納
        HashMap<String, Object> map = new HashMap<>();
        map.put("userInfo", userInfo);
        map.put("playDataInfo", playDataInfo);

        // 変換(パスワードを除外)
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(map);
        logger.debug(json);

        return json;
    }
}
