package com.youTrack;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public class ComptaYouTrackConnection {

    private static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Log4JLogger.class);
    private String credentials;


    public String getCredentials(String login, String password) throws ConfigurationException {

        RestTemplate rest = new RestTemplate();
        configure(rest);

        String url = "http://trac.tentelemed.com:8080/youtrack/rest/user/login?{login}&{password}";

        MultiValueMap<String, String> parameters = setParameters(login, password);
        ResponseEntity<String> responseEntity = rest.postForEntity(url, parameters, String.class, parameters);

        HttpHeaders headers = responseEntity.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");
        this.credentials = cookies.get(0);

        return credentials;
    }

    public void getDevelopperCredentials() throws ConfigurationException {
        PropertiesConfiguration pc = new PropertiesConfiguration("config.properties");
        String login = (String) pc.getProperty("login");
        String password = (String) pc.getProperty("password");
        this.getCredentials(login, password);
    }


    private void configure(RestTemplate rest) {
        HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(formHttpMessageConverter);
        list.add(stringHttpMessageConverternew);
        rest.setMessageConverters(list);
    }

    private MultiValueMap<String, String> setParameters(String login, String password) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("password", password);
        map.add("Content-Length", "24");
        map.add("Connection", "keep-alive");
        map.add("Content-Type", "application/x-www-form-urlencoded");
        map.add("Cookie", "login=" + login + "&password=" + password);
        return map;
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

        return createIssueFrom(fields);

    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

    private Issue createIssueFrom(List<Map<String, Object>> fields) {
        Issue issue = new Issue();
        for (Map<String, Object> data : fields) {
            if (String.valueOf(data.get("name")).toLowerCase().contains("projectshortname"))
                issue.setProjectShortName(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("numberinproject"))
                issue.setNumberInProject(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("summary"))
                issue.setSummary(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("created"))
                issue.setCreated(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("updated"))
                issue.setUpdated(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("type"))
                issue.setType((String) ((List) (data.get("value"))).get(0));
            if (String.valueOf(data.get("name")).toLowerCase().contains("state"))
                issue.setState(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("assignee"))
                issue.setAssignee(String.valueOf(((Map) ((List) data.get("value")).get(0)).get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("subsystem"))
                issue.setSubsystem(String.valueOf(data.get("value")));
            if (String.valueOf(data.get("name")).toLowerCase().contains("estimation"))
                issue.setEstimation(String.valueOf(data.get("value")));
        }
        return issue;
    }


}

