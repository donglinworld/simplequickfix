package simplefix.examples.executor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CreateTags {

	static Map<Integer,String> _tags = new TreeMap<Integer,String> ();
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		parseTags("quickfixj-messages/quickfixj-messages-fix40/src/main/resources/FIX40.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix41/src/main/resources/FIX41.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix42/src/main/resources/FIX42.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix43/src/main/resources/FIX43.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix44/src/main/resources/FIX44.modified.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix50/src/main/resources/FIX50.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix50sp1/src/main/resources/FIX50SP1.modified.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fix50sp2/src/main/resources/FIX50SP2.modified.xml");
		parseTags("quickfixj-messages/quickfixj-messages-fixt11/src/main/resources/FIXT11.xml");

		 for (Map.Entry<Integer, String > tag : _tags.entrySet()) {
			 System.out.println("    public static Tag " + tag.getValue() + "                              = _addTag(" +  tag.getKey() + "," + '"' + tag.getValue() + '"' + ");");
		 }
		
		//Set<Integer> tagNums = _tags.keySet();
		
	}
	
	static void parseTags(String filePath) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(filePath));
		Element element = document.getDocumentElement();

		NodeList fieldsNodeList = element.getElementsByTagName("fields");
		for (int i = 0; i < fieldsNodeList.getLength(); i++) {
			NodeList fieldNodeList = ((Element) fieldsNodeList.item(i)).getElementsByTagName("field");
			for (int j = 0; j < fieldNodeList.getLength(); j++) {
				Element field = (Element) fieldNodeList.item(j);
				_tags.put(Integer.valueOf(field.getAttribute("number")) ,field.getAttribute("name"));
			}
		}
	}
}
