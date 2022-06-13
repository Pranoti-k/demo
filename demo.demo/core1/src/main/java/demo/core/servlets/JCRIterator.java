package demo.core.servlets;

import com.adobe.granite.crypto.CryptoException;
import com.adobe.granite.crypto.CryptoSupport;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.query.Query;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Slf4j
@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "sling/servlet/default",
        extensions = "json",
        selectors = "data")
public class JCRIterator extends SlingSafeMethodsServlet {

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private CryptoSupport cryptoSupport;

    private static final Map<String, Object> LOGIN_MAP = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "writeService");

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        try(PrintWriter writer = response.getWriter()) {

            JSONObject resourceName = new JSONObject();
            JSONObject properties = new JSONObject();
            final DateTimeFormatter dtf = ISODateTimeFormat.dateTime();

            Resource parResource = request.getResource().getChild("jcr:content").getChild("root");
            if(parResource != null) {
                for(Resource resource : parResource.getChildren()) {
                    ValueMap value = resource.getValueMap();
                    resourceName.put(resource.getName(), properties);
                    for(Map.Entry<String, Object> e : value.entrySet()) {
                        if (e.getValue() instanceof Calendar) {
                            final Calendar cal = (Calendar) e.getValue();
                            properties.put(e.getKey(), dtf.print(cal.getTimeInMillis()));
                        } else if (e.getValue() instanceof Date) {
                            final Date date = (Date) e.getValue();
                            properties.put(e.getKey(), dtf.print(date.getTime()));
                        } else {
                            properties.put(e.getKey(), e.getValue());
                        }
                    }
                }
            }
            writer.write(resourceName.toString());
            //writer.write(findContentResource().toString());
            writer.write(getCrypto());

        } catch (JSONException | CryptoException e) {
            log.debug("Failed to generate JSON", e);
        }
    }
    private List<Resource> findContentResource() throws LoginException {
        ResourceResolver resolver = resolverFactory.getServiceResourceResolver(LOGIN_MAP);
        String sql2 = String.format("SELECT * FROM [nt:base] AS s " +
                "WHERE ISDESCENDANTNODE([/content]) " +
                "AND [sling:resourceType] = '%s'", "weretail/components/content/heroimage");
        Iterator<Resource> component = resolver.findResources(sql2, Query.JCR_SQL2);
        return Lists.newArrayList(component);
    }

    private String getCrypto() throws CryptoException {
        String password = null;
        if (this.cryptoSupport.isProtected("{c30b9f4afe6a2a5945b8ad0d8795d3356c939070912fac488f59782580a047e5}")) {
            password = this.cryptoSupport.unprotect("{c30b9f4afe6a2a5945b8ad0d8795d3356c939070912fac488f59782580a047e5}");
        }
        return password;

    }
}
