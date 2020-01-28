package com.leo.zzq.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author chao.li
 * @date 2019/1/17 16:05
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Tag implements Serializable {
    private static final long serialVersionUID = 8123223589323643931L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    /**
     * 1-种族，2-职业
     */
    private Integer type;

    @Getter
    public enum TypeEnum {

        /**
         * 种族
         */
        RACE(1),
        /**
         * 职业
         */
        CAREER(2),
        ;

        private int type;

        TypeEnum(int type) {
            this.type = type;
        }
    }
}
