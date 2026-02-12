package com.kce.book.servlets;


import java.io.IOException;

import com.kce.book.bean.BookBean;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet<RequestDispather> extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String operation=request.getParameter("operations");
    	if(operation.equals("AddBook")) {
    		String result=addBook(request);	
    	}
    	Object result = null;
		if(result.equals("SUCCESS")) {
    		response.sendRedirect("Menu.html");
    	}
    	else if(result.equals("INVALID")) {
    		response.sendRedirect("Invalid.html");
    	}
    	else if(result.equals("FAILURE")) {
    		response.sendRedirect("Failure.html");	
    	}
    	else if ( operation.equals("Search")) {
    		String isbn = request.getParameter("isbn");
    		String bookBean=viewBook (isbn);
    		if(bookBean==null) {
    			response.sendRedirect("Invalid.html");
    			
    		}
    		else {
    			HttpSession session = request.getSession();
    			session.setAttribute("book",bookBean);
    			RequestDispather rd = (RequestDispather) request.getRequestDispatcher("ViewServlet");
    			((RequestDispatcher) rd).forward(request,response);
    		}
    	}
	}
    
   public  String addBook(HttpServletRequest request )
    {
    	//get form data from request object ref
    	//create bookBean object and assign from data  

	
	   String isbn = request.getParameter("isbn");
	   String bookName = request.getParameter("bookName");
	   String bookType = request.getParameter("bookType");
	   String authorName = request.getParameter("authorName");
	   String cost = request.getParameter("cost");
	   
	   BookBean bookBean = new BookBean();
	   bookBean.setIsbn(isbn);
	   bookBean.setBookName(bookName);
	   bookBean.setBookType(bookType.charAt(0));
	   bookBean.setCost(Float.parseFloat(cost));
	   
	   String Result = new Administrator().AddBook();
	   return Result;
    }
    
    
   public String viewBook(String isbn) {
	   return new Administrator().AddBook();
   }
}
