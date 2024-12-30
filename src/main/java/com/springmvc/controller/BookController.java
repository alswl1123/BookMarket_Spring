package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; //필요1
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

@Controller //필요2
@RequestMapping("/books") //추가
public class BookController {

	@Autowired
	private BookService bookService;
	

	@RequestMapping("/all")
	public String requestAllBooks(Model model) {
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}
}
