package controllers;

import constanst.WebResource;
import models.bean.Album;
import models.bean.Image;
import models.bean.User;
import models.dao.AlbumDao;
import models.dao.ImageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RenameItemServlet", urlPatterns = "/user/doi-ten")
public class RenameItemServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();
    private AlbumDao albumDao = AlbumDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String imageId = request.getParameter("imageId");
        String albumId = request.getParameter("albumId");
        String name = request.getParameter("name");

        if(imageId != null){
            String ext = request.getParameter("ext");
            imageDao.updateNameForImagesByUserId(name + ext, Integer.parseInt(imageId), user.getId());
        } else if(albumId != null){
            albumDao.updateNameByAlbumIdAndUserId(name, Integer.parseInt(albumId), user.getId());
        }
        WebResource.redirect(request, response, (String) request.getSession().getAttribute("previousPage"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String imageId = request.getParameter("imageId");
        String albumId = request.getParameter("albumId");

        if(imageId != null){
            Image image = imageDao.getImageByImageIdAndUserId(Integer.parseInt(imageId), user.getId());
            String fileFullName = image.getName();
            int index = fileFullName.lastIndexOf(".");
            String fileName = fileFullName.substring(0, index);
            String ext = fileFullName.substring(index);

            request.setAttribute("previousName", fileName);
            request.setAttribute("ext", ext);
            request.setAttribute("imageId", imageId);
        }else if(albumId != null){
            Album album = albumDao.getAlbumByAlbumIdAndUserId(Integer.parseInt(albumId), user.getId());
            request.setAttribute("previousName", album.getName());
            request.setAttribute("albumId", albumId);
        }
        request.getSession().setAttribute("previousPage", WebResource.getPreviousPage(request, response));
        WebResource.forward(request, response, "/rename.jsp");
    }
}
