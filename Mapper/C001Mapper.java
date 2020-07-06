package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Pogo.C001;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface C001Mapper extends BaseMapper<C001> {
    @Select("select tableId from c001 where title=#{title}")
    Integer findTableByTitle(String title);

}
