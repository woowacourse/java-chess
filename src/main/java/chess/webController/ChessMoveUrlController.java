package chess.webController;

import chess.dto.ChessPositionDTO;
import chess.service.Service;

import static spark.Spark.post;

public class ChessMoveUrlController {
    public static void run() {
        Service service = new Service();

        post("/move", (req, res) -> {
            ChessPositionDTO chessPositionDTO =
                    new ChessPositionDTO(req.queryParams("source"), req.queryParams("target"));
            try {
                return service.moveChessBoard(chessPositionDTO);
            } catch (Exception e) {
                res.status(403);
                return e.getMessage();
            }
        });
    }
}
