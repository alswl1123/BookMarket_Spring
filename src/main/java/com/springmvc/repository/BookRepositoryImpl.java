package com.springmvc.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//필요한 import 요소들
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;

@Repository //어노테이션 필수
public class BookRepositoryImpl implements BookRepository {
	
	private JdbcTemplate template;
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	private List<Book> listOfBooks = new ArrayList<Book>();
	
	public BookRepositoryImpl() {
		Book book1 = new Book("ISBN1234", "C# 교과서", 30000);
		book1.setAuthor("박용준");
		book1.setDescription("C# 교과서는 생애 첫 프로그래밍 언어로 C#을 시작하는 독자를 대상으로 한다. 특히 응용 프로그래머를 위한 C# 입문서로, C#을 사용하여 게임(유니티), 웹, 모바일, IoT 등을 개발할 때 필요한 C# 기초 문법을 익히고 기본기를 탄탄하게 다지는 것이 목적이다.");
		book1.setPublisher("길벗");
		book1.setCategory("IT전문서");
		book1.setUnitsInStock(1000);
		book1.setReleaseDate("2020/05/29");
		
		Book book2 = new Book("ISBN1235", "Node.js 교과서", 36000);
		book2.setAuthor("조현영");
		book2.setDescription("이 책은 프런트부터 서버, 데이터베이스, 배포까지 아우르는 광범위한 내용을 다룬다. 군더더기 없는 직관적인 설명으로 기본 개념을 확실히 이해하고, 노드의 기능과 생태계를 사용해 보면서 실제로 동작하는 서버를 만들어보자. 예제와 코드는 최신 문법을 사용했고 실무에 참고하거나 당장 적용할 수 있다.");
		book2.setPublisher("길벗");
		book2.setCategory("IT전문서");
		book2.setUnitsInStock(1000);
		book2.setReleaseDate("2020/07/25");
		
		Book book3 = new Book("ISBN1236", "어도비 XD CC 2020", 25000);
		book3.setAuthor("김두한");
		book3.setDescription("어도비 XD 프로그램을 통해 UI/UX 디자인을 배우고자 하는 예비 디자이너의 눈높이에 맞게 기본적인 도구를 활용한 아이콘 디자인과 웹&앱 페이지 디자인, UI 디자인, 앱 디자인에 애니메이션과 인터랙션을 적용한 프로토타이핑을 학습합니다.");
		book3.setPublisher("길벗");
		book3.setCategory("IT활용서");
		book3.setUnitsInStock(1000);
		book3.setReleaseDate("2019/05/29");
		
		listOfBooks.add(book1);
		listOfBooks.add(book2);
		listOfBooks.add(book3);
	}

	@Override
	public List<Book> getAllBookList() {
		//DB의 book 테이블에 등록된 모든 전체 도서 목록 조회
		String SQL = "SELECT * From book"; //SQL문을 간단히 작성
		List<Book> listOfBooks = template.query(SQL, new BookRowMapper()); //query() 메서드 안 쓰면 queryForList() 메서드 써도 됨. 그러나 RowMapper 대신 book.set 형태로 작성
		return listOfBooks;
	}
	
	public List<Book> getBookListByCategory(String category) { //도서 분류와 일치하는 도서 목록 반환
		List<Book> booksByCategory = new ArrayList<Book>();
		//for(int i = 0; i<listOfBooks.size(); i++) {
		//	Book book = listOfBooks.get(i);
		//	if(category.equalsIgnoreCase(book.getCategory()))
		//		booksByCategory.add(book);
		//}
		
		String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
		booksByCategory = template.query(SQL, new BookRowMapper());
		return booksByCategory;
	}

	@Override
	public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
		Set<Book> booksByPublisher = new HashSet<Book>();
		Set<Book> booksByCategory = new HashSet<Book>();
		Set<String> criterias = filter.keySet();
		
		//Set<String> booksByFilter = filter.keySet();
		
		//if(booksByFilter.contains("publisher")) {
		if(criterias.contains("publisher")) {
			for(int j = 0; j<filter.get("publisher").size(); j++) {
				String publisherName = filter.get("publisher").get(j);
				//for(int i = 0; i<listOfBooks.size(); i++) {
				//	Book book = listOfBooks.get(i);
					
				//	if(publisherName.equalsIgnoreCase(book.getPublisher()))
				//		booksByPublisher.add(book);
				//}
				
				String SQL = "SELECT * FROM book where b_publisher LIKE '%" + publisherName + "%'";
				booksByPublisher.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		
		//if(booksByFilter.contains("category")) {
		if(criterias.contains("category")) {
			for(int i=0; i<filter.get("category").size(); i++) {
				String category = filter.get("category").get(i);
				//List<Book> list = getBookListByCategory(category);
				//booksByCategory.addAll(list);
				String SQL = "SELECT * FROM book where b_category LIKE '%" + category + "%'";
				booksByCategory.addAll(template.query(SQL, new BookRowMapper()));
			}
		}
		
		booksByCategory.retainAll(booksByPublisher);
		return booksByCategory;
	}

	@Override
	public Book getBookById(String bookId) { //도서 아이디와 일치하는 도서 반환
		Book bookInfo = null;
		//for(int i = 0; i<listOfBooks.size(); i++) {
		//	Book book = listOfBooks.get(i);
		//	if(book!=null && book.getBookId()!=null && book.getBookId().equals(bookId)) {
		//		bookInfo = book;
		//		break;
		//	}
		//} 이 부분을
		
		String SQL = "SELECT count(*) FROM book where b_bookId=?"; //레코드 개수 가져옴. 도서 ID가 등록될 때만 데이터베이스에 접근해 해당 도서 조회
		int rowCount = template.queryForObject(SQL, Integer.class, bookId);
		if(rowCount != 0) {
			SQL = "SELECT * FROM book where b_bookId=?";
			bookInfo = template.queryForObject(SQL, new Object[] {bookId}, new BookRowMapper()); //도서ID의 도서는 1개만 있으므로 queryForObject() 메서드 사용
		} //이렇게 변경
		
		//if(bookInfo == null)
		//	throw new IllegalArgumentException("도서 ID가 " + bookId + "인 해당 도서를 찾을 수 없습니다.");
		//	return bookInfo; 이 부분을
		
		if(bookInfo == null)
			throw new BookIdException(bookId); //import 필요함
		return bookInfo; //이렇게 변경
	}
	
	public void setNewBook(Book book) {
		listOfBooks.add(book);
	}
	
	
}
