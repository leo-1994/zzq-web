package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.RelationHeroTag;
import com.leo.zzq.repository.RelationHeroTagRepository;
import com.leo.zzq.service.RelationHeroTagDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 16:55
 */
@Service
public class RelationHeroTagDataServiceImpl implements RelationHeroTagDataService {

    @Autowired
    private RelationHeroTagRepository relationHeroTagRepository;

    @Override
    public List<RelationHeroTag> getByHeroName(String heroName) {
        return relationHeroTagRepository.findAllByHName(heroName);
    }

    @Override
    public List<RelationHeroTag> getByTagName(String tagName) {
        return relationHeroTagRepository.findAllByTName(tagName);
    }

    @Override
    public RelationHeroTag add(String heroNAme, String tagName) {
        RelationHeroTag relationHeroTag = new RelationHeroTag();
        relationHeroTag.setHName(heroNAme);
        relationHeroTag.setTName(tagName);
        return relationHeroTagRepository.save(relationHeroTag);
    }
}
