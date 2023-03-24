package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Turn;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"c:4", "c:3"}, delimiter = ':')
    void should_true반환_when_WHITE_팀일때_움직일_수_있는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.TWO);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        Pawn pawn = new Pawn(Team.WHITE);

        //when
        boolean actual = pawn.canMove(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:6", "c:5"}, delimiter = ':')
    void should_true반환_when_BLACK_팀일때_움직일_수_있는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.SEVEN);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        Pawn pawn = new Pawn(Team.BLACK);

        //when
        boolean actual = pawn.canMove(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:2", "c:1", "d:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.TWO);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        Pawn pawn = new Pawn(Team.WHITE);

        //when
        boolean actual = pawn.canMove(source, destination);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void should_false반환_when_움직인_기록이_있을_때_2칸을_움직이려하면() {
        //given
        Position source = Position.of(Rank.C, File.TWO);
        Position destination = Position.of(Rank.C, File.FOUR);
        Pawn pawn = new Pawn(Team.WHITE);
        pawn.addTrace(new Turn(), source);

        //when
        boolean actual = pawn.canMove(source, destination);

        //then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"b:3", "d:3"}, delimiter = ':')
    void should_ture반환_when_팀이_WHITE일때_공격할_수_있는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.TWO);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        Pawn pawn = new Pawn(Team.WHITE);

        //when
        boolean actual = pawn.canAttack(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"b:1", "d:1"}, delimiter = ':')
    void should_ture반환_when_팀이_BLACK일때_공격할_수_있는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.TWO);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        Pawn pawn = new Pawn(Team.BLACK);

        //when
        boolean actual = pawn.canAttack(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:2", "c:3", "c:4", "c:1"}, delimiter = ':')
    void should_false반환_when_공격할_수_없는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.TWO);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        Pawn pawn = new Pawn(Team.WHITE);

        //when
        boolean actual = pawn.canAttack(source, destination);

        //then
        assertThat(actual).isFalse();
    }
}