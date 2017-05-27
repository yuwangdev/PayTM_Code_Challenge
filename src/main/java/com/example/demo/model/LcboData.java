package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by yuwang on 5/24/17.
 */

@Data
@ToString(includeFieldNames = true)
public class LcboData {
    private String id;
    private String name;
    private String tags;
    private String price_in_cents;
    private String bonus_reward_miles;
    private String stock_type;
    private String primary_category;
    private String secondary_category;
    private String origin;
    private String producer_name;
    private String released_on;

    public LcboData() {
    }


}
