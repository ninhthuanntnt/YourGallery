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
import java.util.Collection;
import java.util.List;

@WebServlet(name = "AddToAlbumServletServlet", urlPatterns = "/them-anh-vao-album")
public class AddToAlbumServletServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String[] imgStrIds = request.getParameterValues("imgIds");
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        Integer[] imgIds = new Integer[imgStrIds.length];

        // convert str array to int array
        for (int i = 0; i < imgIds.length; i++) {
            imgIds[i] = Integer.parseInt(imgStrIds[i]);
        }

        boolean success = imageDao.updateAlbumIdForImagesByUserId(imgIds, albumId, user.getId());

        WebResource.redirect(request, response, "/");
    }
}
