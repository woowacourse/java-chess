package chess.webController;

import chess.service.ChessGameCalculatorScoreService;

import static spark.Spark.post;

public class ChessStatusUrlController {
    public static void run(ChessGameCalculatorScoreService chessGameCalculatorScore) {
        post("/status", (req, res) -> {
            res.body(String.format("%s점수: %.1f", chessGameCalculatorScore.getColor(),
                    chessGameCalculatorScore.calculateScore()));
            return res.body();
        });
    }
}
