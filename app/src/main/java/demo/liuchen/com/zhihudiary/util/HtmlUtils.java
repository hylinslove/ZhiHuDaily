package demo.liuchen.com.zhihudiary.util;

import java.util.List;

/**
 * Created by meng on 2016/11/4.
 */
public class HtmlUtils {


    public static String getHtmlCode(String originBody,String css){
        String header = "<html><head><link href=\"%s\" type=\"text/css\" rel=\"stylesheet\"/></head><body>";
        String footer = "</body></html>";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(header, css));
        sb.append(originBody);
        sb.append(footer);
        return  sb.toString();
    }

//    public static String structHtml(String oriStr, List<String> cssList) {
//        StringBuilder htmlString = new StringBuilder("<html><head>");
//        for (String css : cssList) {
//            htmlString.append(structCssLink(css));
//        }
//        htmlString.append("</head><body>");
//        htmlString.append("<style>img{max-width:340px !important;}</style>");
//        htmlString.append(oriStr);
//        htmlString.append("</body></html>");
//        return htmlString.toString();
//    }
//
//    public static String structCssLink(String css) {
//        return "<link type=\\\"text/css\\\" rel=\\\"stylesheet\\\" href=\\\"" + css + "\">";
//    }
}
