<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Darts Live Rating予報</title>
<!--[if lt IE 9]>
<script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquerymobile/1.4.5/jquery.mobile.min.js"></script>
<script src="https:///cdnjs.cloudflare.com/ajax/libs/numeral.js/1.5.3/numeral.min.js"></script>
<!-- <script src="https://github.com/wwwtyro/cryptico/blob/master/cryptico.min.js"></script> -->

<!--
  独自javascript
-->
<script type="text/javascript">
$(function(){

  /**
   * 初期処理
   */
  {
    // ストレージから値の取得
    f_get_storage();

    // ログイン前画面の表示
    f_display(1);
  }

  /**
   * ログインアンカー or 更新ボタン押下処理
   */
  $("#loginAnchor, #refreshAnchor").on('click', function(){
    // データ取得処理
    f_get_data();
  });

  /**
   * ログアウトアンカー押下処理
   */
  $("#logoutAnchor").on('click', function(){
    // ログイン前画面の表示
    f_display(1);
  });

  /**
   * データ取得処理
   */
  function f_get_data(){
    // くるくる表示
    $.mobile.loading('show');

    var url = location.pathname + "main";
    $.ajax({
      url: url,
      type: "POST",
      data: {
        "i" : $("#cardId").val(),  //CardID
        "p" : $("#password").val() //Password
      },
      dataType: 'json',
      timeout: 10000, // 10秒
    }).done(function(data, textStatus, jqXHR){
      // ログイン成功

      // ストレージに値を格納
      f_set_storage();

      // ログイン後画面の表示
      f_display(2);

      // データ反映処理
      f_data_reflect(data);

    }).fail(function(data, textStatus, errorThrown){
      // ログイン失敗
      alert("Login Error");
    }).always(function(data, textStatus, returnedObject){
      // くるくる非表示
      $.mobile.loading('hide');
    });
  }

  /**
   * データ反映処理
   * @param {json} data
   */
  function f_data_reflect(data){

    // ユーザー情報
    var userInfo = data.userInfo;
    // プレイデータ情報
    var playDataInfo = data.playDataInfo;

    // 初期化
    $(".playList01 li").remove();
    $(".playListCricket li").remove();
    $(".notPlay01").show();
    $(".notPlayCricket").show();


    // 反映
    $("#userNm").text(" [" + userInfo.userNm + "]");

    $("#ratingNew").text(          f_format(playDataInfo.ratingNew,          false) ).text();
    $("#ratingDiff").text(         f_format(playDataInfo.ratingDiff,         true)  ).text();
    $("#rating01New").text(        f_format(playDataInfo.rating01New,        false) ).text();
    $("#rating01Diff").text(       f_format(playDataInfo.rating01Diff,       true)  ).text();
    $("#ratingCricketNew").text(   f_format(playDataInfo.ratingCricketNew,   false) ).text();
    $("#ratingCricketDiff").text(  f_format(playDataInfo.ratingCricketDiff,  true)  ).text();

    $("#stats01New").text(         f_format(playDataInfo.stats01New,         false) ).text();
    $("#stats01Diff").text(        f_format(playDataInfo.stats01Diff,        true)  ).text();
    $("#statsCricketNew").text(    f_format(playDataInfo.statsCricketNew,    false) ).text();
    $("#statsCricketDiff").text(   f_format(playDataInfo.statsCricketDiff,   true)  ).text();

    var stats01List = playDataInfo.stats01List;
    for(var i=0; i < stats01List.length; i++){
      $(".notPlay01").hide();
      $("#rating01Today").text(      f_format(playDataInfo.rating01Today,      false)  ).text();
      $("#stats01Today").text(       f_format(playDataInfo.stats01Today,       false)  ).text();
      $(".playList01").append( $("<li>").text( f_format(stats01List[i], false)) );
    }

    var statsCricketList = playDataInfo.statsCricketList;
    for(var i=0; i < statsCricketList.length; i++){
      $(".notPlayCricket").hide();
      $("#ratingCricketToday").text( f_format(playDataInfo.ratingCricketToday, false)  ).text();
      $("#statsCricketToday").text(  f_format(playDataInfo.statsCricketToday,  false)  ).text();
      $(".playListCricket").append( $("<li>").text( f_format(statsCricketList[i], false)) );
    }
  }

  /**
   * データフォーマット
   * @param {int,float} val 数値
   * @param {boolean} plusSign [true:プラス記号付与  false:プラス記号付与無し]
   * @return フォーマット後の数値
   */
   function f_format(val, plusSign){
      var formatVal = numeral(val).format("0.00");
      if(plusSign == true && formatVal > 0){
        formatVal = "+" + formatVal;
      }
      return formatVal;
  }

  /**
   * 画面表示制御
   *  1:ログインページ
   *  2:メインページ
   * @param {int} page 区分
   */
   function f_display(page){

     switch (page){
      case 1:
        // ログイン前
        $("#page [data-page='login']").show();
        $("#page [data-page='main']").hide();

        break;
      case 2:
        // ログイン後
        $("#page [data-page='login']").hide();
        $("#page [data-page='main']").show();
        break;
    }
  }

  /**
   * 公開鍵の生成
   * @param PassPhrase  パスフレーズ
   */
  function f_create_public_Key(PassPhrase){

    //公開鍵の作り方
    var Bits = 1024;
    var MattsRSAkey = cryptico.generateRSAKey(PassPhrase, Bits);
    var MattsPublicKeyString = cryptico.publicKeyString(MattsRSAkey);

    // 面倒なので固定で
    var publicKeyString = "";

    return publicKeyString;
  }

  /**
   * 公開鍵を用いて暗号化
   * @param plainText 平文
   * @param 暗号化文
   */
  function f_encrypt(plainText){

    // 公開鍵の生成
    var publicKeyString = f_create_public_Key("PassPhrase");

    // 暗号化
    var encryptText = cryptico.encrypt(plainText, publicKeyString);

    return encryptText;
  }

  /**
   * ストレージから値の取得
   */
  function f_get_storage(){
    // ローカルストレージ
    $("#cardId").val(localStorage.getItem("i"));
    // セッションストレージ
    $("#password").val(sessionStorage.getItem("p"));
  }

  /**
   * ストレージに値の格納
   */
  function f_set_storage(){
    // ローカルストレージ
    localStorage.setItem("i", $("#cardId").val());
    // セッションストレージ
    sessionStorage.setItem("p", $("#password").val());
  }


});
</script>


<!--
  独自css
-->
<style type="text/css">

/**
 * 全体構成
 */
body {
  font-family: "メイリオ","Hiragino Kaku Gothic ProN",sans-serif;
  font-size: 90%;
}

article,
footer {
  font-size: 90%;
}

table {
  text-align: center;
  font-size: 95%;
}

ol {
  margin-left: -5%;
}


.ui-header .ui-title {
  margin-left: 15%;
  margin-right: 15%
}

/**
 * 個別
 */
/* Rating予報 */
.ratingForecast {
  padding: 1em;
  font-size: larger;
  color: #D0F8FF;
  text-shadow: 0 0  5px #A5F1FF, 0 0 10px #A5F1FF,
               0 0 20px #A5F1FF, 0 0 30px #A5F1FF,
               0 0 40px #A5F1FF;
  margin-top: -0.6em;
}

/* テーブルデザイン */
th.empty,
th.zeroOne,
th.cricket {
  line-height: 1.6em;
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0.00, #666), color-stop(1.00, #111));
  background: -webkit-linear-gradient(#666, #111);
  background: -moz-linear-gradient(#666, #111);
  background: -ms-linear-gradient(#666, #111);
  background: linear-gradient(#666, #111);
}

th.column {
  line-height: 3.5em;
  background: -webkit-gradient(radial, 50% 50%, 0, 0% 50%, 241, color-stop(1.00, #1f1f1f), color-stop(0.00, #656565));
  background: -webkit-radial-gradient(50% 50%, circle, #656565, #1f1f1f);
  background: -moz-radial-gradient(50% 50%, circle, #656565, #1f1f1f);
  background: -ms-radial-gradient(50% 50%, circle, #656565, #1f1f1f);
  background: radial-gradient(50% 50%, circle, #656565, #1f1f1f);
}

td.item {
  background: -webkit-gradient(linear, left top, left bottom, color-stop(0.00, #000), color-stop(0.00, #4a4a4a));
  background: -webkit-linear-gradient(top, #000 0%, #4a4a4a 0%);
  background: -moz-linear-gradient(top, #000 0%, #4a4a4a 0%);
  background: -ms-linear-gradient(top, #000 0%, #4a4a4a 0%);
  background: linear-gradient(to bottom, #000 0%, #4a4a4a 0%);
}

/* 見出し */
h3{
  position: relative;
  padding: 0.5em 0.5em 0.5em 1.7em;
  margin: 0.3em
}

h3:before{
  content: "";
  position: absolute;
  background: #d48789;
  top: 0;
  left: 0.4em;
  height: 12px;
  width: 12px;
  transform: rotate(45deg);
  -moz-transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
}

h3:after{
  content: "";
  position: absolute;
  background:#d26466;
  top: 1.0em;
  left: 0;
  height: 8px;
  width: 8px;
  transform: rotate(15deg);
  -moz-transform: rotate(15deg);
  -webkit-transform: rotate(15deg);
  -ms-transform: rotate(15deg);
}

</style>

</head>
<body>
<form name="formMain" autocomplete="on">

<div id="page" data-role="page" data-theme="b">

  <!--
    ヘッダ
  -->
  <header data-role="header" data-theme="b" data-position="fixed">
    <h1>Darts Live Rating予報<span id="userNm" data-page="main"></span></h1>
    <a href="#" id="refreshAnchor" data-page="main" class="ui-btn ui-btn-right ui-btn-icon-notext ui-icon-refresh"></a>
    <hr>
  </header>

  <!--
    コンテンツ
  -->
  <article data-role="content" data-theme="b">

    <!--
      Login
    -->
    <div class="loginSection" data-page="login">
      <table>
        <tr>
          <th align="right">ID：</th>
          <td><input type="text" id="cardId" name="cardId" maxlength="16" style="ime-mode: inactive;" data-clear-btn="true" autofocus></td>
        </tr>
        <tr>
          <th align="right">PASS：</th>
          <td>
            <input type="password" id="password" name="password" maxlength="4" data-clear-btn="true">
          </td>
        </tr>
      </table>

      <a href="#" id="loginAnchor" class="ui-btn ui-btn-icon-right ui-icon-carat-r">Login</a>
    </div>

    <!--
      Rating予報
    -->
    <div class="ratingForecastSection" data-page="main">
      <h3>Rating予報</h3>
      <div class="ratingForecast">
        <span id="ratingNew">0.00</span>
        (<span id="ratingDiff">+0.00</span>)
      </div>
    <br>
    </div>

    <!--
      ゲーム別（累計）
    -->
    <div class="gameTypeTotalSection" data-page="main">
      <h3>ゲーム別（累計）</h3>
      <table>
        <colgroup>
          <col style="width: 6em">
          <col style="width: 7em">
          <col style="width: 7em">
        </colgroup>
        <thead>
          <tr>
            <th class="empty"></th>
            <th class="zeroOne">01GAME</th>
            <th class="cricket">CRICKET</th>
          </tr>
        </thead>
        <tbody>
          <!-- Rating -->
          <tr>
            <th class="column">Rating</th>
            <td class="item">
              <span id="rating01New">0.00</span><br>
              (<span id="rating01Diff">+0.00</span>)
            </td>
            <td class="item">
              <span id="ratingCricketNew">0.00</span><br>
              (<span id="ratingCricketDiff">+0.00</span>)
            </td>
          </tr>
          <!-- Stats -->
          <tr>
            <th class="column">Stats</th>
            <td class="item">
              <span id="stats01New">0.00</span><br>
              (<span id="stats01Diff">+0.00</span>)
            </td>
            <td class="item">
              <span id="statsCricketNew">0.00</span><br>
              (<span id="statsCricketDiff">+0.00</span>)
            </td>
          </tr>
        </tbody>
      </table>
    <br>
    <br>
    </div>

    <!--
      ゲーム別（今日）
    -->
    <div class="gameTypeTodaySection" data-page="main">
      <h3>ゲーム別（今日）</h3>
      <table>
        <colgroup>
          <col style="width: 6em">
          <col style="width: 7em">
          <col style="width: 7em">
        </colgroup>
        <thead>
          <tr>
            <th class="empty"></th>
            <th class="zeroOne">01GAME</th>
            <th class="cricket">CRICKET</th>
          </tr>
        </thead>
        <tbody>
          <!-- Rating -->
          <tr>
            <th class="column">Rating</th>
            <td class="item">
              <p class="notPlay01">Not Play</p>
              <span id="rating01Today"></span>
            </td>
            <td class="item">
              <p class="notPlayCricket">Not Play</p>
              <span id="ratingCricketToday"></span>
            </td>
          </tr>
          <!-- Stats -->
          <tr>
            <th class="column">Stats</th>
            <td class="item">
              <p class="notPlay01">Not Play</p>
              <span id="stats01Today"></span>
            </td>
            <td class="item">
              <p class="notPlayCricket">Not Play</p>
              <span id="statsCricketToday"></span>
            </td>
          </tr>
          <!-- Play Data -->
          <tr>
            <th class="column">Play Data</th>
            <td class="item" valign="top">
              <p class="notPlay01">Not Play</p>
              <ol class="playList01">
              </ol>
            </td>
            <td class="item" valign="top">
              <p class="notPlayCricket">Not Play</p>
              <ol class="playListCricket">
              </ol>
            </td>
          </tr>

        </tbody>
      </table>
    <br>
    <br>
    </div>

    <!--
      Logout
    -->
    <div class="logoutSection" data-page="main">
      <a href="#" id="logoutAnchor" class="ui-btn ui-btn-icon-right ui-icon-carat-l">logout</a>
    </div>

  </article>

  <!--
    フッタ
  -->
  <footer data-role="footer" data-theme="b">
    <hr>
    <p><i>Copyright &copy; 2015 壁Corp. All Rights Reserved.</i></p>
  </footer>

</div>

</form>
</body>
</html>