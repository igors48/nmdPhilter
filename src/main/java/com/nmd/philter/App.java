package com.nmd.philter;

import com.nmd.crawler.UrlRequestHandler;
import com.nmd.crawler.UrlResponse;
import com.nmd.crawler.exception.UrlRequestHandlerException;
import com.nmd.crawler.http.HttpClientUrlRequestHandler;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, UrlRequestHandlerException {
//        final Log log = LogFactory.getLog(App.class);

//        final String url = "http://www.the-ebook.org/?p=9279";
//        final String url = "http://edition.cnn.com/2011/TRAVEL/01/27/black.sea.coast.ukraine/index.html"; // !!!
//        final String url = "http://www.engadget.com/2011/09/14/ziilabs-unleashes-jaguar3-super-slim-slate-reference-design/"; // !!!
//        final String url = "http://www.cnews.ru/news/top/index.shtml?2011/09/14/455234";
//        final String url = "http://www.popmech.ru/article/9669-neravnaya-vselennaya/";
//        final String url = "http://www.membrana.ru/particle/16748";
//        final String url = "http://isport.ua/auto/f1/news/166587.html";
//        final String url = "http://football.ua/uefa/news/141734.html";
//        final String url = "http://korrespondent.net/business/mmedia_and_adv/1261330-martini-ishchet-specialista-po-poceluyam";
//        final String url = "http://www.3dnews.ru/news/616835";
//        final String url = "http://www.bbc.co.uk/russian/society/2011/09/110913_kennedy_widow_memoires.shtml";
//        final String url = "http://habrahabr.ru/blogs/arduino/128423/";
//        final String url = "http://news.zn.ua/articles/87801";
//        final String url = "http://www.epicurious.com/recipes/food/views/Creamy-Rice-Grits-with-Tomato-Relish-367169";
//        final String url = "http://www.autoreview.ru/news/189/116901/";
//        final String url = "http://www.ut.net.ua/Politics/30568";
//        final String url = "http://www.ixbt.com/news/hard/index.shtml?15/04/67";
//        final String url = "http://www.digitlife.ru/news/index.shtml?15/04/49";
//        final String url = "http://podrobnosti.ua/power/2011/09/14/791427.html";
//        final String url = "http://itc.ua/news/processor_amd_serii_fx_ustanovil_novyj_mirovoj_rekord_chastoty_55524";
//        final String url = "http://tsn.ua/groshi/v-kiyevi-z-yavlyatsya-apteki-z-deshevimi-likami.html";
//        final String url = "http://www.ictv.ua/wasp/ua/facts/1419/1483910/";
//        final String url = "http://kp.ua/daily/140911/301153/";
//        final String url = "http://www.inopressa.ru/article/14Sep2011/time/russia3.html";
//        final String url = "http://inosmi.ru/politic/20110914/174635025.html";
//        final String url = "http://www.forbes.com/sites/parmyolson/2011/09/07/court-lets-anonymous-suspects-keep-using-twitter/";
//        final String url = "http://www.rusnovosti.ru/news/163640/";
//        final String url = "http://techcrunch.com/2011/08/25/tim-cooks-letter-to-apple-staff/";
        final String url = "http://www.apple.com/ru/pr/library/2011/08/24Letter-from-Steve-Jobs.html";

        final UrlRequestHandler requestHandler = new HttpClientUrlRequestHandler();
        final UrlResponse response = requestHandler.get(url, url);

        final String processedHtml = new Philter().filter(response.getContent());

        System.out.println(processedHtml);
//        System.out.println(best.getText().toString().replaceAll("\\s", "").toLowerCase());
    }


}
