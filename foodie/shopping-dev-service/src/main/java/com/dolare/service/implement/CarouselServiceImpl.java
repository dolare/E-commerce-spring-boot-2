package com.dolare.service.implement;

import com.dolare.mapper.CarouselMapper;
import com.dolare.pojo.Carousel;
import com.dolare.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example((Carousel.class));

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("isShow", isShow);

        List<Carousel> listCarousel = carouselMapper.selectByExample(example);

        return listCarousel;
    }
}
