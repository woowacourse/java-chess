package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"c:4", "c:3"}, delimiter = ':')
    void should_true반환_when_WHITE_팀일때_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position("c", "2");
        Position endPosition = new Position(rank, file);
        Pawn pawn = new Pawn(Team.WHITE);

        //when
        boolean actual = pawn.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:6", "c:5"}, delimiter = ':')
    void should_true반환_when_BLACK_팀일때_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position("c", "7");
        Position endPosition = new Position(rank, file);
        Pawn pawn = new Pawn(Team.BLACK);

        //when
        boolean actual = pawn.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:2", "c:1", "d:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position("c", "2");
        Position endPosition = new Position(rank, file);
        Pawn pawn = new Pawn(Team.WHITE);

        //when
        boolean actual = pawn.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void should_false반환_when_움직인_기록이_있을_때_2칸을_움직이려하면() {
        //given
        Position startPosition = new Position("c", "2");
        Position endPosition = new Position("c", "4");
        Pawn pawn = new Pawn(Team.WHITE);
        pawn.addTrace(1, startPosition);

        //when
        boolean actual = pawn.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }
}