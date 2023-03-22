package chess.model.piece;

import static chess.model.piece.PieceFixture.WHITE_BISHOP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.board.DefaultColor;
import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest(name = "movable()은 비숍이 ({0}, {1})로 이동할 때 {2}를 반환한다.")
    @CsvSource({"0,3,false", "3,0,false", "-4,-4,true", "6,-6,true", "3,3,true", "-3,3,true"})
    void bishop_givenRankAndFile_thenReturnIfMovable(
            final int file,
            final int rank,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = WHITE_BISHOP.isMovable(distance, DefaultColor.EMPTY);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
