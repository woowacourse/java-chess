package chess;

import chess.controller.WebChessController;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/static");
        WebChessController webChessController = new WebChessController();

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "board.html");
        });

        get("/game/path", (request, response) -> {
           String source = request.queryParams("source");
            return webChessController.movablePath(source).getPath();
        }, gson::toJson);

        post("/game/move", (request, response) -> {
            String source = request.queryParams("source");
            String target = request.queryParams("target");
            webChessController.move(source, target);
            return "/board.html";
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
