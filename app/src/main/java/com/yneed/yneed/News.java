package com.yneed.yneed;

/**
 * Created by 咸味 on 2016/3/8.
 */
public class News {
    private  String title;
    private  String desc;
    private  String time;
    private  String content_url;
    private  String pic_url;

    public News(String title,String desc,String time, String content_url,String pic_url) {
        setTitle(title);
        setDesc(desc);
        setTime(time);
        setContent_url(content_url);
        setPic_url(pic_url);
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.title = time;
    }
    public String getContent_url(){
        return title;
    }
    public void setContent_url(String content_url){
        this.content_url = content_url;
    }
    public String getPic_url(){
        return pic_url;
    }
    public void setPic_url(String pic_url){
        this.pic_url = pic_url;
    }


}
