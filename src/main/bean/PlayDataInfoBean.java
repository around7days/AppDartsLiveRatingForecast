package main.bean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * プレイデータ情報Bean
 * @author 7days
 */
public class PlayDataInfoBean {

    /*
     * レーティング
     */

    /** レーティング */
    @Expose
    private Double rating = 0.0;

    /** レーティング（new） */
    @Expose
    private Double ratingNew = 0.0;

    /** レーティング（前日比） */
    @Expose
    private Double ratingDiff = 0.0;

    /** ０１レーティング */
    @Expose
    private Double rating01 = 0.0;

    /** ０１レーティング（new） */
    @Expose
    private Double rating01New = 0.0;

    /** ０１レーティング（前日比） */
    @Expose
    private Double rating01Diff = 0.0;

    /** ０１レーティング（今日） */
    @Expose
    private Double rating01Today = 0.0;

    /** クリケットレーティング */
    @Expose
    private Double ratingCricket = 0.0;

    /** クリケットレーティング（new） */
    @Expose
    private Double ratingCricketNew = 0.0;

    /** クリケットレーティング（前日比） */
    @Expose
    private Double ratingCricketDiff = 0.0;

    /** クリケットレーティング（今日） */
    @Expose
    private Double ratingCricketToday = 0.0;

    /*
     * スタッツ
     */

    /** カウントアップスタッツ（new） */
    private Double statsCountUpNew = 0.0;

    /** ０１スタッツ */
    @Expose
    private Double stats01 = 0.0;

    /** ０１スタッツ（new） */
    @Expose
    private Double stats01New = 0.0;

    /** ０１スタッツ（前日比） */
    @Expose
    private Double stats01Diff = 0.0;

    /** ０１スタッツ（今日） */
    @Expose
    private Double stats01Today = 0.0;

    /** クリケットスタッツ */
    @Expose
    private Double statsCricket = 0.0;

    /** クリケットスタッツ（new） */
    @Expose
    private Double statsCricketNew = 0.0;

    /** クリケットスタッツ（前日比） */
    @Expose
    private Double statsCricketDiff = 0.0;

    /** クリケットスタッツ（今日） */
    @Expose
    private Double statsCricketToday = 0.0;
    /*
     * 本日実績データ
     */

    /** ０１スタッツ */
    @Expose
    private List<Double> stats01List = new ArrayList<>();

    /** クリケットスタッツ */
    @Expose
    private List<Double> statsCricketList = new ArrayList<>();

    /** カウントアップスタッツ */
    private List<Double> statsCountUpList = new ArrayList<>();

    /**
     * レーティングを取得します。
     * @return レーティング
     */
    public Double getRating() {
        return rating;
    }

    /**
     * レーティングを設定します。
     * @param rating レーティング
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * レーティング（new）を取得します。
     * @return レーティング（new）
     */
    public Double getRatingNew() {
        return ratingNew;
    }

    /**
     * レーティング（new）を設定します。
     * @param ratingNew レーティング（new）
     */
    public void setRatingNew(Double ratingNew) {
        this.ratingNew = ratingNew;
    }

    /**
     * レーティング（前日比）を取得します。
     * @return レーティング（前日比）
     */
    public Double getRatingDiff() {
        return ratingDiff;
    }

    /**
     * レーティング（前日比）を設定します。
     * @param ratingDiff レーティング（前日比）
     */
    public void setRatingDiff(Double ratingDiff) {
        this.ratingDiff = ratingDiff;
    }

    /**
     * ０１レーティングを取得します。
     * @return ０１レーティング
     */
    public Double getRating01() {
        return rating01;
    }

    /**
     * ０１レーティングを設定します。
     * @param rating01 ０１レーティング
     */
    public void setRating01(Double rating01) {
        this.rating01 = rating01;
    }

    /**
     * ０１レーティング（new）を取得します。
     * @return ０１レーティング（new）
     */
    public Double getRating01New() {
        return rating01New;
    }

    /**
     * ０１レーティング（new）を設定します。
     * @param rating01New ０１レーティング（new）
     */
    public void setRating01New(Double rating01New) {
        this.rating01New = rating01New;
    }

    /**
     * ０１レーティング（前日比）を取得します。
     * @return ０１レーティング（前日比）
     */
    public Double getRating01Diff() {
        return rating01Diff;
    }

    /**
     * ０１レーティング（前日比）を設定します。
     * @param rating01Diff ０１レーティング（前日比）
     */
    public void setRating01Diff(Double rating01Diff) {
        this.rating01Diff = rating01Diff;
    }

    /**
     * ０１レーティング（今日）を取得します。
     * @return ０１レーティング（今日）
     */
    public Double getRating01Today() {
        return rating01Today;
    }

    /**
     * ０１レーティング（今日）を設定します。
     * @param rating01Today ０１レーティング（今日）
     */
    public void setRating01Today(Double rating01Today) {
        this.rating01Today = rating01Today;
    }

    /**
     * クリケットレーティングを取得します。
     * @return クリケットレーティング
     */
    public Double getRatingCricket() {
        return ratingCricket;
    }

    /**
     * クリケットレーティングを設定します。
     * @param ratingCricket クリケットレーティング
     */
    public void setRatingCricket(Double ratingCricket) {
        this.ratingCricket = ratingCricket;
    }

    /**
     * クリケットレーティング（new）を取得します。
     * @return クリケットレーティング（new）
     */
    public Double getRatingCricketNew() {
        return ratingCricketNew;
    }

    /**
     * クリケットレーティング（new）を設定します。
     * @param ratingCricketNew クリケットレーティング（new）
     */
    public void setRatingCricketNew(Double ratingCricketNew) {
        this.ratingCricketNew = ratingCricketNew;
    }

    /**
     * クリケットレーティング（前日比）を取得します。
     * @return クリケットレーティング（前日比）
     */
    public Double getRatingCricketDiff() {
        return ratingCricketDiff;
    }

    /**
     * クリケットレーティング（前日比）を設定します。
     * @param ratingCricketDiff クリケットレーティング（前日比）
     */
    public void setRatingCricketDiff(Double ratingCricketDiff) {
        this.ratingCricketDiff = ratingCricketDiff;
    }

    /**
     * クリケットレーティング（今日）を取得します。
     * @return クリケットレーティング（今日）
     */
    public Double getRatingCricketToday() {
        return ratingCricketToday;
    }

    /**
     * クリケットレーティング（今日）を設定します。
     * @param ratingCricketToday クリケットレーティング（今日）
     */
    public void setRatingCricketToday(Double ratingCricketToday) {
        this.ratingCricketToday = ratingCricketToday;
    }

    /**
     * カウントアップスタッツ（new）を取得します。
     * @return カウントアップスタッツ（new）
     */
    public Double getStatsCountUpNew() {
        return statsCountUpNew;
    }

    /**
     * カウントアップスタッツ（new）を設定します。
     * @param statsCountUpNew カウントアップスタッツ（new）
     */
    public void setStatsCountUpNew(Double statsCountUpNew) {
        this.statsCountUpNew = statsCountUpNew;
    }

    /**
     * ０１スタッツを取得します。
     * @return ０１スタッツ
     */
    public Double getStats01() {
        return stats01;
    }

    /**
     * ０１スタッツを設定します。
     * @param stats01 ０１スタッツ
     */
    public void setStats01(Double stats01) {
        this.stats01 = stats01;
    }

    /**
     * ０１スタッツ（new）を取得します。
     * @return ０１スタッツ（new）
     */
    public Double getStats01New() {
        return stats01New;
    }

    /**
     * ０１スタッツ（new）を設定します。
     * @param stats01New ０１スタッツ（new）
     */
    public void setStats01New(Double stats01New) {
        this.stats01New = stats01New;
    }

    /**
     * ０１スタッツ（前日比）を取得します。
     * @return ０１スタッツ（前日比）
     */
    public Double getStats01Diff() {
        return stats01Diff;
    }

    /**
     * ０１スタッツ（前日比）を設定します。
     * @param stats01Diff ０１スタッツ（前日比）
     */
    public void setStats01Diff(Double stats01Diff) {
        this.stats01Diff = stats01Diff;
    }

    /**
     * ０１スタッツ（今日）を取得します。
     * @return ０１スタッツ（今日）
     */
    public Double getStats01Today() {
        return stats01Today;
    }

    /**
     * ０１スタッツ（今日）を設定します。
     * @param stats01Today ０１スタッツ（今日）
     */
    public void setStats01Today(Double stats01Today) {
        this.stats01Today = stats01Today;
    }

    /**
     * クリケットスタッツを取得します。
     * @return クリケットスタッツ
     */
    public Double getStatsCricket() {
        return statsCricket;
    }

    /**
     * クリケットスタッツを設定します。
     * @param statsCricket クリケットスタッツ
     */
    public void setStatsCricket(Double statsCricket) {
        this.statsCricket = statsCricket;
    }

    /**
     * クリケットスタッツ（new）を取得します。
     * @return クリケットスタッツ（new）
     */
    public Double getStatsCricketNew() {
        return statsCricketNew;
    }

    /**
     * クリケットスタッツ（new）を設定します。
     * @param statsCricketNew クリケットスタッツ（new）
     */
    public void setStatsCricketNew(Double statsCricketNew) {
        this.statsCricketNew = statsCricketNew;
    }

    /**
     * クリケットスタッツ（前日比）を取得します。
     * @return クリケットスタッツ（前日比）
     */
    public Double getStatsCricketDiff() {
        return statsCricketDiff;
    }

    /**
     * クリケットスタッツ（前日比）を設定します。
     * @param statsCricketDiff クリケットスタッツ（前日比）
     */
    public void setStatsCricketDiff(Double statsCricketDiff) {
        this.statsCricketDiff = statsCricketDiff;
    }

    /**
     * クリケットスタッツ（今日）を取得します。
     * @return クリケットスタッツ（今日）
     */
    public Double getStatsCricketToday() {
        return statsCricketToday;
    }

    /**
     * クリケットスタッツ（今日）を設定します。
     * @param statsCricketToday クリケットスタッツ（今日）
     */
    public void setStatsCricketToday(Double statsCricketToday) {
        this.statsCricketToday = statsCricketToday;
    }

    /**
     * ０１スタッツを取得します。
     * @return ０１スタッツ
     */
    public List<Double> getStats01List() {
        return stats01List;
    }

    /**
     * ０１スタッツを設定します。
     * @param stats01List ０１スタッツ
     */
    public void setStats01List(List<Double> stats01List) {
        this.stats01List = stats01List;
    }

    /**
     * クリケットスタッツを取得します。
     * @return クリケットスタッツ
     */
    public List<Double> getStatsCricketList() {
        return statsCricketList;
    }

    /**
     * クリケットスタッツを設定します。
     * @param statsCricketList クリケットスタッツ
     */
    public void setStatsCricketList(List<Double> statsCricketList) {
        this.statsCricketList = statsCricketList;
    }

    /**
     * カウントアップスタッツを取得します。
     * @return カウントアップスタッツ
     */
    public List<Double> getStatsCountUpList() {
        return statsCountUpList;
    }

    /**
     * カウントアップスタッツを設定します。
     * @param statsCountUpList カウントアップスタッツ
     */
    public void setStatsCountUpList(List<Double> statsCountUpList) {
        this.statsCountUpList = statsCountUpList;
    }

    /**
     * ０１スタッツを設定します。
     * @param stats ０１スタッツ
     */
    public void addStats01List(Double stats) {
        this.stats01List.add(stats);
    }

    /**
     * クリケットスタッツを設定します。
     * @param stats クリケットスタッツ
     */
    public void addStatsCricketList(Double stats) {
        this.statsCricketList.add(stats);
    }

    /**
     * カウントアップスタッツを設定します。
     * @param stats カウントアップスタッツ
     */
    public void addStatsCountUpList(Double stats) {
        this.statsCountUpList.add(stats);
    }

}
