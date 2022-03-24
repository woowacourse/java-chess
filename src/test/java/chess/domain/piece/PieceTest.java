package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PieceTest {

    @DisplayName("isAtDisplayRowIdxOf 메서드는 체스말의 rank 정보가 row 값에 대응되는지 반환한다.")
    @ParameterizedTest(name = "rankIdx {0} : rowIdx {1}")
    @CsvSource(value = {"a1,7", "a2,6", "a3,5", "a4,4", "a5,3", "a6,2", "a7,1", "a8,0"})
    void isAtDisplayRowIdxOf(String positionKey, int rowIdx) {
        Piece piece = new Pawn(BLACK, Position.of(positionKey));

        boolean actual = piece.isAtDisplayRowIdxOf(rowIdx);

        assertThat(actual).isTrue();
    }
}
