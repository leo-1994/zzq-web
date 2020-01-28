package com.leo.zzq.controller;

import com.alibaba.fastjson.JSON;
import com.leo.zzq.enums.ResultEnum;
import com.leo.zzq.exception.ServiceException;
import com.leo.zzq.pojo.entity.BuffContent;
import com.leo.zzq.pojo.entity.Team;
import com.leo.zzq.pojo.vo.ResultVO;
import com.leo.zzq.service.*;
import com.leo.zzq.util.DateUtil;
import com.leo.zzq.util.DateformatEnum;
import com.leo.zzq.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/18 11:36
 */
@Slf4j
@RequestMapping("/data")
@RestController
public class DataController {

    private final DataService dataService;

    private final HeroDataService heroDataService;

    private final TagDataService tagDataService;

    private final TagEffectDataService tagEffectDataService;

    private final TeamDataService teamDataService;

    @Autowired
    public DataController(HeroDataService heroDataService, TagDataService tagDataService, TagEffectDataService tagEffectDataService, TeamDataService teamDataService, DataService dataService) {
        this.heroDataService = heroDataService;
        this.tagDataService = tagDataService;
        this.tagEffectDataService = tagEffectDataService;
        this.teamDataService = teamDataService;
        this.dataService = dataService;
    }

    @Cacheable("allDataForJson")
    @GetMapping("/allDataForJson")
    public ResultVO allDataForJson() {
        HashMap<String, Object> map = new HashMap<>(4);
        map.put("hero", heroDataService.getAllForJson());
        map.put("tag", tagDataService.getAllFroJson());
        map.put("tagEffect", tagEffectDataService.getAllForJson());
        map.put("career", tagDataService.getAllCareerForJson());
        map.put("race", tagDataService.getAllRaceForJson());
        return ResultUtil.success(map);
    }

    @CacheEvict("allDataForJson")
    @GetMapping("/flushAllDataCache")
    public ResultVO flushAllDataCache() {
        return ResultUtil.success();
    }

    @Cacheable("allHero")
    @GetMapping("/allHero")
    public ResultVO allHero() {
        return ResultUtil.success(heroDataService.getAllWithTags());
    }


    @Cacheable("allTag")
    @GetMapping("/allTag")
    public ResultVO allTag() {
        return ResultUtil.success(tagDataService.getAll());
    }

    /**
     * 获取top20阵容
     *
     * @return ResultVO
     */
    @Cacheable("teamTop20")
    @GetMapping("/getTop20Team")
    public ResultVO getTop20Team() {
        BuffContent buffContent = teamDataService.getLastedBuffContent();
        List<Team> teamList = teamDataService.getTeamListByBId(buffContent.getId());
        return ResultUtil.success(new ArrayList<>(teamList));
    }

    /**
     * 获取top20阵容（带日期）
     *
     * @return ResultVO
     */
    @Cacheable("teamTop20WithTime")
    @GetMapping("/getTop20WithTime")
    public ResultVO getTop20WithTime() {
        BuffContent buffContent = teamDataService.getLastedBuffContent();
        HashMap<String, Object> map = new HashMap<>(2);
        List<Team> teamList = teamDataService.getTeamListByBId(buffContent.getId());
        map.put("teamList", teamList);
        map.put("createTime", DateUtil.formatDateToString(buffContent.getCreateTime(), DateformatEnum.yyyyMMddHHmmChiness));
        return ResultUtil.success(map);
    }

    /**
     * 根据数据生成top20阵容
     *
     * @param buffsData 小黑盒buffs数据
     * @return ResultVO
     */
    @PostMapping("/generateTop20Team")
    public ResultVO generateTop20Team(String buffsData) {
        if (!JSON.isValid(buffsData)) {
            throw new ServiceException(ResultEnum.PARAM_ERROR, "buffsData 参数错误，必须是正确的json字符串");
        }
        BuffContent buffContent = teamDataService.saveBuffContent(buffsData);
        teamDataService.generateTeamListSortByWinRate(buffContent);
        return ResultUtil.success();
    }

    /**
     * 清空top20阵容缓存
     *
     * @return ResultVO
     */
    @GetMapping("/flushTop20Cache")
    public ResultVO flushTop20Cache() {
        dataService.flushTop20Cache();
        dataService.flushTop20WithTimeCache();
        return ResultUtil.success();
    }

    /**
     * 自动生成top20数据,每天0点5分自动执行
     */
    @Scheduled(cron = "0 5 0,12 * * ?")
    @GetMapping("/autoGenerateTop20Team")
    public void autoGenerateTop20Team() {
        log.info("【生成阵容胜率top20数据】开始");
        BuffContent buffContent = teamDataService.saveBuffContent(dataService.getXhhBuffsData());
        teamDataService.generateTeamListSortByWinRate(buffContent);
        dataService.flushTop20Cache();
        dataService.flushTop20WithTimeCache();
        log.info("【生成阵容胜率top20数据】完成");
    }


}
