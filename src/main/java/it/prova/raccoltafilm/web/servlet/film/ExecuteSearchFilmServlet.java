package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteSearchFilmServlet")
public class ExecuteSearchFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String durataParam = request.getParameter("minutiDurata");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String registaParam = request.getParameter("regista.id");

		Film example = UtilityForm.createFilmFromParams(titoloParam, genereParam, durataParam, dataPubblicazioneParam,
				registaParam);

		try {

			if (registaParam != null && !registaParam.isEmpty()) {
				example.setRegista(MyServiceFactory.getRegistaServiceInstance()
						.caricaSingoloElemento(Long.parseLong(registaParam)));
			}

			request.setAttribute("film_list_attribute",
					MyServiceFactory.getFilmServiceInstance().findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/film/list.jsp").forward(request, response);
	}

}
