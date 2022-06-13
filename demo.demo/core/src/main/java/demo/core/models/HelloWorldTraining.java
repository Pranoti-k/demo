package demo.core.models;

import lombok.Data;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.Resource;
import javax.inject.Inject;

@Data
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HelloWorldTraining  {

    @Inject
    private String firstName;

    @Inject
    private String lastName;

    @Inject
    private String gender;

    @Inject
    private String country;
}
