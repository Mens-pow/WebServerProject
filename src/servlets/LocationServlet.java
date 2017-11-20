package servlets;

import Interfaces.DBService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import executor.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocationServlet extends HttpServlet {
    private final DBService dbService;

    public LocationServlet(DBService dbService) { this.dbService = dbService; }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            if (dbService.haveToken(Integer.parseInt(req.getParameter("token")))) {
                resp.getWriter().println(
                        " Location: " +
                        dbService.getLocation(dbService.getLoginByToken(Integer.parseInt(req.getParameter("token")))));
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
            else{
                resp.getWriter().println("Unauthorized");
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (DBException e) {
            resp.getWriter().println("DBEXCEPTIION");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
