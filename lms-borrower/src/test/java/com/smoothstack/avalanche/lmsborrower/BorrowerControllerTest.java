package com.smoothstack.avalanche.lmsborrower;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.smoothstack.avalanche.lms.borrowermsvc.entity.Book;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.BookLoans;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.BookLoansId;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.Borrower;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.Branch;
import com.smoothstack.avalanche.lms.borrowermsvc.entity.Publisher;

@SpringBootTest
public class BorrowerControllerTest {
	
	private static TestRestTemplate restTemplate = new TestRestTemplate();
	@Test
	public void testGetLoansByCardNoShouldReturn()
	{
		
		ResponseEntity<BookLoans[]> response = restTemplate.getForEntity("http://localhost:8083/lms/borrower/bookloans/1", BookLoans[].class);
		BookLoans[] loans = response.getBody();
		//check if there are 5 loans from card_no: 1
		assertTrue(loans.length == 5);
	}
	
	@Test
	public void testPostLoan()
	{
		//for try instance: a new composite primary key
		BookLoansId workingId = new BookLoansId(Long.valueOf(4),Long.valueOf(4),Long.valueOf(4));
		//for catch instance: an existing loan
		BookLoansId notWorkingId = new BookLoansId(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1));
		//Entity Must Match Book Loans Id
		Publisher testPublisher = new Publisher();
		Book testBook1 = new Book(Long.valueOf(4),"Hello World", testPublisher);
		Branch testBranch1 = new Branch(Long.valueOf(4), "Test Name", "Test Address");
		Borrower testBorrower1 = new Borrower(Long.valueOf(4), "Test Name", "Test Address", "Test Phone");
		
		Book testBook2 = new Book(Long.valueOf(1),"Hello World", testPublisher);
		Branch testBranch2 = new Branch(Long.valueOf(1), "Test Name", "Test Address");
		Borrower testBorrower2 = new Borrower(Long.valueOf(1), "Test Name", "Test Address", "Test Phone");
		
		BookLoans workingBookLoan = new BookLoans(workingId, new Date(),new Date(), new Date());
		workingBookLoan.setBook(testBook1);
		workingBookLoan.setBorrower(testBorrower1);
		workingBookLoan.setBranch(testBranch1);
		BookLoans notWorkingBookLoan = new BookLoans(notWorkingId, new Date(), new Date(), new Date());
		notWorkingBookLoan.setBook(testBook2);
		notWorkingBookLoan.setBorrower(testBorrower2);
		notWorkingBookLoan.setBranch(testBranch2);
		ResponseEntity<BookLoans> workingTest = restTemplate.postForEntity("http://localhost:8083/lms/borrower/bookloan", workingBookLoan, BookLoans.class);
		ResponseEntity<BookLoans> notWorkingTest = restTemplate.postForEntity("http://localhost:8083/lms/borrower/bookloan", notWorkingBookLoan, BookLoans.class);
		//Test that it was successfully created
		assertTrue(workingTest.getStatusCode().equals(HttpStatus.CREATED));
		//Test that it was successfully reject since id already existed
		assertTrue(notWorkingTest.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE));
	}
	
	@Test
	public void testPutLoans()
	{
		//for try instance
		BookLoansId workingId = new BookLoansId(Long.valueOf(1),Long.valueOf(1),Long.valueOf(1));
		//for catch instance
		BookLoansId notWorkingId = new BookLoansId(Long.valueOf(4), Long.valueOf(4), Long.valueOf(4));
		//Entity Must Match Book Loans Id
		//Entity for try test
		Publisher testPublisher = new Publisher();
		Book testBook1 = new Book(Long.valueOf(1),"Hello World", testPublisher);
		Branch testBranch1 = new Branch(Long.valueOf(1), "Test Name", "Test Address");
		Borrower testBorrower1 = new Borrower(Long.valueOf(1), "Test Name", "Test Address", "Test Phone");
		//Entity for catch test 
		Book testBook2 = new Book(Long.valueOf(4),"Hello World", testPublisher);
		Branch testBranch2 = new Branch(Long.valueOf(4), "Test Name", "Test Address");
		Borrower testBorrower2 = new Borrower(Long.valueOf(4), "Test Name", "Test Address", "Test Phone");
		
		BookLoans workingBookLoan = new BookLoans(workingId, new Date(),new Date(), new Date());
		workingBookLoan.setBook(testBook1);
		workingBookLoan.setBorrower(testBorrower1);
		workingBookLoan.setBranch(testBranch1);
		BookLoans notWorkingBookLoan = new BookLoans(notWorkingId, new Date(), new Date(), new Date());
		notWorkingBookLoan.setBook(testBook2);
		notWorkingBookLoan.setBorrower(testBorrower2);
		notWorkingBookLoan.setBranch(testBranch2);
		
		String url = "http://localhost:8083/lms/borrower/bookloans:bookloan";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BookLoans> entity = new HttpEntity<BookLoans>(workingBookLoan, headers);
		ResponseEntity<BookLoans> workingTest = restTemplate.exchange(url, HttpMethod.PUT, entity, BookLoans.class);
		
		assertTrue(workingTest.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
}
