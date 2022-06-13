package demo.core.workflow.pdfGenerator;


import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import lombok.extern.slf4j.Slf4j;
import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component(name = "PDFGeneratorImpl Service", service = PDFGenerator.class, immediate = true)
public class PDFGeneratorImpl implements PDFGenerator {

    private static final String FILE_TYPE_SETTING_NAME = "Standard";

    //@Reference
    //private GeneratePDFService generatePdfService;

    @Override
    public void generatePDF(WorkItem workItem, WorkflowSession workflowSession) {
       /* Document myPDFSource = null;
        String bytes = null;
        InputStream stream = new ByteArrayInputStream(bytes.getBytes(StandardCharsets.UTF_8));
        myPDFSource = new Document(stream);
        myPDFSource.setContentType("text/html");
        try {
            CreatePDFResult result1 = generatePdfService.createPDF2(myPDFSource,FILE_TYPE_SETTING_NAME, null, null, null, null, null);
            HtmlToPdfResult result = this.generatePdfService.htmlFileToPdf(myPDFSource, FILE_TYPE_SETTING_NAME, null, null, null);
        } catch (ConversionException | InvalidParameterException | FileFormatNotSupportedException e) {
            log.error("Unable to convert html to pdf", e);
        }*/
    }
}
