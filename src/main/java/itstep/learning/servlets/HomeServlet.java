package itstep.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
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
    private final HashService hashService;
    private final KdfService kdfService;
    private final DbService dbService;



    @Inject
    public HomeServlet(HashService hashService, KdfService kdfService, DbService dbService) {
        this.hashService = hashService;
        this.kdfService = kdfService;
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isSigned = false;
        Object signature = req.getAttribute("signature");
        if (signature instanceof Boolean) {
            isSigned = (Boolean) signature;
        }

        if (isSigned) {
            String dbMessage;
            try {
                dbService.getConnection();
                dbMessage = "Connection OK";
            } catch (SQLException ex) {
                dbMessage = ex.getMessage();
            }
            req.setAttribute("hash", hashService.hash("123") + " | " + kdfService.dk("password", "salt.4") + " | " + dbMessage);
            req.setAttribute("body", "home.jsp");
        } else {
            req.setAttribute("body", "insecure.jsp");  // Якщо підпис неправильний - insecure.jsp
        }

        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}

/*
Сервлети - спеціалізовані класи для мережних задач, зокрема,
HttpServlet - для веб-задач, є аналогом контролерів в ASP
 */