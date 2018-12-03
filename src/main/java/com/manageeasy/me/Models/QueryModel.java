package com.manageeasy.me.Models;

public class QueryModel<T> {
    public T data;
    public T totalCount;

    public QueryModel(T data, T totalCount){
        this.data = data;
        this.totalCount = totalCount;
    }
}
