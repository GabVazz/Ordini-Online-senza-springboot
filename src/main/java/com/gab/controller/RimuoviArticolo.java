package com.gab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gab.businesscomponent.facade.AdminFacade;
import com.gab.businesscomponent.model.Articolo;

@WebServlet("/rimuoviArticolo")
public class RimuoviArticolo extends HttpServlet {

	private static final long serialVersionUID = 2364270173039195692L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			if (id != null) {
				Articolo a = new Articolo();
				a.setIdArticolo(Long.parseLong(id));
				AdminFacade.getIstance().delete(a);
			}
			response.sendRedirect("admin/gestisciArticoli.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}
}
