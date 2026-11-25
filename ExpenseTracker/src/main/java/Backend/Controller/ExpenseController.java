package Backend.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Backend.Service.json;
import Backend.dao.ExpenseDao;
import Backend.model.ExpenseData;
import Backend.model.HistoryData;
import Backend.model.profile;

@WebServlet("/expense")
public class ExpenseController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ExpenseDao exdao = new ExpenseDao();
        System.out.print("sdsddddedddfddfddf"+action.equals("addexpense"));
        if (action.equals("addexpense")) {
            String title = request.getParameter("title");
            String amount = request.getParameter("amount");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String date = request.getParameter("date");
            int userId = (int) request.getSession().getAttribute("userId");
            ExpenseData data = new ExpenseData(amount, category, description, title, userId, date);
            boolean addExpense = exdao.AddExpense(data);
            response.setContentType("text/plain");
            PrintWriter pw = response.getWriter();
            if (addExpense) {
                pw.println("success");
            } else {
                pw.println("failed");
            }
        } else if (action.equals("viewhistory")) {
        	//System.out.print("hello");
            int userId = (int) request.getSession().getAttribute("userId");
            ArrayList<HistoryData> historyData = exdao.getHistory(userId);
            if (historyData != null) {
              
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                String data = json.HistoryToJson(historyData);
                out.print(data);
            }
            else {
            	 PrintWriter out = response.getWriter();
            	 out.print("failed");
            }
        } else if (action.equals("viewprofile")) {
        	int userId = (int) request.getSession().getAttribute("userId");
        	profile profile = exdao.getProfile(userId);
        	if(profile != null) {
        		response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                String data = json.profileToJson(profile);
                out.print(data);
        	}
        	else {
           	 PrintWriter out = response.getWriter();
           	 out.print("failed");
           }
        }
        else {
        	response.setContentType("text/plain");
			PrintWriter pw = response.getWriter();
			pw.write("who are you");
        }

    }

}
