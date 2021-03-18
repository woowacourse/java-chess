package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveValidatorTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.init();
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

    @DisplayName("대각선으로 폰이 이동할 수 있는 경우")
    @Test
    void validateDiagonalMove() {
        board.movePiece(Position.of("a7"), Position.of("a3"));
        assertThatCode(() -> MoveValidator
            .validateDiagonalMove(board, new Pawn(Team.WHITE), Position.of("a3"), 1))
            .doesNotThrowAnyException();
    }

    @Test
    void validateDirection() {
        Strategy strategy = new Knight(Team.BLACK).strategy();
        assertThatThrownBy(() -> MoveValidator.validateDirection(Direction.SOUTHEAST, strategy))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("해당 좌표로 이동할 수 없습니다.");
    }

    @Test
    void validateStraightMove() {
        assertThatThrownBy(() -> MoveValidator.validateStraightMove(3))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("폰은 두 칸 이상");
    }
}