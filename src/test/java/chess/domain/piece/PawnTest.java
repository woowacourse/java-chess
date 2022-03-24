package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @DisplayName("흰색 팀일 때 이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"e2,d3", "e2,e3", "e2,f3"})
    void movable_while(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertThat(pawn.movable(to, from)).isTrue();
    }

    @DisplayName("흰색 팀일 때 이동 불가 확인")
    @Test
    void movable_while_x() {
        // given
        Position to = new Position("a2");
        Position from = new Position("a1");

        Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertThat(pawn.movable(to, from)).isFalse();
    }

    @DisplayName("검은 팀일 때 이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"e7,e6", "e7,d6", "e7,f6"})
    void movable_black(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertThat(pawn.movable(to, from)).isTrue();
    }

    @DisplayName("검은 팀일 때 이동 불가 확인")
    @Test
    void movable_black_x() {
        // given
        Position to = new Position("a1");
        Position from = new Position("a2");

        Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertThat(pawn.movable(to, from)).isFalse();
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,P", "WHITE,p"})
    void name(Team team, String expect) {
        // given
        Pawn pawn = new Pawn(team);

        // then
        assertThat(pawn.getName()).isEqualTo(expect);
    }
}
