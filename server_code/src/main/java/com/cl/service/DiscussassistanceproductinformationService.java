package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussassistanceproductinformationEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussassistanceproductinformationView;


/**
 * assistanceproductinformation评论表
 *
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface DiscussassistanceproductinformationService extends IService<DiscussassistanceproductinformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussassistanceproductinformationView> selectListView(Wrapper<DiscussassistanceproductinformationEntity> wrapper);
   	
   	DiscussassistanceproductinformationView selectView(@Param("ew") Wrapper<DiscussassistanceproductinformationEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussassistanceproductinformationEntity> wrapper);
   	
   
}

