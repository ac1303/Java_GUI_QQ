package com.fanshuhua.route;

import com.fanshuhua.function.LoginAndRegister;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 范书华
 * @create 2022/6/15 20:06
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(String.valueOf(LoginAndRegister.login(req.getParameter("studentId"),req.getParameter("password"))));
    }
}
