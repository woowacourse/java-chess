package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest(name = "KING이 ({0}, {1})로 이동할 때 movable 결과가 {2}로 나온다.")
    @CsvSource({"0,1,true", "1,0,true", "-1,-1,true", "1,-1,true", "1,1,true", "-1,1,true"})
    void king_givenRankAndFile_thenReturnIfMovable(
            final int rank,
            final int file,
            final boolean result
    ) {
        // given
        final King king = new King(PieceColor.WHITE);
        final Distance distance = new Distance(rank, file);

        // when
        final boolean movable = king.movable(distance);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
