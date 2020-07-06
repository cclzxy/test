package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Mapper.C001Mapper;
import com.example.demo.Mapper.C002Mapper;
import com.example.demo.Mapper.T001Mapper;
import com.example.demo.Pogo.C001;
import com.example.demo.Pogo.T001;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * author ccl
 */
@Controller
public class C001Controller {
    @Resource
    C001Mapper c001Mapper;
    @Resource
    C002Mapper c002Mapper;
    @Resource
    T001Mapper t001Mapper;

    /**
     * 获取所有表名列表
     *
     * @return 列表
     */
    @ResponseBody
    @RequestMapping(value = "/getTableName", method = {RequestMethod.POST})
    public JSONArray getTableName() {
        try {
            List<C001> c001List = c001Mapper.selectList(null);
            String json = JSONArray.toJSONString(c001List);
            return JSONArray.parseArray(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param tableName 表名
     * @param cloName   所有列的数组
     * @return 返回成功失败
     */
    @ResponseBody
    @RequestMapping(value = "/addTable", method = {RequestMethod.POST})
    public String addTable(String tableName, String cloName, String tableClo) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        String[] clo = cloName.split(",");//用户定义的名称(用户定义的名称)
        String[] tbClo = tableClo.split(",");//表中可用的列名(数据库)
        C001 c001 = new C001();
        c001.setTitle(tableName);
        c001Mapper.insert(c001);//添加表名
        Integer tableId = c001Mapper.findTableByTitle(tableName);//表名id
        for (int i = 0; i <= clo.length - 1; i++) {
            c002Mapper.insertTableClo(tableId, tbClo[i], clo[i]);//添加各个列
        }
        Class<T001> reflectTestClass = T001.class; //反射机制,动态获取对象属性
        T001 t001 = reflectTestClass.newInstance(); //获取实例对象
        for (int i = 0; i <= tbClo.length - 1; i++) {//获取属性对象,并修改值
            reflectTestClass.getDeclaredField(tbClo[i]).set(t001, "");
        }
        t001Mapper.insert(t001);
        return "ok";
    }

}
