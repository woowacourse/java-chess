package chess.model.piece;

import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceFixture.BLACK_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest(name = "movable()은 퀸이 ({0}, {1})로 이동할 때 {2}를 나온다.")
    @CsvSource({"0,3,true", "3,0,true", "-4,-4,true", "6,-6,true", "3,3,true", "-3,3,true"})
    void queen_givenRankAndFile_thenReturnIfMovable(
            final int rank,
            final int file,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = BLACK_QUEEN.isMovable(distance, WHITE);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
