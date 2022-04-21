package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月01日23时15分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private int rdId;
    private int bkId;
    private String dateBorrow;
    private String dateLendPlan;
    private String dateLendAct;
    private byte overdue;
    private byte isReturn;
}
