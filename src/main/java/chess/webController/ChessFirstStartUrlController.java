package chess.webController;


import chess.service.CreateInitialBoard;

import static spark.Spark.post;

public class ChessFirstStartUrlController {
    public static void run(CreateInitialBoard createInitialBoard) {
        post("/chessStart", (req, res) ->
                createInitialBoard.initialChessBoard());
    }
}
