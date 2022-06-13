package demo.core.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

@Component(service = DemoRunModeTest.class, immediate = true, configurationPolicy = ConfigurationPolicy.REQUIRE)
public class DemoRunModeTest {

}
