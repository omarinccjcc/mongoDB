package org.mongodb.week1;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

		Spark.get(new Route("/") {

			@Override
			public Object handle(Request arg0, Response arg1) {
				try {
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

					Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");

					StringWriter write = new StringWriter();
					fruitPickerTemplate.process(fruitsMap, write);
					// TODO Auto-generated method stub
					return write;
				} catch (Exception e) {
					halt(500);
					return null;
				}
			}
		});

		Spark.post(new Route("/favorite_fruit") {
			@Override
			public Object handle(Request arg0, Response arg1) {
				final String fruit = arg0.queryParams("fruit");
				System.out.println("fruit: " + fruit);
				// TODO Auto-generated method stub
				if (fruit == null) {
					return "Why don't you pick one?";
				} else {
					return "Your favorite fruit is " + fruit;
				}

			}
		});
		
//        Spark.post(new Route("/favorite_fruit") {
//            @Override
//            public Object handle(final Request request, final Response response) {
//                final String fruit = request.queryParams("fruit");
//                if (fruit == null) {
//                    return "Why don't you pick one?";
//                }
//                else {
//                    return "Your favorite fruit is " + fruit;
//                }
//            }
//        });

	}
}
