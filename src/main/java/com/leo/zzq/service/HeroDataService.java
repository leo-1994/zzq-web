package com.leo.zzq.service;

import com.leo.zzq.pojo.entity.Hero;
import com.leo.zzq.pojo.vo.HeroJson;
import com.leo.zzq.pojo.vo.HeroVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 16:44
 */
public interface HeroDataService {
    List<Hero> getAll();

    ArrayList<HeroVO> getAllWithTags();

    ArrayList<HeroJson> getAllForJson();

    List<Hero> getByTagName(String tagName);

    List<Hero> getByHeroIds(String ids);

    Hero getByName(String name);
}
