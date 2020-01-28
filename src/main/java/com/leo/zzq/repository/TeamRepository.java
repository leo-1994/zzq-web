package com.leo.zzq.repository;

import com.leo.zzq.pojo.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chao.li
 * @date 2019-02-25 13:07
 */
public interface TeamRepository extends JpaRepository<Team,Long> {

    @Modifying
    @Query("delete from Team t where t.bId = ?1")
    void deleteAllByBId(long bId);

    List<Team> findAllByBId(long bId);
}
