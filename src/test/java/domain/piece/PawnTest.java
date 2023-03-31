package domain.piece;

import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.position.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰은 ")
class PawnTest {
    @DisplayName("직진 한 칸 갈 수 있다")
    @Test
    void goStraight() {
        //given
        final Pawn black = new Pawn(Team.BLACK);
        final Pawn white = new Pawn(Team.WHITE);
        final Position source = D4;
        final Position blackDestination = D3;
        final Position whiteDestination = D5;

        //when

        //then
        assertThat(black.isMovable(source, blackDestination)).isTrue();
        assertThat(white.isMovable(source, whiteDestination)).isTrue();
    }

    @DisplayName("두 칸 초과해서 갈 수 없다.")
    @Test
    void cannotGo() {
        //given
        final Pawn pawn = new Pawn(Team.BLACK);
        final Position source = D4;
        final List<Position> cannotGo = List.of(D1, B2, F6);

        //when

        //then
        assertThat(cannotGo).allMatch(destination -> !pawn.isMovable(source, destination));
    }

    @DisplayName("후진할 수 없다.")
    @Test
    void cannotGo2() {
        //given
        final Pawn pawn = new Pawn(Team.BLACK);
        final Position source = D4;
        final List<Position> cannotGo = List.of(D5, C5, E6);

        //when

        //then
        assertThat(cannotGo).allMatch(destination -> !pawn.isMovable(source, destination));
    }

    @DisplayName("처음에 두 칸 또는 한 칸 갈 수 있다")
    @Test
    void canGoTwoAtFirst() {
        //given
        final Pawn black = new Pawn(Team.BLACK);
        final Pawn white = new Pawn(Team.WHITE);
        final Position blackSource = D7;
        final Position whiteSource = D2;
        final Position blackDestination = D5;
        final Position whiteDestination = D4;

        //when

        //then
        assertThat(black.isMovable(blackSource, blackDestination)).isTrue();
        assertThat(white.isMovable(whiteSource, whiteDestination)).isTrue();
    }

    @DisplayName("처음이 아니면 두 칸 갈 수 없다")
    @Test
    void cannotGoTwoAtNotFirst() {
        //given
        final Pawn pawn = new Pawn(Team.BLACK);
        final Position source = D4;
        final Position destination = D2;

        //when

        //then
        assertThat(pawn.isMovable(source, destination)).isFalse();
    }

    @DisplayName("기본 점수는 1점이다.")
    @Test
    void score() {
        //given
        final Pawn pawn = new Pawn(Team.BLACK);

        //when

        //then
        assertThat(pawn.getScore()).isEqualTo(1);
    }
}
