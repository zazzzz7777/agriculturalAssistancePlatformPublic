package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ProductclassificationEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ProductclassificationView;


/**
 * 商品分类
 *
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface ProductclassificationService extends IService<ProductclassificationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ProductclassificationView> selectListView(Wrapper<ProductclassificationEntity> wrapper);
   	
   	ProductclassificationView selectView(@Param("ew") Wrapper<ProductclassificationEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ProductclassificationEntity> wrapper);
   	
   
}

