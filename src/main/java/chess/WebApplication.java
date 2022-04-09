package chess;

import chess.controller.ChessWebController;
import chess.domain.ChessGameService;
import chess.domain.board.strategy.WebBasicBoardStrategy;

public class WebApplication {

    private static final ChessWebController controller = new ChessWebController(new ChessGameService());

    public static void main(final String... args) {
        controller.start(new WebBasicBoardStrategy());
    }
}

