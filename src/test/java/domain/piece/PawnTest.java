package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.TestFixture;
import domain.position.Position;
import domain.position.Positions;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("폰은 직진 한 칸 갈 수 있다")
    @Test
    void goStraight() {
        //given
        final Pawn black = TestFixture.BLACK_PAWN;
        final Pawn white = TestFixture.WHITE_PAWN;
        final Position source = Positions.from("D4");
        final Position blackDestination = Positions.from("D3");
        final Position whiteDestination = Positions.from("D5");

        //when

        //then
        assertThat(black.isMovable(source, blackDestination)).isTrue();
        assertThat(white.isMovable(source, whiteDestination)).isTrue();
    }

    @DisplayName("폰은 두 칸 초과해서 갈 수 없다.")
    @Test
    void cannotGo() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Position source = Positions.from("D4");
        final List<Position> cannotGo = Positions.of("D1", "B2", "F6");

        //when

        //then
        assertThat(cannotGo).allMatch(destination -> !pawn.isMovable(source, destination));
    }

    @DisplayName("폰은 후진할 수 없다.")
    @Test
    void cannotGo2() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Position source = Positions.from("D4");
        final List<Position> cannotGo = Positions.of("D5", "C5", "E6");

        //when

        //then
        assertThat(cannotGo).allMatch(destination -> !pawn.isMovable(source, destination));
    }

    @DisplayName("폰은 처음에 두 칸 또는 한 칸 갈 수 있다")
    @Test
    void canGoTwoAtFirst() {
        //given
        final Pawn black = TestFixture.BLACK_PAWN;
        final Pawn white = TestFixture.WHITE_PAWN;
        final Position blackSource = Positions.from("D7");
        final Position whiteSource = Positions.from("D2");
        final Position blackDestination = Positions.from("D5");
        final Position whiteDestination = Positions.from("D4");

        //when

        //then
        assertThat(black.isMovable(blackSource, blackDestination)).isTrue();
        assertThat(white.isMovable(whiteSource, whiteDestination)).isTrue();
    }

    @DisplayName("폰은 처음이 아니면 두 칸 갈 수 없다")
    @Test
    void cannotGoTwoAtNotFirst() {
        //given
        final Pawn pawn = TestFixture.BLACK_PAWN;
        final Position source = Positions.from("D4");
        final Position destination = Positions.from("D2");

        //when

        //then
        assertThat(pawn.isMovable(source, destination)).isFalse();
    }
}
