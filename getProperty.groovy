import com.sap.it.api.mapping.*;
def String getProperty(String propertyName, MappingContext context)
{
String propertyValue= context.getProperty(propertyName);
propertyValue= propertyValue.toString();
return propertyValue;
}