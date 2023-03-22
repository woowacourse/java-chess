package chess.model.piece;

import static chess.model.piece.PieceFixture.BLACK_PAWN;
import static chess.model.piece.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.DefaultColor;
import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest(name = "movable()은 폰이 {0} 팀일 때, ({1}, {2})만큼 이동하려고 하면 {3}을 반환한다")
    @CsvSource({
            "0,-1,true", "0,-2,false", "0,1,false", "0,2,false",
            "1,0,false", "2,0,false",
    })
    void movable_givenBlackPawnAndDistance_thenReturnIfMovable(
            final int file,
            final int rank,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = BLACK_PAWN.isMovable(distance, DefaultColor.EMPTY);

        // then
        assertThat(movable).isEqualTo(result);
    }

    @ParameterizedTest(name = "movable()은 폰이 {0} 팀일 때, ({1}, {2})만큼 이동하려고 하면 {3}을 반환한다")
    @CsvSource({
            "0,-1,false", "0,-2,false", "0,1,true", "0,2,false",
            "1,0,false", "2,0,false"
    })
    void movable_givenWhitePawnAndDistance_thenReturnIfMovable(
            final int file,
            final int rank,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = WHITE_PAWN.isMovable(distance, DefaultColor.EMPTY);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
