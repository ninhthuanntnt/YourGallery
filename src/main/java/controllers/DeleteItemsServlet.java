package controllers;

import constanst.WebResource;
import models.bean.Image;
import models.bean.User;
import models.bo.AlbumBo;
import models.bo.ImageBo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "DeleteItemsServlet", urlPatterns = "/xoa-muc-da-chon")
public class DeleteItemsServlet extends HttpServlet {
    private ImageBo imageBo = ImageBo.getInstance();
    private AlbumBo albumBo = AlbumBo.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String[] albumStrIds = request.getParameterValues("albumIds");
        String[] imgStrIds = request.getParameterValues("imgIds");

        if(albumStrIds != null && albumStrIds.length > 0) {
            int[] albumIds = new int[albumStrIds.length];

            for(int i = 0 ; i < albumIds.length; i++){
                albumIds[i] = Integer.parseInt(albumStrIds[i]);
            }

            albumBo.deleteAlbumByAlbumIds(albumIds, user.getId());
        }

        if(imgStrIds != null && imgStrIds.length > 0){
            int[] imgIds = new int[imgStrIds.length];

            for(int i = 0 ; i < imgIds.length; i++){
                imgIds[i] = Integer.parseInt(imgStrIds[i]);
            }

            imageBo.deleteImagesByImageIdsAndUserId(imgIds, user.getId());
        }
        if(WebResource.getPreviousPage(request, response).contains("chi-tiet")){
            WebResource.redirect(request, response, (String) request.getSession().getAttribute("previousPage"));
        }else{
            WebResource.redirectPreviousPage(request, response);
        }
    }
}
