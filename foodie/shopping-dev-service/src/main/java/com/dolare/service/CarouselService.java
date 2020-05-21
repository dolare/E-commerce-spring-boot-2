package com.dolare.service;

import com.dolare.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    public List<Carousel> queryAll(Integer isShow);
}
