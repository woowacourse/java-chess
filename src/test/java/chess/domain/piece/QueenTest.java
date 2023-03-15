package chess.domain.piece;

import static chess.domain.board.FileCoordinate.C;
import static chess.domain.board.FileCoordinate.D;
import static chess.domain.board.FileCoordinate.E;
import static chess.domain.board.FileCoordinate.F;
import static chess.domain.board.RankCoordinate.FIVE;
import static chess.domain.board.RankCoordinate.FOUR;
import static chess.domain.board.RankCoordinate.SEVEN;
import static chess.domain.board.RankCoordinate.SIX;
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
class QueenTest {

    private static final Position C_4 = new Position(C, FOUR);
    private static final Position D_4 = new Position(D, FOUR);
    private static final Position E_4 = new Position(E, FOUR);
    private static final Position F_4 = new Position(F, FOUR);
    private static final Position C_5 = new Position(C, FIVE);
    private static final Position C_6 = new Position(C, SIX);
    private static final Position C_7 = new Position(C, SEVEN);
    private static final Position D_5 = new Position(D, FIVE);
    private static final Position E_6 = new Position(E, SIX);
    private static final Position F_7 = new Position(F, SEVEN);


    @ParameterizedTest
    @CsvSource(value = {"A:TWO:true", "E:SIX:true", "B:TWO:false", "C:FOUR:false", "B:SIX:false", "A:FOUR:true",
            "C:SIX:true"}, delimiter = ':')
    void 비숍이_움직일_수_있는지_알_수_있다(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate, boolean expect) {
        Queen queen = new Queen(Color.WHITE);
        assertThat(queen.canMove(C_4, new Position(fileCoordinate, rankCoordinate))).isEqualTo(expect);
    }

    @Test
    void 룩이_랭크로_움직일_수_있는_경로를_알_수_있다() {
        Queen queen = new Queen(Color.WHITE);
        List<Position> path = queen.findPath(C_4, F_4);
        List<Position> reversePath = queen.findPath(F_4, C_4);

        assertThat(path).contains(D_4, E_4);
        assertThat(reversePath).contains(D_4, E_4);
    }

    @Test
    void 룩이_파일로_움직일_수_있는_경로를_알_수_있다() {
        Queen queen = new Queen(Color.WHITE);
        List<Position> path = queen.findPath(C_4, C_7);
        List<Position> reversePath = queen.findPath(C_7, C_4);

        assertThat(path).contains(C_5, C_6);
        assertThat(reversePath).contains(C_5, C_6);
    }

    @Test
    void 비숍이_움직일_수_있는_경로를_알_수_있다() {
        Queen queen = new Queen(Color.WHITE);
        List<Position> path = queen.findPath(C_4, F_7);
        List<Position> reversePath = queen.findPath(F_7, C_4);

        assertThat(path).contains(D_5, E_6);
        assertThat(reversePath).contains(D_5, E_6);
    }
}
