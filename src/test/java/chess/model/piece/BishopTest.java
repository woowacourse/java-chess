package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest(name = "BISHOP이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
    @CsvSource({"0,3,false", "3,0,false", "-4,-4,true", "6,-6,true", "3,3,true", "-3,3,true"})
    void bishop_givenRankAndFile_thenReturnIfMovable(
            final int rank,
            final int file,
            final boolean result
    ) {
        // given
        final Bishop bishop = new Bishop(PieceColor.WHITE);
        final Distance distance = new Distance(rank, file);

        // when
        final boolean movable = bishop.movable(distance);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
