package chess.webController;

import chess.service.ChessGameMove;

import static spark.Spark.exception;
import static spark.Spark.post;

public class ChessMoveUrlController {
    public static void run(ChessGameMove chessGameMove) {

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
