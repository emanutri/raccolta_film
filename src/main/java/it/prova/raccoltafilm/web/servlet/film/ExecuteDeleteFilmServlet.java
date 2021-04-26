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

@WebServlet("/ExecuteDeleteFilmServlet")
public class ExecuteDeleteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteFilmServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idFilmParameter = request.getParameter("idDeleteInput");

		FilmService filmService = MyServiceFactory.getFilmServiceInstance();

		Film filmInstance = new Film();
		if (!NumberUtils.isCreatable(idFilmParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/delete.jsp").forward(request, response);
			return;
		}

		try {

			filmInstance = filmService.caricaSingoloElementoEager(Long.parseLong(idFilmParameter));
			filmService.rimuovi(filmInstance);
			request.setAttribute("film_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");
		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;

		}

		RequestDispatcher rd = request.getRequestDispatcher("/film/list.jsp");
		rd.forward(request, response);
	}

}
