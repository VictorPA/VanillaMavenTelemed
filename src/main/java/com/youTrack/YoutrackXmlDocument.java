package com.youTrack;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public abstract class YouTrackXmlDocument {


    private YouTrackXmlDocument() {
        //No external subclassing allowed
    }

    public static YouTrackXmlDocument getYouTrackXmlDocument(String xmlResult) {
        try {
            return new FunctionnalYouTrackXmlDocument(xmlResult);
        } catch (DocumentException e) {
            return new NullYouTrackXmlDocument(xmlResult);
        }
    }

    public abstract List selectNodes(String xpathExpression);

    private static class FunctionnalYouTrackXmlDocument extends YouTrackXmlDocument {
        private Document document;

        private FunctionnalYouTrackXmlDocument(String xmlResult) throws DocumentException {
            SAXReader reader = new SAXReader();

            try (StringReader stringReader = new StringReader(xmlResult)) {
                this.document = reader.read(stringReader);
            }

        }

        @Override
        public List selectNodes(String xpathExpression) {
            return this.document.selectNodes(xpathExpression);
        }

        @Override public String toString() {

            return document.asXML();
        }
    }

    private static class NullYouTrackXmlDocument extends YouTrackXmlDocument {
        private NullYouTrackXmlDocument(String xmlResult) {

        }

        @Override public List selectNodes(String xpathExpression) {
            return Collections.EMPTY_LIST;
        }
    }
}
