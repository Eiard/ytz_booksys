package dao.account;

import pojo.Account;

import java.util.List;

public interface AccountMapper {
    /**
     * Using :
     *     AccountMapper accountMapper = new AccountMapperImpl();
     *     accountMapper.(Method)
     */

    /**
     * Sign Up
     * User private interface
     * <p>
     * 注册一个用户
     *
     * @param account
     * @return int
     */
    int addAccount(Account account);

    /**
     * Log Out
     * Common interface
     * <p>
     * 删除一个用户
     *
     * @param identification
     * @return int
     */
    int deleteAccount(String identification);

    /**
     * Update User Information
     * Only password QQ  user can change
     * Common interface
     * <p>
     * 修改用户信息 QQ 密码
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
     * <p>
     * 查询所有读者信息
     *
     * @return List<Account>
     */
    List<Account> queryAllAccount();
}
