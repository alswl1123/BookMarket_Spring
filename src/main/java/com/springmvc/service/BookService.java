package com.springmvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

// import com.springmvc.controller.Set; //import 이거 아님!
import com.springmvc.domain.Book;

public interface BookService {
	List<Book> getAllBookList();
	List<Book> getBookListByCategory(String bookCategory);
	Set<Book> getBookListByFilter(Map<String, List<String>> bookFilter);
	Book getBookById(String bookId);
	void setNewBook(Book book);
	void setUpdateBook(Book book);
	void setDeleteBook(String bookId);
}

