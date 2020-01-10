package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class rated {
    private String food_image_names; //图片的食物
    private String image_hash;//图片
    private String rating;//评分
    private String rated_at;//日期
    private String rating_text;//评论
    private String username;//用户名
    private String content;//回复信息
    private String created_at;//回复时间
    private String shopname;//店名
    private String shopid;//店id
    private String food_names;//食物
    private String insertdata;//插入时间

}
