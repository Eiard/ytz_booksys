package controller.controllerEnum;


/**
 * Book Status Code
 * 通信状态码
 */
public enum BookEnum {

    /**
     * 添加书籍成功
     * 添加的书籍有书名 作者 出版社 全相同
     */
    ADD_BOOK_SUCCESS,
    ADD_SAME_BOOK_ERROR,




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
    QUERY_NO_BOOK_ERROR,

    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
}
