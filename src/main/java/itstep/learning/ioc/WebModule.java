package itstep.learning.ioc;

import itstep.learning.servlets.*;

import com.google.inject.servlet.ServletModule;
import itstep.learning.filters.*;

public class WebModule extends ServletModule {
    @Override
    protected void configureServlets() {
        // За наявності IoC реєстрація фільтрів та сервлетів здійснюється
        // Не забути !! прибрати реєстрацію фільтрів з web.xml
        // та додати @Singleton до класів фільтрів
        filter("/*").through(CharsetFilter.class);
        filter("/*").through(SecurityFilter.class);

        // те ж саме з сервлетами
        serve( "/"        ).with( HomeServlet.class);
        serve( "/web-xml" ).with( WebXmlServlet.class );



    }
}
