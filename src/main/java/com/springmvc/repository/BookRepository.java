package com.springmvc.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.springmvc.domain.Book;

public interface BookRepository {
	List<Book> getAllBookList(); //List 가 ArrayList 의 부모 클래스
	List<Book> getBookListByCategory(String category);
	Set<Book> getBookListByFilter(Map<String, List<String>> filter);
}
