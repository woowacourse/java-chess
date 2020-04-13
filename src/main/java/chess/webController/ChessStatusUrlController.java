package chess.webController;

import chess.service.ChessGameService;

import static spark.Spark.post;

public class ChessStatusUrlController {
    public static void run() {
        ChessGameService chessGameService = new ChessGameService();

        post("/status", (req, res) -> {
            res.body(String.format("%s점수: %.1f", chessGameService.getColor(), chessGameService.calculateScore()));
            return res.body();
        });
    }
}
