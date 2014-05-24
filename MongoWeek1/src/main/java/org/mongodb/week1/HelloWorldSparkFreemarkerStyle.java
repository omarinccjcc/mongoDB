package org.mongodb.week1;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreemarkerStyle {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request,
                                 final Response response) {

            	StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Freemarker, This is my first example with Spark and Freemarker");

                    helloTemplate.process(helloMap, writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
//            		return "Hello World::::";
            }
        });

	}

}
