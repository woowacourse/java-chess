package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Score;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Position;
import chess.domain.piece.info.Team;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {


    @ParameterizedTest
    @CsvSource(value = {"a:1", "a:5", "e:1", "h:8"}, delimiter = ':')
    void 기물이_움직일_수_있는_위치라면_true반환(String rank, String file) {
        //given
        Position source = Position.of(File.from(rank), Rank.from(file));
        Position destination = Position.of(File.C, Rank.THREE);
        Bishop bishop = new Bishop(Team.WHITE);

        //when
        boolean actual = bishop.canMove(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:1", "a:3", "c:8", "h:3", "c:3"}, delimiter = ':')
    void 기물이_움직일_수_없는_위치라면_false반환(String rank, String file) {
        //given
        Position source = Position.of(File.from(rank), Rank.from(file));
        Position destination = Position.of(File.C, Rank.THREE);
        Bishop bishop = new Bishop(Team.WHITE);

        //when
        boolean actual = bishop.canMove(source, destination);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 기물의_점수_계산() {
        //given
        Map<PieceType, Long> pieceCountBoard = Map.of(PieceType.BISHOP, 1L);
        Bishop bishop = new Bishop(Team.WHITE);

        //when
        Score actual = bishop.calculateScore(pieceCountBoard);

        //then
        assertThat(actual).isEqualTo(new Score(3.0));
    }
}
