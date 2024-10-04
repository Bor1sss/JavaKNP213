package itstep.learning.filters;

import com.google.inject.Singleton;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.security.SecureRandom;

@Singleton// Фільтр буде застосовуватись до всіх запитів
public class FileNameService implements Filter {
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int DEFAULT_LENGTH = 8; // приблизно 64 біти ентропії
    private SecureRandom random;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.random = new SecureRandom();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Генерація випадкових імен файлів
        String randomFileNameDefault = generateRandomFileName(DEFAULT_LENGTH);
        String randomFileNameWithLength = generateRandomFileName(12);

        // Додаємо ці значення в атрибути запиту
        servletRequest.setAttribute("randomFileNameDefault", randomFileNameDefault);
        servletRequest.setAttribute("randomFileNameWithLength", randomFileNameWithLength);

        // Продовжуємо ланцюг обробки запиту
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public String generateRandomFileName() {
        return generateRandomFileName(DEFAULT_LENGTH);
    }

    public String generateRandomFileName(int length) {
        if (length <= 0) {
            length = DEFAULT_LENGTH;
        }

        StringBuilder fileName = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            fileName.append(ALLOWED_CHARACTERS.charAt(index));
        }

        return fileName.toString();
    }

    @Override
    public void destroy() {
        this.random = null;
    }
}
