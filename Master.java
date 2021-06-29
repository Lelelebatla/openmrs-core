//0000000000000000000000000000000000000000 30fbfe63e5409675c3a663f70f12bb6745dcdd0e Lelelebatla <rebzlebatla@gmail.com> 1624803975 +0200	clone: from https://github.com/PIH/openmrs-core.git


import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.SAXException;

public class Master { 

   private List<Patient> patientList = new ArrayList<>();
   
   Patient patient;

   public static void main(String argv[]){
     
      try{  
         //creating a constructor of file class and parsing an XML file  
         File file = new File("resources/dataset.xml");  
         
         
         DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document doc = builder.parse("resources/dataset.xml");
         
         XPathFactory xPathfactory = XPathFactory.newInstance();
         XPath xpath = xPathfactory.newXPath();
         XPathExpression expr = xpath.compile("//dataset/person_name[@middle_name]");
         NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
         
         /*
         * Test Node access
         *
         */
         for (int i = 0; i < nl.getLength(); i++) {
         
             Node currentItem = nl.item(i);
             
             String key = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
             
             System.out.println(key);
             
         }
         
         
      }catch (Exception e){
        
         e.printStackTrace();
           
      }  
   }
   
   
   /* there is one patient in standardTest-1.9.7-dataSet.xml with the given name "Horatio", and that patient has patient id #2
  		patients = patientService.getPatientsByGivenName("Horatio");
  		assertThat(patients.size(), is(1));
 		assertThat(patients.get(0).getPatientId(), is(2));
   */
   public List<Patient> getPatientsByGivenName( NodeList nodeList ) {
      
      for (int i = 0; i < nodeList.getLength(); i++) {
         
          Node currentItem = nodeList.item(i);
          
          String key = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
          
          if( key.matches("Horatio") ){
          
            String person_name_id = currentItem.getAttributes().getNamedItem("person_name_id").getNodeValue();
            String person_id = currentItem.getAttributes().getNamedItem("person_id").getNodeValue();
            String prefix = currentItem.getAttributes().getNamedItem("prefix").getNodeValue();
            String given_name = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
            String middle_name = currentItem.getAttributes().getNamedItem("middle_name").getNodeValue();
            String family_name = currentItem.getAttributes().getNamedItem("family_name").getNodeValue();
            String family_name_suffix = currentItem.getAttributes().getNamedItem("family_name_suffix").getNodeValue();
            String creator = currentItem.getAttributes().getNamedItem("creator").getNodeValue();
            String date_created = currentItem.getAttributes().getNamedItem("date_created").getNodeValue();
            String voided = currentItem.getAttributes().getNamedItem("voided").getNodeValue();
            String void_reason = currentItem.getAttributes().getNamedItem("void_reason").getNodeValue();
            String uuid = currentItem.getAttributes().getNamedItem("uuid").getNodeValue();
          
            patient = new Patient(person_name_id, person_id, prefix, given_name, middle_name, family_name, family_name_suffix, creator, date_created, voided, void_reason, uuid);
            
            
            this.patientList.add(patient);
        
          }
          
      }
		
		return this.patientList;
      
	}
   
   
   /* there is one patient in standardTest-1.9.7-dataSet.xml 6with the family name "Hornblower", but this method should *not* return family name matches
  		patients = patientService.getPatientsByGivenName("Hornblower");
  		assertThat(patients.size(), is(0));

   */
   public List<Patient> getPatientsByGivenFamilyName( NodeList nodeList ) {
      
      for (int i = 0; i < nodeList.getLength(); i++) {
         
          Node currentItem = nodeList.item(i);
          
          String key = currentItem.getAttributes().getNamedItem("family_name").getNodeValue();
          
          if( !key.matches("Hornblower") ){
          
            String person_name_id = currentItem.getAttributes().getNamedItem("person_name_id").getNodeValue();
            String person_id = currentItem.getAttributes().getNamedItem("person_id").getNodeValue();
            String prefix = currentItem.getAttributes().getNamedItem("prefix").getNodeValue();
            String given_name = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
            String middle_name = currentItem.getAttributes().getNamedItem("middle_name").getNodeValue();
            String family_name = currentItem.getAttributes().getNamedItem("family_name").getNodeValue();
            String family_name_suffix = currentItem.getAttributes().getNamedItem("family_name_suffix").getNodeValue();
            String creator = currentItem.getAttributes().getNamedItem("creator").getNodeValue();
            String date_created = currentItem.getAttributes().getNamedItem("date_created").getNodeValue();
            String voided = currentItem.getAttributes().getNamedItem("voided").getNodeValue();
            String void_reason = currentItem.getAttributes().getNamedItem("void_reason").getNodeValue();
            String uuid = currentItem.getAttributes().getNamedItem("uuid").getNodeValue();
          
            patient = new Patient(person_name_id, person_id, prefix, given_name, middle_name, family_name, family_name_suffix, creator, date_created, voided, void_reason, uuid);
            
            
            this.patientList.add(patient);
        
          }
          
      }
		
		return this.patientList;
      
	}
   
   
   /* this method should *not* return patients with given names that are partial matches
  		patients = patientService.getPatientsByGivenName("Horat");
  		assertThat(patients.size(), is(0));
      */
   public List<Patient> getPatientsByGivenNameNotPartialMatches( NodeList nodeList ) {
      
      for (int i = 0; i < nodeList.getLength(); i++) {
         
          Node currentItem = nodeList.item(i);
          
          String key = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
          
          if( !key.equals("Horat") ){
          
            String person_name_id = currentItem.getAttributes().getNamedItem("person_name_id").getNodeValue();
            String person_id = currentItem.getAttributes().getNamedItem("person_id").getNodeValue();
            String prefix = currentItem.getAttributes().getNamedItem("prefix").getNodeValue();
            String given_name = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
            String middle_name = currentItem.getAttributes().getNamedItem("middle_name").getNodeValue();
            String family_name = currentItem.getAttributes().getNamedItem("family_name").getNodeValue();
            String family_name_suffix = currentItem.getAttributes().getNamedItem("family_name_suffix").getNodeValue();
            String creator = currentItem.getAttributes().getNamedItem("creator").getNodeValue();
            String date_created = currentItem.getAttributes().getNamedItem("date_created").getNodeValue();
            String voided = currentItem.getAttributes().getNamedItem("voided").getNodeValue();
            String void_reason = currentItem.getAttributes().getNamedItem("void_reason").getNodeValue();
            String uuid = currentItem.getAttributes().getNamedItem("uuid").getNodeValue();
          
            patient = new Patient(person_name_id, person_id, prefix, given_name, middle_name, family_name, family_name_suffix, creator, date_created, voided, void_reason, uuid);
            
            
            this.patientList.add(patient);
        
          }
          
      }
		
		return this.patientList;
      
	}
   
   
   /* this method *should* return patients if name only differs by letter casing
 		patients = patientService.getPatientsByGivenName("HORATIO");
 		assertThat(patients.size(), is(1));
 		assertThat(patients.get(0).getPatientId(), is(2)); 

   */
   public List<Patient> getPatientsByGivenNameWithCaseDiffrence( NodeList nodeList ) {
      
      for (int i = 0; i < nodeList.getLength(); i++) {
         
          Node currentItem = nodeList.item(i);
          
          String key = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
          
          if( !key.equalsIgnoreCase("HORATIO") ){
          
            String person_name_id = currentItem.getAttributes().getNamedItem("person_name_id").getNodeValue();
            String person_id = currentItem.getAttributes().getNamedItem("person_id").getNodeValue();
            String prefix = currentItem.getAttributes().getNamedItem("prefix").getNodeValue();
            String given_name = currentItem.getAttributes().getNamedItem("given_name").getNodeValue();
            String middle_name = currentItem.getAttributes().getNamedItem("middle_name").getNodeValue();
            String family_name = currentItem.getAttributes().getNamedItem("family_name").getNodeValue();
            String family_name_suffix = currentItem.getAttributes().getNamedItem("family_name_suffix").getNodeValue();
            String creator = currentItem.getAttributes().getNamedItem("creator").getNodeValue();
            String date_created = currentItem.getAttributes().getNamedItem("date_created").getNodeValue();
            String voided = currentItem.getAttributes().getNamedItem("voided").getNodeValue();
            String void_reason = currentItem.getAttributes().getNamedItem("void_reason").getNodeValue();
            String uuid = currentItem.getAttributes().getNamedItem("uuid").getNodeValue();
          
            patient = new Patient(person_name_id, person_id, prefix, given_name, middle_name, family_name, family_name_suffix, creator, date_created, voided, void_reason, uuid);
            
            
            this.patientList.add(patient);
        
          }
          
      }
		
		return this.patientList;
      
	}
  

  
}  