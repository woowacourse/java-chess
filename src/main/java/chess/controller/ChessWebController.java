package chess.controller;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.service.ChessService;
import chess.ResponseStatus;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public final class ChessWebController {

    private final ChessService chessService;
    private final Gson gson;

    public ChessWebController() {
        this.chessService = new ChessService();
        gson = new Gson();
    }

    public void run() {
        staticFileLocation("/static");

        get("/room", roomRender());

        get("/chess", chessRender());

        post("/create", createBoard());

        post("/join", joinBoard());

        put("/move", movePiece());

        get("/movablePositions", movablePosition());

        get("/search", searchBoard());
    }

    private Route roomRender() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "room.html");
        };
    }

    private Route chessRender() {
        return (request, response) -> {
            try {
                Map<String, Object> model = new HashMap<>();
                Object boardId = request.queryParams("boardId");
                model.put("boardId", boardId);
                return render(model, "index.html");
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST);
            }
        };
    }

    private Route createBoard() {
        return (request, response) -> {
            try {
                Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class);
                String whitePlayer = (String) requestBody.get("whitePlayer");
                String blackPlayer = (String) requestBody.get("blackPlayer");
                Map<String, Integer> boardInfo = chessService.start(whitePlayer, blackPlayer);
                return gson.toJson(boardInfo);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST);
            }
        };
    }

    private Route joinBoard() {
        return (request, response) -> {
            try {
                Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class);
                int boardId = (int) (double) requestBody.get("boardId");
                Map<String, String> boardInfo = chessService.joinBoard(boardId);
                return gson.toJson(boardInfo);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST);
            }
        };
    }

    private Route movePiece() {
        return (request, response) -> {
            try {
                Map<String, Object> requestBody = gson.fromJson(request.body(), HashMap.class);
                int boardId = (int) (double) requestBody.get("boardId");
                String source = (String) requestBody.get("source");
                String target = (String) requestBody.get("target");
                Map<String, String> boardInfo = chessService.movedPiece(boardId, source, target);
                return gson.toJson(boardInfo);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST);
            }
        };
    }

    private Route movablePosition() {
        return (request, response) -> {
            try {
                String boardId = request.queryParams("boardId");
                String source = request.queryParams("source");
                List<String> movablePositions = chessService
                    .movablePositions(Integer.parseInt(boardId), source);
                return gson.toJson(movablePositions);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST);
            }
        };
    }

    private Route searchBoard() {
        return (request, response) -> {
            try {
                String playerName = request.queryParams("playerName");
                List<Map<String, Object>> boards = chessService.searchBoard(playerName);
                return gson.toJson(boards);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST);
            }
        };
    }



    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
