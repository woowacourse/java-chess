package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        // http://localhost:4567/hello/philz
        get("/hello/:name", (req, res) -> {
            return "hello ~! " + req.params(":name");
        });

        // http://localhost:4567/hello2?name=philz
        get("/hello2", (req, res) -> {
            return "hello ~! " + req.queryParams("name");
        });

        // http://localhost:4567/hello3?name=philz&age=123
        get("/hello3", (req, res) -> {
            return "hello ~! " + req.queryParams("name") + ", 나이는 " + req.queryParams("age");
        });

        post("/member", (req, res) -> {
            return "save ~! " + req.queryParams("name") + ", 나이는 " + req.queryParams("age");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
