package com.example.demo.dao;

import com.example.demo.model.LcboData;
import com.google.common.collect.Lists;
import com.google.gson.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yuwang on 5/24/17.
 */
public class LcboDao {

    private static LcboDao intance;
    private final String address = "http://www.lcboapi.com/products?q=";
    private RestTemplate restTemplate;
    private Gson gson;
    private LcboDao() {
        this.restTemplate = new RestTemplate();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static LcboDao getInstance() {
        if (intance == null) {
            intance = new LcboDao();
        }
        return intance;
    }

    @Async
    @Cacheable(value = "searchResult", sync = true)
    private Future<String> getRawSearchResult(String key) {
        String response = this.restTemplate.getForObject(this.address + key, String.class);
        return new AsyncResult<String>(response);
    }

    public List<LcboData> getLcboDataResponse(String key) {
        List<LcboData> lcboDatas = Lists.newArrayList();
        try {
            JsonObject jsonObject = this.gson.fromJson(getRawSearchResult(key).get(), JsonObject.class);

            JsonElement jsonElement = jsonObject.get("result");
            // System.out.println(jsonElement);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
           /* System.out.println(jsonArray);
            System.out.println(jsonArray.size());*/

            jsonArray.forEach(k -> {
                JsonObject js = k.getAsJsonObject();
                lcboDatas.add(this.gson.fromJson(js, LcboData.class));
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return lcboDatas;

    }


}
