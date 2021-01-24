package com.my.business.service;

import com.my.business.dao.ItemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2021/1/23
 */
@Service
public class ItemService {
    @Resource
    private ItemDao itemDao;

}
