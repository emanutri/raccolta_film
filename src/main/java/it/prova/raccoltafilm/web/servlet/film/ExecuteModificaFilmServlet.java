package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.FilmService;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteModificaFilmServlet")
public class ExecuteModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteModificaFilmServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idFilm");
		String titoloParameter = request.getParameter("titolo");
		String genereParameter = request.getParameter("genere");
		String dataParameter = request.getParameter("dataPubblicazione");
		String durataParameter = request.getParameter("durata");
		String idRegistaParameter = request.getParameter("regista.id");

		Film filmInstance = UtilityForm.createFilmFromParams(titoloParameter, genereParameter, durataParameter,
				dataParameter, idRegistaParameter);
		filmInstance.setId(Long.parseLong(idParameter));

		FilmService filmService = MyServiceFactory.getFilmServiceInstance();

		try {

			if (!UtilityForm.validateFilmBean(filmInstance)) {

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.setAttribute("film_attribute", filmInstance);
				request.setAttribute("list_reg_attr", MyServiceFactory.getRegistaServiceInstance().listAllElements());
				request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
				return;
			}

			filmService.aggiorna(filmInstance);
			request.setAttribute("film_list_attribute", filmService.listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/film/list.jsp").forward(request, response);
	}

}
