package com.leo.zzq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leo.zzq.enums.ResultEnum;
import com.leo.zzq.exception.ServiceException;
import com.leo.zzq.pojo.dto.BuffContentTeamDto;
import com.leo.zzq.pojo.entity.BuffContent;
import com.leo.zzq.pojo.entity.Team;
import com.leo.zzq.repository.BuffContentRepository;
import com.leo.zzq.repository.TeamRepository;
import com.leo.zzq.service.TeamDataService;
import com.leo.zzq.util.StringUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


/**
 * @author chao.li
 * @date 2019-02-25 12:10
 */
@Service
public class TeamDataServiceImpl implements TeamDataService {

    @Autowired
    private BuffContentRepository buffContentRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public BuffContent saveBuffContent(String buffsData) {
        BuffContent buffContent = new BuffContent();
        buffContent.setJson(buffsData);
        buffContent.setCreateTime(new Date());
        return buffContentRepository.save(buffContent);
    }

    @Override
    public BuffContent getLastedBuffContent() {
        Page<BuffContent> page = buffContentRepository.findAll(PageRequest.of(0, 1, Sort.Direction.DESC, "id"));
        if (page.getContent().size() == 0) {
            return null;
        }
        return page.getContent().get(0);
    }

    @Override
    public List<BuffContentTeamDto> getBuffContentTeamListSortByWinRate(BuffContent buffContent) {
        JSONArray jsonArray = JSON.parseObject(buffContent.getJson()).getJSONArray("buffs");
        List<BuffContentTeamDto> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 平均排名
            Float avgRank = jsonObject.getFloat("avg_rank");
            // 阵容
            String buffKey = jsonObject.getString("buff_key");
            // 场次
            Long matchCount = jsonObject.getLong("match_count");
            // 选择率
            Float pickRate = jsonObject.getFloat("pick_rate");
            // 胜率
            Float winRate = jsonObject.getFloat("win_rate");
            BuffContentTeamDto dto = BuffContentTeamDto.builder().bId(buffContent.getId()).avgRank(avgRank).buffKey(buffKey).matchCount(matchCount).pickRate(pickRate).winRate(winRate).build();
            result.add(dto);
        }
        result.sort((a, b) -> b.getWinRate().compareTo(a.getWinRate()));
        return result;
    }

    @Override
    @Transactional
    public List<Team> generateTeamListSortByWinRate(BuffContent buffContent) {
        List<BuffContentTeamDto> dtoList = getBuffContentTeamListSortByWinRate(buffContent);
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
        }
        long bId = dtoList.get(0).getBId();
        List<Team> teamList = new ArrayList<>();
        for (BuffContentTeamDto dto : dtoList) {
            Team team = new Team();
            BeanUtils.copyProperties(dto, team);
            team.setBuffs(getBuff(dto.getBuffKey()));
            String heroName = getHeroNamesByBuffKey(team.getBuffKey());
            if (StringUtil.isEmpty(heroName)) {
                continue;
            }
            team.setHeroNames(heroName);
            team.setCreateTime(new Date());
            teamList.add(team);
            if (teamList.size() >= 20) {
                break;
            }
        }
        teamRepository.deleteAllByBId(bId);
        return teamRepository.saveAll(teamList);
    }

    @Override
    public List<Team> getTeamListByBId(long bId) {
        return teamRepository.findAllByBId(bId);
    }

    private String getBuff(String buffKey) {
        String[] buffArr = buffKey.split(",|\\|");
        StringBuilder buffBuilder = new StringBuilder();
        String pre = "";
        for (String buff : buffArr) {
            buffBuilder.append(pre).append(BUFF_MAP.get(buff));
            pre = ",";
        }
        return buffBuilder.toString();
    }

    private static final Map<String, String> BUFF_MAP = new HashMap<>();

    static {
        // 兽人 is_orc
        BUFF_MAP.put("is_orc", "2兽人");
        BUFF_MAP.put("is_orc1", "2兽人,4兽人");
        // 巨魔 is_troll
        BUFF_MAP.put("is_troll", "2巨魔");
        BUFF_MAP.put("is_troll1", "2巨魔,4巨魔");
        BUFF_MAP.put("is_troll11", "2巨魔,4巨魔,6巨魔");
        // 精灵 is_elf
        BUFF_MAP.put("is_elf", "3精灵");
        BUFF_MAP.put("is_elf1", "3精灵,6精灵");
        BUFF_MAP.put("is_elf11", "3精灵,6精灵，9精灵");
        // 地精 is_goblin
        BUFF_MAP.put("is_goblin", "3地精");
        BUFF_MAP.put("is_goblin1", "3地精,6地精");
        // 龙 is_dragon
        BUFF_MAP.put("is_dragon", "3龙");
        BUFF_MAP.put("is_dragon1", "3龙，5龙");
        // 亡灵 is_undead
        BUFF_MAP.put("is_undead", "2亡灵");
        BUFF_MAP.put("is_undead1", "2亡灵,4亡灵");
        BUFF_MAP.put("is_undead11", "2亡灵,4亡灵,6亡灵");
        // 野兽 is_beast
        BUFF_MAP.put("is_beast", "2野兽");
        BUFF_MAP.put("is_beast1", "2野兽,4野兽");
        BUFF_MAP.put("is_beast11", "2野兽,4野兽,6野兽");
        // 元素 is_element
        BUFF_MAP.put("is_element", "2元素");
        BUFF_MAP.put("is_element1", "2元素,4元素");
        // 恶魔 is_demon
        BUFF_MAP.put("is_demon", "1恶魔");
        // 娜迦 is_naga
        BUFF_MAP.put("is_naga", "2娜迦");
        BUFF_MAP.put("is_naga1", "2娜迦,4娜迦");
        // 人类 is_human
        BUFF_MAP.put("is_human", "2人类");
        BUFF_MAP.put("is_human1", "2人类,4人类");
        BUFF_MAP.put("is_human11", "2人类,4人类,6人类");
        // 食人魔 is_ogre
        BUFF_MAP.put("is_ogre", "1食人魔");
        // 矮人 is_dwarf
        BUFF_MAP.put("is_dwarf", "1矮人");
        // 战士 is_warrior
        BUFF_MAP.put("is_warrior", "3战士");
        BUFF_MAP.put("is_warrior1", "3战士,6战士");
        BUFF_MAP.put("is_warrior11", "3战士,6战士,9战士");
        // 德鲁伊 is_druid
        BUFF_MAP.put("is_druid", "2德鲁伊");
        BUFF_MAP.put("is_druid1", "2德鲁伊,4德鲁伊");
        // 法师 is_mage
        BUFF_MAP.put("is_mage", "3法师");
        BUFF_MAP.put("is_mage1", "3法师,6法师");
        BUFF_MAP.put("is_mage11", "3法师,6法师,9法师");
        // 猎人 is_hunter
        BUFF_MAP.put("is_hunter", "3猎人");
        BUFF_MAP.put("is_hunter1", "3猎人,6猎人");
        // 刺客 is_assassin
        BUFF_MAP.put("is_assassin", "3刺客");
        BUFF_MAP.put("is_assassin1", "3刺客,6刺客");
        // 工匠 is_mech
        BUFF_MAP.put("is_mech", "2工匠");
        BUFF_MAP.put("is_mech1", "2工匠,4工匠");
        BUFF_MAP.put("is_mech11", "2工匠,4工匠,6工匠");
        // 萨满 is_shaman
        BUFF_MAP.put("is_shaman", "2萨满");
        // 骑士 is_knight
        BUFF_MAP.put("is_knight", "2骑士");
        BUFF_MAP.put("is_knight1", "2骑士,4骑士");
        BUFF_MAP.put("is_knight11", "2骑士,4骑士,6骑士");
        // 恶魔猎手 is_demonhunter
        BUFF_MAP.put("is_demonhunter", "1恶魔猎手");
        BUFF_MAP.put("is_demonhunter1", "1恶魔猎手,2恶魔猎手");
        // 术士 is_warlock
        BUFF_MAP.put("is_warlock", "2术士");
        BUFF_MAP.put("is_warlock1", "2术士,4术士");
        BUFF_MAP.put("is_warlock11", "2术士,4术士,6术士");
        // 巫师 is_wizard
        BUFF_MAP.put("is_wizard", "2巫师");

    }

    private static String getHeroNamesByBuffKey(String buffKey) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 9; MI 8 Build/PKQ1.180729.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/72.0.3626.105 Mobile Safari/537.36");
        try {
            buffKey = URLEncoder.encode(buffKey, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse<String> stringHttpResponse = null;
        try {
            stringHttpResponse = Unirest.get("https://api.xiaoheihe.cn/game/dac/get_buff_detail/?buff_key=" + buffKey).headers(headers).asString();
        } catch (UnirestException e) {
            throw new ServiceException(ResultEnum.CONNECT_ERROR, "请求小黑盒获取阵容错误");
        }
        JSONObject response = JSONObject.parseObject(stringHttpResponse.getBody());
        JSONArray matches = response.getJSONObject("result").getJSONArray("matches");
        if (matches == null || matches.size() == 0) {
            return null;
        }
        JSONArray heros = matches.getJSONObject(0).getJSONArray("heros");
        String pre = "";
        for (int i = 0; i < heros.size(); i++) {
            JSONObject hero = heros.getJSONObject(i);
            sb.append(pre).append(hero.getString("name"));
            pre = ",";
        }
        return sb.toString().replaceAll("食人魔魔法师", "食人魔法师").replaceAll("天界战神", "玛尔斯");
    }

}
