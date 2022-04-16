package service.book;

import dao.book.BookMapper;
import dao.book.BookMapperImpl;
import pojo.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年03月02日15时23分
 */
public class BookServiceImpl implements BookService {

    /**
     * 调用dao层对象
     */
    private BookMapper bookMapper;

    public BookServiceImpl() {
        bookMapper = new BookMapperImpl();
    }

    @Override
    public int addBook(Book book) {
        List<Book> books = fuzzyQueryAllBook(book.getBkName(), book.getBkAuthor(), book.getBkPress());
        if (books.size() == 1) {
            return 2;
        }
        return bookMapper.addBook(book);
    }

    @Override
    public int addBooks(List<Book> Books) {
        if (Books == null) {
            return 0;
        }
        for (Book book : Books) {
            addBook(book);
        }
        return 1;
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public int updateBooks(List<Book> Books) {
        if (Books == null) {
            return 0;
        }
        for (Book book : Books) {
            updateBook(book);
        }
        return 1;
    }

    @Override
    public int changeBookStatus(int bkId) {
        Book book = queryOneBookByBkId(bkId);
        if (book == null) {
            return 2;
        }
        book.setBkState((byte) ((book.getBkState() + 1) % 2));
        return updateBook(book);
    }

    @Override
    public List<Book> queryAllBook() {
        return bookMapper.queryAllBook();
    }

    @Override
    public List<Book> fuzzyQueryAllBook(String bkName, String bkAuthor, String bkPress) {
        List<Book> bookList = new ArrayList<>();
        List<Book> books = queryAllBook();
        for (Book book : books) {
            if (book.getBkName().contains(bkName) &&
                    book.getBkAuthor().contains(bkAuthor) &&
                    book.getBkPress().contains(bkPress)) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    @Override
    public List<Book> fuzzyQueryAllBook(String bkInfo) {
        List<Book> bookList = new ArrayList<>();
        List<Book> books = queryAllBook();
        for (Book book : books) {
            if (book.getBkName().contains(bkInfo) ||
                    book.getBkAuthor().contains(bkInfo) ||
                    book.getBkPress().contains(bkInfo)) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    @Override
    public Book queryOneBookByBkId(int bkId) {
        List<Book> books = queryAllBook();
        for (Book book : books) {
            if (book.getBkId() == bkId)
                return book;
        }
        return null;
    }
}
