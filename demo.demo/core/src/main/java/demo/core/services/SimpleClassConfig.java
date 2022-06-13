package demo.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition
public @interface SimpleClassConfig {

    @AttributeDefinition(name = "Name", description = "Please enter name", type = AttributeType.STRING)
    String[] name() default {"a", "b", "c"};

    @AttributeDefinition(name = "Gender", description = "Please Select Gender", options = {
            @Option(label = "Male", value = "male"),
            @Option(label = "Female", value = "female"),
            @Option(label = "Trangender", value = "transgender")
    })
    String gender() default "male";

    @AttributeDefinition(name = "user.validity", description ="Validity of user account", defaultValue = "10", required = true, type = AttributeType.INTEGER, min = "10")
    int getValidity() default 10;
}
