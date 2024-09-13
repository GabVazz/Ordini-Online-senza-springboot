package com.gab.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gab.businesscomponenet.utility.Carrello;
import com.gab.businesscomponent.facade.ClientFacade;
import com.gab.businesscomponent.model.Articolo;

@WebServlet("/aggiungiCarrello")
public class AggiungiCarrello extends HttpServlet {

	private static final long serialVersionUID = 5288324665745526133L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");

		long id = Long.valueOf(request.getParameter("id"));

		Articolo articolo;
		try {
			articolo = ClientFacade.getIstance().findArticoloById(id);
			if (id != 0) {
				carrello.aggiungiArticolo(Long.toString(id), articolo.getMarca(), articolo.getModello(),
						articolo.getPrezzo());
			}
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
