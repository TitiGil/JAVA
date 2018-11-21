/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlcontroller;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 *
 * @author AMG
 */
public class XmlController {

    /**
     * Read a Xml File as dom // 
     * should read element one by one
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            
           DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        /*
        Create a simple Xml
        */
         File inputFile=new File("c://sample.xml");
        
        /*
        Parse the input element
        can Be file of xml
        */
        Document doc=builder.parse(inputFile);
        
        /*
        Creating the root element of Xml Dom tree
        */
        Element root=doc.getDocumentElement();
         System.err.println(root.getNodeName());
         
         /*
         create a list of element with specific tag name
         */
        NodeList nList=doc.getElementsByTagName("food");
        for(int i=0;i<nList.getLength();i++){
            Node node=nList.item(i);
            
            System.out.println(" tag Name : "+node.getNodeName());
            
            /*
            check if this node is an element(has child)
            */
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element el=(Element)node;
                System.out.println("Kind : "+el.getAttribute("kind"));
               System.out.println(el.getElementsByTagName("name").item(0).getTextContent());
               
               
            }
             System.out.println("----------------------------");
        }
        
           
        } catch (Exception e) { System.out.println("Exception :"+e.getMessage());
        }
      
    
    }
    
}
