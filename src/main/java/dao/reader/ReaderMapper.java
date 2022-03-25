package dao.reader;

import pojo.Reader;

import java.util.List;

public interface ReaderMapper {
    /**
     * Using :
     *     ReaderMapper readerMapper = new ReaderMapperImpl();
     *     readerMapper.(Method)
     */

    /**
     * Add Reader
     * Root interface
     *
     * 管理员添加读者信息
     *
     * @param reader
     * @return int
     */
    int addReader(Reader reader);

    /**
     * Update Reader Information Or Delete Reader
     * Root interface
     *
     * 更改读者信息
     *
     * @param reader
     * @return int
     */
    int updateReader(Reader reader);

    /**
     * Query All Reader Information
     * Common interface
     *
     * 查询所有读者
     *
     * @return List<Reader>
     */
    List<Reader> queryAllReader();
}
