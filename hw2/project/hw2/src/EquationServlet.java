import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class EquationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject coefs = new Gson().fromJson(req.getReader(), JsonObject.class);
        Double a = coefs.get("a").getAsDouble();
        Double b = coefs.get("b").getAsDouble();
        Double c = coefs.get("c").getAsDouble();

        QuadricEquation eq = new QuadricEquation(a, b, c);
        String jsonRoots = eq.getRootsAsJson();

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(jsonRoots);
    }
}
