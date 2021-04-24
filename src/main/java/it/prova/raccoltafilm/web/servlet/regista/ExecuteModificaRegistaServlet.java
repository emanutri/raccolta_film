package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteModificaRegistaServlet")
public class ExecuteModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteModificaRegistaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParameter = request.getParameter("idRegista");
		String nomeParameter = request.getParameter("nome");
		String cognomeParameter = request.getParameter("cognome");
		String nickParameter = request.getParameter("nickName");
		String dataParameter = request.getParameter("dataDiNascita");
		String sessoParameter = request.getParameter("sesso");

		Regista registaInstance = UtilityForm.createRegistaFromParams(nomeParameter, cognomeParameter, nickParameter,
				dataParameter, sessoParameter);
		registaInstance.setId(Long.parseLong(idParameter));

		if (!UtilityForm.validateRegistaBean(registaInstance)) {

			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("regista_attribute", registaInstance);
			request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
			return;
		}

		try {

			MyServiceFactory.getRegistaServiceInstance().aggiorna(registaInstance);
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {

			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
	}

}
