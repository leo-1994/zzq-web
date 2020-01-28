package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.Skill;
import com.leo.zzq.repository.SkillRepository;
import com.leo.zzq.service.SkillDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chao.li
 * @date 2019/1/26 17:16
 */
@Service
public class SkillDataServiceImpl implements SkillDataService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void addSkill(Skill skill) {
        skillRepository.save(skill);
    }
}
