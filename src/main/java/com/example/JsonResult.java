package com.example;

import lombok.Data;

import java.util.Date;

/**
 * created by on 2021/9/12
 * 描述：封装Json数据返回统一格式
 *
 * @author ZSAndroid
 * @create 2021-09-12-23:12
 */
@Data
public class JsonResult<T> {
    private Date now= new Date();//生成时间
    private T data;//数据内容
    private String code;//状态码
    private String msg;//提示消息


    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public JsonResult() {
        this.code = "200";
        this.msg = "操作成功！";

    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 200，默认提示信息为“操作成功！”
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = "200";
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为 200，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = "200";
        this.msg = msg;
    }

    /**
     * 自定义
     * @param data
     * @param msg
     */
    public JsonResult(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
