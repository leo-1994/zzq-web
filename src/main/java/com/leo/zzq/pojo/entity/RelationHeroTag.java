package com.leo.zzq.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author chao.li
 * @date 2019/1/17 16:09
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class RelationHeroTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hName;
    private String tName;
}
