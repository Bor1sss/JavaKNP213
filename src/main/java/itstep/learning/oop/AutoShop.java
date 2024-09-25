package itstep.learning.oop;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import itstep.learning.oop.annotations.Product;
import itstep.learning.oop.annotations.Required;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class AutoShop {
    private final List<Vehicle> vehicles;

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
        vehicles.add(new Crossover("Audi Q5", 110));
        vehicles.add(new Crossover("Lamborghini Urus", 90));
        vehicles.add(new Crossover("Honda CR-V", 110));
        vehicles.add(new Crossover("BMW X5", 120));
        vehicles.add(new Car("Toyota Corolla", "Hatchback"));
        vehicles.add(new Car("BMW 320", "Hearse"));
        vehicles.add(new Car("VW Golf", "Fastback"));
        vehicles.add(new Car("Honda Accord", "Convertible"));
        vehicles.add(new Car("Audi RS6", "Hatchback"));
    }

    public void run() {
        try (InputStream stream = Objects.requireNonNull(
                this.getClass().getClassLoader().getResourceAsStream("shop.json"))) {

            String jsonContent = readAsString(stream);

            // Используем Gson для парсинга JSON
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            List<Map<String, Object>> jsonVehicles = gson.fromJson(jsonContent, listType);

            for (Map<String, Object> jsonVehicle : jsonVehicles) {
                Set<Class<?>> matchingClasses = findMatchingClasses(jsonVehicle);

                if (matchingClasses.size() > 1) {
                    throw new IllegalArgumentException("Множинный матч для JSON: " + jsonVehicle);
                } else if (!matchingClasses.isEmpty()) {
                    System.out.println("Найден соответствующий класс: " + matchingClasses);
                } else {
                    System.out.println("Не найдено соответствующего класса для JSON: " + jsonVehicle);
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String readAsString(InputStream stream) throws IOException {
        byte[] buffer = new byte[4];
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        int length;
        while ((length = stream.read(buffer)) != -1) {
            byteBuilder.write(buffer, 0, length);
        }
        return byteBuilder.toString();
    }

    private Set<Class<?>> findMatchingClasses(Map<String, Object> jsonVehicle) {
        Set<Class<?>> matchingClasses = new HashSet<>();
        for (Class<?> cls : getProductClasses("itstep.learning.oop")) {
            if (canProcessJson(cls, jsonVehicle)) {
                matchingClasses.add(cls);
            }
        }
        return matchingClasses;
    }

    private boolean canProcessJson(Class<?> cls, Map<String, Object> jsonVehicle) {
        if (!cls.isAnnotationPresent(Product.class)) {
            return false;
        }

        String jsonType = (String) jsonVehicle.get("type");
        Product annotation = cls.getAnnotation(Product.class);
        return true;
    }

    private List<Class<?>> getProductClasses(String packageName) {
        URL classLocation = this.getClass().getClassLoader().getResource(".");
        if (classLocation == null) {
            throw new RuntimeException("Class not found!");
        }
        File classRoot = null;
        try {
            classRoot = new File(URLDecoder.decode(classLocation.getPath(), "UTF-8"),
                    packageName.replace(".", "/"));
        } catch (Exception ignored) {
        }
        if (classRoot == null) {
            throw new RuntimeException("Error resource traversing");
        }

        List<String> classNames = new ArrayList<>();
        findClasses(classRoot, packageName, classNames);

        List<Class<?>> classes = new ArrayList<>();
        for (String className : classNames) {
            Class<?> cls;
            try {
                cls = Class.forName(className);
            } catch (Exception ignored) {
                continue;
            }
            if (cls.isAnnotationPresent(Product.class)) {
                classes.add(cls);
            }
        }

        return classes;
    }

    private void findClasses(File directory, String packageName, List<String> classNames) {
        if (!directory.exists()) {
            return;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, packageName + "." + file.getName(), classNames);
            } else if (file.getName().endsWith(".class") && file.isFile() && file.canRead()) {
                String className = file.getName().substring(0, file.getName().length() - 6);
                classNames.add(packageName + "." + className);
            }
        }
    }

    public void printAll() {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getInfo());
        }
    }

    public void printLargeSized() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof LargeSized) {
                System.out.println(vehicle.getInfo());
            }
        }
    }

    public void printNonLargeSized() {
        for (Vehicle vehicle : vehicles) {
            if (!(vehicle instanceof LargeSized)) {
                System.out.println(vehicle.getInfo());
            }
        }
    }

    private void printTrailers() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Trailer) {
                System.out.print(vehicle.getInfo());
                System.out.println(" может иметь трейлер типа " +
                        ((Trailer) vehicle).trailerInfo());
            }
        }
    }
}
