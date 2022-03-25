package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月01日23时30分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    private int rdId;
    private int rdType;
    private String rdName;
    private String rdDept;
    private int rdBorrowQty;
    private byte rdState;
}
