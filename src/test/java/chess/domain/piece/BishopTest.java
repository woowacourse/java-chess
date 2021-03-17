package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    private Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = Bishop.of(Location.of(3, 1), Team.WHITE);
    }

    @DisplayName("비숍은 대각선으로 이동 가능하다.")
    @Test
    void movable_test() {
        // given, when
        Location diagonalTarget = Location.of(8, 6);

        // then
        assertThat(bishop.isMovable(diagonalTarget)).isTrue();
    }

    @DisplayName("비숍은 대각선 외에 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(8, 5);

        // then
        assertThat(bishop.isMovable(nonMovableTarget)).isFalse();
    }

}