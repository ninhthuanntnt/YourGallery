package constanst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebResource {
    public static String PATH_TO_PAGES = "/WEB-INF/pages";

    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(WebResource.PATH_TO_PAGES + path)
                .forward(request,response);
    }
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + url);
    }
}
