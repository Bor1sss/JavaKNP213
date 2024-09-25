package itstep.learning;
import itstep.learning.annotations.Product;
import itstep.learning.annotations.Required;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class AutoShop {
    private List<Vehicle> vehicles;
    public AutoShop() {
        vehicles = new ArrayList<>();
        vehicles.add(new Bike("Kawasaki Ninja", "Sport"));
        vehicles.add(new Bike("Harley-Davidson Sportster", "Road"));
        vehicles.add(new Bus("Renault Master", 48));
        vehicles.add(new Bus("Mercedes-Benz Sprinter", 21));
        vehicles.add(new Bus("Bogdan A092", 24));
        vehicles.add(new Bus("Volvo 9700", 54));
        vehicles.add(new Truck("Renault C-Truck", 7.5));
        vehicles.add(new Truck("DAF XF 106 2018", 3.5));
        vehicles.add(new Truck("Mercedes Actros L", 15.0));
        vehicles.add(new Car("Toyota Camry", "Sedan"));
        vehicles.add(new Car("Honda Accord", "Sedan"));
        vehicles.add(new Crossover("Audi RS Q8", 3.0));
        vehicles.add(new Crossover("BMW X7", 2.5));
    }
    public void run() {
        for (Class<?> cls : getProductClasses("itstep.learning")) {
            System.out.println("Class: " + cls.getName());
            printRequired(cls);
        }
    }
    private void printRequired(Class<?> cls) {
        for(Field field : cls.getDeclaredFields()) {
            if(field.isAnnotationPresent(Required.class)) {
                String requiredName = field.getAnnotation(Required.class).value();
                boolean isAlter = field.getAnnotation(Required.class).isAlternative();
                System.out.println("".equals(requiredName) ? field.getName() : requiredName + " or " + field.getName() + "=" + isAlter);
            }
        }
    }
    private List<Class<?>> getProductClasses(String packageName) {
        URL classLocation = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        if (classLocation == null) throw new RuntimeException("Класс не найден!");

        File classRoot;
        File[] files;
        try { classRoot = new File(URLDecoder.decode(classLocation.getPath(), "UTF-8")); }
        catch (Exception e) { throw new RuntimeException("Ошибка при декодировании пути", e); }
        if (!classRoot.exists() || !classRoot.isDirectory()) throw new RuntimeException("Неправильный путь к каталогу классов");

        List<Class<?>> classes = new ArrayList<>();
        scanDirectory(classRoot, packageName, classes);
        return classes;
    }
    private void scanDirectory(File directory, String packageName, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) scanDirectory(file, packageName + "." + file.getName(), classes);
            else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> cls = Class.forName(className);
                    if (cls.isAnnotationPresent(Product.class)) classes.add(cls);
                } catch (ClassNotFoundException e) { System.err.println("Класс не найден: " + className); }
            }
        }
    }
    private void showAllClasses() {
        URL classLocation = this.getClass().getClassLoader().getResource(".");
        if (classLocation == null) {
            System.err.println("Class not found!");
            return;
        }

        File classRoot = null;
        File[] files;
        try {
            classRoot = new File(
                URLDecoder.decode(classLocation.getPath(), "UTF-8"),
                "itstep/learning/oop/"
            );
        } catch (Exception ignored) {}
        if (classRoot == null || (files = classRoot.listFiles()) == null) {
            System.err.println("Ошибка обхода ресурса!");
            return;
        }

        List<String> classNames = new ArrayList<>();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".class") && file.isFile() && file.canRead())
                classNames.add("itstep.learning." + fileName.substring(0, fileName.length() - 6));
        }

        List<Class<?>> classes = new ArrayList<>();
        for (String className : classNames) {
            Class<?> cls;
            try { cls = Class.forName(className); }
            catch (ClassNotFoundException ignored) {continue;}
            if(cls.isAnnotationPresent(Product.class)) classes.add(cls);
        }
        for (Class<?> cls : classes) System.out.println("Класс: " + cls.getName());
    }
    public void printAll() {
        for (Vehicle v : vehicles) System.out.println(v.getInfo());
    }
    public void printLargeSized() {
        for (Vehicle v : vehicles) if (v instanceof LargeSized) System.out.println(v.getInfo());
    }
    public void printNonLargeSized() {
        for (Vehicle v : vehicles) if (!(v instanceof LargeSized)) System.out.println(v.getInfo());
    }
    private void printTrailers() {
        for (Vehicle v : vehicles) {
            if (v instanceof Trailer) {
                System.out.print(v.getInfo());
                System.out.println(" мог бы иметь прицеп типа " + ((Trailer) v).trailerInfo());
            }
        }
    }
}