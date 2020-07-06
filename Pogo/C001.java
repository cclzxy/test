package com.example.demo.Pogo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 表名对象
 * author ccl
 */
@TableName("c001")
public class C001 {
    @TableId(value = "tableId",type = IdType.AUTO)
    private long tableId;//表单id
    private String title;//表单的名称
    private String name;//名称的含义


    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
