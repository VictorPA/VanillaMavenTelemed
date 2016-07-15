package jsonTrain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gwt.thirdparty.guava.common.collect.HashMultimap;
import com.jsonTrain.Cerise;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author Victor Papakirikos (vpa)
 * @since 11/07/2016
 */
public class JsonTest {


    private JsonObject jsonObject;
    private Gson gson;
    private String jsonString;

    private static int NBR_GRAINES = 10;
    private static double TAILLE = 60.21;
    private static String NOM = "rouge";
    private static boolean EST_MANGEABLE = Boolean.TRUE;


    @Before
    public void init() {


        jsonObject = new JsonObject();

        jsonObject.addProperty("nom", NOM);
        jsonObject.addProperty("graines", NBR_GRAINES);
        jsonObject.addProperty("taille", TAILLE);
        jsonObject.addProperty("estMangeable", EST_MANGEABLE);

        gson = new GsonBuilder().setPrettyPrinting().create();

        jsonString = gson.toJson(jsonObject);

    }

    @Test
    public void toJsonTest() {

        String jsonOutput = gson.toJson(jsonObject);
        System.out.println(jsonOutput);

    }

    @Test
    public void fromJsonTest() {

        Cerise cerise = gson.fromJson(jsonString, Cerise.class);
        assertThat(cerise.getNom(), is(NOM));
        assertThat(cerise.getGraines(), is(NBR_GRAINES));
        assertThat(cerise.getTaille(), is(TAILLE));
        assertThat(cerise.isEstMangeable(), is(EST_MANGEABLE));
    }

    @Test
    public void getGit() throws Exception {
        String urlString = "https://api.github.com/orgs/octokit/repos";
        StringBuilder result = new StringBuilder();

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        JsonReader jsonReader = gson.newJsonReader(rd);
        System.out.println(jsonReader.toString());
        rd.close();

    }


    @Test
    public void testXml2Jersey() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://trac.tentelemed.com:8080/rest/export/links");

        System.out.println(target.request(MediaType.APPLICATION_XML).get(String.class));
    }

}
