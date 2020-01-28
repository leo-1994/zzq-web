package com.leo.zzq.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author chao.li
 * @date 2019/1/26 16:02
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 英雄名
     */
    private String heroName;
    /**
     * 技能名
     */
    private String name;
    /**
     * 技能详情
     */
    private String detail;
}
