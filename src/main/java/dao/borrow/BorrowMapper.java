package dao.borrow;

import pojo.Borrow;

import java.util.List;

public interface BorrowMapper {
    /**
     * Using :
     *     BorrowMapper borrowMapper = new BorrowMapperImpl();
     *     borrowMapper.(Method)
     */

    /**
     * Add Borrow Information (Lend Book)
     * User interface
     * 借书同时添加借书记录
     * 利用触发器实现书-- 和 已借书籍++
     *
     * @param borrow
     * @return int
     */
    int addBorrow(Borrow borrow);

    /**
     * Update Book Information (Return Book)
     * User interface
     * 还书并且更新借阅信息
     * 利用触发器实现书++ 和 已借书籍--
     *
     * @param borrow
     * @return int
     */
    int updateBorrow(Borrow borrow);

    /**
     * Query All Borrow Information
     * Common interface
     * 查询所有借阅信息
     *
     * @return List<Borrow>
     */
    List<Borrow> queryAllBorrow();
}
