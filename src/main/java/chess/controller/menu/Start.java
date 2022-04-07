package chess.controller.menu;

import chess.controller.ChessController;
import chess.domain.board.strategy.BasicBoardStrategy;
import chess.domain.board.strategy.BoardGenerationStrategy;

public class Start implements Menu {

    private final BoardGenerationStrategy boardGenerator = new BasicBoardStrategy();

    @Override
    public void play(ChessController chessController) {
            chessController.start(boardGenerator);
    }
}
