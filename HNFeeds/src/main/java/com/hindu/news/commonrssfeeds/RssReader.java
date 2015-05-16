package com.hindu.news.commonrssfeeds;

import android.util.*;
import android.util.Base64;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
/*
 * Copyright (C) 2014 Shirwa Mohamed <shirwa99@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class RssReader {

    private String rssUrl;

    public RssReader(String url) {

        /* URLConnection uc = url.openConnection();
        uc.setUseCaches(false);
        String val = "testfeed:testfeed1";
        byte[] base = val.getBytes();
        String authorizationString = "Basic " + android.util.Base64.encodeToString(base, Base64.NO_WRAP);
        uc.setRequestProperty ("Authorization", authorizationString);*/
        rssUrl = url;
    }

    public List<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        //Creates a new RssHandler which will do all the parsing.
        RssHandler handler = new RssHandler();
        //Pass SaxParser the RssHandler that was created.
        saxParser.parse(rssUrl, handler);
        return handler.getRssItemList();
    }

   /* public static RssFeed read(URL url) throws SAXException, IOException {
       *//* URLConnection uc = url.openConnection();
        uc.setUseCaches(false);
        String val = "testfeed:testfeed1";
        byte[] base = val.getBytes();
        String authorizationString = "Basic " + android.util.Base64.encodeToString(base, Base64.NO_WRAP);
        uc.setRequestProperty ("Authorization", authorizationString);*//*
        return read(url.openStream());

    }

    public static RssFeed read(InputStream stream) throws SAXException, IOException {

        try {
            System.out.println("News feed data..."+stream);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            RssHandler handler = new RssHandler();
            InputSource input = new InputSource(stream);

            reader.setContentHandler(handler);
            reader.parse(input);

            return handler.getResult();

        } catch (ParserConfigurationException e) {
        	e.printStackTrace();
            throw new SAXException();
        }

    }

    public static RssFeed read(String source) throws SAXException, IOException {
        return read(new ByteArrayInputStream(source.getBytes()));
    }*/
}
