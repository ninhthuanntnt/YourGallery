package controllers.indentify;

import constanst.WebResource;
import models.bean.User;
import models.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/dang-ky")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re-password");

        User user = new User(0, username, password, name, null);
        boolean isValid = true;
        if (username.length() == 0 || password.length() == 0 || name.length() == 0) {
            request.setAttribute("errMsg", "Vui lòng điền đầy đủ thông tin");
            isValid = false;
        }else if (userDao.getUserByUsername(username) != null) {
            request.setAttribute("errMsg", "Tên đăng nhập đã tồn tại");
            isValid = false;
        } else if (password.length() < 3) {
            request.setAttribute("errMsg", "Mật khẩu quá ngắn");
            isValid = false;
        } else if (!password.equals(rePassword)) {
            request.setAttribute("errMsg", "Nhập lại mật khẩu không khớp");
            isValid = false;
        }

        if(isValid){
            userDao.addUser(new User(0, username, password, name, null));
            WebResource.redirect(request, response, "/");
        }else{
            request.setAttribute("user", user);
            WebResource.forward(request, response, "/register.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebResource.forward(request, response, "/register.jsp");
    }
}
