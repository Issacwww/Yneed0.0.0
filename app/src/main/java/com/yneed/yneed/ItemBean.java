package com.yneed.yneed;

/**
 * Created by 咸味 on 2016/2/27.
 */

public class ItemBean {

    public int itemImageResid;
    public String itemTitle;
    public String itemContent;

    public ItemBean(int itemImageResid, String itemTitle, String itemContent) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }
}
