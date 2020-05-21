package com.dolare.service.implement;

import com.dolare.mapper.CategoryMapper;
import com.dolare.mapper.CategoryMapperCustom;
import com.dolare.pojo.Category;
import com.dolare.pojo.vo.CategoryVO;
import com.dolare.pojo.vo.NewItemsVO;
import com.dolare.service.CarouselService;
import com.dolare.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Override
    public List<Category> queryAllRootLevelCategory () {

        Example example = new Example(Category.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("type", 1);

        List<Category> listCatogory = categoryMapper.selectByExample(example);

        return listCatogory;
    }

    @Override
    @Transactional(propagation =  Propagation.REQUIRED)
    public List<CategoryVO> queryAllSubCategoryList (Integer rootCategoryId) {
        return categoryMapperCustom.getSubCategoryList(rootCategoryId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<NewItemsVO> querySixNewItems(Integer rootCategoryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCategoryId);
        return categoryMapperCustom.getSixNewItems(map);
    }
}
