package chess.controller.chessround;

import chess.WebUIChessApplication;
import chess.application.chessround.ChessRoundService;
import chess.application.chessround.dto.ChessPlayerDTO;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ChessRoundController {
    public static final String PATH_CHESS_ROUND = "/chess-round";

    public static final Route fetchChessRound = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        ChessBoard chessBoard = ChessBoard.createEmpty();

        ChessRoundService chessRoundService = ChessRoundService.getInstance();

        ChessPlayerDTO whitePlayerDTO = chessRoundService.fetchWhitePlayer();
        chessBoard.fillWhiteChessPiecesOfPlayer(whitePlayerDTO);

        ChessPlayerDTO blackPlayerDTO = chessRoundService.fetchBlackPlayer();
        chessBoard.fillBlackChessPiecesOfPlayer(blackPlayerDTO);

        model.put("chess-blocks", chessBoard);
        model.put("current-turn", chessRoundService.isWhiteTurn() ? "White" : "Black");
        model.put("white-score", chessRoundService.getWhitePlayerScore());
        model.put("black-score", chessRoundService.getBlackPlayerScore());
        model.put("error-message", chessRoundService.getErrorMessage());
        return WebUIChessApplication.render(model, "chess-round.hbs");
    };

    public static final Route handleChessMove = (req, res) -> {
        String sourceId = req.queryParams("source-id");
        String targetId = req.queryParams("target-id");

        ChessRoundService chessRoundService = ChessRoundService.getInstance();
        chessRoundService.move(sourceId, targetId);

        res.redirect("/chess-round");
        return null;
    };

    private ChessRoundController() {
    }
}
