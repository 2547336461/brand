package com.ZYT.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ZYT.pojo.Brand;
import com.ZYT.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    /**
     * 添加数据
     */
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收品牌数据
        BufferedReader br= request.getReader();
        String params = br.readLine(); // json数据

        // 转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        // 2.调用service添加
        brandService.add(brand);

        // 3.响应成功的标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
