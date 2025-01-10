package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.PovertyalleviationinformationEntity;
import com.cl.entity.view.PovertyalleviationinformationView;

import com.cl.service.PovertyalleviationinformationService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 扶贫资讯
 * 后端接口
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
@RestController
@RequestMapping("/povertyalleviationinformation")
public class PovertyalleviationinformationController {
    @Autowired
    private PovertyalleviationinformationService povertyalleviationinformationService;

    @Autowired
    private StoreupService storeupService;






    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PovertyalleviationinformationEntity povertyalleviationinformation,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("merchant")) {
			povertyalleviationinformation.setMerchantname((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<PovertyalleviationinformationEntity> ew = new EntityWrapper<PovertyalleviationinformationEntity>();

		PageUtils page = povertyalleviationinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, povertyalleviationinformation), params), params));

        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PovertyalleviationinformationEntity povertyalleviationinformation, 
		HttpServletRequest request){
        EntityWrapper<PovertyalleviationinformationEntity> ew = new EntityWrapper<PovertyalleviationinformationEntity>();

		PageUtils page = povertyalleviationinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, povertyalleviationinformation), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PovertyalleviationinformationEntity povertyalleviationinformation){
       	EntityWrapper<PovertyalleviationinformationEntity> ew = new EntityWrapper<PovertyalleviationinformationEntity>();
      	ew.allEq(MPUtil.allEQMapPre( povertyalleviationinformation, "povertyalleviationinformation")); 
        return R.ok().put("data", povertyalleviationinformationService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PovertyalleviationinformationEntity povertyalleviationinformation){
        EntityWrapper< PovertyalleviationinformationEntity> ew = new EntityWrapper< PovertyalleviationinformationEntity>();
 		ew.allEq(MPUtil.allEQMapPre( povertyalleviationinformation, "povertyalleviationinformation")); 
		PovertyalleviationinformationView povertyalleviationinformationView =  povertyalleviationinformationService.selectView(ew);
		return R.ok("查询扶贫资讯成功").put("data", povertyalleviationinformationView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PovertyalleviationinformationEntity povertyalleviationinformation = povertyalleviationinformationService.selectById(id);
		povertyalleviationinformation.setClickNumber(povertyalleviationinformation.getClickNumber()+1);
		povertyalleviationinformation.setClicktime(new Date());
		povertyalleviationinformationService.updateById(povertyalleviationinformation);
		povertyalleviationinformation = povertyalleviationinformationService.selectView(new EntityWrapper<PovertyalleviationinformationEntity>().eq("id", id));
        return R.ok().put("data", povertyalleviationinformation);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PovertyalleviationinformationEntity povertyalleviationinformation = povertyalleviationinformationService.selectById(id);
		povertyalleviationinformation.setClickNumber(povertyalleviationinformation.getClickNumber()+1);
		povertyalleviationinformation.setClicktime(new Date());
		povertyalleviationinformationService.updateById(povertyalleviationinformation);
		povertyalleviationinformation = povertyalleviationinformationService.selectView(new EntityWrapper<PovertyalleviationinformationEntity>().eq("id", id));
        return R.ok().put("data", povertyalleviationinformation);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        PovertyalleviationinformationEntity povertyalleviationinformation = povertyalleviationinformationService.selectById(id);
        if(type.equals("1")) {
        	povertyalleviationinformation.setThumbsupNumber(povertyalleviationinformation.getThumbsupNumber()+1);
        } else {
        	povertyalleviationinformation.setCrazilyNumber(povertyalleviationinformation.getCrazilyNumber()+1);
        }
        povertyalleviationinformationService.updateById(povertyalleviationinformation);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PovertyalleviationinformationEntity povertyalleviationinformation, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(povertyalleviationinformation);
        povertyalleviationinformationService.insert(povertyalleviationinformation);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PovertyalleviationinformationEntity povertyalleviationinformation, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(povertyalleviationinformation);
        povertyalleviationinformationService.insert(povertyalleviationinformation);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody PovertyalleviationinformationEntity povertyalleviationinformation, HttpServletRequest request){
        //ValidatorUtils.validateEntity(povertyalleviationinformation);
        povertyalleviationinformationService.updateById(povertyalleviationinformation);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        povertyalleviationinformationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PovertyalleviationinformationEntity povertyalleviationinformation, HttpServletRequest request,String pre){
        EntityWrapper<PovertyalleviationinformationEntity> ew = new EntityWrapper<PovertyalleviationinformationEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "click_number");
        params.put("order", "desc");
		PageUtils page = povertyalleviationinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, povertyalleviationinformation), params), params));
        return R.ok().put("data", page);
    }









}
