package com.dolare.service;

import com.dolare.pojo.Items;
import com.dolare.pojo.ItemsImg;
import com.dolare.pojo.ItemsParam;
import com.dolare.pojo.ItemsSpec;
import com.dolare.pojo.vo.CommentLevelCountsVO;
import com.dolare.pojo.vo.ItemCommentVO;
import com.dolare.utils.PagedGridResult;

import java.util.List;

public interface ItemService {
    public Items queryItemById(String id);

    public List<ItemsImg> queryItemImgList(String itemId);

    public List<ItemsSpec> queryItemSpecList(String itemId);

    public ItemsParam queryItemParam(String itemId);

    public CommentLevelCountsVO queryCommentCounts(String itemId);

    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);
}
