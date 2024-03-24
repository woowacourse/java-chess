package chess.domain.piece.type;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Movement;
import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("퀸은 직선, 대각선 이외로는 이동할 수 없다.")
    @Test
    void canNotMove() {
        // given
        final Queen queen = new Queen(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.A, Rank.ONE)); // 유효하지 않은 이동 전략

        // when && then
        assertThat(queen.canMove(movement)).isFalse();
    }

    @DisplayName("도착 지점이 왼쪽일 때 위치들을 반환한다.")
    @Test
    void getRouteLeft() {
        // given
        final Queen queen = new Queen(Color.BLACK);
        final Movement movement = new Movement(new Position(File.D, Rank.FIVE), new Position(File.G, Rank.TWO));

        // when
        final Set<Position> positions = queen.getRoute(movement);

        // then
        assertThat(positions).containsExactlyInAnyOrder(
                new Position(File.E, Rank.FOUR),
                new Position(File.F, Rank.THREE)
        );
    }
}
