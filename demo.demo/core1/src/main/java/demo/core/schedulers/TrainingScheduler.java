package demo.core.schedulers;

import com.day.cq.commons.jcr.JcrConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.*;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Component(service = Runnable.class, immediate = true, property = {
        //Scheduler.PROPERTY_SCHEDULER_EXPRESSION + "=" +"* * * * * ?",
        Scheduler.PROPERTY_SCHEDULER_CONCURRENT + ":Boolean=" + "false"
})
public class TrainingScheduler implements Runnable {

    @Reference
    private ResourceResolverFactory resolverFactory;

    private static final Map<String, Object> LOGIN_MAP = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "writeService");

    @Override
    public void run() {
        log.debug("Training Scheduler is running");
        ResourceResolver resolver = null;
        try {
            resolver = resolverFactory.getServiceResourceResolver(LOGIN_MAP);
            Resource resource = resolver.getResource("/SampleTraining");
            if(resource != null) {
                resolver.delete(resource);
                resolver.commit();
                log.debug("Resource has been deleted successfully");
            }
            else {
                resolver.create(resolver.getResource("/"),"SampleTraining", Collections.singletonMap("jcr:primaryType", "nt:folder"));
                resolver.commit();
                log.info("Resource has been created successfully");
            }
        } catch (LoginException | PersistenceException  e) {
            log.debug("Failed to get resource service resolver");
        } finally {
            if(resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }
}
