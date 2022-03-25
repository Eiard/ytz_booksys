package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月01日23时34分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderType {
    private int rdType;
    private String rdTypeName;
    private int canLendQty;
    private int canLendDay;
}
