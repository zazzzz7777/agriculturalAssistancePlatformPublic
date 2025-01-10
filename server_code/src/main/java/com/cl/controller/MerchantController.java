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

import com.cl.entity.MerchantEntity;
import com.cl.entity.view.MerchantView;

import com.cl.service.MerchantService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 商家
 * 后端接口
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;



	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		MerchantEntity u = merchantService.selectOne(new EntityWrapper<MerchantEntity>().eq("merchantname", username));
        if(u==null || !u.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
		String token = tokenService.generateToken(u.getId(), username,"merchant",  "merchant" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody MerchantEntity merchant){
    	//ValidatorUtils.validateEntity(merchant);
    	MerchantEntity u = merchantService.selectOne(new EntityWrapper<MerchantEntity>().eq("merchantname", merchant.getMerchantname()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		merchant.setId(uId);
        merchantService.insert(merchant);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        MerchantEntity u = merchantService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	MerchantEntity u = merchantService.selectOne(new EntityWrapper<MerchantEntity>().eq("merchantname", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setPassword("123456");
        merchantService.updateById(u);
        return R.ok("密码已重置为：123456");
    }




    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MerchantEntity merchant,
		HttpServletRequest request){
        EntityWrapper<MerchantEntity> ew = new EntityWrapper<MerchantEntity>();

		PageUtils page = merchantService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, merchant), params), params));

        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MerchantEntity merchant, 
		HttpServletRequest request){
        EntityWrapper<MerchantEntity> ew = new EntityWrapper<MerchantEntity>();

		PageUtils page = merchantService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, merchant), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MerchantEntity merchant){
       	EntityWrapper<MerchantEntity> ew = new EntityWrapper<MerchantEntity>();
      	ew.allEq(MPUtil.allEQMapPre( merchant, "merchant")); 
        return R.ok().put("data", merchantService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MerchantEntity merchant){
        EntityWrapper< MerchantEntity> ew = new EntityWrapper< MerchantEntity>();
 		ew.allEq(MPUtil.allEQMapPre( merchant, "merchant")); 
		MerchantView merchantView =  merchantService.selectView(ew);
		return R.ok("查询商家成功").put("data", merchantView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MerchantEntity merchant = merchantService.selectById(id);
		merchant = merchantService.selectView(new EntityWrapper<MerchantEntity>().eq("id", id));
        return R.ok().put("data", merchant);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MerchantEntity merchant = merchantService.selectById(id);
		merchant = merchantService.selectView(new EntityWrapper<MerchantEntity>().eq("id", id));
        return R.ok().put("data", merchant);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MerchantEntity merchant, HttpServletRequest request){
        if(merchantService.selectCount(new EntityWrapper<MerchantEntity>().eq("merchantname", merchant.getMerchantname()))>0) {
            return R.error("商家名称已存在");
        }
    	merchant.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(merchant);
    	MerchantEntity u = merchantService.selectOne(new EntityWrapper<MerchantEntity>().eq("merchantname", merchant.getMerchantname()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		merchant.setId(new Date().getTime());
        merchantService.insert(merchant);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody MerchantEntity merchant, HttpServletRequest request){
        if(merchantService.selectCount(new EntityWrapper<MerchantEntity>().eq("merchantname", merchant.getMerchantname()))>0) {
            return R.error("商家名称已存在");
        }
    	merchant.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(merchant);
    	MerchantEntity u = merchantService.selectOne(new EntityWrapper<MerchantEntity>().eq("merchantname", merchant.getMerchantname()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		merchant.setId(new Date().getTime());
        merchantService.insert(merchant);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody MerchantEntity merchant, HttpServletRequest request){
        //ValidatorUtils.validateEntity(merchant);
        merchantService.updateById(merchant);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        merchantService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
