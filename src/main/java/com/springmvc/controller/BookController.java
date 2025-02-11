package com.springmvc.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;
import com.springmvc.exception.CategoryException;
import com.springmvc.service.BookService;
import com.springmvc.validator.BookValidator;
import com.springmvc.validator.UnitsInStockValidator;


@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	//UnitsInStockValidator 클래스를 BookValidator 클래스로 수정
	private BookValidator bookValidator; 
	
	@GetMapping
	public String requestBookList(Model model) {
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		return "books";
	}

	@GetMapping("/all")
	public ModelAndView requestAllBooks() {
		ModelAndView modelAndView = new ModelAndView();
		List<Book> list = bookService.getAllBookList();
		modelAndView.addObject("bookList", list);
		modelAndView.setViewName("books");
		return modelAndView;
	}
	
	@GetMapping("/{category}") 
	public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) {
		List<Book> booksByCategory=bookService.getBookListByCategory(bookCategory);
		
		if(booksByCategory == null || booksByCategory.isEmpty()) {
			throw new CategoryException();
		}
		
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
	
	
	//순서1.파일이름 2.빈 파일 생성 3.파일 기록(transferTo)
	//유효성 검사 추가
	//커맨드 객체에 @Valid 가 선언되어 있는지, 오류 처리 내용이 작성되어 있는지 확인
	@PostMapping("/add")          //이 부분이랑
	public String submitAddNewBook(@Valid @ModelAttribute("NewBook") Book book, BindingResult result, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "addBook";
		}
		
		MultipartFile bookImage = book.getBookImage();
		String save = request.getServletContext().getRealPath("/resources/images");
		String saveName = bookImage.getOriginalFilename();
		File saveFile = new File(save, saveName);
		
		if (bookImage != null && !bookImage.isEmpty()) {
			try {
				bookImage.transferTo(saveFile);
				book.setFileName(saveName); //이 부분 추가됨
			} catch (Exception e) {
				throw new RuntimeException("도서 이미지 업로드가 실패하였습니다", e);
			}
		}
		
		bookService.setNewBook(book);
		return "redirect:/books";
	
		}
	
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
		binder.setValidator(bookValidator); //이 부분도 수정됨
		binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category", "unitsInStock", "totalPages", "releaseDate", "condition", "bookImage");	
		}
	
	@ExceptionHandler(value={BookIdException.class})
	public ModelAndView handleError(HttpServletRequest req, BookIdException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidBookId", exception.getBookId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("errorBook");
		return mav;
	}
}
