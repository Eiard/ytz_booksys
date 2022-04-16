package dao.readertype;

import pojo.ReaderType;

import java.util.List;

public interface ReaderTypeMapper {
    /**
     * Using :
     *     ReaderTypeMapper readerTypeMapper = new ReaderTypeMapperImpl();
     *     readerTypeMapper.(Method)
     */

    /**
     * Add ReaderType
     * Root interface
     * <p>
     * 添加读者类别
     *
     * @param readerType
     * @return int
     */
    int addReaderType(ReaderType readerType);

    /**
     * Delete ReaderType
     * Root interface
     * <p>
     * 删除读者类别
     *
     * @param rdType
     * @return int
     */
    int deleteReaderType(int rdType);

    /**
     * Update ReaderType Information
     * Root interface
     * <p>
     * 更改读者类别信息
     *
     * @param readerType
     * @return int
     */
    int updateReaderType(ReaderType readerType);

    /**
     * Query All ReaderType Information
     * Root interface
     * <p>
     * 管理员查询所有读者类别信息
     *
     * @return List<ReaderType>
     */
    List<ReaderType> queryAllReaderType();
}
