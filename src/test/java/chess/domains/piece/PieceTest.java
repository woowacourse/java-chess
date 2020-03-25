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


    @DisplayName("Bishop 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"d3, true", "f5, true", "d5, true", "g2, true", "e3, false"})
    void canMove_2(String target, boolean expectedResult) {
        Piece bishop = new Bishop(PieceColor.WHITE);

        boolean actualResult = bishop.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Queen 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e2, true", "e6, true", "d4, true", "g4, true",
            "d3, true", "f5, true", "d5, true", "g2, true",
            "f6, false"})
    void canMove_3(String target, boolean expectedResult) {
        Piece queen = new Queen(PieceColor.WHITE);

        boolean actualResult = queen.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("King 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"e3, true", "e5, true", "d4, true", "f4, true",
            "d3, true", "f5, true", "d5, true", "f5, true",
            "f6, false"})
    void canMove_4(String target, boolean expectedResult) {
        Piece king = new King(PieceColor.WHITE);

        boolean actualResult = king.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Knight 말이 원하는 위치로 이동할 수 있는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"c3, true", "c5, true", "d2, true", "f2, true",
            "d6, true", "f6, true", "g3, true", "g5, true",
            "f4, false"})
    void canMove_5(String target, boolean expectedResult) {
        Piece knight = new Knight(PieceColor.WHITE);

        boolean actualResult = knight.canMove(Position.ofPositionName("e4"), Position.ofPositionName(target));

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}