package com.dolare.service.implement;

import com.dolare.enums.CommentLevel;
import com.dolare.mapper.*;
import com.dolare.pojo.*;
import com.dolare.pojo.vo.CommentLevelCountsVO;
import com.dolare.pojo.vo.ItemCommentVO;
import com.dolare.pojo.vo.SearchItemsVO;
import com.dolare.service.ItemService;
import com.dolare.utils.DesensitizationUtil;
import com.dolare.utils.JSONResult;
import com.dolare.utils.PagedGridResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private  ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example itemsImgExp = new Example(ItemsImg.class);
        Example.Criteria criteria = itemsImgExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsImgMapper.selectByExample(itemsImgExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example itemsSpecExp = new Example(ItemsSpec.class);
        Example.Criteria criteria = itemsSpecExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsSpecMapper.selectByExample(itemsSpecExp);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example itemsParamExp = new Example(ItemsParam.class);
        Example.Criteria criteria = itemsParamExp.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        return itemsParamMapper.selectOneByExample(itemsParamExp);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        // Integer totalCounts = getCommentCounts()
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.type);

        CommentLevelCountsVO countsVO = new CommentLevelCountsVO(goodCounts + normalCounts + badCounts, goodCounts, normalCounts, badCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments condition = new ItemsComments();

        condition.setItemId(itemId);

        if (level != null) {
            condition.setCommentLevel(level);
        }

        return itemsCommentsMapper.selectCount(condition);

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();

        map.put("itemId", itemId);
        map.put("level", level);

        // page helper from mybatis

        PageHelper.startPage(page, pageSize);

        List<ItemCommentVO> resutl = itemsMapperCustom.queryItemsComments(map);

        for (ItemCommentVO vo : resutl) {
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }
        PageInfo<?> pageList = new PageInfo<>();

        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(resutl);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();

        map.put("keywords", keywords);
        map.put("sort", sort);

        // page helper from mybatis

        PageHelper.startPage(page, pageSize);

        List<SearchItemsVO> result = itemsMapperCustom.searchItems(map);

        for (SearchItemsVO vo : result) {
            vo.setItemName(DesensitizationUtil.commonDisplay(vo.getItemName()));
        }
        PageInfo<?> pageList = new PageInfo<>();

        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(result);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }
}
