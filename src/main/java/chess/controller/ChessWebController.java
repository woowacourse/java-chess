package chess.controller;

import chess.db.ChessPieceDao;
import chess.db.MoveHistoryDao;
import chess.domains.board.Board;
import chess.service.ChessWebService;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessWebController {
    private final ChessWebService service;
    private final HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();
    private final Board board = new Board();

    public ChessWebController() {
        this.service = new ChessWebService(new ChessPieceDao(), new MoveHistoryDao());
    }

    private static String render(HandlebarsTemplateEngine templateEngine, Map<String, Object> model, String templatePath) {
        return templateEngine.render(new ModelAndView(model, templatePath));
    }

    public String gameId() {
        return render(templateEngine, new HashMap<>(), "index.html");
    }

    public String canResume(Request req) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String gameId = req.queryParams("game_id");

        service.canResume(model, gameId);

        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }

    public String startNewGame(Request req) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String gameId = req.queryParams("game_id");

        service.startNewGame(board, gameId);

        service.provideGameInfo(model, board);
        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }

    public String resumeGame(Request req) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String gameId = req.queryParams("game_id");

        service.resumeGame(board, gameId);

        service.provideGameInfo(model, board);
        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }

    public String move(Request req) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String gameId = req.queryParams("game_id");
        String source = req.queryParams("source");
        String target = req.queryParams("target");

        service.move(board, gameId, source, target);
        service.checkGameOver(model, board, gameId);

        service.provideGameInfo(model, board);
        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }
}
