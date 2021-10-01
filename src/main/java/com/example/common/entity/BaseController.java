package com.example.common.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * created by on 2021/10/1
 * 描述：
 *
 * @author ZSAndroid
 * @create 2021-10-01-14:04
 */

public class BaseController<M extends IService<T>,T> {

    @Autowired
    private M m;

    /**
     * 查询所有数据
     * @return
     */
    @GetMapping("/all")
    public List<T> all(){
        QueryWrapper<T> wrapper=new QueryWrapper<>();
        wrapper.eq("del",false);
        return m.list(wrapper);
    }

    @PostMapping("/page")
    public Page<T> page(@RequestBody Page page, T entity){
        QueryWrapper<T> wrapper=new QueryWrapper<>();

        wrapper.eq("del",false);
        return m.page(page,wrapper);
    }

    /**
     * 增加数据
     * @param entity
     */
    @PostMapping("/save")
    public void save(@RequestBody T entity){
        m.save(entity);
    }

    /**
     * 删除数据
     * @param pkId
     */
    @PostMapping("/remove")
    public void remove(String pkId){
        m.removeById(pkId);
    }

    /**
     * 修改
     * @param entity
     */
    @PostMapping("/update")
    public void update(T entity){
        m.updateById(entity);
    }
}
