package learning.oop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
public @interface Required {
    String value() default "";
    boolean isAlternate() default true;
}
/*
Д.З. Забезпечити у getProductClasses( String packageName )
сканування не лише самого пакету, а й усіх його внутрішніх
пакетів. Всі класи збирати до однієї колекції.
Для випробування створити внутрішній пакет, перенести
один з класів-продуктів до нього (або описати ще один клас).
** Забезпечити сканування з довільною глибиною
 */