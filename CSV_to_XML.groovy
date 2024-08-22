import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {
    def body = message.getBody(java.lang.String) as String
    def lines = body.readLines()

    def writer = new StringWriter()
    def xml = new MarkupBuilder(writer)
    xml.'ns0:SCB_BANSTA'('xmlns:ns0': 'http://scb.com/xi/payment/10/lite') {
        lines.each { line ->
            def values = line.split(',')
            switch (values[0].replaceAll('"', '')) {
                case 'H':
                    'Header_Record' {
                        'Record_Type'(values[0]?.replaceAll('"', ''))
                        'Sender_Id'(values[1]?.replaceAll('"', ''))
                        'Recipient_Id'(values[2]?.replaceAll('"', ''))
                        'Date_of_Preparation'(values[3]?.replaceAll('"', ''))
                        'Time_of_Preparation'(values[4]?.replaceAll('"', ''))
                        'Unique_Ref_no'(values[5]?.replaceAll('"', ''))
                        'File_Type'(values[6]?.replaceAll('"', ''))
                        'Branch_Code'(values[7]?.replaceAll('"', ''))
                        'Customer_Name1'(values[8]?.replaceAll('"', ''))
                        'Customer_Name2'(values[9]?.replaceAll('"', ''))
                        'Customer_Name3'(values[10]?.replaceAll('"', ''))
                        'Customer_Name4'(values[11]?.replaceAll('"', ''))
                        'Customer_Name5'(values[12]?.replaceAll('"', ''))
                        'Address1'(values[13]?.replaceAll('"', ''))
                        'Address2'(values[14]?.replaceAll('"', ''))
                        'Address3'(values[15]?.replaceAll('"', ''))
                        'Address4'(values[16]?.replaceAll('"', ''))
                        'Address5'(values[17]?.replaceAll('"', ''))
                        'Post_code'(values[18]?.replaceAll('"', ''))
                        'Country_code'(values[19]?.replaceAll('"', ''))
                        'Char_filler1'(values[20]?.replaceAll('"', ''))
                        'Char_filler2'(values[21]?.replaceAll('"', ''))
                        'Char_filler3'(values[22]?.replaceAll('"', ''))
                        'Char_filler4'(values[23]?.replaceAll('"', ''))
                    }
                    break
                case 'D':
                    'Detail_Record' {
                        'Record_Type'(values[0]?.replaceAll('"', ''))
                        'Customer_reference'(values[1]?.replaceAll('"', ''))
                        'Status'(values[2]?.replaceAll('"', ''))
                        'Tran_Status_description'(values[3]?.replaceAll('"', ''))
                        'Batch_Reference'(values[4]?.replaceAll('"', ''))
                        'Payment_Reference'(values[5]?.replaceAll('"', ''))
                        'Payment_issue_date'(values[6]?.replaceAll('"', ''))
                        'Payment_date'(values[7]?.replaceAll('"', ''))
                        'Debit_date'(values[8]?.replaceAll('"', ''))
                        'Payment_Amount'(values[9]?.replaceAll('"', ''))
                        'Payment_Currency'(values[10]?.replaceAll('"', ''))
                        'Source_Currency'(values[11]?.replaceAll('"', ''))
                        'Target_Currency'(values[12]?.replaceAll('"', ''))
                        'Exchange_Rate'(values[13]?.replaceAll('"', ''))
                        'Exchange_Currency'(values[14]?.replaceAll('"', ''))
                        'Status_description'(values[15]?.replaceAll('"', ''))
                        'Cheque_Number'(values[16]?.replaceAll('"', ''))
                        'Cheque_date'(values[17]?.replaceAll('"', ''))
                        'Beneficiary_AC_No'(values[18]?.replaceAll('"', ''))
                        'Beneficiary_AC_Name'(values[19]?.replaceAll('"', ''))
                        'Beneficiary_name'(values[20]?.replaceAll('"', ''))
                        'Beneficiary_Address1'(values[21]?.replaceAll('"', ''))
                        'Beneficiary_Address2'(values[22]?.replaceAll('"', ''))
                        'Beneficiary_Address3'(values[23]?.replaceAll('"', ''))
                        'Beneficiary_Address4'(values[24]?.replaceAll('"', ''))
                        'Beneficiary_City'(values[25]?.replaceAll('"', ''))
                        'Beneficiary_postcode'(values[26]?.replaceAll('"', ''))
                        'Beneficiary_country'(values[27]?.replaceAll('"', ''))
                        'Beneficiary_information'(values[28]?.replaceAll('"', ''))
                        if (values.size() > 29) 'Filler1'(values[29]?.replaceAll('"', ''))
                        if (values.size() > 30) 'Filler2'(values[30]?.replaceAll('"', ''))
                        if (values.size() > 31) 'Filler2a'(values[31]?.replaceAll('"', ''))
                        if (values.size() > 32) 'Filler3'(values[32]?.replaceAll('"', ''))
                        if (values.size() > 33) 'Filler4'(values[33]?.replaceAll('"', ''))
                        if (values.size() > 34) 'Filler5'(values[34]?.replaceAll('"', ''))
                        if (values.size() > 35) 'Filler6'(values[35]?.replaceAll('"', ''))
                        if (values.size() > 36) 'Filler7'(values[36]?.replaceAll('"', ''))
                        if (values.size() > 37) 'Filler8'(values[37]?.replaceAll('"', ''))
                    }
                    break
                case 'T':
                    'Trailer_Record' {
                        'Record_Type'(values[0]?.replaceAll('"', ''))
                        'Total_No_of_Records'(values[1]?.replaceAll('"', ''))
                    }
                    break
            }
        }
    }

    message.setBody(writer.toString())
    return message
}