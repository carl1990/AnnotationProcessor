package com.carl.processor;


import com.carl.annotation.Hello;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("com.carl.annotation.Hello")

public class MyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        StringBuilder builder = new StringBuilder();

        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Hello.class)) {
            String objectType = element.getSimpleName().toString();
            // this is appending to the return statement
            builder.append(objectType);
        }

        MethodSpec main = MethodSpec.methodBuilder("getMessage")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(String.class)
//            .addParameter(String[].class, "args")
            .addStatement("return $S",builder.toString() + "Say::: Hello,Processor&JavaPoet!")
            .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(main)
            .build();

        JavaFile javaFile = JavaFile.builder("com.carl.processgenerated", helloWorld)
            .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }
}
