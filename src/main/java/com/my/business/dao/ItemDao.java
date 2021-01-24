package com.my.business.dao;

import com.my.business.po.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2020/12/29
 */
@Repository
@Mapper
public interface ItemDao {

    Item findById(@Param("id") Long id);

    List<Item> findAll();

    void insert(@Param("pojo") Item item);

    List<String> findAllType();

    List<Item> findByType(@Param("type") String type);
}
