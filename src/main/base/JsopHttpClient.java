package main.base;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Jsop HttpClientクラス
 * @author 7days
 */
public class JsopHttpClient {

    /** logger */
    private static final Logger logger = Logger.getLogger(JsopHttpClient.class);

    /** コネクション */
    private Connection connection;
    /** Cookie */
    private Map<String, String> cookies;
    /** HTML Documet */
    private Document document;
    /** Response */
    private Response response;

    /**
     * コネクションの生成<br>
     * （Cookieが存在する場合、引き継ぐ）
     * @param url URL
     */
    public void connect(String url) {
        connection = Jsoup.connect(url);
        if (cookies != null) {
            connection.cookies(cookies);
        }
    }

    /**
     * HTML Documetの取得<br>
     * （GET通信）
     * @return HTML Documet
     */
    public Document get() {
        return get(Method.GET);
    }

    /**
     * HTML Documetの取得<br>
     * @param method method
     * @return HTML Document
     */
    public Document get(Method method) {
        try {
            response = connection.method(method).execute();
            document = response.parse();
            if (!response.cookies().isEmpty()) {
                // cookiesが存在する場合は保存する
                cookies = response.cookies();
            }
        } catch (IOException e) {
            // エラー発生時はHTML documentとcookiesをnullに設定
            logger.error("get error", e);
            document = null;
            cookies = null;
        }

        return document;
    }

    /**
     * コネクションを取得します。
     * @return コネクション
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Cookieを取得します。
     * @return Cookie
     */
    public Map<String, String> getCookies() {
        return cookies;
    }

    /**
     * HTML Documetを取得します。
     * @return HTML Documet
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Responseを取得します。
     * @return Response
     */
    public Response getResponse() {
        return response;
    }
}
