package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.location.Location;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    private King king;

    @BeforeEach
    void setUp() {
        king = King.of(Location.of(5, 1), Team.WHITE);
    }

    @DisplayName("킹은 인접한 위치로 이동할 수 있다.")
    @Test
    void movable_test() {
        // given, when
        Location horizontalTarget = Location.of(6, 1);
        Location verticalTarget = Location.of(5, 2);
        Location diagonalTarget = Location.of(6, 2);

        // then
        assertThat(king.isMovable(horizontalTarget)).isTrue();
        assertThat(king.isMovable(verticalTarget)).isTrue();
        assertThat(king.isMovable(diagonalTarget)).isTrue();
    }

    @DisplayName("킹은 인접하지 않은 위치로 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(7, 1);

        // then
        assertThat(king.isMovable(nonMovableTarget)).isFalse();
    }
}
