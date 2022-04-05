package chess;

import chess.controller.ChessWebController;
import chess.domain.ChessGame;
import chess.domain.board.BasicBoardStrategy;

public class WebApplication {

    private static final ChessWebController controller = new ChessWebController(new ChessGame());

    public static void main(final String... args) {
        controller.start(new BasicBoardStrategy());
    }
}

