package controllers.image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ServeImageServlet", urlPatterns = "/public/*")
public class ServeImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String pathToSavedImages = request.getServletContext().getInitParameter("pathToSavedImages");
            String pathInfo = request.getPathInfo();
            String finalPath = pathToSavedImages + pathInfo;
            String mimeType = request.getServletContext().getMimeType(finalPath);
            String realPath = request.getServletContext().getRealPath("");

            try(FileInputStream fis = new FileInputStream(finalPath);
                OutputStream out = response.getOutputStream()) {

                byte b[] = new byte[1024];
                int count;
                while ((count = fis.read(b)) >= 0) {
                    out.write(b, 0, count);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(404);
        }
    }
}
