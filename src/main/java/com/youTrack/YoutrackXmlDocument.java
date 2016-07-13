package com.youTrack;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public abstract class YouTrackXmlDocument {
    protected Document document;

    private YouTrackXmlDocument() {
    }

    public static YouTrackXmlDocument getYouTrackXmlDocument(String xmlResult) {
        try {
            return new FunctionnalYouTrackXmlDocument(xmlResult);
        } catch (FileNotFoundException | MalformedURLException | DocumentException e) {
            return new NullYouTrackXmlDocument(xmlResult);
        }
    }

    public abstract List<Node> selectNodes(String xpathExpression);

    private static class FunctionnalYouTrackXmlDocument extends YouTrackXmlDocument {

        private FunctionnalYouTrackXmlDocument(String xmlResult) throws FileNotFoundException, MalformedURLException, DocumentException {

            File file = new File("./temp.xml");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(xmlResult);
            printWriter.close();
            SAXReader reader = new SAXReader();
            this.document = reader.read(file);
        }

        @Override
        public List<Node> selectNodes(String xpathExpression) {
            return document.selectNodes(xpathExpression);
        }


    }


    private static class NullYouTrackXmlDocument extends YouTrackXmlDocument {


        private NullYouTrackXmlDocument(String xmlResult) {

        }

        @Override public List<Node> selectNodes(String xpathExpression) {
            return Collections.EMPTY_LIST;
        }
    }
}
