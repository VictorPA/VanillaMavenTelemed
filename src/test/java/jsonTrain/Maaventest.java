package jsonTrain;

import com.autow.Jardin;
import com.config.SpringConfiguration;
import com.xmlassource.MonMaaven;
import org.junit.Test;

import static org.junit.Assert.*;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Victor on 30/05/2016.
 */
public class Maaventest {
   /* @Test
    public void monMaavenTest() throws Exception {
        PropertiesConfiguration conf = new PropertiesConfiguration("application.properties");
        MonMaaven monMaaven = new MonMaaven(3);
        assertEquals(monMaaven.getTest(), 3);
        assertEquals("hello!",conf.getString("message"));
        // assertEquals();
    }

    @Test
    public void autoWiredTest(){
        Jardin jardinManuel = new Jardin();
        assertTrue(jardinManuel.getArbre() == null);

        ConfigurableApplicationContext contextAuto = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        Jardin jardin = (Jardin)contextAuto.getBean("jardin");
        assertTrue(jardin != null);
        assertTrue(jardin.getArbre() != null);
        assertEquals("L'arbre est bien là",jardin.getArbre().toString());
    }
*/
}