package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusspovertyalleviationinformationEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusspovertyalleviationinformationView;


/**
 * povertyalleviationinformation评论表
 *
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface DiscusspovertyalleviationinformationService extends IService<DiscusspovertyalleviationinformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusspovertyalleviationinformationView> selectListView(Wrapper<DiscusspovertyalleviationinformationEntity> wrapper);
   	
   	DiscusspovertyalleviationinformationView selectView(@Param("ew") Wrapper<DiscusspovertyalleviationinformationEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusspovertyalleviationinformationEntity> wrapper);
   	
   
}

