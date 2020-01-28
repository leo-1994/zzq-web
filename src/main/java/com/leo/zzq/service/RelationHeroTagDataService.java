package com.leo.zzq.service;

import com.leo.zzq.pojo.entity.RelationHeroTag;

import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 16:54
 */
public interface RelationHeroTagDataService {
    List<RelationHeroTag> getByHeroName(String heroName);

    List<RelationHeroTag> getByTagName(String tagName);

    RelationHeroTag add(String heroNAme, String tagName);
}
