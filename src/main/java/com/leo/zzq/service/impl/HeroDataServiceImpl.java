package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.Attribute;
import com.leo.zzq.pojo.entity.Hero;
import com.leo.zzq.pojo.entity.RelationHeroTag;
import com.leo.zzq.pojo.entity.Tag;
import com.leo.zzq.pojo.vo.HeroJson;
import com.leo.zzq.pojo.vo.HeroVO;
import com.leo.zzq.repository.AttributeRepository;
import com.leo.zzq.repository.HeroRepository;
import com.leo.zzq.service.HeroDataService;
import com.leo.zzq.service.RelationHeroTagDataService;
import com.leo.zzq.service.TagDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chao.li
 * @date 2019/1/17 16:45
 */
@Service
public class HeroDataServiceImpl implements HeroDataService {
    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private RelationHeroTagDataService relationHeroTagDataService;
    @Autowired
    private TagDataService tagDataService;
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public List<Hero> getAll() {
        return heroRepository.findAll();
    }

    @Override
    public ArrayList<HeroVO> getAllWithTags() {

        return heroRepository.findAll().stream().map(item -> {
            HeroVO heroVO = new HeroVO();
            BeanUtils.copyProperties(item, heroVO);
            List<Tag> tagList = tagDataService.getByHeroName(item.getName());
            heroVO.setTags(getTagNames(tagList));
            return heroVO;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<HeroJson> getAllForJson() {
        return heroRepository.findAll(new Sort(Sort.Direction.ASC,"level","id")).stream().map(item -> {
            HeroJson heroJson = new HeroJson();
            BeanUtils.copyProperties(item, heroJson);
            List<Tag> tagList = tagDataService.getByHeroName(item.getName());
            heroJson.setTags(getTagNameArray(tagList));
            Attribute attribute = attributeRepository.findById(item.getName()).orElse(new Attribute());
            heroJson.setAttribute(attribute);
            return heroJson;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private String[] getTagNameArray(List<Tag> tagList) {
        String[] result = new String[tagList.size()];
        for (int i = 0; i < tagList.size(); i++) {
            result[i] = tagList.get(i).getName();
        }
        return result;
    }

    private String getTagNames(List<Tag> tagList) {
        if (tagList.size() == 0) {
            return "";
        }
        StringBuilder tagNames = new StringBuilder(tagList.get(0).getName());
        if (tagList.size() > 1) {
            for (int i = 1; i < tagList.size(); i++) {
                tagNames.append(",").append(tagList.get(i).getName());
            }
        }
        return tagNames.toString();
    }

    @Override
    public List<Hero> getByTagName(String tagName) {
        List<RelationHeroTag> relationHeroTagList = relationHeroTagDataService.getByTagName(tagName);
        return relationHeroTagList.stream()
                .map(item -> heroRepository.findByName(item.getHName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Hero> getByHeroIds(String ids) {
        return heroRepository.findByIdIn(Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
    }

    @Override
    public Hero getByName(String name) {
        return heroRepository.findByName(name);
    }


}
