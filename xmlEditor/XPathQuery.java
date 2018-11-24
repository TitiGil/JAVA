/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlcontroller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathFactory;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;

/**
 *
 * @author AMG
 */
public class XPathQuery {

    public static void main(String[] args) {

        SAXBuilder sax = new SAXBuilder();
        try {
            Document doc = sax.build(new File("c:/sample.xml"));

            /**
             * Create a new xpath factory for query
             */
            XPathFactory factory = XPathFactory.instance();
            /**
            *      Create the rout of node
            *      no need to insert the entire route only the target node
            */
            XPathExpression<Element> elExpression=factory.compile("//drink",Filters.element());
            
            
           /**
            * @return all the child element of the target node
            * in this example it will return only one element
            * the target node
            * no child
            * it means the list has only 1 item length
            * the .getValue() return child value of node
            */
            List<Element> list=elExpression.evaluate(doc);
            System.out.println("Select Drink element ");
            for(Element ee:list){
                System.out.println(ee.getValue());
            }
            
            
                /**
            * @return all the child element of the target node food with the attribute
            * kind=med 
            * @+attribute reveals a node with specific attribute
            */
                
            elExpression=factory.compile("//food[@kind='Med']",Filters.element());
            list=elExpression.evaluate(doc);
            System.out.println("Select food with kind=med ");
            System.out.println(list.get(0).getValue());
            
            
            /** 
             * you can either choose the n-th target node like this
             * //food[2]
             */
            
                  elExpression=factory.compile("//food[2]",Filters.element());
            list=elExpression.evaluate(doc);
            System.out.println("Select third food element ");
            System.out.println(list.get(0).getValue());
                
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
