package com.leo.zzq.repository;

import com.leo.zzq.pojo.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 16:14
 */
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    List<Hero> findByIdIn(List<Integer> ids);

    Hero findByName(String name);
}
