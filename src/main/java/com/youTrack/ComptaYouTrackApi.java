package com.youTrack;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.impl.Log4JLogger;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.Assert.assertThat;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public class ComptaYouTrackApi {

    private static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Log4JLogger.class);
    private String credentials;

    public void getVpaCredentials() throws ConfigurationException {
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

    public Issue getIssue(String issueId) {

        String xpathExpression = "issue/field/*";
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Cookie", this.credentials);

        String url = "http://trac.tentelemed.com:8080/youtrack/rest/issue/" + issueId;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameters", httpHeaders);
        restTemplate.setMessageConverters(getMessageConverters());

        ResponseEntity<Map> result2 = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String, List<Map<String, Object>>> myIssue = result2.getBody();
        List<Map<String, Object>> fields = myIssue.get("field");
        Issue issue = new Issue();
        for (Map<String, Object> data : fields) {
            if (String.valueOf(data.get("name")).toLowerCase().contains("projectshortname"))
                issue.setProjectShortName(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("numberInProject"))
                issue.setNumberInProject(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("summary"))
                issue.setSummary(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("description"))
                issue.setDescription(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("created"))
                issue.setCreated(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("updated"))
                issue.setUpdated(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("updatername"))
                issue.setUpdaterName(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("updaterfullname"))
                issue.setUpdaterFullName(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("reportername"))
                issue.setReporterName(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("reporterfullname"))
                issue.setReporterFullName(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("commentscount"))
                issue.setCommentsCount(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("votes"))
                issue.setVotes(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("type"))
                issue.setType((String) ((List) (data.get("value"))).get(0));
            if (String.valueOf(data.get("name")).toLowerCase().contains("state"))
                issue.setState(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("assignee"))
                issue.setAssignee(String.valueOf(((Map) (((List) (data.get("value"))).get(0))).get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("subsystem"))
                issue.setSubsystem(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("estimation"))
                issue.setEstimation(String.valueOf(data.get("value")));
        }

        System.out.println(myIssue);

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

    private void fillField(Issue issue, Node node) {
        if (node instanceof Element) {
            String name = node.getParent().attributeValue("name");
            Element element = (Element) node;
            if (name.toLowerCase().contains("projectshortname")) issue.setProjectShortName(element.getText());
            if (name.toLowerCase().contains("numberinproject")) issue.setNumberInProject(element.getText());
            if (name.toLowerCase().contains("summary")) issue.setSummary(element.getText());
            if (name.toLowerCase().contains("description")) issue.setDescription(element.getText());
            if (name.toLowerCase().contains("created")) issue.setCreated(element.getText());
            if (name.toLowerCase().contains("updated")) issue.setUpdated(element.getText());
            if (name.toLowerCase().contains("updatername")) issue.setUpdaterName(element.getText());
            if (name.toLowerCase().contains("updaterfullname")) issue.setUpdaterFullName(element.getText());
            if (name.toLowerCase().contains("reportername")) issue.setReporterName(element.getText());
            if (name.toLowerCase().contains("reporterfullname")) issue.setReporterFullName(element.getText());
            if (name.toLowerCase().contains("commentscount")) issue.setCommentsCount(element.getText());
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

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

}

