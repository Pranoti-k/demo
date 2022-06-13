package demo.core.services;

import org.apache.sling.api.resource.ResourceResolver;

public interface StringGenerator {

   String generateString();
   ResourceResolver getServiceResolver();
}
