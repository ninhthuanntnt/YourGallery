package controllers.image;

import constanst.WebResource;
import models.bean.Image;
import models.bean.User;
import models.dao.ImageDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

        Image image = new Image();
        image.setUserId(user.getId());

        String name = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);

                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        if (fileItem.getFieldName().equals("name")) {
                            name = fileItem.getString();
                        }
                    } else {
                        // retrieve infos
                        String fileFullName = fileItem.getName();

                        if(name == null && name.trim().length() == 0){
                            name = fileFullName;
                        }


                        if (fileFullName != null) {
                            int dotIndex = fileFullName.lastIndexOf('.');
                            String ext = fileFullName.substring(dotIndex);
                            String fileName = fileFullName.substring(0, dotIndex);
                            String finalName = (name == null)?fileName:name + "_" + generateRandomChain() + ext;

                            image.setPath(WebResource.PATH_TO_RAW_IMAGES + "/" + finalName);
                            image.setName(name + ext);

                            File fullFile = new File(finalName);
                            File savedFile = new File("D:\\4th year\\LTM\\CuoiKi\\YourGallery\\src\\main\\webapp\\public\\images\\raw", fullFile.getName());
                            fileItem.write(savedFile);
                        }
                    }
                }
                imageDao.addImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Image> images = imageDao.getAllImageByUserId(user.getId());

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
