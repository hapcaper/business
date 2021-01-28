package com.my.business.controller;

import com.my.business.dao.ItemDao;
import com.my.business.po.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Description:给客户展示的视图
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2021/1/12
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    @Resource
    ItemDao itemDao;

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "type",required = false) String type) {
        List<Item> list;
        if (StringUtils.isEmpty(type)) {
            list = itemDao.findAll();
        } else {
            list = itemDao.findByType(type);
        }
        model.addAttribute("list", list);
        return "public/list";
    }
    @GetMapping("/info")
    public String info(Model model, @RequestParam("id") Long id) {
        Item item = itemDao.findById(id);
        item.setDescPicList(Arrays.asList(item.getDescPic().split(",")));
        item.setShowPicList(Arrays.asList(item.getShowPic().split(",")));
        model.addAttribute("item", item);
        return "public/info";
    }


}
