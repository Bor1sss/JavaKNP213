package learning.ios;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Resolver {

    public Injector getInjector() {
        return Guice.createInjector(
                new ServiceModule()
        );
    }
    /*
Resolver - клас, задачею якого є створення об'єктів
із впровадженням у них залежностей
*/
}
