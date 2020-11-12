package controllers.image;

import constanst.WebResource;
import models.bean.User;
import models.dao.ImageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveFromAlbumServlet", urlPatterns = "/xoa-anh-khoi-album")
public class RemoveFromAlbumServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String[] imgStrIds = request.getParameterValues("imgIds");

        if (imgStrIds != null && imgStrIds.length > 0) {
            Integer[] imgIds = new Integer[imgStrIds.length];

            for (int i = 0; i < imgIds.length; i++) {
                imgIds[i] = Integer.parseInt(imgStrIds[i]);
            }

            imageDao.updateAlbumIdForImagesByUserId(imgIds, null, user.getId());
        }
        WebResource.redirectPreviousPage(request, response);
    }
}
