package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackPawnTest {

    @Test
    @DisplayName("왼쪽 아래, 오른쪽 아래 방향으로 움직이는지 확인한다.")
    void checkIsCaptureMove() {
        BlackPawn blackPawn = new BlackPawn(new Position(2, 7));
        assertAll(
                () -> assertThat(blackPawn.isCaptureMove(new Position(1, 6))).isTrue(),
                () -> assertThat(blackPawn.isCaptureMove(new Position(2, 6))).isFalse(),
                () -> assertThat(blackPawn.isCaptureMove(new Position(3, 6))).isTrue()
        );
    }

    @Test
    @DisplayName("(2, 7)일 때 (2, 5)로는 이동할 수 없다.")
    void findPathToInvalidDestinationDownDown() {
        BlackPawn blackPawn = new BlackPawn(new Position(2, 7));
        Position destination = new Position(2, 5);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackPawn.findPathTo(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("(2, 7)일 때 (2, 8)으로는 이동할 수 없다.")
    void findPathToInvalidDestinationDown() {
        BlackPawn blackPawn = new BlackPawn(new Position(2, 7));
        Position destination = new Position(2, 8);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackPawn.findPathTo(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        BlackPawn blackPawn = new BlackPawn(new Position(2, 7));

        org.junit.jupiter.api.Assertions.assertAll(
                () -> assertThat(blackPawn.isSameColor(Color.BLACK)).isTrue(),
                () -> assertThat(blackPawn.isSameColor(Color.WHITE)).isFalse()
        );
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        BlackPawn blackPawn = new BlackPawn(new Position(2, 7));

        assertThat(blackPawn.pieceType()).isEqualTo(PieceType.BLACK_PAWN);
    }
}
