package com.ZYT.web.servlet;

import com.alibaba.fastjson.JSON;
import com.ZYT.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/deleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
    /**
     * 单个删除
     */
    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        // 转为int
        int id = JSON.parseObject(params, int.class);

        // 2.调用service删除
        brandService.deleteById(id);

        // 3.响应成功的标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
