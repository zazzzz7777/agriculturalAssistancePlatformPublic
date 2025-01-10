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
import com.cl.entity.OrdersEntity;
import com.cl.service.OrdersService;

import com.cl.entity.AssistanceproductinformationEntity;
import com.cl.entity.view.AssistanceproductinformationView;

import com.cl.service.AssistanceproductinformationService;
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
 * 帮扶商品信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-09-26 15:19:52
 */
@RestController
@RequestMapping("/assistanceproductinformation")
public class AssistanceproductinformationController {
    @Autowired
    private AssistanceproductinformationService assistanceproductinformationService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;





    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,AssistanceproductinformationEntity assistanceproductinformation,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("merchant")) {
			assistanceproductinformation.setMerchantname((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<AssistanceproductinformationEntity> ew = new EntityWrapper<AssistanceproductinformationEntity>();

		PageUtils page = assistanceproductinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, assistanceproductinformation), params), params));

        return R.ok().put("data", page);
    }
    
    
    
    
    
    
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,AssistanceproductinformationEntity assistanceproductinformation, 
		HttpServletRequest request){
        EntityWrapper<AssistanceproductinformationEntity> ew = new EntityWrapper<AssistanceproductinformationEntity>();

		PageUtils page = assistanceproductinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, assistanceproductinformation), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( AssistanceproductinformationEntity assistanceproductinformation){
       	EntityWrapper<AssistanceproductinformationEntity> ew = new EntityWrapper<AssistanceproductinformationEntity>();
      	ew.allEq(MPUtil.allEQMapPre( assistanceproductinformation, "assistanceproductinformation")); 
        return R.ok().put("data", assistanceproductinformationService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(AssistanceproductinformationEntity assistanceproductinformation){
        EntityWrapper< AssistanceproductinformationEntity> ew = new EntityWrapper< AssistanceproductinformationEntity>();
 		ew.allEq(MPUtil.allEQMapPre( assistanceproductinformation, "assistanceproductinformation")); 
		AssistanceproductinformationView assistanceproductinformationView =  assistanceproductinformationService.selectView(ew);
		return R.ok("查询帮扶商品信息成功").put("data", assistanceproductinformationView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        AssistanceproductinformationEntity assistanceproductinformation = assistanceproductinformationService.selectById(id);
		assistanceproductinformation.setClicktime(new Date());
		assistanceproductinformationService.updateById(assistanceproductinformation);
		assistanceproductinformation = assistanceproductinformationService.selectView(new EntityWrapper<AssistanceproductinformationEntity>().eq("id", id));
        return R.ok().put("data", assistanceproductinformation);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        AssistanceproductinformationEntity assistanceproductinformation = assistanceproductinformationService.selectById(id);
		assistanceproductinformation.setClicktime(new Date());
		assistanceproductinformationService.updateById(assistanceproductinformation);
		assistanceproductinformation = assistanceproductinformationService.selectView(new EntityWrapper<AssistanceproductinformationEntity>().eq("id", id));
        return R.ok().put("data", assistanceproductinformation);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        AssistanceproductinformationEntity assistanceproductinformation = assistanceproductinformationService.selectById(id);
        if(type.equals("1")) {
        	assistanceproductinformation.setThumbsupNumber(assistanceproductinformation.getThumbsupNumber()+1);
        } else {
        	assistanceproductinformation.setCrazilyNumber(assistanceproductinformation.getCrazilyNumber()+1);
        }
        assistanceproductinformationService.updateById(assistanceproductinformation);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AssistanceproductinformationEntity assistanceproductinformation, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(assistanceproductinformation);
        assistanceproductinformationService.insert(assistanceproductinformation);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody AssistanceproductinformationEntity assistanceproductinformation, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(assistanceproductinformation);
        assistanceproductinformationService.insert(assistanceproductinformation);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody AssistanceproductinformationEntity assistanceproductinformation, HttpServletRequest request){
        //ValidatorUtils.validateEntity(assistanceproductinformation);
        assistanceproductinformationService.updateById(assistanceproductinformation);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<AssistanceproductinformationEntity> list = new ArrayList<AssistanceproductinformationEntity>();
        for(Long id : ids) {
            AssistanceproductinformationEntity assistanceproductinformation = assistanceproductinformationService.selectById(id);
            assistanceproductinformation.setSfsh(sfsh);
            assistanceproductinformation.setShhf(shhf);
            list.add(assistanceproductinformation);
        }
        assistanceproductinformationService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        assistanceproductinformationService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,AssistanceproductinformationEntity assistanceproductinformation, HttpServletRequest request,String pre){
        EntityWrapper<AssistanceproductinformationEntity> ew = new EntityWrapper<AssistanceproductinformationEntity>();
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
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = assistanceproductinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, assistanceproductinformation), params), params));
        return R.ok().put("data", page);
    }

        /**
     * 按用户购买推荐
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,AssistanceproductinformationEntity assistanceproductinformation, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String goodtypeColumn = "productclassification";
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "assistanceproductinformation").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<AssistanceproductinformationEntity> assistanceproductinformationList = new ArrayList<AssistanceproductinformationEntity>();
	//去重
    	List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
    	for(OrdersEntity o1 : orders) {
    		boolean addFlag = true;
    		for(OrdersEntity o2 : ordersDist) {
    			if(o1.getGoodid()==o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
    				addFlag = false;
    				break;
    			}
    		}
    		if(addFlag) ordersDist.add(o1);
    	}
        if(ordersDist!=null && ordersDist.size()>0) {
                for(OrdersEntity o : ordersDist) {
                        assistanceproductinformationList.addAll(assistanceproductinformationService.selectList(new EntityWrapper<AssistanceproductinformationEntity>().eq(goodtypeColumn, o.getGoodtype())));
                }
        }
        EntityWrapper<AssistanceproductinformationEntity> ew = new EntityWrapper<AssistanceproductinformationEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = assistanceproductinformationService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, assistanceproductinformation), params), params));
        List<AssistanceproductinformationEntity> pageList = (List<AssistanceproductinformationEntity>)page.getList();
        if(assistanceproductinformationList.size()<limit) {
                int toAddNum = (limit-assistanceproductinformationList.size())<=pageList.size()?(limit-assistanceproductinformationList.size()):pageList.size();
                for(AssistanceproductinformationEntity o1 : pageList) {
                    boolean addFlag = true;
                    for(AssistanceproductinformationEntity o2 : assistanceproductinformationList) {
                        if(o1.getId().intValue()==o2.getId().intValue()) {
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag) {
                        assistanceproductinformationList.add(o1);
                        if(--toAddNum==0) break;
                    }   
                }
        } else if(assistanceproductinformationList.size()>limit) {
            assistanceproductinformationList = assistanceproductinformationList.subList(0, limit);
        }
        page.setList(assistanceproductinformationList);
        return R.ok().put("data", page);
    }








}
