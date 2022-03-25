package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月01日23时37分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String identification;
    private String password;
    private byte root;
    private String QQ;
    private int rdId;
}
