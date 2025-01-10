package com.cl.dao;

import com.cl.entity.PovertyalleviationinformationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.PovertyalleviationinformationView;


/**
 * 扶贫资讯
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface PovertyalleviationinformationDao extends BaseMapper<PovertyalleviationinformationEntity> {
	
	List<PovertyalleviationinformationView> selectListView(@Param("ew") Wrapper<PovertyalleviationinformationEntity> wrapper);

	List<PovertyalleviationinformationView> selectListView(Pagination page,@Param("ew") Wrapper<PovertyalleviationinformationEntity> wrapper);
	
	PovertyalleviationinformationView selectView(@Param("ew") Wrapper<PovertyalleviationinformationEntity> wrapper);


}
