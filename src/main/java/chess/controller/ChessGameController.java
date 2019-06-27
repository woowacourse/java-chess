package chess.controller;

import chess.domain.piece.pieceinfo.TeamType;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class ChessGameController {
    private static ChessGameService chessGameService = ChessGameService.getInstance();

    public static final Route CREATE_NEW_GAME = (request, response) -> {
        Map<String, Object> model = new HashMap<>();

        String game = new Gson().toJson(chessGameService.createNewGame());
        model.put("game", game);

        return render(model, "chessBoard.html");
    };

    public static final Route CREATE_LATEST_GAME = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        String game = new Gson().toJson(chessGameService.createLatestGame());

        model.put("game", game);
        return render(model, "chessBoard.html");
    };

    public static final Route CREATE_MOVABLE_POSITIONS = (request, response) -> {
        String possiblePositions = new Gson().toJson(chessGameService.getPossiblePositions(request.queryParams("source")));

        return possiblePositions;
    };

    public static final Route MOVE_PIECES = (request, response) -> {
        String source = request.queryParams("source");
        String destination = request.queryParams("destination");

        Map<String, Object> model = new HashMap<>();
        String game = new Gson().toJson(chessGameService.movePiece(source, destination));
        model.put("game", game);

        return render(model, "chessBoard.html");
    };

    public static final Route CALCULATE_SCORE = (request, response) -> {
        Map<String, Double> scores = new HashMap<>();
        scores.put("WHITE", chessGameService.calculateScore(TeamType.WHITE));
        scores.put("BLACK", chessGameService.calculateScore(TeamType.BLACK));

        return new Gson().toJson(scores);
    };
}
