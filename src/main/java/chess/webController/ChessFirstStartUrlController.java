package chess.webController;


import chess.service.ChessGameService;

import static spark.Spark.post;

public class ChessFirstStartUrlController {
    public static void run() {
        ChessGameService chessGameService = new ChessGameService();
        post("/chessStart", (req, res) -> chessGameService.initialChessBoard());
    }
}
