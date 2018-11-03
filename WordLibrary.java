import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * WordLibrary class reads words from external file and can generate random word
 * 
 * @author BluePuddleCat
 * @version 11/2/18 
 * Written for HackHolyoke 2018
 *
 */
public class WordLibrary {

	private LinkedList<Word> library = new LinkedList<Word>();

	/**
	 * Constructor reads file
	 */
	public WordLibrary() {
		try {
			// Setup XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File xmlFile = new File("WordLibrary.xml");
			Document document = builder.parse(xmlFile);
			parseFounderFile(document);
		}
		// catch exceptions
		catch (ParserConfigurationException pce) {
			System.out.println("ParserConfigurationException found");
		} catch (SAXException saxe) {
			System.out.println("SAXException found");
		} catch (IOException ioe) {
			System.out.println("IOException found");
		}
	}

	/**
	 * Navigate through the major document structure
	 * 
	 * @param document:
	 *            document to be parsed
	 */
	private void parseFounderFile(Document document) {
		// get first node
		Node docRoot = document.getDocumentElement();
		parseNode(docRoot);
	}

	/**
	 * Go through every node in the document
	 * 
	 * @param node:
	 *            node in file
	 */
	private void parseNode(Node node) {
		// check if node is an element
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			// rename element node
			Element currentElt = (Element) node;
			// create Word object
			Word newWord = new Word();
			// check for attribute of the element
			if (currentElt.hasAttribute("noun"))
				// set word category
				newWord.setCategory("Noun");
			else if (currentElt.hasAttribute("verb"))
				// set word category
				newWord.setCategory("Verb");
			else if (currentElt.hasAttribute("adjective"))
				// set word category
				newWord.setCategory("Adjective");
			String nodeName = node.getNodeName();
			// only print out name, college and year
			if (nodeName.equals("name")) {
				// store name in word
				newWord.setData(node.getTextContent());
			}
			library.add(newWord);
		}

		// recursively go through all nodes, including their childNodes
		if (node.hasChildNodes()) {
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				parseNode(nodeList.item(i));
			}
		}
	}

	/**
	 * get library
	 * 
	 * @return library
	 */
	public LinkedList<Word> getLibrary() {
		return library;
	}

	/**
	 * get random word in the library
	 * 
	 * @return random word
	 */
	public Word getRandomWord() {
		//check if the library is empty
		if (library.size() == 0) {
			System.out.println("No available word in the library.");
			return null;
		} 
		//if not empty
		else {
			int index = (int) ((library.size() - 1) * Math.random());
			return library.get(index);
		}
	}
	
}
