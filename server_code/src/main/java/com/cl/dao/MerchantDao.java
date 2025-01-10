package com.cl.dao;

import com.cl.entity.MerchantEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.MerchantView;


/**
 * 商家
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface MerchantDao extends BaseMapper<MerchantEntity> {
	
	List<MerchantView> selectListView(@Param("ew") Wrapper<MerchantEntity> wrapper);

	List<MerchantView> selectListView(Pagination page,@Param("ew") Wrapper<MerchantEntity> wrapper);
	
	MerchantView selectView(@Param("ew") Wrapper<MerchantEntity> wrapper);


}
