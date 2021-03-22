package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Path;
import chess.domain.board.Team;
import chess.domain.piece.Pawn;
import chess.domain.utils.BoardInitializer;
import chess.domain.utils.MoveValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        board.movePiece(new Path(Arrays.asList(Position.of("a7"), Position.of("a3"))));
        assertThatCode(() -> MoveValidator
                .validateDiagonalMove(board, new Pawn(Team.WHITE), Position.of("a3"), 1))
                .doesNotThrowAnyException();
    }

    @Test
    void validateStraightMove() {
        assertThatThrownBy(() -> MoveValidator.validateStraightMove(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 두 칸 이상");
    }

    @Test
    void validateMoveRange() {
        assertThatThrownBy(() -> MoveValidator.validateMoveRange(3, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동할 수 있는 거리");
    }
}