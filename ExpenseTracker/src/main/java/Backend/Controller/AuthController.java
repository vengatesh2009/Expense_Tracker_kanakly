package Backend.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import Backend.dao.UserDao;
import Backend.model.User;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

    private static final long serialVersionUID = 1L;

//    public AuthController() {
//        super();
//      
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      

		String action = request.getParameter("action");
        UserDao dao = new UserDao();

        if ("signup".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phoneNumber");
            String password = request.getParameter("password");
            
            User user = new User( name,email, password, phone);

            User signup = dao.signUp(user);
            response.setContentType("text/plain");
            PrintWriter pw = response.getWriter();

            if (signup != null && signup.isFound()) {
            	
                pw.println("success");
                HttpSession session = request.getSession();
                session.setAttribute("userId", signup.getUserId());

            }
            else {
                pw.println("failed");
            }
        }   
        else if ("signin".equals(action)) {
        	String email = request.getParameter("email");
			String password = request.getParameter("password");

			User signin = dao.signIn(email, password);

			response.setContentType("text/plain");
			PrintWriter pw = response.getWriter();
			
			if (signin != null && signin.isFound()) {
				 HttpSession session = request.getSession();
				    session.setAttribute("userId", signin.getUserId());

			    pw.println("success");
			}
			else {
			    pw.println("failed");
			}
        }
		else{
			response.setContentType("text/plain");
			PrintWriter pw = response.getWriter();
			pw.write("who are you");
		}
    }
}
