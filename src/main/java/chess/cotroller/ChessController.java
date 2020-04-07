package chess.cotroller;

import chess.domain.Chess;
import chess.domain.coordinate.Coordinate;
import chess.domain.piece.Team;
import chess.dto.MoveDto;
import chess.result.Result;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ChessController {
    private static Map<Integer, Chess> gameMap = new HashMap<>();
    private ChessService chessService = new ChessService();

    public Object move(Request request, Response response) {
        int roomId = Integer.parseInt(request.queryParams("roomId"));
        Coordinate source = Coordinate.of(request.queryParams("source"));
        Coordinate target = Coordinate.of(request.queryParams("target"));
        Result result = chessService.move(new MoveDto(roomId, source, target));
        if (result.isSuccess()) {
            return result.getObject();
        }
        throw new IllegalArgumentException(result.getObject().toString());
    }

    public Object getMovableWay(Request request, Response response) {
        int roomId = Integer.parseInt(request.queryParams("roomId"));
        Team team = Team.valueOf(request.queryParams("team"));
        Coordinate coordinate = Coordinate.of(request.queryParams("coordinate"));
        Result result = chessService.getMovableWay(roomId, team, coordinate);
        if (result.isSuccess()) {
            return new Gson().toJson(result.getObject());
        }
        throw new IllegalArgumentException(result.getObject().toString());
    }

    public Object renew(Request request, Response response) {
        int roomId = Integer.parseInt(request.queryParams("roomId"));
        Result result = chessService.renew(roomId);
        if (result.isSuccess()) {
            return new Gson().toJson(result.getObject());
        }
        throw new IllegalArgumentException(result.getObject().toString());
    }
}
