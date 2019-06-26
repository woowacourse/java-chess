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
        ChessRoundService chessRoundService = ChessRoundService.getInstance();

        ChessBoard chessBoard = ChessBoard.createEmpty();
        ChessPlayerDTO whitePlayerDTO = chessRoundService.fetchWhitePlayer();
        chessBoard.fillWhiteChessPiecesOfPlayer(whitePlayerDTO);

        ChessPlayerDTO blackPlayerDTO = chessRoundService.fetchBlackPlayer();
        chessBoard.fillBlackChessPiecesOfPlayer(blackPlayerDTO);

        Map<String, Object> model = new HashMap<>();
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
        if (chessRoundService.isGameFinished()) {
            boolean isWhiteWinner = !chessRoundService.isWhiteTurn();
            String winner = isWhiteWinner ? "White" : "Black";
            res.redirect("/chess-result?winner=" + winner);
        }

        res.redirect("/chess-round");
        return null;
    };

    private ChessRoundController() {
    }
}
