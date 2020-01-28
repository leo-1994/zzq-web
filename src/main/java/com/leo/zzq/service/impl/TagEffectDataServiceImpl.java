package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.Tag;
import com.leo.zzq.pojo.entity.TagEffect;
import com.leo.zzq.pojo.vo.TagEffectJson;
import com.leo.zzq.repository.TagEffectRepository;
import com.leo.zzq.repository.TagRepository;
import com.leo.zzq.service.TagEffectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/21 18:48
 */
@Service
public class TagEffectDataServiceImpl implements TagEffectDataService {
    private final TagEffectRepository tagEffectRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TagEffectDataServiceImpl(TagEffectRepository tagEffectRepository, TagRepository tagRepository) {
        this.tagEffectRepository = tagEffectRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public TagEffect addTagEffect(int tagId, int threshold, String name, String detail) {
        TagEffect tagEffect = new TagEffect();
        tagEffect.setTagId(tagId);
        tagEffect.setThreshold(threshold);
        tagEffect.setDetail(detail);
        tagEffect.setName(name);
        return tagEffectRepository.save(tagEffect);
    }

    @Cacheable("allTagEffect")
    @Override
    public List<TagEffect> getAll() {
        return tagEffectRepository.findAll();
    }

    @Override
    public ArrayList<TagEffectJson> getAllForJson() {
        ArrayList<TagEffectJson> result = new ArrayList<>();
        tagEffectRepository.findAll().forEach(e -> {
            Tag tag = tagRepository.findById(e.getTagId()).orElse(new Tag());
            result.add(TagEffectJson.builder().effectName(e.getName())
                    .tagName(tag.getName()).detail(e.getDetail())
                    .threshold(e.getThreshold()).build());
        });
        return result;
    }
}
