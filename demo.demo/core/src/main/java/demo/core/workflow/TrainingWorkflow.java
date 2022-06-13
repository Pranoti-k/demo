package demo.core.workflow;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Component(service = WorkflowProcess.class, property = {
        "process.label=Training Demo Workflow"
})
public class TrainingWorkflow implements WorkflowProcess {

    private static final Map<String, Object> LOGIN_MAP = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "writeService");

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        Workflow workflow = workItem.getWorkflow();
        String status = workflow.getWorkflowData().getMetaDataMap().get("status", String.class);
        log.debug("Workflow ID: "+ workflow.getId());
        log.debug("Workflow Status: "+ status);
        ResourceResolver resolver = null;
        try {
            resolver = resolverFactory.getServiceResourceResolver(LOGIN_MAP);
            Session session = workflowSession.adaptTo(Session.class);
            log.debug("System user Id: "+ resolver.getUserID());
        } catch (LoginException e) {
            throw new IllegalStateException("Filed to resolve service resolver", e);
        }
        finally {
            if(resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }
}
