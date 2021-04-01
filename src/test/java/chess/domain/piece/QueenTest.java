package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.location.Location;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    private Queen queen;

    @BeforeEach
    void setUp() {
        queen = Queen.of(Location.of(4, 1), Team.WHITE);
    }

    @DisplayName("퀸이 수평, 수직, 대각선으로 이동 가능하다.")
    @Test
    void movable_test() {
        // given, when
        Location horizontalTarget = Location.of(1, 1);
        Location verticalTarget = Location.of(4, 7);
        Location diagonalTarget = Location.of(8, 5);

        // then
        assertThat(queen.isMovable(horizontalTarget)).isTrue();
        assertThat(queen.isMovable(verticalTarget)).isTrue();
        assertThat(queen.isMovable(diagonalTarget)).isTrue();
    }

    @DisplayName("퀸은 수평, 수직, 대각선 외에 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(3, 3);

        // then
        assertThat(queen.isMovable(nonMovableTarget)).isFalse();
    }
}
