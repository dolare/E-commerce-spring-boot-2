package com.dolare.controller;

import com.dolare.enums.CommentLevel;
import com.dolare.enums.YesOrNo;
import com.dolare.pojo.*;
import com.dolare.pojo.vo.CategoryVO;
import com.dolare.pojo.vo.CommentLevelCountsVO;
import com.dolare.pojo.vo.ItemInfoVO;
import com.dolare.pojo.vo.NewItemsVO;
import com.dolare.service.CarouselService;
import com.dolare.service.CategoryService;
import com.dolare.service.ItemService;
import com.dolare.utils.JSONResult;
import com.dolare.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController extends BaseController{
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

    @GetMapping("/commentLevel")
    public JSONResult getCommentLevelCounts (@RequestParam String itemId) {
        if (StringUtil.isEmpty(itemId)) {
            return JSONResult.errorMsg("itemId or commentLevel can not be empty!");
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);

        // construct json
        return JSONResult.ok(countsVO);
    }

    @GetMapping("/comments")
    public JSONResult getPagedCommetns (@RequestParam String itemId,
                                             @RequestParam Integer level,
                                             @RequestParam Integer page,
                                             @RequestParam Integer pageSize) {
        if (StringUtil.isEmpty(itemId)) {
            return JSONResult.errorMsg("itemId or commentLevel can not be empty!");
        }

        if (level == null) {
            level = 1;
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult result = itemService.queryPagedComments(itemId, level, page, pageSize);

        // construct json
        return JSONResult.ok(result);
    }

    @GetMapping("/search")
    public JSONResult getPagedCommetns (@RequestParam String keywords,
                                        @RequestParam String sort,
                                        @RequestParam Integer page,
                                        @RequestParam Integer pageSize) {
        if (StringUtil.isEmpty(keywords)) {
            return JSONResult.errorMsg("keywords can not be empty!");
        }

        if (sort == null) {
            sort = "c";
        }

        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }

        PagedGridResult result = itemService.searchItems(keywords, sort, page, pageSize);

        // construct json
        return JSONResult.ok(result);
    }
}
