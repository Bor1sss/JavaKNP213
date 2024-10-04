package itstep.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.dal.dao.AuthDao;
import itstep.learning.filters.FileNameService;
import itstep.learning.kdf.KdfService;
import itstep.learning.services.db.DbService;
import itstep.learning.services.hash.HashService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class HomeServlet extends HttpServlet {
    private final AuthDao authDao; // інжекцію класів (не інтерфейсів) реєструвати не треба
    private final FileNameService fileNameService;


    @Inject
    public HomeServlet(AuthDao authDao, FileNameService fileNameService) {

        this.authDao = authDao;
        this.fileNameService = fileNameService;
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        boolean isSigned = false;
        Object signature = req.getAttribute("signature");
        if (signature instanceof Boolean) {
            isSigned = (Boolean) signature;
        }

        if (isSigned) {
            String dbMessage = authDao.install() ? "Install OK" : "Install failed";
            req.setAttribute("hash", dbMessage);
            req.setAttribute("body", "home.jsp");

            // Генерація випадкових імен файлів
            String randomFileNameDefault = fileNameService.generateRandomFileName();
            String randomFileNameWithLength = fileNameService.generateRandomFileName(12);
            req.setAttribute("randomFileNameDefault", randomFileNameDefault);
            req.setAttribute("randomFileNameWithLength", randomFileNameWithLength);

        } else {
            req.setAttribute("body", "not_found.jsp");  // Якщо підпис неправильний - insecure.jsp
        }

        // ~ return View();
        req.getRequestDispatcher( "WEB-INF/views/_layout.jsp" ).forward(req, resp);

        // resp.getWriter().println("<h1>Home</h1>");
    }
}

/*
Сервлети - спеціалізовані класи для мережних задач, зокрема,
HttpServlet - для веб-задач, є аналогом контролерів в ASP
 */