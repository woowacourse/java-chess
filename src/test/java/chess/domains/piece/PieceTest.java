package chess.domains.piece;

import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @DisplayName("Rook 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e2, true", "e6, true", "d4, true", "g4, true", "f6, false"})
    void canMove_1(String target, boolean expectedResult) {
        Piece rook = new Rook(PieceColor.WHITE);

        boolean actualResult = rook.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}