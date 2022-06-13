package demo.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition
public @interface StringGenratorConfig {

    @AttributeDefinition(name = "Name", description = "Please enter name", type = AttributeType.STRING)
    String name() default  "LOL";

}
