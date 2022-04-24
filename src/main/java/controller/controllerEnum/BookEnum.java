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
    ADD_BOOK_IMAGE_ERROR,

    /**
     * 修改图书信息
     */
    CHANGE_BOOK_SUCCESS,
    CHANGE_BOOK_IMAGE_ERROR,


    /**
     * 查询成功
     */
    QUERY_SUCCESS,
    QUERY_NONE_BOOK_ERROR,

    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
}
