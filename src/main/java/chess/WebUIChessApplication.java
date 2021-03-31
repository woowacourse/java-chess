package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    public static void main(String[] args) {
//        Spark.port(8080);

        staticFileLocation("/static");
        final ChessService webController = new ChessService();
        Gson gson = new Gson();

        get("/room", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "room.html");
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (request, response) -> {
            Map<String, String> boardInfo = webController.start();
            return gson.toJson(boardInfo);
        });

        post("/join", (request, response) -> {
            Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class) ;
            int boardId = (int) (double) requestBody.get("boardId");
            Map<String, String> boardInfo = webController.joinBoard(boardId);
            return gson.toJson(boardInfo);
        });

        put("/move", (request, response) -> {
            Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class) ;
            int boardId = (int) (double) requestBody.get("boardId");
            String source = (String) requestBody.get("source");
            String target = (String) requestBody.get("target");
            Map<String, String> boardInfo = webController.movedPiece(boardId, source, target);
            return gson.toJson(boardInfo);
        });

        get("/movablePositions", (request, response) -> {
            String boardId = request.queryParams("boardId");
            String source = request.queryParams("source");
            List<String> movablePositions = webController.movablePositions(Integer.parseInt(boardId), source);
            return gson.toJson(movablePositions);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
