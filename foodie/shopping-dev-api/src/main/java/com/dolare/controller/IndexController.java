package com.dolare.controller;

import com.dolare.enums.YesOrNo;
import com.dolare.pojo.Carousel;
import com.dolare.pojo.Category;
import com.dolare.pojo.vo.CategoryVO;
import com.dolare.pojo.vo.NewItemsVO;
import com.dolare.service.CarouselService;
import com.dolare.service.CategoryService;
import com.dolare.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/carousel")
    public JSONResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);

        return JSONResult.ok(list);
    }

    @GetMapping("/cats")
    public JSONResult category() {
        List<Category> list = categoryService.queryAllRootLevelCategory();

        return JSONResult.ok(list);
    }

    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subCategory (@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return JSONResult.errorMsg("rootCatId can not be empty!");
        }

        List<CategoryVO> list = categoryService.queryAllSubCategoryList(rootCatId);

        return JSONResult.ok(list);
    }
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems (@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return JSONResult.errorMsg("rootCatId can not be empty!");
        }

        List<NewItemsVO> list = categoryService.querySixNewItems(rootCatId);

        return JSONResult.ok(list);
    }
}
