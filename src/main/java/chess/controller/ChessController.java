package chess.controller;

import chess.domain.Game;
import chess.domain.Piece;
import chess.domain.Position;
import chess.domain.ScoreCalculator;
import chess.dto.CommandDto;
import chess.service.ChessService;
import chess.service.RoomService;
import chess.utils.PositionConverter;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class ChessController {
    private final ChessService chessService;
    private final RoomService roomService;

    public ChessController(final ChessService chessService, final RoomService roomService) {
        this.chessService = chessService;
        this.roomService = roomService;
    }

    public Object initialize(Request req, Response res) throws SQLException {
        Game game = chessService.initGame();
        long roomId = roomService.latestId();

        req.session().attribute("game", game);

        res.redirect("/chess?roomId=" + roomId);
        return null;
    }

    public Object show(final Request req, final Response res) {
        Map<String, Object> model = new HashMap<>();

        Game game = req.session().attribute("game");
        List<Piece> pieces = chessService.getPieces(game);
        long roomId = Long.parseLong(req.queryParams("roomId"));

        model.put("board", pieces);
        model.put("currentColor", game.currentColor().getName());
        model.put("message", req.queryParams("message"));
        model.put("roomId", roomId);
        return render(model, "board.html");
    }

    public Object action(Request req, Response res) {
        Game game = req.session().attribute("game");
        Position origin = PositionConverter.convert(req.queryParams("origin"));
        Position target = PositionConverter.convert(req.queryParams("target"));
        long roomId = Long.parseLong(req.queryParams("roomId"));

        chessService.action(game, origin, target, roomId);

        req.session().attribute("game", game);

        res.redirect("/chess?roomId=" + roomId);
        return null;
    }

    public Object end(final Request req, final Response res) {
        Map<String, Object> model = new HashMap<>();
        long roomId = Long.parseLong(req.queryParams("roomId"));
        Game game = req.session().attribute("game");
        String winner = game.currentColor().getName();

        roomService.updateStatus(roomId, winner);

        model.put("winner", winner);
        return render(model, "end.html");
    }

    public Object score(final Request req, final Response res) {
        Map<String, Object> model = new HashMap<>();
        Game game = req.session().attribute("game");
        ScoreCalculator scoreCalculator = chessService.createScoreCalculator(game);

        model.put("whiteScore", scoreCalculator.getScore(Piece.Color.WHITE));
        model.put("blackScore", scoreCalculator.getScore(Piece.Color.BLACK));
        model.put("roomId", req.queryParams("roomId"));
        return render(model, "score.html");
    }

    public Object load(final Request req, final Response res) {
        long roomId = Long.parseLong(req.queryParams("roomId"));
        List<CommandDto> commandDtos = chessService.findByRoomId(roomId);
        Game game = chessService.load(commandDtos);

        req.session().attribute("game", game);
        res.redirect("/chess?roomId=" + roomId);
        return null;
    }
}
