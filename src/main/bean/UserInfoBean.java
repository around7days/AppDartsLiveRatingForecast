package main.bean;

import com.google.gson.annotations.Expose;

/**
 * ユーザー情報Bean
 * @author 7days
 */
public class UserInfoBean {

    /** カードID */
    @Expose
    private String cardId;

    /** パスワード */
    private String password;

    /** ユーザー名 */
    @Expose
    private String userNm;

    /**
     * カードIDを取得します。
     * @return カードID
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * カードIDを設定します。
     * @param cardId カードID
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します。
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * ユーザー名を取得します。
     * @return ユーザー名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザー名を設定します。
     * @param userNm ユーザー名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

}
