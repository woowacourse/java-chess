package chess.domain.piece;

import static chess.domain.PositionFixture.C_4;
import static chess.domain.PositionFixture.C_5;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"B:FIVE:true", "D:THREE:true", "E:SIX:false", "C:FOUR:false"}, delimiter = ':')
    void 킹은_상하좌우_대각선으로_이동할_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        King king = new King(Color.WHITE);

        assertThat(king.canMove(C_4, new Position(fileCoordinate, rankCoordinate))).isEqualTo(expect);
    }

    @Test
    void 킹이_움직일_수_있는_경로를_알_수_있다() {
        King king = new King(Color.WHITE);
        List<Position> path = king.findPath(C_4, C_5);

        assertThat(path).isEmpty();
    }
}
