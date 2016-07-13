package jsonTrain;

import com.youTrack.Issue;
import com.youTrack.YouTrackApi;
import org.apache.commons.configuration.ConfigurationException;
import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static org.hamcrest.core.Is.is;

/**
 * @author Victor Papakirikos (vpa)
 * @since 13/07/2016
 */
public class YouTrackApiTest {
    @Test
    public void test() throws ConfigurationException, FileNotFoundException, MalformedURLException, SAXException, DocumentException {
        YouTrackApi youTrackApi = new YouTrackApi();
        youTrackApi.getVpaCredentials();
        Issue issue = youTrackApi.getIssue("CMPT-129");
        Assert.assertThat(issue.getEstimation(), is("2 Points"));
        Assert.assertThat(issue.getAssignee(), is("vpa"));
    }
}
