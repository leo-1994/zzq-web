package com.leo.zzq.service;

import com.leo.zzq.pojo.entity.TagEffect;
import com.leo.zzq.pojo.vo.TagEffectJson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/21 18:47
 */
public interface TagEffectDataService {
    TagEffect addTagEffect(int tagId, int threshold,String name, String detail);

    List<TagEffect> getAll();

    ArrayList<TagEffectJson> getAllForJson();
}
