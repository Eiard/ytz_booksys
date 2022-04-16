package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年02月26日23时01分
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bkId;
    private String bkName;
    private String bkAuthor;
    private String bkPress;
    private double bkPrice;
    private int bkNum;
    private byte bkState;
    private String bkImageUrl;
}
