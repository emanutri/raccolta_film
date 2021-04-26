package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.FilmService;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareDeleteFilmServlet")
public class PrepareDeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteFilmServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filmId = request.getParameter("idFilm");

		FilmService filmService = MyServiceFactory.getFilmServiceInstance();
		Film result = null;

		if (!NumberUtils.isCreatable(filmId)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/list.jsp").forward(request, response);
			return;
		}

		RequestDispatcher rd = null;

		try {

			result = filmService.caricaSingoloElementoEager(Long.parseLong(filmId));
			request.setAttribute("film_delete", result);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/film/delete.jsp");
		rd.forward(request, response);
	}

}
