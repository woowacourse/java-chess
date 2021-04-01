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
        final ChessService chessService = new ChessService();
        Gson gson = new Gson();

        get("/room", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "room.html");
        });

        get("/chess", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Object boardId = request.queryParams("boardId");
            model.put("boardId", boardId);
            return render(model, "index.html");
        });

        post("/create", (request, response) -> {
            Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class);

            String whitePlayer = (String) requestBody.get("whitePlayer");
            String blackPlayer = (String) requestBody.get("blackPlayer");
            Map<String, Integer> boardInfo = chessService.start(whitePlayer, blackPlayer);
            return gson.toJson(boardInfo);
        });

        post("/join", (request, response) -> {
            Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class) ;
            System.out.println("이동");
            int boardId = (int) (double) requestBody.get("boardId");
            Map<String, String> boardInfo = chessService.joinBoard(boardId);
            return gson.toJson(boardInfo);
        });

        put("/move", (request, response) -> {
            Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class) ;
            int boardId = (int) (double) requestBody.get("boardId");
            String source = (String) requestBody.get("source");
            String target = (String) requestBody.get("target");
            Map<String, String> boardInfo = chessService.movedPiece(boardId, source, target);
            return gson.toJson(boardInfo);
        });

        get("/movablePositions", (request, response) -> {
            String boardId = request.queryParams("boardId");
            String source = request.queryParams("source");
            List<String> movablePositions = chessService.movablePositions(Integer.parseInt(boardId), source);
            return gson.toJson(movablePositions);
        });

        get("/search", (request, response) -> {
            String playerName = request.queryParams("playerName");
            List<Map<String, Object>> boards = chessService.searchBoard(playerName);
            return gson.toJson(boards);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
