package com.cl.dao;

import com.cl.entity.DiscusspovertyalleviationinformationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusspovertyalleviationinformationView;


/**
 * povertyalleviationinformation评论表
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface DiscusspovertyalleviationinformationDao extends BaseMapper<DiscusspovertyalleviationinformationEntity> {
	
	List<DiscusspovertyalleviationinformationView> selectListView(@Param("ew") Wrapper<DiscusspovertyalleviationinformationEntity> wrapper);

	List<DiscusspovertyalleviationinformationView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusspovertyalleviationinformationEntity> wrapper);
	
	DiscusspovertyalleviationinformationView selectView(@Param("ew") Wrapper<DiscusspovertyalleviationinformationEntity> wrapper);


}
