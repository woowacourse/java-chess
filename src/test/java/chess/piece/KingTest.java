package chess.piece;

import chess.Position;
import chess.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class KingTest {

    @Test
    @DisplayName("킹의 진행 방향과 거리가 맞는다면 true 반환")
    void correctDirectionMove() {
        King king = new King(Position.of('a', '1'), Team.WHITE);

        assertAll(
                () -> assertThat(king.isMovable(Position.of('a', '2'))).isTrue(),
                () -> assertThat(king.isMovable(Position.of('a', '2'))).isTrue(),
                () -> assertThat(king.isMovable(Position.of('b', '1'))).isTrue()
        );
    }

    @Test
    @DisplayName("킹이 이동할수 없는 거리이면 false 반환")
    void noCorrectDistanceMove() {
        King king = new King(Position.of('a', '1'), Team.WHITE);

        assertThat(king.isMovable(Position.of('c', '3'))).isFalse();
    }
}
