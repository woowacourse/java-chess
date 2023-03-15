package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @ParameterizedTest(name = "ROOK이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
    @CsvSource({"0,3,true", "3,0,true", "0,-6,true", "-3,0,true", "1,3,false", "-1,3,false"})
    void rook_givenRankAndFile_thenReturnIfMovable(
            final int rank,
            final int file,
            final boolean result
    ) {
        // given
        final Rook rook = new Rook(PieceColor.BLACK);
        final Distance distance = new Distance(rank, file);

        // when
        final boolean movable = rook.movable(distance);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
