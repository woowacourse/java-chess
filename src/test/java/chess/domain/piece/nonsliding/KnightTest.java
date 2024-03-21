package chess.domain.piece.nonsliding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.color.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("(1, 1)일 때 (1, 3)으로는 이동할 수 없다.")
    void findPathToInvalidDestination() {
        Knight knight = new Knight(new Position(1, 1), Color.WHITE);
        Position destination = new Position(1, 3);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> knight.findPathTo(destination))
                .withMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 말의 색상과 동일한 색을 가졌는지 확인한다.")
    void isSameColor() {
        Knight knight = new Knight(new Position(1, 1), Color.WHITE);

        Assertions.assertAll(
                () -> assertThat(knight.isSameColor(Color.WHITE)).isTrue(),
                () -> assertThat(knight.isSameColor(Color.BLACK)).isFalse()
        );
    }

    @Test
    @DisplayName("말의 색상과 모양에 맞는 PieceType을 반환한다.")
    void getPieceType() {
        Knight knight = new Knight(new Position(1, 1), Color.WHITE);

        assertThat(knight.pieceType()).isEqualTo(PieceType.WHITE_KNIGHT);
    }
}