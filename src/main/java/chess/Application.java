package chess;

import chess.Service.ChessService;
import chess.controller.ChessController;
import chess.database.DbChessBoardDao;
import chess.domain.ChessBoardMaker;
import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.piece.Camp;

public class Application {
    public static void main(String[] args) {
        ChessController controller = new ChessController(
                new ChessService(
                        new ChessGame(ChessBoardMaker.create(), new Turn(Camp.WHITE)),
                        new DbChessBoardDao()
                )
        );
        controller.run();
    }
}
