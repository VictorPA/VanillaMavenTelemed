package com.integrationMails;

import com.mesTemplates.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.StringWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 07/06/2016.
 */
public class envoiMail {

    public static void main(String[] args) throws Exception {

        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration();
        Path path = FileSystems.getDefault().getPath("src","main","java","com","integrationMails");
        String absolutePath = path.toAbsolutePath().toString();
        cfg.setDirectoryForTemplateLoading(new File(absolutePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // cfg.setLogTemplateExceptions(false);

        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

        /* Create a data-model */
        Map<String,Object> root = new HashMap<>();

        Map<String, String> userData = new HashMap<>();
        userData.put("MAIL1","USER1");
        userData.put("MAIL2","USER2");
        root.put("owner", "OWNER");
        root.put("caption", "TITLE");
        root.put("location", "LOCATION");
        root.put("month", "MOIS");
        root.put("day", "JOUR");
        root.put("year", "YEAR");
        root.put("startMinutes", "MINUTES");
        root.put("startHour", "HOUR");
        root.put("endMinutes", "MINUTES");
        root.put("endHour", "HOUR");
        root.put("description", "DESCRIPTION");
        root.put("rows", 2*2);
        root.put("userData", userData);

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("mail.html");

        /* Merge data-model with template */
        StringWriter out = new StringWriter();

        temp.process(root, out);
        String message = out.toString();
        System.out.println(message);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }
}
