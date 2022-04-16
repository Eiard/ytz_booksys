package dao.account;

import Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Account;

import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日10时08分
 */
public class AccountMapperImpl implements AccountMapper {
    /**
     * jdbcTemplate object
     */
    private JdbcTemplate Sql;

    public AccountMapperImpl() {
        Sql = new JdbcTemplate(JdbcUtils.getDataSource());
    }

    @Override
    public int addAccount(Account account) {
        String sql = "INSERT INTO account " +
                "(identification,password,QQ,rdId)" +
                "VALUES (?,?,?,?)";

        return Sql.update(sql,
                account.getIdentification(),
                account.getPassword(),
                account.getQQ(),
                account.getRdId());
    }

    @Override
    public int deleteAccount(String identification) {
        String sql = "DELETE FROM account WHERE" +
                " identification = ?";

        return Sql.update(sql, identification);
    }

    @Override
    public int updateAccount(String password, String QQ, String identification) {
        String sql =
                "UPDATE account SET " +
                        "password = ?,QQ = ?" +
                        "WHERE identification = ?";

        return Sql.update(sql, password, QQ, identification);
    }

    @Override
    public List<Account> queryAllAccount() {
        String sql = "SELECT * FROM account";

        return Sql.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
    }


}
