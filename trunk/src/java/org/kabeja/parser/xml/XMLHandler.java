/*
   Copyright 2009 Simon Mieth

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.kabeja.parser.xml;

import java.util.Map;

import org.kabeja.parser.Handler;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public interface XMLHandler{
	
	
    
	public String getNamespaceHandle();
	
	public String getElementNameHandle();
	
	public String getQNameHandle();
	
	/**
	 * 
	 * @param namespace
	 * @param name
	 * @param attributes
	 * @param parsingContext
	 * @return delegate nested elements
	 * @throws SAXException
	 */
	public boolean startParseElement(String namespace,
			String name, Attributes attributes, ParsingContext parsingContext) throws SAXException ;

	public void endParseElement(String namespace, String name, ParsingContext parsingContext)throws SAXException ;
	
	public void characters(char[] ch, int start, int length)
	throws SAXException;
	
	
}
