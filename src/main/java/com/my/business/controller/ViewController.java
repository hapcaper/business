package com.my.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:给客户展示的视图
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2021/1/12
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String tt() {
        return "admin/edit.html";
    }

}
