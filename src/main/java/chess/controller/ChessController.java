package chess.controller;

import chess.domain.Game;
import chess.domain.Position;
import chess.domain.Square;
import chess.service.ChessService;
import chess.service.RoomService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class ChessController {
    private static final String DELIMITER = "";

    private final ChessService chessService;
    private final RoomService roomService;

    public ChessController(final ChessService chessService, final RoomService roomService) {
        this.chessService = chessService;
        this.roomService = roomService;
    }

    public Object initialize(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        Game game = chessService.initGame();
        long roomId = roomService.latestId();

        req.session().attribute("game", game);
        List<Square> squares = chessService.getSquares(game);

        model.put("currentColor", game.currentColor());
        model.put("board", squares);
        model.put("roomId", roomId);
        return render(model, "board.html");
    }

    public Object action(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        Game game = req.session().attribute("game");

        Position origin = toPosition(req.queryParams("origin"));
        Position target = toPosition(req.queryParams("target"));
        long roomId = Long.parseLong(req.queryParams("roomId"));

        boolean result = chessService.action(game, origin, target, roomId);
        List<Square> squares = chessService.getSquares(game);

        model.put("board", squares);
        model.put("currentColor", game.currentColor());
        model.put("message", req.queryParams("message"));
        model.put("roomId", roomId);
        return render(model, "board.html");
    }

    private Position toPosition(final String input) {
        String[] position = input.split(DELIMITER);
        String col = position[0];
        String row = position[1];
        return Position.of(row, col);
    }


    public Object end(final Request req, final Response res) {
        Map<String, Object> model = new HashMap<>();
        long roomId = Long.parseLong(req.queryParams("roomId"));
        Game game = req.session().attribute("game");
        String winner = game.currentColor();

        roomService.updateStatus(roomId, winner);

        model.put("winner", winner);
        return render(model, "end.html");
    }
}
