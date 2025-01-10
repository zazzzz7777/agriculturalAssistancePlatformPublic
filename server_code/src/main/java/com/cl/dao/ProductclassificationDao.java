package com.cl.dao;

import com.cl.entity.ProductclassificationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ProductclassificationView;


/**
 * 商品分类
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface ProductclassificationDao extends BaseMapper<ProductclassificationEntity> {
	
	List<ProductclassificationView> selectListView(@Param("ew") Wrapper<ProductclassificationEntity> wrapper);

	List<ProductclassificationView> selectListView(Pagination page,@Param("ew") Wrapper<ProductclassificationEntity> wrapper);
	
	ProductclassificationView selectView(@Param("ew") Wrapper<ProductclassificationEntity> wrapper);


}
