package com.leo.zzq.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chao.li
 * @date 2019-02-25 12:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BuffContentTeamDto {
    private Long bId;
    private Float avgRank;
    private String buffKey;
    private Long matchCount;
    private Float pickRate;
    private Float winRate;
}
