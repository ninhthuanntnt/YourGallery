package controllers;

import constanst.WebResource;
import models.bean.Album;
import models.bean.Image;
import models.bean.User;
import models.dao.AlbumDao;
import models.dao.ImageDao;
import models.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    private AlbumDao albumDao = AlbumDao.getInstance();
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Album> albums = albumDao.getAllAlbumByUserId(user.getId());
            List<Image> images = imageDao.getImagesByAlbumIdAndUserId(null, user.getId());

            request.setAttribute("albums", albums);
            request.setAttribute("images", images);
        }
        WebResource.forward(request, response, "/home.jsp");
    }
}
