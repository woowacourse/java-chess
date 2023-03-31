package chess.domain.strategy.pawn;

import chess.domain.Position;
import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;
import chess.domain.strategy.PieceStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class PawnStrategyTest {

    PieceStrategy pawnStrategy = new PawnStrategy();
    MoveRequest request;

    @Test
    @DisplayName("첫번째 차례에 화이트 폰은 앞으로 최대 2칸 이동할 수 있다.")
    void moveWhitePieceTwoStep() {
        // given
        // when
        request = MoveRequest.from(
                List.of(),
                WHITE,
                new PositionDto(Position.from(1, 'a')),
                new PositionDto(Position.from(3, 'a'))
        );
        // then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("첫번째 차례가 아닌 경우 화이트 폰이 앞으로 2칸 이동시 예외가 발생한다.")
    void validateMoveWhitePieceTwoStep() {
        // given
        // when
        request = MoveRequest.from(
                List.of(),
                WHITE,
                new PositionDto(Position.from(3, 'a')),
                new PositionDto(Position.from(5, 'a')));

        // when, then
        assertThatThrownBy(() -> pawnStrategy.validateDirection(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("화이트 폰은 앞으로 1칸 이동할 수 있다.")
    void moveWhitePieceOneStep() {
        // given
        request = MoveRequest.from(
                List.of(),
                WHITE,
                new PositionDto(Position.from(1, 'a')),
                new PositionDto(Position.from(2, 'a')));

        // when, then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("화이트 폰은 뒤로 1칸 이동하는 경우 예외가 발생한다.")
    void validateMoveWhitePieceOneStep() {
        // given
        request = MoveRequest.from(
                List.of(),
                WHITE,
                new PositionDto(Position.from(3, 'a')),
                new PositionDto(Position.from(2, 'a')));

        // then
        assertThatThrownBy(() -> pawnStrategy.validateDirection(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫번째 차례에 블랙폰은 앞으로 최대 2칸 이동할 수 있다.")
    void moveBlackPieceTwoStep() {
        // given
        request = MoveRequest.from(
                List.of(),
                BLACK,
                new PositionDto(Position.from(6, 'a')),
                new PositionDto(Position.from(4, 'a'))
        );

        // when
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("첫번째 차례가 아닌 경우 블랙 폰이 앞으로 2칸 이동시 예외가 발생한다.")
    void validateMoveBlackPieceTwoStep() {
        // given
        request = MoveRequest.from(
                List.of(),
                BLACK,
                new PositionDto(Position.from(5, 'a')),
                new PositionDto(Position.from(3, 'a'))
        );

        // when, then
        assertThrows(IllegalArgumentException.class, () -> pawnStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("블랙폰은 앞으로 1칸 이동할 수 있다.")
    void moveBlackPieceOneStep() {
        // given
        request = MoveRequest.from(
                List.of(Position.from(6, 'a')),
                BLACK,
                new PositionDto(Position.from(6, 'a')),
                new PositionDto(Position.from(5, 'a')));

        // when, then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("블랙폰은 뒤로 1칸 이동하는 경우 예외가 발생한다.")
    void validateMoveBlackPieceOneStep() {
        // given
        request = MoveRequest.from(
                List.of(),
                BLACK,
                new PositionDto(Position.from(6, 'a')),
                new PositionDto(Position.from(7, 'a'))
        );

        // when then
        assertThatThrownBy(() -> pawnStrategy.validateDirection(request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("화이트 진영 기물이 대각선으로 이동할 수 있다.")
    void moveWhiteDiagonal() {
        // when, then
        request = MoveRequest.from(
                List.of(Position.from(3, 'b')),
                WHITE,
                new PositionDto(Position.from(2, 'a')), // movablePiecePosition
                new PositionDto(Position.from(3, 'b')) // targetPosition
        );

        assertDoesNotThrow(() -> pawnStrategy.validateDirection(request));
    }

    @Test
    @DisplayName("대각선 앞에 화이트 진영 기물이 존재할 경우 이동하고 잡을 수 있다.")
    void moveBlackDiagonal() {
        // given, when
        request = MoveRequest.from(
                List.of(Position.from(5, 'a')),
                BLACK, // 이동할 기물 진영
                new PositionDto(Position.from(6, 'b')), // movablePiecePosition
                new PositionDto(Position.from(5, 'a')) // targetPosition
        );

        // then
        assertDoesNotThrow(() -> pawnStrategy.validateDirection(request));
    }

}
