package demo.core.workflow.pdfGenerator;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Slf4j
@Component(service = WorkflowProcess.class, immediate = true, property = {
        "process.label" +"="+ "PDF Generator Workflow"
})
public class GeneratePDFProcess implements WorkflowProcess {

    @Reference
    private PDFGenerator pdfGenerator;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        pdfGenerator.generatePDF(workItem, workflowSession);
    }
}
