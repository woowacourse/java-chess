package chess.webController;


import chess.service.createInitialBoard;

import static spark.Spark.post;

public class ChessFirstStartUrlController {
    public static void run(createInitialBoard createInitialBoard) {
        post("/chessStart", (req, res) ->
                createInitialBoard.initialChessBoard());
    }
}
