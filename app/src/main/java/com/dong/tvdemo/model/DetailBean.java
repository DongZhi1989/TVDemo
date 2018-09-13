package com.dong.tvdemo.model;

import java.util.List;

/**
 * Author      : <Dr_dong>
 * Date        : 2018/9/13
 * Description :
 */
public class DetailBean {

    private String count;
    private List<ItemsBean> items;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * _id : 5b923299f3514c2a48d87753
         * is_series : 0
         * category_id : 2
         * infotext : 7.3
         * mark : 女侠闯神秘世界
         * poster : http://i.gtimg.cn/qqlive/img/jpgcache/files/qqvideo/b/bzhzppxdqsv6sr0_q.jpg
         * title : 神秘世界历险记4
         * corner_tag_type : red
         * cid : bzhzppxdqsv6sr0
         * corner_tag_text : 热
         * vip_type : ["999","1"]
         * is_trailer : 0
         * spec_title : 神秘世界历险记4
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
        private ZoneBean zone;
        private List<String> vip_type;

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

        public ZoneBean getZone() {
            return zone;
        }

        public void setZone(ZoneBean zone) {
            this.zone = zone;
        }

        public List<String> getVip_type() {
            return vip_type;
        }

        public void setVip_type(List<String> vip_type) {
            this.vip_type = vip_type;
        }

        public static class ZoneBean {
        }
    }
}
