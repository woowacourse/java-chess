package chess;

import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.controller.WebController;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {

        staticFileLocation("/public");
        WebController webController = new WebController();
        Gson gson = new Gson();
//
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "index.html");
//        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "board.html");
        });

//        get("/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "board.html");
//        });
//
        get("/new", (req, res) -> {
            Map<String, String> model = webController.startGame();
//            return render(model, "board.html");
            return gson.toJson(model);
        });

        put("/move", (req, res) -> {
            Map<String, String> requestBody = gson.fromJson(req.body(), HashMap.class);
            Map<String, String> model = webController.move(requestBody.get("source"), requestBody.get("target"));
            return gson.toJson(model);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
