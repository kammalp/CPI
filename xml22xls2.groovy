import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.ss.usermodel.*
import groovy.xml.XmlParser
import java.io.ByteArrayOutputStream

def Message processData(Message message) {
    // Get the XML payload
    def xmlContent = message.getBody(String)
    
    // Parse the XML
    def xml = new XmlParser().parseText(xmlContent)
    
    // Create a new workbook and sheet
    Workbook workbook = new XSSFWorkbook()
    Sheet sheet = workbook.createSheet("Sheet1")
    
    int rowNum = 0
    xml.record.each { record ->
        Row row = sheet.createRow(rowNum++)
        int cellNum = 0
        record.children().each { field ->
            Cell cell = row.createCell(cellNum++)
            cell.setCellValue(field.text())
        }
    }
    
    // Create a ByteArrayOutputStream to hold the XLSX data
    ByteArrayOutputStream baos = new ByteArrayOutputStream()
    workbook.write(baos)
    workbook.close()
    
    // Set the message body to the ByteArrayOutputStream content
    message.setBody(baos.toByteArray())
    
    // Set headers for XLSX
    message.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    message.setHeader("Content-Disposition", 'attachment; filename="output.xlsx"')
    
    return message
}
