package com.example.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Mapper.C002Mapper;
import com.example.demo.Pogo.C002;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

@Controller
public class C002Controller {
    @Resource
    C002Mapper c002Mapper;

    /**
     * 获取表结构
     * @param tableId 哪一张表
     * @return 表结构
     */
    @ResponseBody
    @RequestMapping(value = "/getClo", method = {RequestMethod.POST})
    public JSONArray getList(Integer tableId) {
        List<C002> c002List = c002Mapper.findListByTableId(tableId);
        String json = JSONArray.toJSONString(c002List);
        return JSONArray.parseArray(json);
    }

}
