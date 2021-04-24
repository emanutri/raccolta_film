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

@WebServlet("/PrepareModificaRegistaServlet")
public class PrepareModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PrepareModificaRegistaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroregistadaModificare = request.getParameter("idRegista");

		RegistaService registaservice = MyServiceFactory.getRegistaServiceInstance();
		Regista result = null;

		if (!NumberUtils.isCreatable(parametroregistadaModificare)) {

			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
			return;

		}
		RequestDispatcher rd = null;

		try {

			result = registaservice.caricaSingoloElemento(Long.parseLong(parametroregistadaModificare));
			request.setAttribute("regista_attribute", result);

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		rd = request.getRequestDispatcher("/regista/edit.jsp");
		rd.forward(request, response);
	}

}
