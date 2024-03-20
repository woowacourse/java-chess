package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePawnTest {

    @Test
    @DisplayName("왼쪽 위, 오른쪽 위 방향으로 움직이는지 확인한다.")
    void checkIsCaptureMove() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));
        assertAll(
                () -> assertThat(whitePawn.isCaptureMove(new Position(1, 3))).isTrue(),
                () -> assertThat(whitePawn.isCaptureMove(new Position(2, 3))).isFalse(),
                () -> assertThat(whitePawn.isCaptureMove(new Position(3, 3))).isTrue()
        );
    }

    @Test
    @DisplayName("왼쪽, 오른쪽으로는 이동할 수 없다.")
    void findMovablePositionsByInvalidDestinationLeftRight() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));

        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> whitePawn.findMovablePositions(new Position(1, 2)))
                        .withMessage("이동할 수 없습니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> whitePawn.findMovablePositions(new Position(3, 2)))
                        .withMessage("이동할 수 없습니다.")
        );
    }

    @Test
    @DisplayName("(2, 2)일 때 (2, 4)으로는 이동할 수 없다.")
    void findMovablePositionsByInvalidDestinationUpUp() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));
        Position destination = new Position(2, 4);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whitePawn.findMovablePositions(destination))
                .withMessage("이동할 수 없습니다.");
    }



    @Test
    @DisplayName("(2, 2)일 때 (2, 1)으로는 이동할 수 없다.")
    void findMovablePositionsByInvalidDestinationDown() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));
        Position destination = new Position(2, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whitePawn.findMovablePositions(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("(2, 2)일 때 (2, 5)로는 이동할 수 없다.")
    void findMovablePositionsByInvalidFarDestination() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));
        Position destination = new Position(2, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whitePawn.findMovablePositions(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));

        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertThat(whitePawn.isSameColor(Color.WHITE)).isTrue(),
                () -> assertThat(whitePawn.isSameColor(Color.BLACK)).isFalse()
        );
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        WhitePawn whitePawn = new WhitePawn(new Position(2, 2));

        assertThat(whitePawn.pieceType()).isEqualTo(PieceType.WHITE_PAWN);
    }
}