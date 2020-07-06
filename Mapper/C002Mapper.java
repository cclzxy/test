package com.example.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Pogo.C002;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface C002Mapper extends BaseMapper<C002> {
    @Insert("insert into c002(tableId,cloTitle,cloName) values(#{tableId},#{cloTitle},#{cloName})")
    void insertTableClo(Integer tableId,String cloTitle,String cloName);
    @Select("select id,tableId,cloTitle,cloName from c002 where tableId=#{tableId}")
    List<C002> findListByTableId(Integer tableId);
}
