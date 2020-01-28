package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.RelationHeroTag;
import com.leo.zzq.pojo.entity.Tag;
import com.leo.zzq.repository.RelationHeroTagRepository;
import com.leo.zzq.repository.TagRepository;
import com.leo.zzq.service.TagDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chao.li
 * @date 2019/1/17 17:55
 */
@Service
public class TagDataServiceImpl implements TagDataService {
    private final TagRepository tagRepository;
    private final RelationHeroTagRepository relationHeroTagRepository;

    @Autowired
    public TagDataServiceImpl(TagRepository tagRepository, RelationHeroTagRepository relationHeroTagRepository) {
        this.tagRepository = tagRepository;
        this.relationHeroTagRepository = relationHeroTagRepository;
    }

    @Override
    public ArrayList<Tag> getAll() {
        return new ArrayList<>(tagRepository.findAll());
    }

    @Override
    @Cacheable(value = "heroTags", key = "#name")
    public List<Tag> getByHeroName(String name) {
        List<RelationHeroTag> relationHeroTags = relationHeroTagRepository.findAllByHName(name);
        return relationHeroTags.stream().map(item -> tagRepository.findByName(item.getTName())).collect(Collectors.toList());
    }

    private final List<Tag> emptyList = new ArrayList<>();

    @Override
    @Cacheable(value = "heroesTags", key = "#heroNames")
    public List<Tag> getByHeroNames(String heroNames) {
        if (StringUtils.isEmpty(heroNames)) {
            return emptyList;
        }
        List<RelationHeroTag> relationHeroTags = relationHeroTagRepository.findByHNameIn(Arrays.stream(heroNames.split(",")).collect(Collectors.toList()));
        return relationHeroTags.stream().map(item -> tagRepository.findByName(item.getTName())).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getAllFroJson() {
        List<Tag> tags = tagRepository.findAll();
        ArrayList<String> result = new ArrayList<>();
        for (Tag tag : tags) {
            result.add(tag.getName());
        }
        return result;
    }

    @Override
    public List<String> getAllCareerForJson() {
        List<Tag> tags = tagRepository.findAllByType(Tag.TypeEnum.CAREER.getType());
        List<String> result = new ArrayList<>();
        for (Tag tag : tags) {
            result.add(tag.getName());
        }
        return result;
    }

    @Override
    public List<String> getAllRaceForJson() {
        List<Tag> tags = tagRepository.findAllByType(Tag.TypeEnum.RACE.getType());
        List<String> result = new ArrayList<>();
        for (Tag tag : tags) {
            result.add(tag.getName());
        }
        return result;
    }

}
