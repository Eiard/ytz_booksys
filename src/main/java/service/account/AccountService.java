package service.account;

import pojo.Account;

import java.util.List;


public interface AccountService {
    /**
     * Using :
     *     AccountService accountService = new AccountServiceImpl();
     *     accountService.(Method)
     */

    /**
     * Sign Up
     * User private interface
     * 注册一个用户
     * 注册成功 返回1
     * 注册失败 返回0
     *
     * @param account
     * @return int
     */
    int addAccount(Account account);

    /**
     * Log Out
     * Common interface
     * 注销图书馆读者账号
     * 删除成功 返回1
     * 删除失败 返回0
     *
     * @param identification
     * @return int
     */
    int deleteAccount(String identification);

    /**
     * Update User Information
     * Only password QQ  user can change
     * Common interface
     * 读者修改自己 图书馆账号的信息
     * 管理员也可以修改读者图书馆账号的信息
     * 更新成功 返回1
     * 更新失败 返回0
     *
     * @param password
     * @param QQ
     * @param identification
     * @return int
     */
    int updateAccount(String password, String QQ, String identification);


    /**
     * Query All Account
     * Common interface
     * 管理员用来查询所有读者信息
     *
     * @return List<Account>
     */
    List<Account> queryAllAccount();

    /**
     * Identification is Already Exist ?
     * 用户名是否已被注册
     * 用户名
     * true(存在)         为已被注册
     * false(不存在)       则可以注册
     *
     * @param identification
     * @return boolean
     */
    boolean identificationIsExist(String identification);

    /**
     * rdId is Already Exist ?
     * Avoid Sign Up Again
     * Common interface
     * 判断 学号是否存在 只有学号存在的人才能注册一个图书管理系统账号
     * 学号
     * true(存在)         为已被注册
     * false(不存在)       则可以注册
     *
     * @param rdId
     * @return boolean
     */
    boolean accountIsExist(int rdId);

    /**
     * Identification And Password Is Right?
     * Common interface
     * 登录 用户名和密码 验证
     * 并且获取其权限值
     * -1 不存在
     * 0为 User
     * 1为 Root
     *
     * @param identification
     * @param password
     * @return int
     */
    int identificationAndPassword(String identification, String password);

    /**
     * 通过用户名找到其学号
     * Common interface
     *
     * @param identification
     * @return Account
     */
    Account queryOneAccount(String identification);
}
