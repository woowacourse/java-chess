package chess.model.piece;

import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceFixture.BLACK_KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Distance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest(name = "movable()은 나이트가 ({0}, {1})로 이동할 때 {2}를 반환한다.")
    @CsvSource({
            "2,-1,true", "2,1,true", "-2,1,true", "-2,-1,true",
            "-1,2,true", "1,2,true", "1,-2,true", "-1,-2,true",
            "0,3,false", "3,0,false", "-4,-4,false", "6,-6,false", "3,3,false", "-3,3,false"
    })
    void knight_givenRankAndFile_thenReturnIfMovable(
            final int rank,
            final int file,
            final boolean result
    ) {
        // given
        final Distance distance = new Distance(file, rank);

        // when
        final boolean movable = BLACK_KNIGHT.isMovable(distance, WHITE);

        // then
        assertThat(movable).isEqualTo(result);
    }
}
