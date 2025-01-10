package com.cl.dao;

import com.cl.entity.DiscussassistanceproductinformationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussassistanceproductinformationView;


/**
 * assistanceproductinformation评论表
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface DiscussassistanceproductinformationDao extends BaseMapper<DiscussassistanceproductinformationEntity> {
	
	List<DiscussassistanceproductinformationView> selectListView(@Param("ew") Wrapper<DiscussassistanceproductinformationEntity> wrapper);

	List<DiscussassistanceproductinformationView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussassistanceproductinformationEntity> wrapper);
	
	DiscussassistanceproductinformationView selectView(@Param("ew") Wrapper<DiscussassistanceproductinformationEntity> wrapper);


}
