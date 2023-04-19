package com.ZYT.service;

import com.ZYT.mapper.BrandMapper;
import com.ZYT.pojo.Brand;
import com.ZYT.pojo.PageBean;
import com.ZYT.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService{
    // 1.创建对应SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询数据
     * @return
     */
    public List<Brand> selectAll() {
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用方法
        List<Brand> brands = mapper.selectAll();

        // 5.释放资源
        sqlSession.close();

        return brands;
    }

    /**
     * 添加数据
     * @param brand
     */
    public void add(Brand brand){
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用方法
        mapper.add(brand);

        // 提交事务
        sqlSession.commit();

        // 5.释放资源
        sqlSession.close();
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteByIds(int[] ids){
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用方法
        mapper.deleteByIds(ids);

        // 提交事务
        sqlSession.commit();

        // 5.释放资源
        sqlSession.close();
    }

    /**
     * 单个删除
     * @param id
     */
    public void deleteById(int id){
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用方法
        mapper.deleteById(id);

        // 提交事务
        sqlSession.commit();

        // 5.释放资源
        sqlSession.close();
    }

    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand){
        // 2.获取SqlSession
        SqlSession sqlSession = factory.openSession();

        // 3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.调用方法
        mapper.update(brand);

        // 提交事务
        sqlSession.commit();

        // 释放资源
        sqlSession.close();
    }

    /**
     *
     * @param currentPage  当前页码
     * @param pageSize  每页显示条数
     * @return
     */
    public PageBean<Brand> selectByPage(int currentPage,int pageSize){
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.计算开始索引
        int begin = (currentPage-1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 5.查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);

        // 6.查询总记录数
        int totalCount = mapper.selectTotalCount();

        // 7.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8.释放资源
        sqlSession.close();

        return pageBean;
    }

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    public PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand){
        // 2.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        // 3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        // 4.计算开始索引
        int begin = (currentPage-1) * pageSize;
        // 计算查询条目数
        int size = pageSize;

        // 处理brand条件 模糊查询
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }

        // 5.查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size,brand);

        // 6.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        // 7.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        // 8.释放资源
        sqlSession.close();

        return pageBean;
    }
}
