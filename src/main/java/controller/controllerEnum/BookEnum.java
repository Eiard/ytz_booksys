package controller.controllerEnum;


/**
 * Book Status Code
 * 通信状态码
 */
public enum BookEnum {


    /**
     * 改变书籍的状态
     * 书Id不存在
     */
    CHANGE_BOOK_STATUS_SUCCESS,
    CHANGE_BOOK_BKID_NOT_EXIST_ERROR,


    /**
     * 查询成功
     */
    QUERY_SUCCESS,

    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
}
