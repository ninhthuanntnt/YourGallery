package controllers.image;

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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet(name = "DownloadImagesServlet", urlPatterns = "/tai-ve")
public class DownloadImagesServlet extends HttpServlet {
    private ImageDao imageDao = ImageDao.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String[] imgStrIds = request.getParameterValues("imgIds");
        String albumStrId = request.getParameter("albumId");
        List<Image> images = new ArrayList<>();

        // Fetch all images get from imgIds and albumId to list images
        if (imgStrIds != null && imgStrIds.length > 0) {
            int[] imgIds = new int[imgStrIds.length];
            for (int i = 0; i < imgIds.length; i++) {
                imgIds[i] = Integer.parseInt(imgStrIds[i]);
            }
            images.addAll(imageDao.getImagesByImageIdsAndUserId(imgIds, user.getId()));
        }

        if (albumStrId != null) {
            int albumId = Integer.parseInt(albumStrId);
            images.addAll(imageDao.getImageByAlbumIdAndUserId(albumId, user.getId()));
        }

        // Handle compress image;
        List<File> files = ImageUtil.getFilesFromImages(images);
        File zipFile = ImageUtil.compressFile(files, "D:\\4th year\\LTM\\CuoiKi\\Data\\Data1.zip");

        if(zipFile != null){
            response.setContentType("application/zip, application/octet-stream, application/x-zip-compressed, multipart/x-zip");
            response.setHeader("Content-disposition", "attachment; filename=images.zip");

            byte[] buff = new byte[1024];
            try(
                    FileInputStream in = new FileInputStream(zipFile);
                    OutputStream out = response.getOutputStream()
                    ){
                int len;
                while((len = in.read(buff)) > 0){
                    out.write(buff, 0, buff.length);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static File zip(List<File> files, String filename) {
        File zipfile = new File(filename);
        // Create a buffer for reading the files
        byte[] buf = new byte[1024];
        try {
            // create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            // compress the files
            for (int i = 0; i < files.size(); i++) {
                FileInputStream in = new FileInputStream(files.get(i));
                // add ZIP entry to output stream
                out.putNextEntry(new ZipEntry(files.get(i).getName()));
                // transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                // complete the entry
                out.closeEntry();
                in.close();
            }
            // complete the ZIP file
            out.close();
            return zipfile;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
