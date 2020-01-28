package com.leo.zzq.service;

/**
 * @author chao.li
 * @date 2019/2/13 10:13
 */
public interface MmrRecordService {

    void addRecord(String steamId, String response, Integer mmrLevel, Integer candy);
}
