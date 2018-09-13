package com.dong.tvdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public class ItemsBean implements Serializable {
    /**
     * _id : 5aa5d6a611b983257bf21a7d
     * is_series : 0
     * category_id : 203
     * infotext : 更新至5集
     * mark :
     * poster : http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/w/w4duvzvd9yqk8fj_q.jpg
     * title : 师说 第二季
     * corner_tag_type : red
     * cid : w4duvzvd9yqk8fj
     * corner_tag_text : 热
     * vip_type : []
     * is_trailer : 0
     * spec_title : 师说 第二季
     * score : 22
     * zone : {}
     */

    private String _id;
    private String is_series;
    private String category_id;
    private String infotext;
    private String mark;
    private String poster;
    private String title;
    private String corner_tag_type;
    private String cid;
    private String corner_tag_text;
    private String is_trailer;
    private String spec_title;
    private String score;
    private ItemsBean.ZoneBean zone;
    private List<?> vip_type;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIs_series() {
        return is_series;
    }

    public void setIs_series(String is_series) {
        this.is_series = is_series;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getInfotext() {
        return infotext;
    }

    public void setInfotext(String infotext) {
        this.infotext = infotext;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCorner_tag_type() {
        return corner_tag_type;
    }

    public void setCorner_tag_type(String corner_tag_type) {
        this.corner_tag_type = corner_tag_type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCorner_tag_text() {
        return corner_tag_text;
    }

    public void setCorner_tag_text(String corner_tag_text) {
        this.corner_tag_text = corner_tag_text;
    }

    public String getIs_trailer() {
        return is_trailer;
    }

    public void setIs_trailer(String is_trailer) {
        this.is_trailer = is_trailer;
    }

    public String getSpec_title() {
        return spec_title;
    }

    public void setSpec_title(String spec_title) {
        this.spec_title = spec_title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ItemsBean.ZoneBean getZone() {
        return zone;
    }

    public void setZone(ItemsBean.ZoneBean zone) {
        this.zone = zone;
    }

    public List<?> getVip_type() {
        return vip_type;
    }

    public void setVip_type(List<?> vip_type) {
        this.vip_type = vip_type;
    }

    @Override
    public String toString() {
        return "ItemsBean{" +
                "_id='" + _id + '\'' +
                ", is_series='" + is_series + '\'' +
                ", category_id='" + category_id + '\'' +
                ", infotext='" + infotext + '\'' +
                ", mark='" + mark + '\'' +
                ", poster='" + poster + '\'' +
                ", title='" + title + '\'' +
                ", corner_tag_type='" + corner_tag_type + '\'' +
                ", cid='" + cid + '\'' +
                ", corner_tag_text='" + corner_tag_text + '\'' +
                ", is_trailer='" + is_trailer + '\'' +
                ", spec_title='" + spec_title + '\'' +
                ", score='" + score + '\'' +
                ", zone=" + zone +
                ", vip_type=" + vip_type +
                '}';
    }

    public static class ZoneBean {
    }
}

