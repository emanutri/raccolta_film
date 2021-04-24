package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;

@WebServlet("/PrepareDeleteRegistaServlet")
public class PrepareDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareDeleteRegistaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idRegista");

		RegistaService registaInstance = MyServiceFactory.getRegistaServiceInstance();
		Regista result = null;

		if (!NumberUtils.isCreatable(idParameter)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
			return;
		}

		try {

			if (!registaInstance.caricaSingoloElementoConFilms(Long.parseLong(idParameter)).getFilms()
					.isEmpty()) {
				request.setAttribute("errorMessageEager",
						"Attenzione impossibile rimuovere regista, sono presenti film a suo nome.");
				
				request.setAttribute("registi_list_attribute",
						MyServiceFactory.getRegistaServiceInstance().listAllElements());
				
				request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
				return;
			}

			result = registaInstance.caricaSingoloElementoConFilms(Long.parseLong(idParameter));

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.setAttribute("regista_delete", result);

		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/regista/delete.jsp");
		rd.forward(request, response);
//		String idParameter = request.getParameter("idRegista");
//
//		try {
//
//			Regista regista = MyServiceFactory.getRegistaServiceInstance()
//					.caricaSingoloElemento(Long.parseLong(idParameter));
//			request.setAttribute("regista_delete", regista);
//			request.getRequestDispatcher("/regista/delete.jsp").forward(request, response);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
//			request.getRequestDispatcher("/regista/search.jsp").forward(request, response);
//			return;
//		}
	}

}
