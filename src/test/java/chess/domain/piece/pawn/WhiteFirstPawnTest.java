package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteFirstPawnTest {

    @Test
    @DisplayName("왼쪽 위, 오른쪽 위 방향으로 움직이는지 확인한다.")
    void checkIsCaptureMove() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();
        assertAll(
                () -> assertThat(whiteFirstPawn.isCaptureMove(new Positions(new Position(2, 2), new Position(1, 3)))).isTrue(),
                () -> assertThat(whiteFirstPawn.isCaptureMove(new Positions(new Position(2, 2), new Position(2, 3)))).isFalse(),
                () -> assertThat(whiteFirstPawn.isCaptureMove(new Positions(new Position(2, 2), new Position(3, 3)))).isTrue()
        );
    }

    @Test
    @DisplayName("(2, 2) -> (2, 4)로 이동하려고 할 때 중간 경로를 찾는다.")
    void findPathToUpUp() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();
        Position departure = new Position(2, 2);
        Position destination = new Position(2, 4);

        assertThat(whiteFirstPawn.findPath(new Positions(departure, destination))).containsExactly(new Position(2, 3));
    }

    @Test
    @DisplayName("왼쪽, 오른쪽으로는 이동할 수 없다.")
    void findPathToInvalidDestinationLeftRight() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();

        assertAll(
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> whiteFirstPawn.findPath(new Positions(new Position(2, 2), new Position(1, 2))))
                        .withMessage("이동할 수 없습니다."),
                () -> assertThatIllegalArgumentException()
                        .isThrownBy(() -> whiteFirstPawn.findPath(new Positions(new Position(2, 2), new Position(1, 2))))
                        .withMessage("이동할 수 없습니다.")
        );
    }

    @Test
    @DisplayName("(2, 2)일 때 (2, 1)로는 이동할 수 없다.")
    void findPathToInvalidDestinationDown() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();
        Position departure = new Position(2, 2);
        Position destination = new Position(2, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whiteFirstPawn.findPath(new Positions(departure, destination)))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("(2, 2)일 때 (2, 5)로는 이동할 수 없다.")
    void findPathToInvalidFarDestination() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();
        Position departure = new Position(2, 2);
        Position destination = new Position(2, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> whiteFirstPawn.findPath(new Positions(departure, destination)))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();

        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertThat(whiteFirstPawn.isSameColor(Color.WHITE)).isTrue(),
                () -> assertThat(whiteFirstPawn.isSameColor(Color.BLACK)).isFalse()
        );
    }

    @Test
    @DisplayName("한 번 움직이면 일반 폰이 된다")
    void updateToGeneralPawn() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();

        assertThat(whiteFirstPawn.update()).isInstanceOf(WhitePawn.class);
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        WhiteFirstPawn whiteFirstPawn = new WhiteFirstPawn();

        assertThat(whiteFirstPawn.pieceType()).isEqualTo(PieceType.WHITE_FIRST_PAWN);
    }
}
