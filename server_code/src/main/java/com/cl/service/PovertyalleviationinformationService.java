package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.PovertyalleviationinformationEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.PovertyalleviationinformationView;


/**
 * 扶贫资讯
 *
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface PovertyalleviationinformationService extends IService<PovertyalleviationinformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PovertyalleviationinformationView> selectListView(Wrapper<PovertyalleviationinformationEntity> wrapper);
   	
   	PovertyalleviationinformationView selectView(@Param("ew") Wrapper<PovertyalleviationinformationEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PovertyalleviationinformationEntity> wrapper);
   	
   
}

