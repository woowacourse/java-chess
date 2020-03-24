package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @DisplayName("체스 말 표시")
    @ParameterizedTest
    @MethodSource({"getCasesForShowPiece"})
    void showPiece(PieceType pieceType, String whiteName, String blackName) {
        WhitePiece whitePiece = new WhitePiece(pieceType);
        assertThat(whitePiece.toString()).isEqualTo(whiteName);

        BlackPiece blackPiece = new BlackPiece(pieceType);
        assertThat(blackPiece.toString()).isEqualTo(blackName);
    }

    private static Stream<Arguments> getCasesForShowPiece() {
        return Stream.of(
                Arguments.of(PieceType.PAWN, "p", "P"),
                Arguments.of(PieceType.KNIGHT, "n", "N"),
                Arguments.of(PieceType.ROOK, "r", "R"),
                Arguments.of(PieceType.BISHOP, "b", "B"),
                Arguments.of(PieceType.KING, "k", "K"),
                Arguments.of(PieceType.QUEEN, "q", "Q")
        );
    }
}
