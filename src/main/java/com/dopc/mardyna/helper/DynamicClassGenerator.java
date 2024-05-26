package com.dopc.mardyna.helper;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;

import java.util.Date;
import java.util.List;

import org.aopalliance.intercept.ConstructorInterceptor;

import java.lang.reflect.Type;
import com.dopc.mardyna.entity.EntityField;

public class DynamicClassGenerator {

    public static Class<?> generateClass(String classname, List<EntityField> fields) throws Exception {
        ByteBuddy byteBuddy = new ByteBuddy();
        DynamicType.Builder<?> builder = byteBuddy.subclass(Object.class).name(classname);

        for (EntityField field : fields) {
        	Type type = String.class;
        	switch (field.getType()) {
			case "boolean": {
				type = Boolean.class;
			}
			case "numeric": {
				type = Number.class;
			}
			case "date": {
				type = Date.class;
			}
			default:
				// throw new IllegalArgumentException("Unexpected value: " + field.getType());
			}
            builder = builder
                .defineField(field.getName(), type, net.bytebuddy.description.modifier.Visibility.PRIVATE)
                .defineMethod("get" + capitalize(field.getName()), type, net.bytebuddy.description.modifier.Visibility.PUBLIC)
                .intercept(FieldAccessor.ofBeanProperty())
                .defineMethod("set" + capitalize(field.getName()), void.class, net.bytebuddy.description.modifier.Visibility.PUBLIC)
                .withParameters(type)
                .intercept(FieldAccessor.ofBeanProperty());
        }
        
     // Adding constructor with 6 parameters
        builder = builder.defineConstructor(net.bytebuddy.description.modifier.Visibility.PUBLIC)
                .withParameters(String.class, String.class, String.class, String.class, String.class, String.class)
                .intercept(MethodDelegation.to(ConstructorInterceptor.class));

        return builder
            .make()
            .load(DynamicClassGenerator.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static class FieldDefinition {
        private final String name;
        private final Class<?> type;

        public FieldDefinition(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public Class<?> getType() {
            return type;
        }
    }

    /*
    public static void main(String[] args) throws Exception {
        List<FieldDefinition> fields = List.of(
            new FieldDefinition("name", String.class),
            new FieldDefinition("age", Integer.class)
        );

        Class<?> dynamicClass = generateClass("com.example.Travail", fields);

        // Créer une instance de la classe générée
        Object travailInstance = dynamicClass.getDeclaredConstructor().newInstance();

        // Appeler les setters pour définir les valeurs des champs
        dynamicClass.getMethod("setName", String.class).invoke(travailInstance, "John Doe");
        dynamicClass.getMethod("setAge", Integer.class).invoke(travailInstance, 30);

        // Appeler les getters pour obtenir les valeurs des champs
        String name = (String) dynamicClass.getMethod("getName").invoke(travailInstance);
        Integer age = (Integer) dynamicClass.getMethod("getAge").invoke(travailInstance);

        // Afficher les valeurs des champs
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
    
    */
}
