package PDF;



import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.TejaITB2.Model.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
 
 
public class UserPDFExporter {
    private List<Employee> listEmps;
     
    public UserPDFExporter(List<Employee> listUsers) {
        this.listEmps = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("EmployeId", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Location", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Dept", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Salary", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Employee empl : listEmps) {
            table.addCell(String.valueOf(empl.getEmpId()));
            table.addCell(empl.getFullName());
            table.addCell(empl.getLocation());
            table.addCell(empl.getDept());
            table.addCell(String.valueOf(empl.getSalary()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Employees Data", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
