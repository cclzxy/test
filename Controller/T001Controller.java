package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Mapper.T001Mapper;
import com.example.demo.Pogo.T001;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class T001Controller {
    @Resource
    T001Mapper t001Mapper;

    /**
     * 获取当前表内容
     *
     * @param nowClo 前端传递过来的当前表结构
     * @return 返回对象集合
     * author ccl
     */
    @ResponseBody
    @RequestMapping(value = "/getNowTableData", method = {RequestMethod.POST})
    public JSONArray getNowTableData(String nowClo) {
        String[] clo = nowClo.split(",");//根据列表名动态查询
        QueryWrapper<T001> wrapper = new QueryWrapper<>();
        wrapper.select(clo);
        List<T001> list = t001Mapper.selectList(wrapper);//动态获取数据
        list.removeAll(Collections.singleton(null));
        String json = JSONArray.toJSONString(list);
        return JSONArray.parseArray(json);
    }



    /**
     * @return 可用的列
     */
    @ResponseBody
    @RequestMapping(value = "/getAllClo", method = {RequestMethod.POST})
    public JSONArray getAllClo() {
        String[] allClo = t001Mapper.findClo();
        List<String> waitClo = new ArrayList<String>();
        for (int i = 0; i <= allClo.length - 1; i++) {
            if (t001Mapper.findCloCount(allClo[i]) == 0) {
                waitClo.add(allClo[i]);
            }
        }
        return JSONArray.parseArray(JSONArray.toJSONString(waitClo));
    }

    /**
     * @return 添加
     */
    @ResponseBody
    @RequestMapping(value = "/addOne", method = {RequestMethod.POST})
    public String addOne(String nowRol, String listName) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        String[] rol = nowRol.split(",");
        String[] name = listName.split(",");
        //反射机制,动态获取对象属性
        Class<T001> reflectTestClass = T001.class;
        //获取实例对象
        T001 t001 = reflectTestClass.newInstance();
        //获取属性对象,并修改值
        for (int i = 0; i <= name.length - 1; i++) {
            reflectTestClass.getDeclaredField(name[i]).set(t001, rol[i]);
        }
        t001Mapper.insert(t001);
        return "ok";
    }
}
