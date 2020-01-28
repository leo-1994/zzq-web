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
 * @date 2019/1/21 17:54
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class TagEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tagId;
    private Integer threshold;
    private String name;
    private String detail;
}
