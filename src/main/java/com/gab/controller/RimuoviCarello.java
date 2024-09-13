package com.gab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gab.businesscomponenet.utility.Carrello;

@WebServlet("/rimuoviCarrello")
public class RimuoviCarello extends HttpServlet {
	
	private static final long serialVersionUID = -6862445666330240750L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		String id = request.getParameter("id");
		try {
			if(id != null) {
				carrello.rimuoviArticolo(id);
			}
			response.sendRedirect("carrello.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}
}
