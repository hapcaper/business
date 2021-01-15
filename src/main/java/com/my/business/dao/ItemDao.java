package com.my.business.dao;

import java.util.List;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2020/12/29
 */
public interface ItemDao {

    List<Object> findById();

    List<Object> findAll();
}
