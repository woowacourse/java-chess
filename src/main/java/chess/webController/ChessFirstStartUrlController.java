package chess.webController;


import chess.service.Service;

import static spark.Spark.post;

public class ChessFirstStartUrlController {
    public static void run() {
        Service service = new Service();
        post("/chessStart", (req, res) -> service.initialChessBoard());
    }
}
