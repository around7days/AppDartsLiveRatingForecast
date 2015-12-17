package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.base.AjaxErrorException;
import main.base.JsopHttpClient;
import main.bean.PlayDataInfoBean;
import main.bean.UserInfoBean;
import main.logic.Logic;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** logger */
    private static final Logger logger = Logger.getLogger(MainServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        logger.info("------ start[DartsLiveRatingForecast] -----");
        try {
            execute(request, response);
        } catch (AjaxErrorException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("System Error", e);
            throw e;
        } finally {
            logger.info("------ end  [DartsLiveRatingForecast] -----");
        }
    }

    /**
     * メイン処理
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void execute(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        /*
         * リクエスト情報の取得
         */
        String cardId = request.getParameter("i"); // CardID
        String password = request.getParameter("p"); // Password

        /*
         * ダーツライブ情報の取得（Json形式）
         */
        String json = accessDartsLive(cardId, password);

        /*
         * レスポンス返却
         */
        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    /**
     * ダーツライブ情報の取得（Json形式）
     * @param cardId
     * @param password
     * @return ダーツライブ情報
     * @throws IOException
     * @throws ServletException
     */
    protected String accessDartsLive(String cardId,
                                     String password) throws ServletException, IOException {

        /*
         * ユーザー情報の生成
         */
        UserInfoBean userInfo = new UserInfoBean();
        userInfo.setCardId(cardId);
        userInfo.setPassword(password);
        logger.debug("カードID  ：" + userInfo.getCardId());
        logger.debug("パスワード：" + userInfo.getPassword());

        /*
         * プレイデータ情報の宣言
         */
        PlayDataInfoBean playDataInfo = new PlayDataInfoBean();

        // HTTP Clientの生成
        JsopHttpClient httpClient = new JsopHttpClient();
        // ロジッククラスの生成
        Logic logic = new Logic();

        /*
         * ログイン処理
         */
        boolean isLogin = logic.login(httpClient, userInfo);
        if (!isLogin) {
            throw new AjaxErrorException("ログインに失敗しました。");
        }

        /*
         * プレイデータ取得処理
         */
        boolean isPlayData = logic.playData(httpClient, playDataInfo);
        if (!isPlayData) {
            throw new AjaxErrorException("プレイデータの取得に失敗しました。");
        }

        /*
         * 本日の実績データ取得処理
         */
        boolean isTodayData = logic.todayData(httpClient, playDataInfo);
        if (!isTodayData) {
            throw new AjaxErrorException("本日の実績データの取得に失敗しました。");
        }

        /*
         * レーティングの計算処理（メイン）
         */
        logic.ratingCalc(playDataInfo);

        /*
         * レーティングの計算処理（その他）
         */
        logic.otherCalc(playDataInfo);

        /*
         * Json形式に変換
         */
        String json = logic.convertJson(userInfo, playDataInfo);

        return json;
    }
}
