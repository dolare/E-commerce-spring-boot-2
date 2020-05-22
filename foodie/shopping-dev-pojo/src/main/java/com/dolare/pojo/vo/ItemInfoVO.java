package com.dolare.pojo.vo;

import com.dolare.pojo.Items;
import com.dolare.pojo.ItemsImg;
import com.dolare.pojo.ItemsParam;
import com.dolare.pojo.ItemsSpec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class ItemInfoVO {

    private Items item;

    private List<ItemsImg> itemImgList;

    private List<ItemsSpec> itemSpecList;

    private ItemsParam itemParams;
}
