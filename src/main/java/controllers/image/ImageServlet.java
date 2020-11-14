package controllers.image;

import constanst.WebResource;
import models.bean.Album;
import models.bean.Image;
import models.bean.User;
import models.dao.ImageDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tasks.ThumbnailTask;
import utils.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "ImageServlet", urlPatterns = "/user/anh")
public class ImageServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String pathToRawImages = request.getServletContext().getInitParameter("pathToRawImages");
        String pathToThumbnailImages = request.getServletContext().getInitParameter("pathToThumbnailImages");

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);

                int albumId = 0;
                String name = null;
                for (FileItem fileItem : fileItems) {

                    if (fileItem.isFormField()) {
                        if (fileItem.getFieldName().equals("name")) {
                            name = fileItem.getString();
                        }
                        if (fileItem.getFieldName().equals("albumId")) {
                            albumId = Integer.parseInt(fileItem.getString());
                        }
                    } else {
                        // retrieve infos
                        Image image = new Image();
                        image.setUserId(user.getId());
                        if(albumId != 0)
                            image.setAlbumId(albumId);

                        String fileFullName = fileItem.getName();

                        if (fileFullName != null) {
                            int dotIndex = fileFullName.lastIndexOf('.');
                            String ext = fileFullName.substring(dotIndex);
                            String fileName = fileFullName.substring(0, dotIndex);

                            if (name == null || name.trim().length() == 0) {
                                name = fileName;
                            }

                            String finalName = name + "_" + generateRandomChain() + ext;

                            image.setPath(WebResource.PATH_TO_RAW_IMAGES + "/" + finalName);
                            image.setPathThumbnail(WebResource.PATH_TO_THUMBNAIL_IMAGES + "/" + finalName);
                            image.setName(name + ext);

                            File savedFile = new File(pathToRawImages, finalName);
                            fileItem.write(savedFile);

                            // Start compressing image task
                            new Thread(
                                    new ThumbnailTask(savedFile.getPath(),
                                            pathToThumbnailImages + File.separator + finalName)
                            ).start();
                        }

                        imageDao.addImage(image);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        WebResource.redirectPreviousPage(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String albumStrId = request.getParameter("albumId");
        String albumName = request.getParameter("albumName");
        List<Image> images;
        if (albumStrId == null) {
            images = imageDao.getAllImageByUserId(user.getId());
        } else {
            Album album = new Album();
            album.setId(Integer.parseInt(albumStrId));
            album.setName(albumName);
            request.setAttribute("album", album);
            images = imageDao.getImagesByAlbumIdAndUserId(Integer.parseInt(albumStrId), user.getId());
        }

        request.setAttribute("images", images);
        WebResource.forward(request, response, "/images.jsp");
    }

    private String generateRandomChain() {
        Random random = new Random();
        int num = (int) Math.round(Math.random() * 1000);
        byte[] numBase64 = Base64.getEncoder().encode(("" + num).getBytes());

        String result = new String(numBase64) + "_" + new Date().getTime();

        return result;
    }
}
