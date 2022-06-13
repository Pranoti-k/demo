package demo.core.servlets;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;

public class NewSample extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            WorkflowSession workflowSession = request.getResourceResolver().adaptTo(WorkflowSession.class);
            WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/training-demo");
            WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH","/content/demo/us/en/hey");

            workflowSession.startWorkflow(workflowModel, workflowData);

           } catch (WorkflowException e) {
            e.printStackTrace();
        }


    }
}
