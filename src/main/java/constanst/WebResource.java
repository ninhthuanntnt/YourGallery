package constanst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebResource {
    public static String PATH_TO_RAW_IMAGES = "public/images/raw";
    public static String PATH_TO_THUMBNAIL_IMAGES = "public/images/thumbnail";
    public static String PATH_TO_PAGES = "/WEB-INF/pages";

    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(WebResource.PATH_TO_PAGES + path)
                .forward(request,response);
    }
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + url);
    }
    public static void redirectPreviousPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String referer = (String) request.getHeader("referer");
        String host = request.getHeader("host");
        referer = referer.replace("http://","");
        referer = referer.replace("https://","");
        referer = referer.replace(host,"");
        response.sendRedirect(referer);
    }
    public static String getPreviousPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String referer = (String) request.getHeader("referer");
        String host = request.getHeader("host");
        referer = referer.replace("http://","");
        referer = referer.replace("https://","");
        referer = referer.replace(host,"");
        referer = referer.replace(request.getContextPath(), "");
        return referer;
    }
}
