package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Color;
import chess.domain.piece.Position;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("폰은 처음에는 앞으로 두칸도 이동할 수 있다.")
    @Test
    void canMoveTwoStepAtFirst() {
        // given
        final Pawn pawn = new Pawn(Color.BLACK, new Position('d', 2));

        // when
        boolean canMove = pawn.canMoveTo(new Position('d', 4));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰은 처음이 아니면 두칸을 이동할 수 없다.")
    @Test
    void canNotMoveTwoStepAtNotFirst() {
        // given
        final Pawn pawn = new Pawn(Color.BLACK, new Position('d', 4));

        // when
        boolean canMove = pawn.canMoveTo(new Position('d', 6));

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("폰은 앞으로 한칸 이동할 수 있다.")
    @Test
    void canMoveForwardOneStep() {
        // given
        final Pawn pawn = new Pawn(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = pawn.canMoveTo(new Position('d', 6));

        // then
        assertThat(canMove).isTrue();
    }

    @DisplayName("폰은 앞으로 한칸 이외에는 이동할 수 없다.")
    @Test
    void canNotMoveForwardOverOneStep() { // TODO: 한칸인데 뒤로가는 경우 추가
        // given
        final Pawn pawn = new Pawn(Color.BLACK, new Position('d', 5));

        // when
        boolean canMove = pawn.canMoveTo(new Position('d', 8));

        // then
        assertThat(canMove).isFalse();
    }

    @DisplayName("처음 시도시 도착 지점이 두칸 앞일 때 위치들을 반환한다.")
    @Test
    void getRouteLeft() {
        // given
        final Pawn pawn = new Pawn(Color.BLACK, new Position('a', 2));

        // when
        Set<Position> positions = pawn.getRoute(new Position('a', 4));

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position('a', 3)
        );
    }
}
