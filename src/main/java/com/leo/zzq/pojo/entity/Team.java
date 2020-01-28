package com.leo.zzq.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author chao.li
 * @date 2019-02-25 13:05
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bId;
    private String heroNames;
    private String buffs;
    private String buffKey;
    private Float avgRank;
    private Long matchCount;
    private Float pickRate;
    private Float winRate;
    private Date createTime;
}
