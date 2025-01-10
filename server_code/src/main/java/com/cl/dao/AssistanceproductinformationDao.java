package com.cl.dao;

import com.cl.entity.AssistanceproductinformationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.AssistanceproductinformationView;


/**
 * 帮扶商品信息
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface AssistanceproductinformationDao extends BaseMapper<AssistanceproductinformationEntity> {
	
	List<AssistanceproductinformationView> selectListView(@Param("ew") Wrapper<AssistanceproductinformationEntity> wrapper);

	List<AssistanceproductinformationView> selectListView(Pagination page,@Param("ew") Wrapper<AssistanceproductinformationEntity> wrapper);
	
	AssistanceproductinformationView selectView(@Param("ew") Wrapper<AssistanceproductinformationEntity> wrapper);


}
