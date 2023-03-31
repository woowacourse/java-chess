package chess.domain.strategy.pawn;

import static chess.domain.model.player.Color.BLACK;
import static chess.domain.model.player.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.model.piece.shape.strategy.pawn.PawnStrategy;
import chess.domain.model.position.Position;
import chess.domain.model.piece.shape.strategy.PieceStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class PawnStrategyTest {

    PieceStrategy pawnStrategy = new PawnStrategy();

    @Test
    @DisplayName("첫번째 차례에 화이트 폰은 앞으로 최대 2칸 이동할 수 있다.")
    void moveWhitePieceTwoStep() {
        // given
        // when
        // then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(
                Position.from(1, 'a'),
                Position.from(3, 'a'),
                WHITE,
                false
        ));
    }

    @Test
    @DisplayName("첫번째 차례가 아닌 경우 화이트 폰이 앞으로 2칸 이동시 예외가 발생한다.")
    void validateMoveWhitePieceTwoStep() {
        // given
        // when, then
        assertThatThrownBy(() -> pawnStrategy.validateDirection(Position.from(3, 'a'),
                Position.from(5, 'a'),
                WHITE,
                false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("화이트 폰은 앞으로 1칸 이동할 수 있다.")
    void moveWhitePieceOneStep() {

        // when, then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(
                Position.from(1, 'a'),
                Position.from(2, 'a'),
                WHITE,
                false
        ));
    }

    @Test
    @DisplayName("화이트 폰은 뒤로 1칸 이동하는 경우 예외가 발생한다.")
    void validateMoveWhitePieceOneStep() {
        // given
        // when
        // then
        assertThatThrownBy(() -> pawnStrategy.validateDirection(
                Position.from(3, 'a'),
                Position.from(2, 'a'),
                WHITE,
                false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫번째 차례에 블랙폰은 앞으로 최대 2칸 이동할 수 있다.")
    void moveBlackPieceTwoStep() {
        // given
        // when
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(
                Position.from(6, 'a'),
                Position.from(4, 'a'),
                BLACK,
                false
        ));
    }

    @Test
    @DisplayName("첫번째 차례가 아닌 경우 블랙 폰이 앞으로 2칸 이동시 예외가 발생한다.")
    void validateMoveBlackPieceTwoStep() {
        // given
        // when, then
        assertThrows(IllegalArgumentException.class, () -> pawnStrategy.validateDirection(
                Position.from(5, 'a'),
                Position.from(3, 'a'),
                BLACK,
                false
        ));
    }

    @Test
    @DisplayName("블랙폰은 앞으로 1칸 이동할 수 있다.")
    void moveBlackPieceOneStep() {
        // given
        // when, then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(
                Position.from(6, 'a'),
                Position.from(5, 'a'),
                BLACK,
                false
        ));
    }

    @Test
    @DisplayName("블랙폰은 뒤로 1칸 이동하는 경우 예외가 발생한다.")
    void validateMoveBlackPieceOneStep() {
        // given
        // when then
        assertThatThrownBy(() -> pawnStrategy.validateDirection(
                Position.from(6, 'a'),
                Position.from(7, 'a'),
                BLACK,
                false
        ))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("화이트 진영 기물이 대각선으로 이동할 수 있다.")
    void moveWhiteDiagonal() {
        // when, then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(
                Position.from(2, 'a'),
                Position.from(3, 'b'),
                WHITE,
                true
        ));
    }

    @Test
    @DisplayName("대각선 앞에 화이트 진영 기물이 존재할 경우 이동하고 잡을 수 있다.")
    void moveBlackDiagonal() {
        // given, when
        // then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(
                Position.from(6, 'b'), // movablePiecePosition
                Position.from(5, 'a'), // targetPosition
                BLACK, // 이동할 기물 진영
                true
        ));
    }

}
