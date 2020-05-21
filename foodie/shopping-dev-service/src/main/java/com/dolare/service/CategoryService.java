package com.dolare.service;

import com.dolare.pojo.Category;
import com.dolare.pojo.vo.CategoryVO;
import com.dolare.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {

    public List<Category> queryAllRootLevelCategory();

    public List<CategoryVO> queryAllSubCategoryList(Integer rootCategoryId);

    public List<NewItemsVO> querySixNewItems(Integer rootCategoryId);
}
