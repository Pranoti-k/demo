package demo.core.workflow.pdfGenerator;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;

public interface PDFGenerator {
    void generatePDF(WorkItem workItem, WorkflowSession workflowSession) throws WorkflowException;
}
