package com.youTrack;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.impl.Log4JLogger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public class YouTrackApi {

    private static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Log4JLogger.class);
    private String credentials;

    public void getCredentials() throws ConfigurationException {
        PropertiesConfiguration pc = new PropertiesConfiguration("config.properties");
        String login = (String) pc.getProperty("login");
        String password = (String) pc.getProperty("password");
        this.getCredentials(login, password);
    }

    public String getCredentials(String login, String password) throws ConfigurationException {

        RestTemplate rest = new RestTemplate();
        configure(rest);

        String url = "http://trac.tentelemed.com:8080/youtrack/rest/user/login?{login}&{password}";

        MultiValueMap<String, String> parameters = configureParameters(login, password);
        ResponseEntity<String> responseEntity = rest.postForEntity(url, parameters, String.class, parameters);

        HttpHeaders headers = responseEntity.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");
        this.credentials = cookies.get(0);

        return credentials;
    }

    public Issue getIssue(String issueId) throws DocumentException, FileNotFoundException, MalformedURLException, SAXException {

        HttpHeaders httpHeaders = new HttpHeaders();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_XML);

        httpHeaders.setAccept(mediaTypes);
        httpHeaders.add("Cookie", this.credentials);

        String url = "http://trac.tentelemed.com:8080/youtrack/rest/issue/" + issueId;
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String xmlResult = result.getBody();

        Document document = makeDocument(xmlResult);
        List<Node> nodes = document.selectNodes("issue/field/*");
        Issue issue = new Issue();
        for (Node node : nodes) {
            fillField(issue, node);
        }
        return issue;
    }

    private MultiValueMap<String, String> configureParameters(String login, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("password", password);
        map.add("Content-Length", "24");
        map.add("Connection", "keep-alive");
        map.add("Content-Type", "application/x-www-form-urlencoded");
        map.add("Cookie", "login=" + login + "&password=" + password);
        return map;
    }

    private void configure(RestTemplate rest) {
        HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(formHttpMessageConverter);
        list.add(stringHttpMessageConverternew);
        rest.setMessageConverters(list);
    }

    private Document makeDocument(String xmlResult) throws FileNotFoundException, MalformedURLException, DocumentException {

        File file = new File("./temp.xml");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(xmlResult);
        printWriter.close();
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);

        return document;
    }

    private void fillField(Issue issue, Node node) {
        if (node instanceof Element) {
            String name = node.getParent().attributeValue("name");
            Element element = (Element) node;
            if (name.toLowerCase().contains("projetShortName")) issue.setProjectShortName(element.getText());
            if (name.toLowerCase().contains("numberInProjet")) issue.setNumberInProject(element.getText());
            if (name.toLowerCase().contains("summary")) issue.setNumberInProject(element.getText());
            if (name.toLowerCase().contains("description")) issue.setDescription(element.getText());
            if (name.toLowerCase().contains("created")) issue.setCreated(element.getText());
            if (name.toLowerCase().contains("updated")) issue.setUpdated(element.getText());
            if (name.toLowerCase().contains("updaterName")) issue.setUpdaterName(element.getText());
            if (name.toLowerCase().contains("updaterFullName")) issue.setUpdaterFullName(element.getText());
            if (name.toLowerCase().contains("reporterName")) issue.setReporterName(element.getText());
            if (name.toLowerCase().contains("reporterFullName")) issue.setReporterFullName(element.getText());
            if (name.toLowerCase().contains("commentsCount")) issue.setCommentsCount(element.getText());
            if (name.toLowerCase().contains("votes")) issue.setVotes(element.getText());
            if (name.toLowerCase().contains("links")) issue.setLinks(element.getText());
            if (name.toLowerCase().contains("priority")) issue.setPriority(element.getText());
            if (name.toLowerCase().contains("type")) issue.setType(element.getText());
            if (name.toLowerCase().contains("state")) issue.setState(element.getText());
            if (name.toLowerCase().contains("assignee")) issue.setAssignee(element.getText());
            if (name.toLowerCase().contains("subsystem")) issue.setSubsystem(element.getText());
            if (name.toLowerCase().contains("estimation")) issue.setEstimation(element.getText());
        }
    }


}

