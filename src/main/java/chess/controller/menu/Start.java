package chess.controller.menu;

import chess.domain.board.BasicBoardStrategy;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerationStrategy;
import chess.view.OutputView;

public class Start implements Menu {

    private final BoardGenerationStrategy boardGenerator = new BasicBoardStrategy();

    @Override
    public boolean play(Board board) {
        board.initBoard(boardGenerator);
        OutputView.printBoard(board.getBoard());
        return true;
    }
}
