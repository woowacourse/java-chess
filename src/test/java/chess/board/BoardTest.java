package chess.board;

import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.board.strategy.BasicBoardStrategy;
import chess.domain.board.Board;
import chess.domain.piece.WhitePawn;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @DisplayName("무사히 이동됨")
    @Test
    void move() {
        // given
        board.initBoard(new BasicBoardStrategy());

        Position to = new Position(Column.E, Row.THREE);

        // then
        assertThatNoException()
                .isThrownBy(() -> board.move(to, new WhitePawn()));
    }
}
