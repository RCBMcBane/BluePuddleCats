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
 * @version 11/2/18 Written for HackHolyoke 2018
 *
 */
public class Library {

	private LinkedList<Word> library = new LinkedList<Word>();

	/**
	 * Constructor reads file
	 */
	public Library(String version) {
		try {
			// Setup XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			if (version.equals("Word Game")) {
				File xmlFile = new File("WordLibrary.xml");
				Document document = builder.parse(xmlFile);
				parseFounderFile(document);
			} else if (version.equals("Recycle Game")) {
				File xmlFile = new File("RecycleLibrary.xml");
				Document document = builder.parse(xmlFile);
				parseFounderFile(document);
			}
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

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.getNodeName().equals("word_library")
					|| node.getNodeName().equals("recycle_library") && node.hasChildNodes()) {
				// for loop through all first-child nodes
				NodeList nodeList = node.getChildNodes();
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node firstChildNode = nodeList.item(i);
					// check if child node is an element
					if (firstChildNode.getNodeType() == Node.ELEMENT_NODE) {
						// rename element node
						Element currentElt = (Element) firstChildNode;
						// check if the node is word
						String nodeName = firstChildNode.getNodeName();
						if (nodeName.equals("word") && firstChildNode.hasChildNodes()) {
							Word newWord = new Word();
							newWord.setCategory(currentElt.getAttribute("category"));
							// for loop through second-child nodes
							NodeList nodeList2 = firstChildNode.getChildNodes();
							for (int j = 0; j < nodeList2.getLength(); j++) {
								Node secondChildNode = nodeList2.item(j);
								// check if child node is an element
								if (secondChildNode.getNodeType() == Node.ELEMENT_NODE) {
									// check if the node is name
									String nodeName2 = secondChildNode.getNodeName();
									if (nodeName2.equals("name")) {
										newWord.setData(secondChildNode.getTextContent());
										library.add(newWord);
									}
								}
							}
						}
					}
				}
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
		// check if the library is empty
		if (library.size() == 0) {
			System.out.println("No available word in the library.");
			return null;
		}
		// if not empty
		else {
			int index = (int) ((library.size() - 1) * Math.random());
			return library.get(index);
		}
	}

	/**
	 * display every word in word library
	 */
	public String toString() {
		String libraryToPrint = "";
		for (int i = 0; i < library.size(); i++)
			libraryToPrint += library.get(i).toString() + "\n";
		return libraryToPrint;
	}
}
