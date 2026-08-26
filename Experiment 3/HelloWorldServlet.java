import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HelloWorldServlet - Google App Engine Hello World
 * Experiment 3: Cloud Computing Lab
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Hello World - GAE</title></head>");
        out.println("<body>");
        out.println("<h1>Hello, world</h1>");
        out.println("<p>Google App Engine Hello World Application</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
