package com.gab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gab.businesscomponent.facade.AdminFacade;
import com.gab.businesscomponent.model.Articolo;

@WebServlet("/inserisciArticolo")
public class InserisciArticolo extends HttpServlet {
	
	private static final long serialVersionUID = 7107762545569718045L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Articolo art = getArticolo(request);
		if(art != null) {
			try {
				AdminFacade.getIstance().createOrUpdate(art);
			} catch(Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
		response.sendRedirect("admin/gestisciArticoli.jsp");
	}

	private Articolo getArticolo(HttpServletRequest request) {
		Articolo art = new Articolo();
		art.setIdArticolo(Long.valueOf(request.getParameter("id")));
		art.setMarca(request.getParameter("marca"));
		art.setModello(request.getParameter("modello"));
		art.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
		return art;
	}
}
