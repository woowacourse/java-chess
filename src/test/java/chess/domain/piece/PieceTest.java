package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {

    private static Stream<Arguments> generatePieces() {
        return Stream.of(
                Arguments.of(new King(Color.BLACK), "K"),
                Arguments.of(new King(Color.WHITE), "k"),
                Arguments.of(new Queen(Color.BLACK), "Q"),
                Arguments.of(new Queen(Color.WHITE), "q"),
                Arguments.of(new Bishop(Color.BLACK), "B"),
                Arguments.of(new Bishop(Color.WHITE), "b"),
                Arguments.of(new Knight(Color.BLACK), "N"),
                Arguments.of(new Knight(Color.WHITE), "n"),
                Arguments.of(new Rook(Color.BLACK), "R"),
                Arguments.of(new Rook(Color.WHITE), "r"),
                Arguments.of(new Pawn(Color.BLACK), "P"),
                Arguments.of(new Pawn(Color.WHITE), "p")
        );
    }

    @DisplayName("King을 생성한다.")
    @Test
    void King을_생성한다() {
        assertDoesNotThrow(() -> new King(Color.BLACK));
    }

    @DisplayName("진영에 맞는 표기 정보를 반환한다")
    @ParameterizedTest
    @MethodSource("generatePieces")
    void 진영에_맞는_표기_정보를_반환한다(Piece piece, String notation) {
        assertThat(piece.getNotation()).isEqualTo(notation);
    }
}
