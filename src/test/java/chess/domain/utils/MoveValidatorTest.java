package chess.domain.utils;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveValidatorTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = Board.of(PieceInitializer.pieceInfo());
    }

    @Test
    void isPieceExist() {
        assertThatThrownBy(() -> MoveValidator.isPieceExist(board, Position.of("a1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("말이 존재합니다.");
    }

    @Test
    void validatePawnLocation() {
        assertThatCode(() -> MoveValidator.validatePawnLocation(Position.of("a2")))
                .doesNotThrowAnyException();
    }

    @Test
    void validateStraightMove() {
        assertThatThrownBy(() -> MoveValidator.validateStraightMove(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 두 칸 초과");
    }

    @Test
    void validateMoveRange() {
        assertThatThrownBy(() -> MoveValidator.validateDistance(3, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 있는 거리");
    }
}