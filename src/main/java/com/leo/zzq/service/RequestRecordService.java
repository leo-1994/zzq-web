package com.leo.zzq.service;

import com.leo.zzq.pojo.entity.RequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author chao.li
 * @date 2019/1/23 10:38
 */
public interface RequestRecordService {
    void addRequestRecord(String ip);

    Page<RequestRecord> getRequestRecord(PageRequest pageRequest);
}
