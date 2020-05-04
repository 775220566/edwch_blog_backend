package com.per.blog.common;

import java.util.List;

/**
 * Created by Administrator on 2019/7/30 0030.
 */
public class PageResult <T>{
    private long total;
    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {

    }

    public long getTotal() {

        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
