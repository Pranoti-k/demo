package demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface DemoSchedulerConfig {

    @AttributeDefinition(name = "Scheduler Expression", description = "Provide Expression", type = AttributeType.STRING)
    String cronExpression () default "0 * * * ?";

}
