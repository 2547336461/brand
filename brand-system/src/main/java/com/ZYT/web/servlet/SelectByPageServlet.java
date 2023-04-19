package com.ZYT.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ZYT.pojo.Brand;
import com.ZYT.pojo.PageBean;
import com.ZYT.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectByPageServlet")
public class SelectByPageServlet extends HttpServlet {

    /**
     * 分页查询
     */
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收 当前页码 和 每页显示条数  url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 2.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        // 3.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        // 4.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
