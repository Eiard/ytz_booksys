package controller.controllerEnum;


/**
 * Account Status Code
 * 通信状态码
 */
public enum AccountEnum {
    /**
     * Root 登陆成功
     * User 登陆成功
     * 账号或密码错误
     */
    LOGIN_ROOT_SUCCESS,
    LOGIN_USER_SUCCESS,
    LOGIN_ACCOUNT_PASSWORD_ERROR,

    /**
     * 注销成功
     * 有未还的书籍 无法注销
     */
    LOGOFF_SUCCESS,
    LOGOFF_ERROR,
    LOGOFF_ID_NOT_EXIST_ERROR,

    /**
     * 注册成功
     * 用户名被注册过
     * 学号被注册过
     * 学号和姓名不匹配 (冒名注册)
     */
    SIGN_SUCCESS,
    SIGN_IDENTIFICATION_EXIST_ERROR,
    SIGN_RDID_EXIST_ERROR,
    SIGN_RDID_MATCH_RDNAME_ERROR,
    SIGN_RDID_BE_BAN_ERROR,

    /**
     * 更改读者信息 QQ 和密码
     *      修改信息成功
     *      修改信息失败
     *      Account JSON 格式有问题
     */
    CHANGE_INFORMATION_SUCCESS,
    CHANGE_INFORMATION_ERROR,
    CHANGE_ACCOUNT_JSON_TYPE_ERROR,
    CHANGE_ROOT_ERROR,



    /**
     * 未知错误
     */
    UNKNOWN_ERROR,
    STATUS_ERROR,
}
