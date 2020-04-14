package chess;

import chess.db.ChessPieceDao;
import chess.db.MoveHistoryDao;
import chess.domains.board.Board;
import chess.service.ChessWebService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        ChessWebService webService = new ChessWebService(new ChessPieceDao(), new MoveHistoryDao());
        Board board = new Board();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/ready", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.canResume(model, gameId);

            return render(model, "game_room.html");
        });

        post("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.startNewGame(model, board, gameId);

            return render(model, "game_room.html");
        });

        post("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.resumeGame(model, board, gameId);

            return render(model, "game_room.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            webService.move(model, board, gameId, source, target);
            return render(model, "game_room.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
