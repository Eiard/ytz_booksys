package service.readertype;

import pojo.ReaderType;

import java.util.List;

public interface ReaderTypeService {
    /**
     * Using :
     *     ReaderTypeService readerTypeService = new ReaderTypeServiceImpl();
     *     readerTypeService.(Method)
     */

    /**
     * Add ReaderType
     * Root interface
     * 添加一个读者类别
     *
     * @param readerType
     * @return boolean
     */
    boolean addReaderType(ReaderType readerType);

    /**
     * Add ReaderTypes
     * Root interface
     * 添加多个读者类别
     *
     * @param readerTypes
     * @return List<Boolean>
     */
    List<Boolean> addReaderTypes(List<ReaderType> readerTypes);

    /**
     * Delete ReaderType
     * Root interface
     * 删除一个读者类别
     *
     * @param readerType
     * @return boolean
     */
    boolean deleteReaderType(ReaderType readerType);

    /**
     * Delete ReaderTypes
     * Root interface
     * 删除一个读者类别
     *
     * @param readerTypes
     * @return List<Boolean>
     */
    List<Boolean> deleteReaderTypes(List<ReaderType> readerTypes);

    /**
     * Update ReaderType
     * Root interface
     * 删除多个读者类别
     *
     * @param readerType
     * @return boolean
     */
    boolean updateReaderType(ReaderType readerType);

    /**
     * Update ReaderType
     * Root interface
     * 删除多个读者类别
     *
     * @param readerTypes
     * @return List<Boolean>
     */
    List<Boolean> updateReaderTypes(List<ReaderType> readerTypes);

    /**
     * Query All ReaderType Information
     * Common interface
     * 查询所有读者类别
     *
     * @return List<ReaderType>
     */
    List<ReaderType> queryAllReaderType();

    /**
     * Type Name Is Exist ?
     * Common interface
     *
     * @param rdTypeName
     * @return boolean
     */
    boolean rdTypeNameIsExist(String rdTypeName);

    /**
     * Fuzzy Query
     * Root interface
     * 通过读者类型名模糊查询
     *
     * @param rdTypeName
     * @return List<ReaderType>
     */
    List<ReaderType> fuzzyQueryReaderType(String rdTypeName);


    /**
     * Query One ReaderType
     * Common interface
     *
     * @param rdType
     * @return ReaderType
     */
    ReaderType queryOneReaderType(int rdType);


}
