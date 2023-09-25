import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

public class LoginServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var body = req.getReader().lines().collect(Collectors.joining());
        var user = objectMapper.readValue(body, User.class);
        var users = UserRepository.getUsers();

        if (users.get(user.getUsername()).equals(user.getPassword())){
            resp.addCookie(new Cookie("authorized", "true"));
        }
    }
}
