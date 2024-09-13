package com.gab.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gab.businesscomponent.facade.ClientFacade;
import com.gab.businesscomponent.model.Utente;

@WebServlet("/registra")
public class Registra extends HttpServlet {
    private static final long serialVersionUID = 3812496773856700271L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Request received");
        System.out.println(request.getParameter("nome") + ", " + request.getParameter("cognome") + ", "
                + request.getParameter("indirizzo") + ", " + request.getParameter("cap") + ", "
                + request.getParameter("nascita") + ", " + request.getParameter("username") + ", "
                + request.getParameter("password") + ", " + request.getParameter("email"));

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Utente utente = new Utente();
        try {
            // Escape degli input che possono contenere dati pericolosi
            String nome = escapeHtml(request.getParameter("nome"));
            String cognome = escapeHtml(request.getParameter("cognome"));
            String indirizzo = escapeHtml(request.getParameter("indirizzo"));
            String cap = escapeHtml(request.getParameter("cap"));
            String username = escapeHtml(request.getParameter("username"));
            String password = escapeHtml(request.getParameter("password"));
            String email = escapeHtml(request.getParameter("email"));

            // Validazione dei campi
            validateField(nome, "nome", "Il campo nome non può essere vuoto e deve contenere solo lettere");
            validateField(cognome, "cognome", "Il campo cognome non può essere vuoto e deve contenere solo lettere");
            validateField(indirizzo, "indirizzo", "Il campo indirizzo non può essere vuoto e deve essere nel formato corretto");
            validateField(cap, "cap", "Il campo cap non può essere vuoto e deve contenere esattamente 5 cifre numeriche");
            validateField(username, "username", "Il campo username non può essere vuoto e deve contenere da 4 a 10 caratteri alfanumerici");
            validateField(password, "password", "Il campo password non può essere vuoto e deve rispettare i criteri di complessità");
            validateField(email, "email", "Il campo email non può essere vuoto e deve essere un indirizzo email valido");

            // Validazione della data di nascita
            String nascitaString = request.getParameter("nascita");
            java.util.Date nascita = validateDate(nascitaString, formato);

            // Popola l'oggetto Utente
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setIndirizzo(indirizzo);
            utente.setCap(cap);
            utente.setNascita(nascita);
            utente.setUsername(username);
            utente.setPassword(password);
            utente.setEmail(email);

            // Salvataggio dell'utente nel database
            ClientFacade.getIstance().create(utente);

            // Redirect alla pagina di login
            response.sendRedirect("login.jsp");
        } catch (Exception exc) {
            exc.printStackTrace();
            throw new ServletException(exc.getMessage());
        }
    }

    private String escapeHtml(String input) {
        if (input == null) {
            return null;
        }
        StringBuilder escaped = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
            case '<':
                escaped.append("&lt;");
                break;
            case '>':
                escaped.append("&gt;");
                break;
            case '&':
                escaped.append("&amp;");
                break;
            case '"':
                escaped.append("&quot;");
                break;
            case '\'':
                escaped.append("&#x27;");
                break;
            case '/':
                escaped.append("&#x2F;");
                break;
            default:
                escaped.append(c);
                break;
            }
        }
        return escaped.toString();
    }

    private void validateField(String field, String fieldName, String errorMessage) throws ServletException {
        if (field == null || field.trim().isEmpty()) {
            throw new ServletException("Il campo " + fieldName + " non può essere vuoto");
        }
        // Utilizza le espressioni regolari per validare il campo specifico
        switch (fieldName) {
        case "nome":
        case "cognome":
            if (!field.matches("^[a-zA-Z ,.'-]{2,30}$")) {
                throw new ServletException(errorMessage);
            }
            break;
        case "indirizzo":
            if (!field.matches("^[a-zA-Z ,.'-]{6,45}[0-9]{1,4}$")) {
                throw new ServletException(errorMessage);
            }
            break;
        case "cap":
            if (!field.matches("^[0-9]{5}$")) {
                throw new ServletException(errorMessage);
            }
            break;
        case "username":
            if (!field.matches("^[a-zA-Z0-9.-]{4,10}$")) {
                throw new ServletException(errorMessage);
            }
            break;
        case "password":
            if (!field.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#&%^$?=])[a-zA-Z0-9@#&%^$?=]{7,15}$")) {
                throw new ServletException(errorMessage);
            }
            break;
        case "email":
            if (!field.matches("^[\\w.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                throw new ServletException(errorMessage);
            }
            break;
        }
    }

    private java.util.Date validateDate(String dateString, SimpleDateFormat dateFormat) throws ServletException {
        if (dateString == null || dateString.isEmpty()) {
            throw new ServletException("La data di nascita non può essere vuota");
        }
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new ServletException(
                    "Formato della data di nascita non valido. Il formato corretto è dd/MM/yyyy.");
        }
    }
}
