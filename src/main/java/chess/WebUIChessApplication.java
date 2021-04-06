package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public/assets");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
