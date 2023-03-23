package chess.model.piece;

import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceFixture.BLACK_ROOK;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @ParameterizedTest(name = "movable()은 룩이 ({0}, {1})로 이동할 때 {2}를 반환한다.")
    @CsvSource({"0,3,true", "3,0,true", "0,-6,true", "-3,0,true", "1,3,false", "-1,3,false"})
    void rook_givenRankAndFile_thenReturnIfMovable(
            final int rank,
            final int file,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = BLACK_ROOK.isMovable(distance, WHITE);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
