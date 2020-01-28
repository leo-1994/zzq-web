package com.leo.zzq.repository;

import com.leo.zzq.pojo.entity.RelationHeroTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/17 16:33
 */
public interface RelationHeroTagRepository extends JpaRepository<RelationHeroTag, Integer> {

    List<RelationHeroTag> findAllByHName(String hName);

    List<RelationHeroTag> findByHNameIn(List<String> hNames);

    List<RelationHeroTag> findAllByTName(String hName);
}
