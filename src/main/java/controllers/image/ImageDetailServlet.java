package controllers.image;

import constanst.WebResource;
import models.bean.Image;
import models.bean.User;
import models.dao.ImageDao;
import org.apache.commons.io.FileUtils;
import utils.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

@WebServlet(name = "ImageDetailServlet", urlPatterns = "/user/chi-tiet")
public class ImageDetailServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String imgStrId = request.getParameter("imgId");

        Image image = imageDao.getImageByImageIdAndUserId(Integer.parseInt(imgStrId), user.getId());

        File imageFile = ImageUtil.getFileFromImage(image);
        Path imagePath = imageFile.toPath();
        BasicFileAttributes attributes = Files.readAttributes(imagePath, BasicFileAttributes.class);
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        double size = FileUtils.sizeOf(imageFile)/1024.0;
        request.setAttribute("width",width);
        request.setAttribute("height",height);
        request.setAttribute("size",size);
        request.setAttribute("image", image);
        request.setAttribute("creationTime", new Date(attributes.creationTime().toMillis()));
        request.setAttribute("lastModifiedTime", new Date(attributes.lastModifiedTime().toMillis()));
        request.setAttribute("lastAccessTime", new Date(attributes.lastAccessTime().toMillis()));
        request.getSession().setAttribute("previousPage", WebResource.getPreviousPage(request, response));
        WebResource.forward(request, response, "/imageDetail.jsp");
    }
}
