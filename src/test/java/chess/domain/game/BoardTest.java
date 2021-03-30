package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    private Board board;
    private Board board2;

    @BeforeEach
    void setUp() {
        board = BoardFactory.create();
        board2 = BoardFactory.create2();
    }

    @Test
    void validatePieceToMove() {
        assertThatCode(() -> board.pickStartPiece(Color.WHITE, Position.from("a2")))
                .doesNotThrowAnyException();
    }

    @Test
    void print() {
        board2.print();
    }

    @Test
    void validatePieceToMoveError() {
        assertThatThrownBy(() -> board.pickStartPiece(Color.WHITE, Position.from("a7")))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void action() {
    }

    @Test
    void move2() {
        board2.move2(Position.from("a7"), Position.from("a6"));
        board2.print();
    }
}