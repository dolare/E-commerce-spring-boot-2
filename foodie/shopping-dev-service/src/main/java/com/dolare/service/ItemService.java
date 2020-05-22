package com.dolare.service;

import com.dolare.pojo.Items;
import com.dolare.pojo.ItemsImg;
import com.dolare.pojo.ItemsParam;
import com.dolare.pojo.ItemsSpec;

import java.util.List;

public interface ItemService {
    public Items queryItemById(String id);

    public List<ItemsImg> queryItemImgList(String itemId);

    public List<ItemsSpec> queryItemSpecList(String itemId);

    public ItemsParam queryItemParam(String itemId);
}
