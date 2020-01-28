package com.leo.zzq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leo.zzq.enums.ResultEnum;
import com.leo.zzq.exception.ServiceException;
import com.leo.zzq.service.DataService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * @author chao.li
 * @date 2019-03-01 11:16
 */
@Slf4j
@Service
public class DataServiceImpl implements DataService {

    @Override
    @CacheEvict("teamTop20")
    public void flushTop20Cache() {
    }

    @Override
    @CacheEvict("teamTop20WithTime")
    public void flushTop20WithTimeCache() {
    }

    /**
     * 获取小黑盒的阵容数据
     *
     * @return 小黑盒的阵容数据
     */
    @Override
    public String getXhhBuffsData() {
        try {
            HttpResponse<String> stringHttpResponse = Unirest.get("https://api.xiaoheihe.cn/game/dac/get_buff_list/").header("User-Agent", "Mozilla/5.0 (Linux; Android 9; MI 8 Build/PKQ1.180729.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/72.0.3626.105 Mobile Safari/537.36").asString();
            JSONObject response = JSON.parseObject(stringHttpResponse.getBody()).getJSONObject("result");
            JSONObject result = new JSONObject();
            result.put("buffs", response.getJSONArray("buffs"));
            return result.toString();
        } catch (UnirestException e) {
            e.printStackTrace();
            log.error("请求小黑盒阵容数据失败,error:{}", e.getMessage());
            throw new ServiceException(ResultEnum.CONNECT_ERROR, "请求小黑盒阵容数据失败");
        }
    }
}
