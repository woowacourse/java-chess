package chess.controller;

import chess.domain.move.Coordinate;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.service.GameService;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

public class ChessGameController {
    public static String newGame(Request request, Response response) throws SQLException {
        GameService gameService = GameService.getInstance();

        return gameService.newGame();
    }

    public static String move(Request request, Response response) throws SQLException {
        QueryParamsMap map = request.queryMap();
        String startX = map.get("startX").value();
        String startY = map.get("startY").value();
        String targetX = map.get("targetX").value();
        String targetY = map.get("targetY").value();
        Position startPosition = Position.of(Coordinate.of(startX), Coordinate.of(startY));
        Position targetPosition = Position.of(Coordinate.of(targetX), Coordinate.of(targetY));
        MovingInfo movingInfo = MovingInfo.of(startPosition, targetPosition);
        GameService gameService = GameService.getInstance();

        return gameService.move(movingInfo);
    }

    public static String continueGame(Request request, Response response) throws SQLException {
        GameService gameService = GameService.getInstance();

        return gameService.continueGame();
    }
}
