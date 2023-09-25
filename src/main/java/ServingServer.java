import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.ServiceConfigurationError;

import static jakarta.servlet.DispatcherType.REQUEST;

public class ServingServer {

    private final Server server = new Server(8181);

    private static final Logger logger = LoggerFactory.getLogger(CatFilter.class);

    public void start() throws Exception {
        var resource = Resource.newClassPathResource("/webapp");
        var handler = new WebAppContext(resource, "/");
        handler.addServlet(new ServletHolder(new ApiServlet()), "/api");
        handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
        handler.addFilter(new FilterHolder(new CatFilter()), "/api", EnumSet.of(REQUEST));
        logger.info("HEI");


        server.setHandler(handler);

        server.start();
    }
}
