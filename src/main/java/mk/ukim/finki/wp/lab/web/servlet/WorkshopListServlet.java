package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Workshop;
import mk.ukim.finki.wp.lab.service.WorkshopService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
@AllArgsConstructor
public class WorkshopListServlet extends HttpServlet {
    private final WorkshopService workshopService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String text = req.getParameter("text");
        String maxPrice = req.getParameter("maxPrice");

        if (text != null && text.isEmpty()) {
            text = null;
        }

        if (maxPrice != null && maxPrice.isEmpty()) {
            maxPrice = null;
        }

        List<Workshop> workshops;

        if (text != null || maxPrice != null) {
            Double maxPrice2 = null;

            if (maxPrice != null) {
                maxPrice2 = Double.parseDouble(maxPrice);
            }

            workshops = workshopService.search(text, maxPrice2);
        } else {
            workshops = workshopService.listAll();
        }

        IWebExchange webExchange =
                JakartaServletWebApplication
                        .buildApplication(getServletContext())
                        .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("workshops", workshops);
        context.setVariable("errorMessage", req.getParameter("errorMessage"));

        springTemplateEngine.process(
                "listWorkshops.html",
                context,
                resp.getWriter()
        );
    }
}
