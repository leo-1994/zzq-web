package com.leo.zzq.service;

/**
 * @author chao.li
 * @date 2019-03-01 11:16
 */
public interface DataService {
    void flushTop20Cache();

    void flushTop20WithTimeCache();

    String getXhhBuffsData();
}
