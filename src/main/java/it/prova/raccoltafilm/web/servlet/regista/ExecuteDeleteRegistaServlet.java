package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteRegistaServlet")
public class ExecuteDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteDeleteRegistaServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRegistaParam = request.getParameter("idDeleteInput");

		if (!NumberUtils.isCreatable(idRegistaParam)) {
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("regista/search.jsp").forward(request, response);
			return;
		}

		try {

			Regista regista = MyServiceFactory.getRegistaServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idRegistaParam));
			MyServiceFactory.getRegistaServiceInstance().rimuovi(regista);
			request.setAttribute("listaRegistiAttribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione eseguita con successo!");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("regista/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("ExecuteListRegistaServlet").forward(request, response);
	}

}
