package chess.domain;

import chess.domain.board.BoardFactory;
import chess.view.OutputView;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void name() {
        OutputView.printChessBoard(BoardFactory.createInitially().getBoard());
    }
}
