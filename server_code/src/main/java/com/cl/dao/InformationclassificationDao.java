package com.cl.dao;

import com.cl.entity.InformationclassificationEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.InformationclassificationView;


/**
 * 资讯分类
 * 
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
public interface InformationclassificationDao extends BaseMapper<InformationclassificationEntity> {
	
	List<InformationclassificationView> selectListView(@Param("ew") Wrapper<InformationclassificationEntity> wrapper);

	List<InformationclassificationView> selectListView(Pagination page,@Param("ew") Wrapper<InformationclassificationEntity> wrapper);
	
	InformationclassificationView selectView(@Param("ew") Wrapper<InformationclassificationEntity> wrapper);


}
