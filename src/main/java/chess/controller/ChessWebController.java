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

    public String chessGame() {
        return render(templateEngine, new HashMap<>(), "index.html");
    }

    public String enterGameRoom(Request req) throws SQLException {
        String gameId = req.queryParams("game_id");

        boolean canResume = service.canResume(gameId);

        Map<String, Object> model = new HashMap<>();
        model.put("canResume", canResume);
        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }

    public String startGame(Request req) throws SQLException {
        String gameId = req.queryParams("game_id");

        service.startNewGame(board, gameId);

        Map<String, Object> gameInfo = service.provideGameInfo(board);
        Map<String, Object> model = new HashMap<>(gameInfo);
        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }

    public String resumeGame(Request req) throws SQLException {
        String gameId = req.queryParams("game_id");

        service.resumeGame(board, gameId);

        Map<String, Object> gameInfo = service.provideGameInfo(board);
        Map<String, Object> model = new HashMap<>(gameInfo);
        model.put("game_id", gameId);
        return render(templateEngine, model, "game_room.html");
    }

    public String move(Request req) throws SQLException {
        String gameId = req.queryParams("game_id");
        String source = req.queryParams("source");
        String target = req.queryParams("target");

        service.move(board, gameId, source, target);
        String winnerInfo = service.provideWinner(board, gameId);

        Map<String, Object> gameInfo = service.provideGameInfo(board);
        Map<String, Object> model = new HashMap<>(gameInfo);
        model.put("game_id", gameId);
        model.put("end", winnerInfo);
        return render(templateEngine, model, "game_room.html");
    }
}
