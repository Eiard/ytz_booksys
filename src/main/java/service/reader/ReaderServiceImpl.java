package service.reader;

import dao.reader.ReaderMapper;
import dao.reader.ReaderMapperImpl;
import pojo.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日18时55分
 */
public class ReaderServiceImpl implements ReaderService {

    /**
     * 调用dao层对象
     */
    private ReaderMapper readerMapper;

    public ReaderServiceImpl() {
        readerMapper = new ReaderMapperImpl();
    }

    @Override
    public int addReader(Reader reader) {
        return readerMapper.addReader(reader);
    }

    @Override
    public int addReaders(List<Reader> readers) {
        if (readers == null) {
            return 0;
        }
        for (Reader reader : readers) {
            addReader(reader);
        }
        return 1;
    }

    @Override
    public int updateReader(Reader reader) {
        return readerMapper.updateReader(reader);
    }

    @Override
    public int updateReaders(List<Reader> readers) {
        if (readers == null) {
            return 0;
        }
        for (Reader reader : readers) {
            updateReader(reader);
        }
        return 1;
    }

    @Override
    public List<Reader> queryAllReader() {
        return readerMapper.queryAllReader();
    }

    @Override
    public boolean changeReaderState(Reader reader) {
        reader.setRdState((byte) ((reader.getRdState() + 1) % 2));

        readerMapper.updateReader(reader);
        return true;
    }

    @Override
    public int queryReaderByRdType(int rdType) {
        int Count = 0;
        List<Reader> readers = readerMapper.queryAllReader();
        for (Reader reader : readers) {
            if (reader.getRdType() == rdType) {
                Count++;
            }
        }
        return Count;
    }

    @Override
    public List<Reader> fuzzyQueryAllReader(String rdName, String rdDept) {
        List<Reader> readerList = new ArrayList<>();
        List<Reader> readers = queryAllReader();
        for (Reader reader : readers) {
            if (reader.getRdName().contains(rdName) &&
                    reader.getRdDept().contains(rdDept)) {
                readerList.add(reader);
            }
        }
        return readerList;
    }

    @Override
    public boolean queryReaderExist(int rdId, String rdName) {
        List<Reader> readers = queryAllReader();
        for (Reader reader : readers) {
            if (rdId == reader.getRdId() && rdName.equals(reader.getRdName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Reader queryOneReader(int rdId) {
        List<Reader> readers = readerMapper.queryAllReader();
        for (Reader reader : readers) {
            if (reader.getRdId() == rdId) {
                return reader;
            }
        }
        return null;
    }
}
