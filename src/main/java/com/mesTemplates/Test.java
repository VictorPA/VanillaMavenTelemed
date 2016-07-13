package com.mesTemplates;

/**
 * Created by Victor on 06/06/2016.
 */
import freemarker.template.*;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

public class Test {

    public static void main(String[] args) throws Exception {

        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */

        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration();
        Path path = FileSystems.getDefault().getPath("src","main","java","com","mesTemplates");
        String absolutePath = path.toAbsolutePath().toString();
        cfg.setDirectoryForTemplateLoading(new File(absolutePath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
       // cfg.setLogTemplateExceptions(false);

        /* ------------------------------------------------------------------------ */
        /* You usually do these for MULTIPLE TIMES in the application life-cycle:   */

        /* Create a data-model */
        Map<String,Object> root = new HashMap<>();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);
        String[] usersArray = new String[]{"Salut","Cava"};
        List<String> users = Arrays.asList(usersArray);
        root.put("users",users);

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("template.html");

        /* Merge data-model with template */
        StringWriter out = new StringWriter();



        temp.process(root, out);
        String message = out.toString();
        System.out.println(message);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }
}