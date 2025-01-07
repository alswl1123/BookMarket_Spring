package com.springmvc.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder; //이 import 필요
import org.springframework.web.bind.annotation.InitBinder; //이 import 필요 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; //추가

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepositoryImpl;
import com.springmvc.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}

	@GetMapping("/all") //수정
	public ModelAndView requestAllBooks() {
		ModelAndView modelAndView = new ModelAndView();
		List<Book> list = bookService.getAllBookList();
		modelAndView.addObject("bookList", list);
		modelAndView.setViewName("books");
		return modelAndView;
	}
	
	@GetMapping("/{category}") //추가
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		List<Book> booksByCategory=bookService.getBookListByCategory(bookCategory);
		model.addAttribute("bookList", booksByCategory);
		return "books";
	}
	
	@GetMapping("/filter/{bookFilter}")
	public String requestBooksByFilter(@MatrixVariable(pathVar="bookFilter") Map<String, List<String>> bookFilter, Model model) {
			Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
			model.addAttribute("bookList", booksByFilter);
			return "books";
	}
	
	@GetMapping("/book")
	public String requestBookById(@RequestParam("id") String bookId, Model model) {
		Book bookById = bookService.getBookById(bookId);
		model.addAttribute("book", bookById);
		return "book";
	}
	
	@GetMapping("/add")
	public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
		return "addBook";
	}
	
	
	//매개변수 수준
	@PostMapping("/add")
	public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
		bookService.setNewBook(book);
		return "redirect:books"; //url 에 ? 붙음(파라미터를 가져가서 쓰지는 않는다)
	}
	
	//메서드 수준
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("addTitle","신규 도서 등록"); //key, value 구조
	}
	//주로 컨트롤러에서 사용되며, 데이터 바인딩과 관련된 설정을 처리
	//HTTP 요청 파라미터를 객체에 자동으로 매핑할 때 발생할 수 있는 타입 불일치 문제를 해결
	//입력값 검증 로직을 일관되게 적용
	//보안상 중요한 필드의 바인딩을 제한
	//날짜, 숫자 등의 형식을 일관되게 처리
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category", "unitsInStock", "totalPages", "releaseDate", "condition");	
		}
}
