package com.leo.zzq.repository;

import com.leo.zzq.pojo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 16:32
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByName(String name);

    List<Tag> findAllByType(Integer type);

}
