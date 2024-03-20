package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteFirstPawnTest {

    @Test
    @DisplayName("왼쪽 위, 오른쪽 위 방향으로 움직이는지 확인한다.")
    void checkIsCaptureMove() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));
        assertAll(
                () -> assertThat(whiteFirstPawn.isCaptureMove(new Position(1, 3))).isTrue(),
                () -> assertThat(whiteFirstPawn.isCaptureMove(new Position(2, 3))).isFalse(),
                () -> assertThat(whiteFirstPawn.isCaptureMove(new Position(3, 3))).isTrue()
        );
    }

    @Test
    @DisplayName("(2, 2) -> (2, 4), (2, 3)")
    void findMovablePositionsByUpUp() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));
        Position destination = new Position(2, 4);

        assertThat(whiteFirstPawn.findMovablePositions(destination)).containsExactly(new Position(2, 3));
    }

    @Test
    @DisplayName("왼쪽, 오른쪽으로는 이동할 수 없다.")
    void findMovablePositionsByInvalidDestinationLeftRight() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));

        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> whiteFirstPawn.findMovablePositions(new Position(1, 2)))
                        .withMessage("이동할 수 없습니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> whiteFirstPawn.findMovablePositions(new Position(3, 2)))
                        .withMessage("이동할 수 없습니다.")
        );
    }

    @Test
    @DisplayName("(2, 2)일 때 (2, 1)로는 이동할 수 없다.")
    void findMovablePositionsByInvalidDestinationDown() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));
        Position destination = new Position(2, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whiteFirstPawn.findMovablePositions(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("(2, 2)일 때 (2, 5)로는 이동할 수 없다.")
    void findMovablePositionsByInvalidFarDestination() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));
        Position destination = new Position(2, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whiteFirstPawn.findMovablePositions(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));

        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertThat(whiteFirstPawn.isSameColor(Color.WHITE)).isTrue(),
                () -> assertThat(whiteFirstPawn.isSameColor(Color.BLACK)).isFalse()
        );
    }

    @Test
    @DisplayName("한 번 움직이면 일반 폰이 된다")
    void updateToGeneralPawn() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));

        assertThat(whiteFirstPawn.update(new Position(2, 4))).isInstanceOf(WhitePawn.class);
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn(new Position(2, 2));

        assertThat(whiteFirstPawn.pieceType()).isEqualTo(PieceType.WHITE_PAWN);
    }
}