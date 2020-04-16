package chess.webController;

import chess.service.ChessGameMoveService;

import static spark.Spark.exception;
import static spark.Spark.post;

public class ChessMoveUrlController {
    public static void run(ChessGameMoveService chessGameMove) {

        post("/move", (req, res) -> {
            try {
                return chessGameMove.moveChessBoard(req.queryParams("source"), req.queryParams("target"));
            } catch (Exception e) {
                throw new MoveException(e.getMessage());
            }
        });
        exception(MoveException.class, (exception, req, res) -> {
            res.status(403);
            res.body(exception.getMessage());
        });
    }
}
