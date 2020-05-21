package com.dolare.mapper;

import com.dolare.my.mapper.MyMapper;
import com.dolare.pojo.Category;
import com.dolare.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom{
    public List<CategoryVO> getSubCategoryList(Integer rootCatId);

    public List getSixNewItems(@Param("paramsMap") Map<String, Object> map);
}