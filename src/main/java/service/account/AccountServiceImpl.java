package service.account;

import controller.controllerEnum.AccountEnum;
import dao.account.AccountMapper;
import dao.account.AccountMapperImpl;
import pojo.Account;

import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日17时04分
 */
public class AccountServiceImpl implements AccountService {

    /**
     * 调用dao层对象
     */
    private AccountMapper accountMapper;

    public AccountServiceImpl() {
        accountMapper = new AccountMapperImpl();
    }

    @Override
    public int addAccount(Account account) {
        return accountMapper.addAccount(account);
    }

    @Override
    public int deleteAccount(String identification) {
        return accountMapper.deleteAccount(identification);
    }

    @Override
    public int updateAccount(String password, String QQ, String identification) {
        return accountMapper.updateAccount(password, QQ, identification);
    }

    @Override
    public List<Account>queryAllAccount () {
        return accountMapper.queryAllAccount();
    }


    /**
     * --------------------------------------------------------------
     * @param identification
     * @return
     */

    @Override
    public boolean identificationIsExist(String identification) {
        List<Account> accounts = queryAllAccount();
        for (Account account : accounts) {
            if (account.getIdentification().equals(identification)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean accountIsExist(int rdId) {
        List<Account> accounts = queryAllAccount();
        for (Account account : accounts) {
            if (account.getRdId() == rdId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int identificationAndPassword(String identification, String password) {
        List<Account> accountList = queryAllAccount();
        for (Account account : accountList) {
            if (identification.equals(account.getIdentification()) && password.equals(account.getPassword())) {
                return account.getRoot();
            }
        }
        return -1;
    }

    @Override
    public Account queryOneAccount(String identification) {
        List<Account> accountList = queryAllAccount();
        for (Account account : accountList) {
            if (identification.equals(account.getIdentification())) {
                return account;
            }
        }
        return null;
    }

    public AccountEnum login(String identification, String password) {

        AccountService accountService = new AccountServiceImpl();


        int status = accountService.identificationAndPassword(identification, password);

        /**
         *   账号或密码错误   LOGIN_ACCOUNT_PASSWORD_ERROR
         *   USER登录成功    LOGIN_USER_SUCCESS
         *   ROOT登录成功    LOGIN_ROOT_SUCCESS
         *   未知错误        UNKNOWN_ERROR
         */
        if (status == -1) {
            return AccountEnum.LOGIN_ACCOUNT_PASSWORD_ERROR;
        } else if (status == 0) {
            return AccountEnum.LOGIN_USER_SUCCESS;
        } else if (status == 1) {
            return AccountEnum.LOGIN_ROOT_SUCCESS;
        }
        return AccountEnum.UNKNOWN_ERROR;
    }
}
