package chess;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            System.out.println("리퀘스트 바디 : "  + req.body());
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
