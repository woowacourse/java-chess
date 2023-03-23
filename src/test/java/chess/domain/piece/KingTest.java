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
    void 기물이_움직일_수_있는_위치라면_true반환(String rank, String file) {
        //given
        Position startPosition = Position.of(Rank.C, File.THREE);
        Position endPosition = Position.of(Rank.from(rank), File.from(file));
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:3", "c:5", "a:3"}, delimiter = ':')
    void 기물이_움직일_수_없는_위치라면_false반환(String rank, String file) {
        //given
        Position startPosition = Position.of(Rank.from(rank), File.from(file));
        Position endPosition = Position.of(Rank.C, File.THREE);
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }
}
