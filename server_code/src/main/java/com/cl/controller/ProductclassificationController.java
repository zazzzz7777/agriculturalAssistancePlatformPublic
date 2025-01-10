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

import com.cl.entity.ProductclassificationEntity;
import com.cl.entity.view.ProductclassificationView;

import com.cl.service.ProductclassificationService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 商品分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
@RestController
@RequestMapping("/productclassification")
public class ProductclassificationController {
    @Autowired
    private ProductclassificationService productclassificationService;







    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ProductclassificationEntity productclassification,
		HttpServletRequest request){
        EntityWrapper<ProductclassificationEntity> ew = new EntityWrapper<ProductclassificationEntity>();

		PageUtils page = productclassificationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, productclassification), params), params));

        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ProductclassificationEntity productclassification, 
		HttpServletRequest request){
        EntityWrapper<ProductclassificationEntity> ew = new EntityWrapper<ProductclassificationEntity>();

		PageUtils page = productclassificationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, productclassification), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ProductclassificationEntity productclassification){
       	EntityWrapper<ProductclassificationEntity> ew = new EntityWrapper<ProductclassificationEntity>();
      	ew.allEq(MPUtil.allEQMapPre( productclassification, "productclassification")); 
        return R.ok().put("data", productclassificationService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ProductclassificationEntity productclassification){
        EntityWrapper< ProductclassificationEntity> ew = new EntityWrapper< ProductclassificationEntity>();
 		ew.allEq(MPUtil.allEQMapPre( productclassification, "productclassification")); 
		ProductclassificationView productclassificationView =  productclassificationService.selectView(ew);
		return R.ok("查询商品分类成功").put("data", productclassificationView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ProductclassificationEntity productclassification = productclassificationService.selectById(id);
		productclassification = productclassificationService.selectView(new EntityWrapper<ProductclassificationEntity>().eq("id", id));
        return R.ok().put("data", productclassification);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ProductclassificationEntity productclassification = productclassificationService.selectById(id);
		productclassification = productclassificationService.selectView(new EntityWrapper<ProductclassificationEntity>().eq("id", id));
        return R.ok().put("data", productclassification);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ProductclassificationEntity productclassification, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(productclassification);
        productclassificationService.insert(productclassification);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ProductclassificationEntity productclassification, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(productclassification);
        productclassificationService.insert(productclassification);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ProductclassificationEntity productclassification, HttpServletRequest request){
        //ValidatorUtils.validateEntity(productclassification);
        productclassificationService.updateById(productclassification);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        productclassificationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
