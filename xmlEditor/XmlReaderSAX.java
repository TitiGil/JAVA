/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlcontroller;
import java.io.File;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
/**
 *
 * @author AMG
 */
public class XmlReaderSAX {
    
    public static void main(String[] args){
        try {
            
            File file=new File("c:/sample.xml");
            SAXBuilder sb=new SAXBuilder();
            
            /**
             * Building the document of xml file
             */
            Document doc=sb.build(file);
            
            
            Element rootElement=doc.getRootElement();
            
            System.out.println("Root : "+rootElement.getName());
            
            
            /**
             * @return A list of children in one deep down
             */
             List<Element> listNode=rootElement.getChildren();      
            for(Element e:listNode){
                System.out.println(e.getName());
            }
            
            System.out.println("---------------------------------");
            /**
             * @return A list of children with a specific name
             */
            
            
            listNode=rootElement.getChildren("food");
             for(Element e:listNode){
                System.out.print(e.getName());
                 try {
                       System.out.print(" kind : "+ e.getAttribute("kind").getValue()+"\n");
                 } catch (Exception eex) {
                     System.out.println("No shuch attr. available");
                 }
                 
                 List<Element> child=e.getChildren();
                 for(Element e2:child){
                     System.out.println(e2.getName() + " : "+e2.getValue());
                 }
                 
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
    }
}
