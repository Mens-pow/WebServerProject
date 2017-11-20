package servlets;

import Interfaces.DBService;
import dataSets.UsersDataSet;
import executor.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final DBService dbService;
    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dbService.printConnectInfo();

        try {
            long userId = dbService.addUser(req.getParameter("login"),
                                            req.getParameter("password"),
                                            Double.parseDouble(req.getParameter("latitude")),
                                            Double.parseDouble(req.getParameter("longitude"))
            );
            if (userId != 0) {
                System.out.println("Added user id: " + userId);

                UsersDataSet dataSet = dbService.getUser(userId);
                System.out.println("User data set: " + dataSet);

                resp.getWriter().println("User data set: " + dataSet.toString());
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else resp.getWriter().println("user exists");

        } catch (DBException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
