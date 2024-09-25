package itstep.learning;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import itstep.learning.annotations.Product;
import itstep.learning.annotations.Required;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class VehicleFactory {
    private Map<Class<?>, List<String>> productClasses = null;

    public List<Vehicle> loadFromJson( String resourceName ) throws Exception {
        try( InputStream stream = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream( resourceName ))) {
            String json = readAsString( stream );
            Gson gson = new Gson();
            JsonArray arr = gson.fromJson(json, JsonArray.class);
            List<Vehicle> vehicles = new ArrayList<>();
            for( int i = 0; i < arr.size(); i++ ) vehicles.add(fromJsonObject(arr.get(i).getAsJsonObject()));
            return vehicles;
        } catch( Exception ex ) { throw new Exception( ex.getMessage() ); }
    }
    private Vehicle fromJsonObject(JsonObject obj) throws Exception {
        List<Class<?>> matchingClasses = new ArrayList<>();

        for (Map.Entry<Class<?>, List<String>> entry : getProductClasses("itstep.learning").entrySet()) {
            boolean isMatch = true;
            for (String fieldName : entry.getValue()) {
                if (!obj.has(fieldName)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) matchingClasses.add(entry.getKey());
        }
        if (matchingClasses.size() == 0) throw new Exception("Нет соответствующего класса для объекта JSON: " + obj.toString());
        if (matchingClasses.size() > 1) throw new Exception("Несколько соответствующих классов для объекта JSON: " + obj.toString());

        Class<?> vehicleClass = matchingClasses.get(0);
        try {
            Vehicle vehicle = (Vehicle) vehicleClass.getConstructor().newInstance();
            Field field = vehicleClass.getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(vehicle, obj.get("name").getAsString());
            return vehicle;
        } catch (Exception ex) {
            throw new Exception("Ошибка создания экземпляра " + vehicleClass.getSimpleName() + ": " + ex.getMessage());
        }
    }
    private Map<Class<?>, List<String>> getProductClasses(String packageName) {
        if(productClasses != null) return productClasses;
        URL classLocation = this.getClass().getClassLoader().getResource(".");
        if( classLocation == null ) throw new RuntimeException("Ошибка поиска ресурса");

        File classRoot = null;
        File[] files;
        try {
            classRoot = new File(
                URLDecoder.decode( classLocation.getPath(), "UTF-8" ),
                packageName.replace( '.', '/' )
            );
        } catch(Exception ignored) { }
        if(classRoot == null || ( files = classRoot.listFiles() ) == null) throw new RuntimeException("Ошибка обхода ресурса");

        List<String> classNames = new ArrayList<>();
        for( File file : files ) {
            String fileName = file.getName();
            if( fileName.endsWith(".class") && file.isFile() && file.canRead() )
                classNames.add(packageName + "." + fileName.substring( 0, fileName.length() - 6 ));
        }

        Map<Class<?>, List<String>> classes = new HashMap<>();
        for(String className : classNames) {
            Class<?> cls;
            try { cls = Class.forName(className) ; }
            catch(ClassNotFoundException ignored) { continue; }
            if(cls.isAnnotationPresent(Product.class)) classes.put( cls, getRequired(cls) ) ;
        }
        productClasses = classes;
        return classes;
    }
    private List<String> getRequired(Class<?> cls) {
        List<String> res = new ArrayList<>();
        for(Field field : cls.getDeclaredFields()) {
            if(field.isAnnotationPresent( Required.class )) {
                Required annotation = field.getAnnotation( Required.class );
                String requiredName = annotation.value();
                res.add("".equals(requiredName) ? field.getName() : requiredName);
            }
        }
        return res;
    }
    private String readAsString( InputStream stream ) throws IOException {
        byte[] buffer = new byte[4096];
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        int length;
        while((length = stream.read(buffer)) != -1) byteBuilder.write(buffer, 0, length);
        return byteBuilder.toString();
    }
}