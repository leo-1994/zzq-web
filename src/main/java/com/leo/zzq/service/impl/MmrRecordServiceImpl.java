package com.leo.zzq.service.impl;

import com.leo.zzq.pojo.entity.MmrRecord;
import com.leo.zzq.repository.MmrRecordRepository;
import com.leo.zzq.service.MmrRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chao.li
 * @date 2019/2/13 10:14
 */
@Service
public class MmrRecordServiceImpl implements MmrRecordService {

    private final MmrRecordRepository mmrRecordRepository;

    @Autowired
    public MmrRecordServiceImpl(MmrRecordRepository mmrRecordRepository) {
        this.mmrRecordRepository = mmrRecordRepository;
    }

    @Override
    public void addRecord(String steamId, String response, Integer mmrLevel, Integer candy) {
        MmrRecord mmrRecord = MmrRecord.builder().steamId(steamId)
                .response(response).mmrLevel(mmrLevel).candy(candy).createTime(new Date()).build();
        mmrRecordRepository.save(mmrRecord);
    }
}
