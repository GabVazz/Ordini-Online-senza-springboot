package com.gab.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gab.businesscomponenet.utility.Carrello;
import com.gab.businesscomponent.facade.ClientFacade;
import com.gab.businesscomponent.model.Ordine;
import com.gab.businesscomponent.model.OrdineArticolo;

@WebServlet("/conferma")
public class Conferma extends HttpServlet {
	
	private static final long serialVersionUID = 4360277273826306686L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		Ordine ordine = new Ordine();
		
		ordine.setTotale(carrello.totaleComplessivo());
		ordine.setUsername((String) session.getAttribute("username"));
		
		try {
			ClientFacade.getIstance().create(ordine);
			Enumeration<String[]> prodotti = carrello.getProdotti();
			OrdineArticolo oa;
			while(prodotti.hasMoreElements()) {
				oa = new OrdineArticolo();
				String[] prodotto = prodotti.nextElement();
				oa.setIdOrdine(ordine.getIdOrdine());
				oa.setIdArticolo(Long.valueOf(prodotto[4]));
				oa.setQta(Integer.valueOf(prodotto[3]));
				ClientFacade.getIstance().create(oa);
			}
			session.setAttribute("idordine", ordine.getIdOrdine());
			response.sendRedirect("ordine.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}
}
