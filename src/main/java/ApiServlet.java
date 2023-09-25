
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.Collectors;

public class ApiServlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(ApiServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameterMap().forEach((key, value) -> System.out.println(key + " " + Arrays.toString(value)));
        logger.info("Hey, I got a request");
        CatRepo.getCats().forEach(cat -> {
            try{
                resp.getWriter().write(cat);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //var body = req.getReader().lines().collect(Collectors.joining());

        var payload = req.getReader().lines().collect(Collectors.joining());
        var cat = new ObjectMapper().readValue(payload, Cat.class);
        CatRepo.addCat(cat);
    }
}


