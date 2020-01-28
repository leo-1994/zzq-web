package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.Attribute;
import com.leo.zzq.repository.AttributeRepository;
import com.leo.zzq.service.AttributeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author chao.li
 * @date 2019/1/26 16:11
 */
@Service
public class AttributeDataServiceImpl implements AttributeDataService {
    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public void addAttribute(Attribute attribute) {
        attributeRepository.save(attribute);
    }
}
