import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class CatFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(CatFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.info(req.getHeader("User-Agent"));

        var cookie = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("authorized")).findFirst().orElse(null);

        if (cookie != null && cookie.getValue().equals("true")) {
            chain.doFilter(req, res);
        }
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
