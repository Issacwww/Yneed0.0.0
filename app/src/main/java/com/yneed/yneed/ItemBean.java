package com.yneed.yneed;

/**
 * Created by 咸味 on 2016/2/27.
 */
public class ItemBean {
    public  int ItemImageResid;
    public String ItemTitle;
    public String ItemContent;


    public ItemBean(int ImageResid, String Title, String Content) {
        ItemImageResid = ImageResid;
        ItemTitle = Title;
        ItemContent = Content;
    }
}
