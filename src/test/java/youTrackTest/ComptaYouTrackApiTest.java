package youTrackTest;

import com.youTrack.Issue;
import com.youTrack.ComptaYouTrackApi;
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
public class ComptaYouTrackApiTest {
    @Test
    public void test() throws ConfigurationException, FileNotFoundException, MalformedURLException, SAXException, DocumentException {
        ComptaYouTrackApi comptaYouTrackApi = new ComptaYouTrackApi();
        comptaYouTrackApi.getVpaCredentials();
        Issue issue = comptaYouTrackApi.getIssue("CMPT-129");
        Assert.assertThat(issue.getEstimation(), is("2 Points"));
        Assert.assertThat(issue.getAssignee(), is("vpa"));
        System.out.println(issue.toString());

    }
}
