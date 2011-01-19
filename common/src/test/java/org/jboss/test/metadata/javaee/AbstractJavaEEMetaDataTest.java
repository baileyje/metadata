/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.test.metadata.javaee;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.jboss.metadata.parser.util.NoopXmlResolver;
import junit.framework.TestCase;

/**
 * A JavaEE metadata Test.
 * 
 * @author jfclere
 * @version $Revision: 1.2 $
 */
public class AbstractJavaEEMetaDataTest extends TestCase
{
   public AbstractJavaEEMetaDataTest()
   {
      super();
   }

   public AbstractJavaEEMetaDataTest(String name)
   {
      super(name);
   }
   
   /**
    * Open some xml
    * 
    * @return the XMLStreamReader
    * @throws Exception for any error
    */
   protected XMLStreamReader getReader() throws Exception
   {
      String name = getClass().getSimpleName();
      int index = name.lastIndexOf("UnitTestCase");
      if (index != -1)
         name = name.substring(0, index);
      name = name + "_" + getName() + ".xml";
      return getReader(name);
   }


   /**
    * Open a xml file
    * 
    * @param <T> the expected type
    * @param name the name
    * @param expected the expected type
    * @param resolver the resolver
    * @return the unmarshalled object
    * @throws Exception for any error
    */
   protected XMLStreamReader getReader(String name) throws Exception
   {
	   final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	   // FileInputStream  is = new FileInputStream(findXML(name));
	   inputFactory.setXMLResolver(NoopXmlResolver.create());
	   return inputFactory.createXMLStreamReader(findXML(name));
   }

   /**
    * Find the xml
    * 
    * @param name the name
    * @return the url of the xml
 * @throws IOException 
    */
   protected  InputStream findXML(String name) throws IOException
   {
      URL url = getResource(name);
      if (url == null)
         fail(name + " not found");
      return url.openStream();
   }

   /**
    * Find the xml
    * 
    * @param name the name
    * @return the url of the xml
    */
   protected static String findXSD(String name)
   {
      URL url = findResource(AbstractJavaEEMetaDataTest.class, "test" +"/" + name);
      if (url == null)
         fail(name + " not found");
      return url.toString();
   }
   public URL getResource(final String name)
   {
      return findResource(getClass(), name);
   }
   public static URL findResource(final Class<?> clazz, final String name)
   {
	  return clazz.getResource(name);
   }
}
