package chess.webController;

import chess.service.ChessGameService;

import static spark.Spark.exception;
import static spark.Spark.post;

public class ChessMoveUrlController {
    public static void run() {
        ChessGameService chessGameService = new ChessGameService();

        post("/move", (req, res) -> {
            try {
                return chessGameService.moveChessBoard(req.queryParams("source"), req.queryParams("target"));
            } catch (Exception e) {
                throw new MoveException(e.getMessage());
            }
        });
        exception(MoveException.class, (exception, req, res)-> {
            res.status(403);
            res.body(exception.getMessage());
        });
    }
}
