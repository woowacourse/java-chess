package chess.model.piece;

import static chess.model.piece.PieceFixture.BLACK_INITIAL_PAWN;
import static chess.model.piece.PieceFixture.WHITE_INITIAL_PAWN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.DefaultColor;
import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InitialPawnTest {

    @ParameterizedTest(name = "movable()은 움직이지 않은 폰이 {0} 팀일 때 ({1}, {2})만큼 이동하려고 하면 {3}을 반환한다")
    @CsvSource({
            "0,-1,true", "0,-2,true", "0,1,false", "0,2,false",
            "1,0,false", "2,0,false"
    })
    void movable_givenBlackInitialPawnAndDistance_thenReturnIfMovable(
            final int file,
            final int rank,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = BLACK_INITIAL_PAWN.isMovable(distance, DefaultColor.EMPTY);

        // then
        assertThat(movable).isEqualTo(result);
    }

    @ParameterizedTest(name = "movable()은 움직이지 않은 폰이 {0} 팀일 때 ({1}, {2})만큼 이동하려고 하면 {3}을 반환한다")
    @CsvSource({
            "0,-1,false", "0,-2,false", "0,1,true", "0,2,true",
            "1,0,false", "2,0,false"
    })
    void movable_givenDistance_thenReturnIfMovable(
            final int file,
            final int rank,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = WHITE_INITIAL_PAWN.isMovable(distance, DefaultColor.EMPTY);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
