package com.ZYT.web.servlet;

import com.ZYT.pojo.User;
import com.ZYT.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    /**
     * 注册验证
     */
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取用户名和密码数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 获取用户输入验证码
        String checkCode = request.getParameter("checkCode");

        // 程序生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        // 比对
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            response.getWriter().write("1");
            // 不允许注册
            return;
        }

        // 2.调用service 注册
        boolean flag = service.register(user);

        // 3.判断注册成功与否
        if(flag){
            // 注册成功 跳转登陆页面
            response.getWriter().write("success");
        }else {
            // 注册失败 跳转到注册页面
            response.getWriter().write("error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
