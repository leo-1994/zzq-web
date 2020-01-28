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
import java.util.Date;

/**
 * @author chao.li
 * @date 2019/2/13 10:09
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class MmrRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String steamId;
    private String response;
    private Integer mmrLevel;
    private Integer candy;
    private Date createTime;

}
