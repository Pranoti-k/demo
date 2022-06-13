package demo.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(immediate = true)
@Designate(ocd = SimpleClassConfig.class)
public class SimpleClass {

    private String name[];
    private String gender;
    private int validity;

    @Activate
    @Modified
    public void activate(SimpleClassConfig config) {

        this.name = config.name();
        this.gender = config.gender();
        this.validity = config.getValidity();
    }
}
