package demo.core.Activator;

import lombok.extern.slf4j.Slf4j;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;

@Slf4j
@Component(service = BundleActivator.class, immediate = true)
public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        throw new IllegalStateException("Error++");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        log.debug("Bundle Deactivated");

    }
}
