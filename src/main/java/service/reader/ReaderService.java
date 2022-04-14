package service.reader;

import pojo.Reader;

import java.util.List;

public interface ReaderService {
    /**
     * Using :
     *     ReaderService readerService = new ReaderServiceImpl();
     *     readerService.(Method)
     */

    /**
     * Add Reader
     * Root interface
     *
     * 添加一个读者
     *
     * @param reader
     * @return int
     */
     int addReader(Reader reader);

    /**
     * Add Readers
     * Root interface
     *
     * 添加多个读者
     *
     * @param readers
     * @return int
     */
    int addReaders(List<Reader> readers);

    /**
     * Update Reader Information Or Delete Reader
     * Root interface
     *
     * 更新一个读者信息
     *
     * @param reader
     * @return int
     */
    int updateReader(Reader reader);

    /**
     * Update Reader Information Or Delete Reader
     * Root interface
     *
     * 更新多个读者信息
     *
     * @param readers
     * @return int
     */
    int updateReaders(List<Reader> readers);

    /**
     * Query All Reader Information
     * Common interface
     *
     * 查询所有读者
     *
     * @return List<Reader>
     */
    List<Reader> queryAllReader();

    /**
     * Change User State  0(Active)  1(Ban)
     * Common interface
     *
     * 改变读者状态  启用0  未启用1
     *
     * @param reader
     * @return boolean
     */
    boolean changeReaderState(Reader reader);

    /**
     * Count The Num Of Reader Is ReaderType
     * Common interface
     *
     * 查询某个读者类别的读者人数
     *
     * @param rdType
     * @return int
     */
    int queryReaderByRdType(int rdType);

    /**
     * Fuzzy Query By rdName And rdType
     * Root interface
     *
     * 模糊查询  通过姓名 和 部门(院) 类别查询
     *
     * @param rdName
     * @param rdDept
     * @return List<Reader>
     */
    List<Reader> fuzzyQueryAllReader(String rdName,String rdDept);

    /**
     * Query By RdId And RdName
     * Common interface
     *
     * 用于查询 注册时 学号与读者姓名 是否匹配
     *
     * @param rdId
     * @param rdName
     * @return boolean
     */
    boolean queryReaderExist(int rdId,String rdName);

    /**
     * Query Reader By RdId
     * Common interface
     *
     * 通过学号 找到读者信息
     *
     * @param rdId
     * @return Reader
     */
    Reader queryOneReader(int rdId);
}
