package com.leo.zzq.controller;

import com.leo.zzq.pojo.entity.Tag;
import com.leo.zzq.pojo.entity.TagEffect;
import com.leo.zzq.pojo.vo.ResultVO;
import com.leo.zzq.service.TagDataService;
import com.leo.zzq.service.TagEffectDataService;
import com.leo.zzq.util.ListUtil;
import com.leo.zzq.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chao.li
 * @date 2019-03-01 13:38
 */
@Slf4j
@RequestMapping("/data")
@RestController
public class EffectResultController {

    private final TagDataService tagDataService;

    private final TagEffectDataService tagEffectDataService;

    @Autowired
    public EffectResultController(TagDataService tagDataService, TagEffectDataService tagEffectDataService) {
        this.tagDataService = tagDataService;
        this.tagEffectDataService = tagEffectDataService;
    }

    @Cacheable(value = "effectResult", key = "#heroNames")
    @GetMapping("/effectResult")
    public ResultVO effectResult(String heroNames) {
        List<Integer> tagIdList = tagDataService.getByHeroNames(heroNames).stream().map(Tag::getId).collect(Collectors.toList());
        List<TagEffect> effectList = tagEffectDataService.getAll();
        ArrayList<TagEffect> result = new ArrayList<>();
        boolean hasWushi = hasWushi(tagIdList);
        effectList.forEach(effect -> {
            // 如果有双巫师，那么4人口以上的羁绊门槛-1
            int threshold = effect.getThreshold();
            if (threshold >= 4 && hasWushi) {
                threshold--;
            }
            if (ListUtil.count(tagIdList, effect.getTagId()) >= threshold) {
                result.add(effect);
            }
        });

        return ResultUtil.success(checkDemon(result));
    }

    private final static int WUSHI_TAG_ID = 30;

    /**
     * 判断是否含有双巫师
     *
     * @param tagIdList
     * @return
     */
    private boolean hasWushi(List<Integer> tagIdList) {
        return ListUtil.count(tagIdList, WUSHI_TAG_ID) >= 2;
    }

    /**
     * 判断恶魔buff是否成立
     *
     * @param list
     * @return
     */
    private ArrayList<TagEffect> checkDemon(ArrayList<TagEffect> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        // 是否有双恶魔猎手
        boolean hasLs2 = false;
        // 是否有单恶魔
        boolean hasEm = false;
        int emIndex = -1;
        // 是否有双恶魔
        boolean hasEm2 = false;
        int em2Index = -1;
        for (int i = 0; i < list.size(); i++) {
            TagEffect tagEffect = list.get(i);
            if ("恶魔猎手(2)".equals(tagEffect.getName())) {
                hasLs2 = true;
            } else if ("恶魔(1)".equals(tagEffect.getName())) {
                hasEm = true;
                emIndex = i;
            } else if ("恶魔(2)".equals(tagEffect.getName())) {
                hasEm2 = true;
                em2Index = i;
            }
        }
        if (hasEm2) {
            list.remove(em2Index);
            if (!hasLs2 && hasEm) {
                list.remove(emIndex);
            }
        }
        return list;
    }

    /**
     * 判断神族buff是否成立
     *
     * @param list
     * @return
     */
    private ArrayList<TagEffect> checkGod(ArrayList<TagEffect> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        // 是否有非神族的种族buff
        boolean hasRace = false;
        boolean hasGod1 = false;
        boolean hasGod2 = false;
        boolean hasGod3 = false;
        int godIndex1 = -1;
        int godIndex2 = -1;
        int godIndex3 = -1;
        for (int i = 0; i < list.size(); i++) {
            TagEffect tagEffect = list.get(i);
            if (!hasRace) {
                hasRace = hasRace(tagEffect.getName());
            } else if ("神族(1)".equals(tagEffect.getName())) {
                hasGod1 = true;
                godIndex1 = i;
            } else if ("神族(2)".equals(tagEffect.getName())) {
                hasGod2 = true;
                godIndex2 = i;
            } else if ("神族(3)".equals(tagEffect.getName())) {
                hasGod3 = true;
                godIndex3 = i;
            }
        }
        if (hasRace) {
            if (hasGod3) {
                list.remove(godIndex3);
            }
            if (hasGod2) {
                list.remove(godIndex2);
            }
            if (hasGod1) {
                list.remove(godIndex1);
            }
        }
        return list;
    }

    private static final String[] RACES = {"兽人", "野兽", "食人魔", "亡灵", "地精", "巨魔", "精灵", "人类", "恶魔(1)", "龙", "元素", "矮人", "娜迦", "萨特"};

    private static boolean hasRace(String tagName) {
        for (String race : RACES) {
            if (tagName.startsWith(race)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasRace("兽人(2)"));
    }

}
