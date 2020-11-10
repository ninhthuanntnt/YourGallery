package controllers.album;

import constanst.WebResource;
import models.bean.Album;
import models.bean.User;
import models.dao.AlbumDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AlbumServlet", urlPatterns = "/user/album")
public class AlbumServlet extends HttpServlet {
    private AlbumDao albumDao = AlbumDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        String name = request.getParameter("name");

        if(name!=null && name.length() > 0){
            Album album = new Album(0, name, user.getId(), null);
            boolean success = albumDao.addAlbum(album);
        }
        WebResource.redirect(request, response, "/user/album");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Album> albums = albumDao.getAllAlbumByUserId(user.getId());

        request.setAttribute("albums", albums);
        WebResource.forward(request, response, "/albums.jsp");
    }
}
