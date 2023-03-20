package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.info.Team;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"b:2", "b:3", "b:4", "d:2", "d:3", "d:4", "c:4", "c:2"}, delimiter = ':')
    void should_true반환_when_움직일_수_있는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.C, File.THREE);
        Position destination = Position.of(Rank.from(rank), File.from(file));
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.canMove(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:3", "c:5", "a:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, int file) {
        //given
        Position source = Position.of(Rank.from(rank), File.from(file));
        Position destination = Position.of(Rank.C, File.THREE);
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.canMove(source, destination);

        //then
        assertThat(actual).isFalse();
    }
}