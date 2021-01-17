package com.my.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.my.business.dao.ItemDao;
import com.my.business.po.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2020/12/29
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource
    private ItemDao itemDao;

    @Value("${web.upload-path}")
    private String uploadPath;


    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "/usr/local/pic/"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);
        return "file";
    }
    @RequestMapping(value = "/add")
    public String add(Model model ,@RequestParam(value = "name") String name,@RequestParam(value = "type") String type,@RequestParam(value = "price") Float price,
                      @RequestParam(value = "order") Integer order,@RequestParam(value = "showPicList") MultipartFile[] showPicList,
                      @RequestParam(value = "descPicList") MultipartFile[] descPicList,@RequestParam(value = "desc") String desc,@RequestParam(value = "itemPic") MultipartFile itemPic){
        Item item = new Item();
        item.setOrder(order);
        item.setDesc(desc);
        item.setName(name);
        item.setPrice(price);
        item.setType(type);
        String showPic = savePicList(showPicList);
        item.setShowPic(showPic);
        String descPic = savePicList(descPicList);
        item.setDescPic(descPic);
        StringBuilder itemPicStr = new StringBuilder();
        save(itemPicStr, itemPic);
        item.setItemPic(itemPicStr.toString());

        System.out.println("item:");
        System.out.println(JSONObject.toJSONString(item));
        itemDao.insert(item);
        return "redirect:/item/toAdd";
    }

    @GetMapping("/toAdd")
    public String toAdd(){



        return "admin/edit";
    }

    private String savePicList(MultipartFile[] picList) {
        StringBuilder picListStr = new StringBuilder();
        if (picList != null && picList.length > 0) {
            for (MultipartFile m : picList) {
                save(picListStr, m);
                picListStr.append(",");
            }
            if (picListStr.toString().endsWith(",")) {
                int i = picListStr.lastIndexOf(",");
                picListStr = new StringBuilder(picListStr.substring(0, i));
            }
        }
        return picListStr.toString();
    }

    private void save(StringBuilder picStr, MultipartFile m) {
        String fileName = m.getOriginalFilename();
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        String pathname = uploadPath + fileName;

        File dest = new File(pathname);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            boolean b = dest.createNewFile();
            if (b) {
                m.transferTo(dest);
                picStr.append(pathname);
            } else {
                System.out.println("创建文件失败!!!!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
