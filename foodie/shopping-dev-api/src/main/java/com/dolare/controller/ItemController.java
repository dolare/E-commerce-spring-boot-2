package com.dolare.controller;

import com.dolare.enums.YesOrNo;
import com.dolare.pojo.*;
import com.dolare.pojo.vo.CategoryVO;
import com.dolare.pojo.vo.ItemInfoVO;
import com.dolare.pojo.vo.NewItemsVO;
import com.dolare.service.CarouselService;
import com.dolare.service.CategoryService;
import com.dolare.service.ItemService;
import com.dolare.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @GetMapping("/info/{itemId}")
    public JSONResult sixNewItems (@PathVariable String itemId) {
        if (StringUtil.isEmpty(itemId)) {
            return JSONResult.errorMsg("itemId can not be empty!");
        }

        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        // construct json
        return JSONResult.ok(new ItemInfoVO(item, itemsImgList, itemsSpecs, itemsParam));
    }
}
