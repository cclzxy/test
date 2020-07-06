package com.example.demo.Pogo;



/**
 * 表数据结构对象
 * author ccl
 */

public class C002 {
    private long id;//唯一id
    private long tableId;//表单唯一id
    private String cloTitle;//表单列名称
    private String cloName;//名称含义


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }


    public String getCloTitle() {
        return cloTitle;
    }

    public void setCloTitle(String cloTitle) {
        this.cloTitle = cloTitle;
    }


    public String getCloName() {
        return cloName;
    }

    public void setCloName(String cloName) {
        this.cloName = cloName;
    }

}
