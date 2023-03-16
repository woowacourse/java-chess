package chess.domain.piece;

import static chess.domain.PositionFixture.B_1;
import static chess.domain.PositionFixture.B_2;
import static chess.domain.PositionFixture.B_3;
import static chess.domain.PositionFixture.C_2;
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
class PawnTest {

    @ParameterizedTest
    @CsvSource(value = {"B:TWO:true", "B:THREE:true", "A:TWO:true", "B:ONE:false", "C:THREE:false",
            "C:TWO:true"}, delimiter = ':')
    void 폰이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.canMove(B_1, new Position(fileCoordinate, rankCoordinate))).isEqualTo(expect);
    }

    @Test
    void 폰이_움직일_수_있는_경로를_알_수_있다() {
        Pawn pawn = new Pawn(Color.WHITE);
        List<Position> path = pawn.findPath(B_1, B_2);
        List<Position> path2 = pawn.findPath(B_1, C_2);
        List<Position> path3 = pawn.findPath(B_1, B_3);

        assertThat(path).isEmpty();
        assertThat(path2).isEmpty();
        assertThat(path3).contains(B_2);
    }
}
