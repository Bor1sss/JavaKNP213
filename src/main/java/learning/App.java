package learning;
import learning.async.AsyncDemo;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        // new Basics().run();
        // new OopDemo().run();
         new AsyncDemo().run();
//        Resolver resolver = new Resolver();
//        Injector injector = resolver.getInjector();
//        IocDemo iocDemo = injector.getInstance(IocDemo.class);
//        iocDemo.run();
    }
}
/*
У Java є прив'язка до файлової системи
- package (аналог namespace) = директорія (папка): itstep.learning -> itstep/learning
- public клас = файл
- casing:
 = Types: CapitalCamelCase
 = fields/methods: lowerCamelCase
 = package: snake_case
 */
