package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    private Rook rook;

    @BeforeEach
    void setUp() {
        rook = Rook.of(Location.of(1, 1), Team.WHITE);
    }

    @DisplayName("룩은 수평, 수직으로 이동 가능하다.")
    @Test
    void movable_test() {
        // given, when
        Location horizontalTarget = Location.of(3, 1);
        Location verticalTarget = Location.of(1, 3);

        // then
        assertThat(rook.isMovable(horizontalTarget)).isTrue();
        assertThat(rook.isMovable(verticalTarget)).isTrue();
    }

    @DisplayName("룩은 수평, 수직 외에 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(3, 3);

        // then
        assertThat(rook.isMovable(nonMovableTarget)).isFalse();
    }

}