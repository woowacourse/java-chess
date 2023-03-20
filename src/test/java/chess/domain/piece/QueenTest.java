package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.info.Team;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"a:1", "h:8", "a:3", "c:1", "e:1", "a:5", "c:8", "h:3"}, delimiter = ':')
    void 기물이_움직일_수_있는_위치라면_true반환(String rank, String file) {
        //given
        Position startPosition = Position.of(Rank.from(rank), File.from(file));
        Position endPosition = Position.of(Rank.C, File.THREE);
        Queen queen = new Queen(Team.WHITE);

        //when
        boolean actual = queen.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:3", "d:6", "h:4"}, delimiter = ':')
    void 기물이_움직일_수_없는_위치라면_false반환(String rank, String file) {
        //given
        Position startPosition = Position.of(Rank.from(rank), File.from(file));
        Position endPosition = Position.of(Rank.C, File.THREE);
        Queen queen = new Queen(Team.WHITE);

        //when
        boolean actual = queen.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }
}
