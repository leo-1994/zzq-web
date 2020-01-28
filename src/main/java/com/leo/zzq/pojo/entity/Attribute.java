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
import java.io.Serializable;

/**
 * @author chao.li
 * @date 2019/1/26 15:59
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class Attribute implements Serializable {

    private static final long serialVersionUID = 8005289196554560344L;
    /**
     * 英雄名
     */
    @Id
    private String heroName;
    /**
     * 攻击速度
     */
    private String attackSpeed;
    /**
     * 攻击力
     */
    private String attack;
    /**
     * 攻击距离
     */
    private String attackDistance;
    /**
     * 护甲
     */
    private String armor;
    /**
     * 血量
     */
    private String hp;
}
