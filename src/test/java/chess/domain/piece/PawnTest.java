package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.location.Location;
import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.of(Location.of(4, 2), Team.WHITE);
        blackPawn = Pawn.of(Location.of(4, 7), Team.BLACK);
    }

    @DisplayName("흰색 폰은 4가지 위치로 이동 가능하다.")
    @Test
    void movable_white_test() {
        // given, when
        Location frontTarget = Location.of(4, 3);
        Location twoFrontTarget = Location.of(5, 3);
        Location leftDiagonalTarget = Location.of(3, 3);
        Location rightDiagonalTarget = Location.of(5, 3);

        // then
        assertThat(whitePawn.isMovable(frontTarget)).isTrue();
        assertThat(whitePawn.isMovable(twoFrontTarget)).isTrue();
        assertThat(whitePawn.isMovable(leftDiagonalTarget)).isTrue();
        assertThat(whitePawn.isMovable(rightDiagonalTarget)).isTrue();
    }

    @DisplayName("검정색 폰은 4가지 위치로 이동 가능하다.")
    @Test
    void movable_black_test() {
        // given, when
        Location frontTarget = Location.of(4, 6);
        Location twoFrontTarget = Location.of(4, 5);
        Location leftDiagonalTarget = Location.of(3, 6);
        Location rightDiagonalTarget = Location.of(5, 6);

        // then
        assertThat(blackPawn.isMovable(frontTarget)).isTrue();
        assertThat(blackPawn.isMovable(twoFrontTarget)).isTrue();
        assertThat(blackPawn.isMovable(leftDiagonalTarget)).isTrue();
        assertThat(blackPawn.isMovable(rightDiagonalTarget)).isTrue();
    }

    @DisplayName("흰색 폰은 4가지 위치 이외로는 이동 불가능하다.")
    @Test
    void nonMovable_white_test() {
        // given, when
        Location backTarget = Location.of(4, 1);
        Location leftTarget = Location.of(3, 2);
        Location rightTarget = Location.of(5, 2);

        // then
        assertThat(whitePawn.isMovable(backTarget)).isFalse();
        assertThat(whitePawn.isMovable(leftTarget)).isFalse();
        assertThat(whitePawn.isMovable(rightTarget)).isFalse();
    }

    @DisplayName("검정색 폰은 4가지 위치 이외로는 이동 불가능하다.")
    @Test
    void nonMovable_black_test() {
        // given, when
        Location backTarget = Location.of(4, 8);
        Location leftTarget = Location.of(3, 7);
        Location rightTarget = Location.of(5, 7);

        // then
        assertThat(blackPawn.isMovable(backTarget)).isFalse();
        assertThat(blackPawn.isMovable(leftTarget)).isFalse();
        assertThat(blackPawn.isMovable(rightTarget)).isFalse();
    }
}
