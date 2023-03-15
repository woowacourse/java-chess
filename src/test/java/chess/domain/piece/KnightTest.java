package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.info.Team;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"d:5", "e:4", "d:1", "e:2", "a:4", "b:5", "b:1", "a:2",}, delimiter = ':')
    void should_true반환_when_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.from(file));
        Position endPosition = new Position(Rank.C, File.THREE);
        Knight knight = new Knight(Team.WHITE);

        //when
        boolean actual = knight.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:1", "a:3", "c:8", "h:3", "a:1", "a:5", "h:4", "c:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.from(file));
        Position endPosition = new Position(Rank.C, File.THREE);
        Knight knight = new Knight(Team.WHITE);

        //when
        boolean actual = knight.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }


}