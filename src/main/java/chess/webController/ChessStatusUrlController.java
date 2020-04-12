package chess.webController;

import chess.service.Service;

import static spark.Spark.post;

public class ChessStatusUrlController {
    public static void run() {
        Service service = new Service();

        post("/status", (req, res) -> {
            res.body(String.format("%s점수: %.1f", service.getColor(), service.calculateScore()));
            return res.body();
        });
    }
}
