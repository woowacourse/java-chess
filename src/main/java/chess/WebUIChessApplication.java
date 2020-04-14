package chess;

import chess.controller.ChessWebController;
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
        final ChessWebController controller = new ChessWebController(webService);
        final Board board = new Board();

        staticFiles.location("/");

        get("/", (req, res) -> {
            return render(templateEngine, new HashMap<>(), "index.html");
        });

        post("/ready", (req, res) -> {
            String gameId = req.queryParams("game_id");

            Map<String, Object> model = controller.canResume(gameId);

            return render(templateEngine, model, "game_room.html");
        });

        post("/play", (req, res) -> {
            String gameId = req.queryParams("game_id");

            Map<String, Object> model = controller.startNewGame(board, gameId);

            return render(templateEngine, model, "game_room.html");
        });

        post("/resume", (req, res) -> {
            String gameId = req.queryParams("game_id");

            Map<String, Object> model = controller.resumeGame(board, gameId);

            return render(templateEngine, model, "game_room.html");
        });

        post("/move", (req, res) -> {
            String gameId = req.queryParams("game_id");
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            Map<String, Object> model = controller.move(board, gameId, source, target);

            return render(templateEngine, model, "game_room.html");
        });
    }

    private static String render(HandlebarsTemplateEngine templateEngine, Map<String, Object> model, String templatePath) {
        return templateEngine.render(new ModelAndView(model, templatePath));
    }
}
