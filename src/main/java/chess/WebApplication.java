package chess;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecture.pobi.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        List<User> users = new ArrayList<>();

        post("/members", (req, res) -> {
            User user = new User(req.queryParams("name"), req.queryParams("age"));
            users.add(user);

            Map<String, Object> model = new HashMap<>();
            model.put("users", users);

            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
