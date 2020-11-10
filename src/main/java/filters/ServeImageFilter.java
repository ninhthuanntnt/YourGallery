package filters;

import constanst.WebResource;
import models.bean.Image;
import models.bean.User;
import models.dao.ImageDao;
import models.dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ServeImageFilter", urlPatterns = "/public/images/*")
public class ServeImageFilter implements Filter {
    private ImageDao imageDao = ImageDao.getInstance();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Image image = imageDao.getImageByPathOrPathThumbnailAndUserId(request.getServletPath().substring(1) + request.getPathInfo(), user.getId());

        if(image != null){
            chain.doFilter(req, resp);
        }else{
            WebResource.redirect(request, (HttpServletResponse) resp, "/");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
