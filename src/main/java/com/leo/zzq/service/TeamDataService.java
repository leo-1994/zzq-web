package com.leo.zzq.service;

import com.leo.zzq.pojo.dto.BuffContentTeamDto;
import com.leo.zzq.pojo.entity.BuffContent;
import com.leo.zzq.pojo.entity.Team;

import java.util.List;

/**
 * @author chao.li
 * @date 2019-02-25 12:08
 */
public interface TeamDataService {
    BuffContent saveBuffContent(String buffsData);

    BuffContent getLastedBuffContent();

    /**
     * 取得按胜率排序后的阵容列表
     *
     * @return
     */
    List<BuffContentTeamDto> getBuffContentTeamListSortByWinRate(BuffContent buffContent);

    /**
     * 生成胜率前20的阵容
     *
     * @return
     */
    List<Team> generateTeamListSortByWinRate(BuffContent buffContent);

    List<Team> getTeamListByBId(long bId);

}
