package demo.core.servlets;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.day.cq.workflow.WorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component(service = Servlet.class)
@SlingServletPaths({"/bin/servlet", "/bin/hello"})
@SlingServletResourceTypes(
        resourceTypes = "sling/servlet/default",
        extensions = {"csv", "txt", "json"},
        selectors = "hello")
public class TrainingServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setStatus(SlingHttpServletResponse.SC_OK);
        response.setContentType("text/html");
        Session session = null;

        try(PrintWriter writer = response.getWriter()) {
            session = request.getResourceResolver().adaptTo(Session.class);
            writer.write("<p>Hello " + session.getUserID()+" Welcome to training</p>");

            WorkflowSession wfSession = request.getResourceResolver().adaptTo(WorkflowSession.class);
            WorkflowModel workflowModel = wfSession.getModel("/var/workflow/models/training-workflow");
            WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", "/content/demo/us/en/hey");

            // Optionally pass in workflow metadata via a Map
            final Map<String, Object> workflowMetadata = new HashMap<>();
            workflowMetadata.put("anyData", "You Want");
            workflowMetadata.put("evenThingsLike", new Date());

            wfSession.startWorkflow(workflowModel, wfData, workflowMetadata);
            log.debug("Success");

        } catch (WorkflowException e) {
            log.debug("Failed to execute Workflow from Servlet", e);
        }
    }
}
