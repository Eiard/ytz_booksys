import Utils.JdbcUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Account;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月14日12时59分
 */
public class ad12 {

    @Test
    public void asd12() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());
        System.out.println(jdbcTemplate.query("SELECT * FROM account", new BeanPropertyRowMapper<Account>(Account.class)));
    }

}
