package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.Positions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("폰은 직진 한 칸 갈 수 있다")
    @Test
    void goStraight() {
        //given
        final Pawn black = new Pawn(Team.BLACK);
        final Pawn white = new Pawn(Team.WHITE);
        final Position source = Positions.from("D4");
        final Position blackDestination = Positions.from("D3");
        final Position whiteDestination = Positions.from("D5");

        //when

        //then
        assertThat(black.isMovable(source, blackDestination)).isTrue();
        assertThat(white.isMovable(source, whiteDestination)).isTrue();
    }

    @DisplayName("폰은 전진이 아니면 갈 수 없다")
    @Test
    void cannotGo() {
        //given
        final Pawn black = new Pawn(Team.BLACK);
        final Pawn white = new Pawn(Team.WHITE);
        final Position source = Positions.from("D4");
        final Position blackDestination = Positions.from("C4");
        final Position whiteDestination = Positions.from("E4");

        //when

        //then
        assertThat(black.isMovable(source, blackDestination)).isFalse();
        assertThat(white.isMovable(source, whiteDestination)).isFalse();
    }

    @DisplayName("폰은 처음에 두 칸 또는 한 칸 갈 수 있다")
    @Test
    void canGoTwoAtFirst() {
        //given
        final Pawn black = new Pawn(Team.BLACK);
        final Pawn white = new Pawn(Team.WHITE);
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
        final Pawn pawn = new Pawn(Team.BLACK);
        final Position source = Positions.from("D4");
        final Position destination = Positions.from("D2");

        //when

        //then
        assertThat(pawn.isMovable(source, destination)).isFalse();
    }
}
