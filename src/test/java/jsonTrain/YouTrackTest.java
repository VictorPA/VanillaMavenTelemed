package jsonTrain;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.impl.Log4JLogger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import java.util.*;

import static jsonTrain.YouTrackTest.YouTrackIssueFields.*;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public class YouTrackTest {

    List<String> cookies = new ArrayList<>();

    enum YouTrackIssueFields {
        PROJECT_SHOT_NAME,
        NUMBER_IN_PROJECT,
        SUMMARY,
        DESCRIPTION,
        CREATED,
        UPDATED,
        UPDATERNAME,
        UPDATERFULLNAME,
        REPORTERNAME,
        REPORTERFULLNAME,
        COMMENTSCOUNT,
        VOTES,
        LINKS,
        PRIORITY,
        TYPE,
        STATE,
        ASSIGNEE,
        SUBSTYSTEM,
        ESTIMATION

    }

    private static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Log4JLogger.class);

    private EnumMap<YouTrackIssueFields, String> youTrackIssueFieldsString = new EnumMap<>(YouTrackIssueFields.class);

    @Before
    public void testPost() throws ConfigurationException {
        RestTemplate rest = new RestTemplate();
        PropertiesConfiguration pc = new PropertiesConfiguration("config.properties");

        HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(formHttpMessageConverter);
        list.add(stringHttpMessageConverternew);
        rest.setMessageConverters(list);

        String login = (String) pc.getProperty("login");
        String password = (String) pc.getProperty("password");


        final String url = "http://trac.tentelemed.com:8080/youtrack/rest/user/login?{login}&{password}";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("login", login);
        map.add("password", password);
        map.add("Content-Length", "24");
        map.add("Connection", "keep-alive");
        map.add("Content-Type", "application/x-www-form-urlencoded");
        map.add("Cookie", "login=" + login + "&password=" + password);

        ResponseEntity<String> responseEntity = rest.postForEntity(url, map, String.class, map);

        LOGGER.info("HEADERS : ");
        HttpHeaders headers = responseEntity.getHeaders();
        cookies = headers.get("Set-Cookie");

    }

    @Test
    public void testXml() throws DocumentException, FileNotFoundException, MalformedURLException, SAXException {

        final String url2 = "http://trac.tentelemed.com:8080/youtrack/rest/issue/CMPT-129";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.add("Cookie", cookies.get(0));
        HttpEntity<String> entity = new HttpEntity<>("parameters", newHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url2, HttpMethod.GET, entity, String.class);
        String xmlResult = result.getBody();
        File file = new File("./temp.xml");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(xmlResult);
        printWriter.close();

        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        file.delete();
        List<Node> nodes = document.selectNodes("issue/field/*");

        System.out.println("----------------------------");
        for (Node node : nodes) {
            if (node instanceof Element) {
                String name = node.getParent().attributeValue("name");
                Element element = (Element) node;
                if (name.equals("projetShortName"))
                    this.youTrackIssueFieldsString.put(PROJECT_SHOT_NAME, element.getText());
                if (name.equals("numberInProjet"))
                    this.youTrackIssueFieldsString.put(NUMBER_IN_PROJECT, element.getText());
                if (name.equals("summary")) this.youTrackIssueFieldsString.put(SUMMARY, element.getText());
                if (name.equals("description")) this.youTrackIssueFieldsString.put(DESCRIPTION, element.getText());
                if (name.equals("created")) this.youTrackIssueFieldsString.put(CREATED, element.getText());
                if (name.equals("updated")) this.youTrackIssueFieldsString.put(UPDATED, element.getText());
                if (name.equals("updaterName")) this.youTrackIssueFieldsString.put(UPDATERNAME, element.getText());
                if (name.equals("updaterFullName"))
                    this.youTrackIssueFieldsString.put(UPDATERFULLNAME, element.getText());
                if (name.equals("reporterName")) this.youTrackIssueFieldsString.put(REPORTERNAME, element.getText());
                if (name.equals("reporterFullName"))
                    this.youTrackIssueFieldsString.put(REPORTERFULLNAME, element.getText());
                if (name.equals("commentsCount")) this.youTrackIssueFieldsString.put(COMMENTSCOUNT, element.getText());
                if (name.equals("votes")) this.youTrackIssueFieldsString.put(VOTES, element.getText());
                if (name.equals("links")) this.youTrackIssueFieldsString.put(LINKS, element.getText());
                if (name.equals("priority")) this.youTrackIssueFieldsString.put(PRIORITY, element.getText());
                if (name.equals("type")) this.youTrackIssueFieldsString.put(TYPE, element.getText());
                if (name.equals("state")) this.youTrackIssueFieldsString.put(STATE, element.getText());
                if (name.equals("assignee")) this.youTrackIssueFieldsString.put(ASSIGNEE, element.getText());
                if (name.equals("subsystem")) this.youTrackIssueFieldsString.put(SUBSTYSTEM, element.getText());
                if (name.equals("estimation")) this.youTrackIssueFieldsString.put(ESTIMATION, element.getText());
            }

        }

        System.out.println(this.youTrackIssueFieldsString.get(DESCRIPTION));

    }

}
