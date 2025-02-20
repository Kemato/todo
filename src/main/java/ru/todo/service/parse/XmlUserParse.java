package ru.todo.service.parse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.todo.interfaces.UserParse;
import ru.todo.model.User;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XmlUserParse implements UserParse {

    public ArrayList<User> read(){
        ArrayList<User> users = new ArrayList<>();
        return users;
    }

    public void write(ArrayList<User> users){


        try {

            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Studentinfo");
            doc.appendChild(root);

            Element Details = doc.createElement("Details");
            root.appendChild(Details);


            for (User user : users) {

                Element name = doc.createElement("Name");
                name.appendChild(doc.createTextNode(String.valueOf(user.getName())));
                Details.appendChild(name);

                Element id = doc.createElement("ID");
                id.appendChild(doc.createTextNode(String.valueOf(user.getId())));
                Details.appendChild(id);

                Element mmi = doc.createElement("Password");
                mmi.appendChild(doc.createTextNode(String.valueOf(user.getPassword())));
                Details.appendChild(mmi);

            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter("./ros.xml");
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }

}
