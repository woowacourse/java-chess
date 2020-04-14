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
        final HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();
        final ChessWebService webService = new ChessWebService(new ChessPieceDao(), new MoveHistoryDao());
        final Board board = new Board();

        staticFiles.location("/");

        get("/", (req, res) -> {
            return render(templateEngine, new HashMap<>(), "index.html");
        });

        post("/ready", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.canResume(model, gameId);

            return render(templateEngine, model, "game_room.html");
        });

        post("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.startNewGame(model, board, gameId);

            return render(templateEngine, model, "game_room.html");
        });

        post("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.resumeGame(model, board, gameId);

            return render(templateEngine, model, "game_room.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            webService.move(model, board, gameId, source, target);

            return render(templateEngine, model, "game_room.html");
        });
    }

    private static String render(HandlebarsTemplateEngine templateEngine, Map<String, Object> model, String templatePath) {
        return templateEngine.render(new ModelAndView(model, templatePath));
    }
}
