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

@WebServlet(name = "LoginServlet", urlPatterns = "/dang-nhap")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.getUserByUsername(username);
        if(user == null || !user.getPassword().equals(password)){
            request.setAttribute("errMsg", "Sai tên đăng nhập hoặc mật khẩu");
            WebResource.forward(request, response, "/login.jsp");
        }else{
            user.setPassword(null);
            request.getSession().setAttribute("user", user);
            WebResource.redirect(request, response, "/");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebResource.forward(request, response, "/login.jsp");
    }
}
