package com.youTrack;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public abstract class YoutrackXmlDocument {


    private YoutrackXmlDocument() {
        //No external subclassing allowed
    }

    public static YoutrackXmlDocument getYouTrackXmlDocument(String xmlResult) {
        try {
            return new FunctionnalYouTrackXmlDocument(xmlResult);
        } catch (DocumentException e) {
            return new NullYouTrackXmlDocument(xmlResult);
        }
    }

    public abstract List selectNodes(String xpathExpression);

    private static class FunctionnalYouTrackXmlDocument extends YoutrackXmlDocument {
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

    private static class NullYouTrackXmlDocument extends YoutrackXmlDocument {
        private NullYouTrackXmlDocument(String xmlResult) {

        }

        @Override public List selectNodes(String xpathExpression) {
            return Collections.EMPTY_LIST;
        }
    }
}
