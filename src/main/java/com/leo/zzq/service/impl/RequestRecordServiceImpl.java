package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.RequestRecord;
import com.leo.zzq.repository.RequestRecordRepository;
import com.leo.zzq.service.RequestRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author chao.li
 * @date 2019/1/23 10:39
 */
@Service
public class RequestRecordServiceImpl implements RequestRecordService {
    @Autowired
    private RequestRecordRepository requestRecordRepository;

    @Override
    public void addRequestRecord(String ip) {
        if (StringUtils.isEmpty(ip)) {
            return;
        }
        requestRecordRepository.save(new RequestRecord(ip, new Date()));
    }

    @Override
    public Page<RequestRecord> getRequestRecord(PageRequest pageRequest) {
        return requestRecordRepository.findAll(pageRequest);
    }
}
