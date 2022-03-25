package service.readertype;

import dao.readertype.ReaderTypeMapper;
import dao.readertype.ReaderTypeMapperImpl;
import pojo.ReaderType;
import service.reader.ReaderService;
import service.reader.ReaderServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日19时42分
 */
public class ReaderTypeServiceImpl implements ReaderTypeService {
    /**
     * 相同类型: 调用dao层对象
     */
    private ReaderTypeMapper readerTypeMapper;

    public ReaderTypeServiceImpl() {
        readerTypeMapper = new ReaderTypeMapperImpl();
    }

    /**
     * 不同类型: 调用Service层对象
     */
    private ReaderService readerService = new ReaderServiceImpl();

    @Override
    public boolean addReaderType(ReaderType readerType) {
        if (rdTypeNameIsExist(readerType.getRdTypeName())) {
            return false;
        }
        return readerTypeMapper.addReaderType(readerType) == 1;
    }

    @Override
    public List<Boolean> addReaderTypes(List<ReaderType> readerTypes) {
        int Length = readerTypes.size();
        List<Boolean> booleans = new ArrayList<>(Length);
        Collections.fill(booleans, false);

        int index = 0;
        for (ReaderType readerType : readerTypes) {
            booleans.set(index, addReaderType(readerType));
            index++;
        }

        return booleans;
    }

    @Override
    public boolean deleteReaderType(ReaderType readerType) {
        if (readerService.queryReaderByRdType(readerType.getRdType()) == 0) {
            return readerTypeMapper.deleteReaderType(readerType.getRdType()) == 1;
        }
        return false;
    }


    @Override
    public List<Boolean> deleteReaderTypes(List<ReaderType> readerTypes) {
        int Length = readerTypes.size();
        List<Boolean> booleans = new ArrayList<>(Length);
        Collections.fill(booleans, false);

        int index = 0;
        for (ReaderType readerType : readerTypes) {
            booleans.set(index, deleteReaderType(readerType));
            index++;
        }

        return booleans;
    }

    @Override
    public boolean updateReaderType(ReaderType readerType) {
        if (rdTypeNameIsExist(readerType.getRdTypeName())) {
            return readerTypeMapper.updateReaderType(readerType) == 1;
        }

        return true;
    }

    @Override
    public List<Boolean> updateReaderTypes(List<ReaderType> readerTypes) {
        int Length = readerTypes.size();
        List<Boolean> booleans = new ArrayList<>(Length);
        Collections.fill(booleans, false);

        int index = 0;
        for (ReaderType readerType : readerTypes) {
            booleans.set(index, updateReaderType(readerType));
            index++;
        }

        return booleans;
    }

    @Override
    public List<ReaderType> queryAllReaderType() {
        return readerTypeMapper.queryAllReaderType();
    }

    @Override
    public boolean rdTypeNameIsExist(String rdTypeName) {
        List<ReaderType> readerTypes = queryAllReaderType();

        for (ReaderType readerType : readerTypes) {
            if (rdTypeName.equals(readerType.getRdTypeName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ReaderType> fuzzyQueryReaderType(String rdTypeName) {
        List<ReaderType> readerTypeList = new ArrayList<>();
        List<ReaderType> readerTypes = queryAllReaderType();
        for (ReaderType readerType : readerTypes) {
            if (readerType.getRdTypeName().contains(rdTypeName)) {
                readerTypeList.add(readerType);
            }
        }
        return readerTypeList;
    }

}
