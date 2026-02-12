package com.kce.book.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.kce.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session = request.getSession();
		BookBean BookBean = (BookBean)session.getAttribute("book");
		out.print("<html><body>");
		out.print("Book title:"+BookBean.getBookName());
		out.print("Author Name:" + BookBean.getAuthor().getAuthorName());
		out.print("Auhtor Contact:"+ BookBean.getAuthor().getContactNo());
		out.print("Book Price:" + BookBean.getCost());
		out.print("Book ISBN:" + BookBean.getIsbn());
		
		out.print("</body></html>");
	}

}
