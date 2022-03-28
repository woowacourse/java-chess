package chess.menu;

import chess.domain.board.BasicBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.view.OutputView;

public class Start implements Menu {

    private final BoardGenerator boardGenerator = new BasicBoardGenerator();

    @Override
    public boolean play(Board board) {
        board.initBoard(boardGenerator);
        OutputView.printBoard(board.getBoard());
        return true;
    }
}
