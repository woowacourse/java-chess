package chess.domain.piece.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTypeTest {
    @ParameterizedTest
    @DisplayName("#findByInitialColumn() : should return Class as to initialCollumn")
    @MethodSource({"getCasesForfindTypeByInitialColumn"})
    void findByInitialColumn(int initialColumn, PieceType expected) {
        PieceType pieceType = PieceType.findByInitialColumn(initialColumn);
        assertThat(pieceType).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForfindTypeByInitialColumn() {
        return Stream.of(
                Arguments.of(1, PieceType.ROOK),
                Arguments.of(2, PieceType.KNIGHT),
                Arguments.of(3, PieceType.BISHOP),
                Arguments.of(4, PieceType.QUEEN),
                Arguments.of(5, PieceType.KING),
                Arguments.of(6, PieceType.BISHOP),
                Arguments.of(7, PieceType.KNIGHT),
                Arguments.of(8, PieceType.ROOK)
        );
    }

    @Test
    @DisplayName("#findByInitialColumn() : should throw IllegalArgumentException with invalid initialCollumn")
    void findTypeByInitialColumnFail() {
        int invalidInitialColumn = 10;
        assertThatThrownBy(() -> PieceType.findByInitialColumn(invalidInitialColumn))
                .isInstanceOf(IllegalArgumentException.class);
    }
}