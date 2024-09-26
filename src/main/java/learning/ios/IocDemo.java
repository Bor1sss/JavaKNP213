package learning.ios;

import com.google.inject.Inject;
import learning.services.HashService;


public class IocDemo {

    private final HashService hashService;

    @Inject // інжекційний конструктор необхідно помітити анотацією
    public IocDemo(HashService hashService) {
        this.hashService = hashService;
    }

    public void run()
    {
        System.out.println("IocDemo");
        System.out.println(hashService.hash("123"));

    }
}

/*
IoC (Inversion of Control, інверсія управління) - архітектурний шаблон (патерн)
проєктування, згідно з яким задачі управлінням циклом об'єктів
покладаються на окремий модуль.
Задача, що вирішується, - управління залежностями, підтримання їх життєвого
циклу, як правилоЮ постійного існування (Singleton)

Робота IoC складається з двох частин
1. Реєстрація залежності - створення переліку доступних об'єктів чи типів
2. Резолюція / інстанція (Resolve) - створення об'єктів з впровадженням
    в них необхідних залежностей із зареєстрованого переліку

Сервіси                         Споживачі
HashService                     SignupController(HashService)
RandomService                   ForgotController(OtpService)
OtpService

1. Реєструємо: (за формалізмом ASP) Services.AddSingleton<HashService>()
2. Декларуємо залежність - додаємо параметр до конструктора
SignupController(HashService hashService)
цим самим унеможливлюється створення об'єкта контролера без передачі
йому посилання на сервіс. Пошук і передача посилань - задача IoC
Складнощі полягають у тому, що одні сервіси можуть залежати від інших
OtpService(RandomService) причому каскадно. Проміжною задачею є вирішення
дерева залежностей - порядок їх створення.

*****

Не плутати
IoC - Inversion of Control - патерн архітектурний
DIP - Dependency Inversion Principle - принцип з SOLID
DI - Dependency Injection - механізм (процес) впровадження залежностей
*/

