package demo.core.services;

import com.adobe.xfa.Obj;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component(immediate = true, service = StringGenerator.class)
@Designate(ocd = StringGenratorConfig.class)
public class StringGeneratorImpl implements StringGenerator {

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private String name;

    @Activate
    @Modified
    public void activate(StringGenratorConfig config) {
        this.name = config.name();
    }

    @Override
    public String generateString() {
        return "Hello";
    }

    /*public ResourceResolver getServiceResolver () {
        ResourceResolver resolver = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "writeService");
        try {
        resolver = resourceResolverFactory.getServiceResourceResolver(param);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return resolver;
    }*/


    public ResourceResolver getServiceResolver() {
        ResourceResolver resourceResolver = null;
        final Map<String, Object> param = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "writeService");
        try {
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return resourceResolver;
    }
}


