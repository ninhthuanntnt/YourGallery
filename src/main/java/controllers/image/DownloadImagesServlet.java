package controllers.image;

import constanst.WebResource;
import models.bean.Image;
import models.bean.User;
import models.dao.ImageDao;
import utils.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DownloadImagesServlet", urlPatterns = "/tai-ve")
public class DownloadImagesServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            User user = (User) request.getSession().getAttribute("user");

            String[] imgStrIds = request.getParameterValues("imgIds");
            String[] albumStrIds = request.getParameterValues("albumIds");
            List<Image> images = new ArrayList<>();

            // Fetch all images get from imgIds and albumId to list images
            if (imgStrIds != null && imgStrIds.length > 0) {
                int[] imgIds = new int[imgStrIds.length];
                for (int i = 0; i < imgIds.length; i++) {
                    imgIds[i] = Integer.parseInt(imgStrIds[i]);
                }
                images.addAll(imageDao.getImagesByImageIdsAndUserId(imgIds, user.getId()));
            }

            if (albumStrIds != null && albumStrIds.length > 0) {
                int[] albumIds = new int[albumStrIds.length];
                for (int i = 0; i < albumIds.length; i++) {
                    albumIds[i] = Integer.parseInt(albumStrIds[i]);
                }
                images.addAll(imageDao.getImagesByAlbumIdsAndUserId(albumIds, user.getId()));
            }

            if (images.size() == 1) {
                response.setContentType("image/jpeg, image/png, image/svg+xml, image/gif");
                response.setHeader("Content-disposition", "attachment; filename=" + images.get(0).getName());

                List<File> files = ImageUtil.getFilesFromImages(images);
                File imageFile = files.get(0);

                writeFileOut(imageFile, response);

            } else if (images.size() >= 1) {
                // Handle compress image;
                List<File> files = ImageUtil.getFilesFromImages(images);
                File zipFile = ImageUtil.compressFile(files, "D:\\4th year\\LTM\\CuoiKi\\Data\\Data1.zip");

                if (zipFile != null) {
                    response.setContentType("application/zip, application/octet-stream, application/x-zip-compressed, multipart/x-zip");
                    response.setHeader("Content-disposition", "attachment; filename=images.zip");

                    writeFileOut(zipFile, response);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        WebResource.redirectPreviousPage(request, response);
    }

    private static void writeFileOut(File file, HttpServletResponse response) throws IOException {
        byte[] buff = new byte[1024];
        try (
                FileInputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream()
        ) {
            int len;
            while ((len = in.read(buff)) > 0) {
                out.write(buff, 0, buff.length);
            }
        }
    }
}
