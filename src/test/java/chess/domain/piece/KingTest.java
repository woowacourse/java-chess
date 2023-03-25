package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Score;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Position;
import chess.domain.piece.info.Team;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"b:2", "b:3", "b:4", "d:2", "d:3", "d:4", "c:4", "c:2"}, delimiter = ':')
    void 기물이_움직일_수_있는_위치라면_true반환(String rank, String file) {
        //given
        Position source = Position.of(File.C, Rank.THREE);
        Position destination = Position.of(File.from(rank), Rank.from(file));
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.canMove(source, destination);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:3", "c:5", "a:3"}, delimiter = ':')
    void 기물이_움직일_수_없는_위치라면_false반환(String rank, String file) {
        //given
        Position source = Position.of(File.from(rank), Rank.from(file));
        Position destination = Position.of(File.C, Rank.THREE);
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.canMove(source, destination);

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 기물의_점수_계산() {
        //given
        Map<PieceType, Long> pieceCountBoard = Map.of(PieceType.KING, 1L);
        King king = new King(Team.WHITE);

        //when
        Score actual = king.calculateScore(pieceCountBoard);

        //then
        assertThat(actual).isEqualTo(new Score(0.0));
    }

}
