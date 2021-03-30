package chess.controller;

import chess.domain.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    public WebController() {
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        List<User> users = new ArrayList<>();
        post("/users", (req, res) -> {
            User user = new User();
            user.setName(req.queryParams("name"));
            user.setAge(req.queryParams("age"));
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
