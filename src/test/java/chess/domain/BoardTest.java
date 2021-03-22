package chess.domain;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardFactory.create();
    }

    @Test
    void validatePieceToMove() {
        assertThatCode(() -> board.pickStartPiece(Color.WHITE, Position.from("a2")))
                .doesNotThrowAnyException();
    }

    @Test
    void validatePieceToMoveError() {
        assertThatThrownBy(() -> board.pickStartPiece(Color.WHITE, Position.from("a7")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}