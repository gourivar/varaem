package com.mycompany.myrestservice.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;

import com.mycompany.myrestservice.Cricketscore;
  
//This is a component so it can provide or consume services
@Component 
     
@Service
public class CricketscoreImpl implements Cricketscore {
 protected final Logger log = LoggerFactory.getLogger(this.getClass());
    public String[] getLivescore() {
        // TODO Auto-generated method stub
          
        try
        {
         
         String title = "";
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet("http://static.cricinfo.com/rss/livescores.xml");
            getRequest.addHeader("accept", "application/xml");
            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
              throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String myXMLdata = "";
            String output;
            while ((output = br.readLine()) != null) {
             myXMLdata = myXMLdata + output;
            }
            httpClient.getConnectionManager().shutdown();
                         
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(myXMLdata));
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("item");
            String[] titles = new String[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++)
            {
              Element element = (Element)nodes.item(i);
              NodeList name = element.getElementsByTagName("link");
              Element line = (Element)name.item(0);
              title = getCharacterDataFromElement(line);
              titles[i] = title;
            }
            return titles;
        }
        catch (Exception e)
        {
            e.printStackTrace() ;
        }
        return null;
    }
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
           CharacterData cd = (CharacterData) child;
           return cd.getData();
        }
        return "";
      }
  }