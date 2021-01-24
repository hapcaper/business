package com.my.business.po;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2020/12/29
 */
public class Item implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String desc;
    /**
     * 轮播图
     */
    private String showPic;
    private List<String> showPicList;
    /**
     * 商品展示用pic
     */
    private String itemPic;
    /**
     * 详细图片,用在描述后面
     */
    private String descPic;
    private List<String> descPicList;
    /**
     * 参考价格
     */
    private Float price;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 分类
     */
    private String type;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getShowPic() {
        return showPic;
    }

    public void setShowPic(String showPic) {
        this.showPic = showPic;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescPic() {
        return descPic;
    }

    public void setDescPic(String descPic) {
        this.descPic = descPic;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public List<String> getShowPicList() {
        return showPicList;
    }

    public void setShowPicList(List<String> showPicList) {
        this.showPicList = showPicList;
    }

    public List<String> getDescPicList() {
        return descPicList;
    }

    public void setDescPicList(List<String> descPicList) {
        this.descPicList = descPicList;
    }
}
