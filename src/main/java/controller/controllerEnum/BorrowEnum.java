package controller.controllerEnum;

public enum BorrowEnum {
    /**
     * 借书成功
     * 传来的json格式解析出错
     */
    LEND_SUCCESS,
    LEND_FORMAT_ERROR,
    LEND_NONE_BOOK_ERROR,

    /**
     * 还书成功
     * 还书接收json格式错误
     */
    RETURN_SUCCESS,
    RETURN_FORMAT_ERROR,
    RETURN_NONE_BOOK_ERROR,

    /**
     * 查询成功
     * 没有借一本书
     */
    QUERY_SUCCESS,
    QUERY_NONE_BOOK_ERROR,
    QUERY_FORMAT_ERROR,

    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
}
