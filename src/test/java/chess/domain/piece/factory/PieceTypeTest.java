package chess.domain.piece.factory;

import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTypeTest {

    @Test
    void valueOf() {
        PieceType pieceType = PieceType.valueOf(InitializedPawn.class);
        assertThat(pieceType).isEqualTo(PieceType.INITIALIZED_PAWN);
    }

    @ParameterizedTest
    @DisplayName("#findTypeByInitialColumn() : should return Class as to initialCollumn")
    @MethodSource({"getCasesForfindTypeByInitialColumn"})
    void findTypeByInitialColumnSucceed(int initialColumn, Class expected) {
        Class type = PieceType.findTypeByInitialColumn(initialColumn);
        assertThat(type).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForfindTypeByInitialColumn() {
        return Stream.of(
                Arguments.of(1, Rook.class),
                Arguments.of(2, Knight.class),
                Arguments.of(3, Bishop.class),
                Arguments.of(4, Queen.class),
                Arguments.of(5, King.class),
                Arguments.of(6, Bishop.class),
                Arguments.of(7, Knight.class),
                Arguments.of(8, Rook.class)
        );
    }

    @Test
    @DisplayName("#findTypeByInitialColumn() : should throw IllegalArgumentException with invalid initialCollumn")
    void findTypeByInitialColumnFail() {
        int invalidInitialColumn = 10;
        assertThatThrownBy(() -> PieceType.findTypeByInitialColumn(invalidInitialColumn))
                .isInstanceOf(IllegalArgumentException.class);
    }
}