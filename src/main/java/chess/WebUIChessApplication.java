package chess;

import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.controller.ChessWebController;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
//        Spark.port(8080);

        staticFileLocation("/static");
        final ChessWebController webController = new ChessWebController();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (request, response) -> {
            Map<String, String> boardInfo = webController.start();
            return gson.toJson(boardInfo);
        });
        put("/move", (request, response) -> {
            Map<String, String> requestBody = gson.fromJson(request.body(), HashMap.class) ;
            Map<String, String> boardInfo = webController.movedPiece(requestBody.get("source"), requestBody.get("target"));
            return gson.toJson(boardInfo);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
