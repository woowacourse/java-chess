package chess;

import chess.controller.ChessController;
import chess.service.FinishedGameService;
import chess.service.PieceService;
import chess.service.RunningGameService;

public class ChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController(new RunningGameService(), new FinishedGameService(),
                new PieceService());
        chessController.start();
    }
}
