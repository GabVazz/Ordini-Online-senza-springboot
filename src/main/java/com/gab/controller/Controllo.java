package com.gab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gab.businesscomponenet.utility.Login;
import com.gab.businesscomponent.security.Algoritmo;

@WebServlet("/controllo")
public class Controllo extends HttpServlet {
	private static final long serialVersionUID = 8688487394251396292L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = Algoritmo.converti(request.getParameter("password"));
	    
	    HttpSession sessione = request.getSession();
	    
	    if (username != null && password != null) {
	        try {
	            Login l = new Login();
	            String userpass = l.getUserPass(username);
	            String adminpass = l.getAdminPass(username);
	            
	            if (userpass != null && userpass.equals(password)) {
	                sessione.setAttribute("username", username);
	                response.sendRedirect("acquisti.jsp");
	            } else if (adminpass != null && adminpass.equals(password)) {
	                sessione.setAttribute("admin", username);
	                response.sendRedirect("admin/admin.jsp");
	            } else {
	                response.sendRedirect("login.jsp");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new ServletException();
	        }
	    } else {
	        response.sendRedirect("login.jsp");
	    }
	}
}
