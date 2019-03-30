package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/access")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addToUser = req.getParameter("add");
        String deleteFromUser = req.getParameter("delete");
        if(!addToUser.isEmpty()){
            Sql add = new Sql();
            try {
                add.addAccess(addToUser);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(!deleteFromUser.isEmpty()){
            Sql delete = new Sql();
            try {
                delete.deleteAccess(deleteFromUser);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/access.jsp");
    }


}
