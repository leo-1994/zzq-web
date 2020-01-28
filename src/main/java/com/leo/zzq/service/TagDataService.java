package com.leo.zzq.service;

import com.leo.zzq.pojo.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 17:54
 */
public interface TagDataService {

    ArrayList<Tag> getAll();

    List<Tag> getByHeroName(String name);

    List<Tag> getByHeroNames(String heroNames);

    ArrayList<String> getAllFroJson();

    /**
     * 获取所有职业
     *
     * @return List<String>
     */
    List<String> getAllCareerForJson();

    /**
     * 获取所有种族
     *
     * @return List<String>
     */
    List<String> getAllRaceForJson();
}
