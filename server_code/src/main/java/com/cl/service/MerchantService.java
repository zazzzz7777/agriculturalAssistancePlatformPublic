package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.MerchantEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MerchantView;


/**
 * 商家
 *
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface MerchantService extends IService<MerchantEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MerchantView> selectListView(Wrapper<MerchantEntity> wrapper);
   	
   	MerchantView selectView(@Param("ew") Wrapper<MerchantEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MerchantEntity> wrapper);
   	
   
}

