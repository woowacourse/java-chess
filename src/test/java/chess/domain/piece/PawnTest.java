package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @Test
    @DisplayName("흑색 폰을 생성한다.")
    void construct() {
        final var piece = new Pawn(Color.BLACK);

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("처음 움직이는 폰은 앞으로 두 칸 갈 수 있다.")
    void isMovableTrue() {
        final Piece pawn = new Pawn(Color.WHITE);
        final boolean actual = pawn.isMovable(Position.of("b2"), Position.of("b4"));

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("폰은 앞으로 한 칸 갈 수 있다.")
    void isMovableFalse() {
        final Piece pawn = new Pawn(Color.BLACK);
        final boolean actual = pawn.isMovable(Position.of("b2"), Position.of("b1"));

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이미 움직인 폰은 앞으로 두 칸 갈 수 없다.")
    void isMovableFalseIfMoving() {
        final Piece pawn = new Pawn(Color.WHITE);
        final boolean actual = pawn.isMovable(Position.of("b3"), Position.of("b5"));

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("폰은 1점이다.")
    void getPoint() {
        final Piece pawn = new Pawn(Color.BLACK);
        final double point = pawn.getPoint();

        assertThat(point).isEqualTo(1);
    }
}
