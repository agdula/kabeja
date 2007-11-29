package org.kabeja.ui.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.kabeja.processing.ProcessingManager;
import org.kabeja.tools.SAXProcessingManagerBuilder;
import org.kabeja.ui.Component;
import org.kabeja.ui.impl.ServiceContainer;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXServiceContainerBuilder implements ContentHandler{

	
	public static final String NAMESPACE="http://kabeja.org/processing/ui/1.0";
	public static final String ELEMET_UICONFIGURATION="uiconfiguration";
	public static final String ELEMET_COMPONENTS="components";
	public static final String ELEMET_COMPONENT="component";
	public static final String ATTRIBUTE_CLASS="class";
	
	protected ServiceContainer container;
	protected boolean components=false;
	
	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	
		
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void startDocument() throws SAXException {
		this.container = new ServiceContainer();
		this.components=false;
		
	}

	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {
		if (uri.equals(NAMESPACE)) {

			if (ELEMET_COMPONENT.equals(localName) && this.components) {
				String className = atts.getValue(ATTRIBUTE_CLASS);
				try {
					Object obj = this.getClass().getClassLoader().loadClass(className).newInstance();
					this.container.addComponent((Component)obj);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(ELEMET_COMPONENTS.equals(localName)){
			this.components=true;
		}
		}
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	
	public ServiceContainer getServiceContainer(){
		return this.container;
		
		
	}
	
	public static ServiceContainer buildFromStream(InputStream in) {
		SAXServiceContainerBuilder builder = new SAXServiceContainerBuilder();

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			factory.setXIncludeAware(true);
			XMLReader saxparser = factory.newSAXParser().getXMLReader();

			saxparser.setContentHandler(builder);
			saxparser.parse(new InputSource(in));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return builder.getServiceContainer();
	}
	
}
