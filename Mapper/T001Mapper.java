package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Pogo.T001;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * author ccl
 */
@Repository
public interface T001Mapper extends BaseMapper<T001> {
    //找到通用表中所有clo
    @Select("select column_name from information_schema.columns where table_schema ='test' and table_name = 't001'")
    String[] findClo();
    //找到表中各个列的数据条数
    @Select("select count(${cloName}) from t001")
    Integer findCloCount(String cloName);
}
