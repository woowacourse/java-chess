package chess.domain.piece.factory;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

//todo: refac
class PieceFactoryTest {

    @ParameterizedTest
    @DisplayName("#createPiece() : should return Class which implements Piece")
    @MethodSource("getCasesForCreatePiece")
    void createInitializedPiece(Class<? extends Piece> expected) {
        //todo: refac
        Piece piece = null;
        assertThat(piece).isInstanceOf(expected);
    }

    private static Stream<Arguments> getCasesForCreatePiece() {
        return Stream.of(
                Arguments.of(null, InitializedPawn.class),
                Arguments.of(null, InitializedPawn.class),
                Arguments.of(null, Rook.class),
                Arguments.of(null, Bishop.class),
                Arguments.of(null, Queen.class),
                Arguments.of(null, King.class)
        );
    }

    @Test
    @DisplayName("#createMovedPiece() : should return Class which implements Piece")
    void createMovedPice() {
        Piece piece = null;
        assertThat(piece).isInstanceOf(InitializedPawn.class);
    }

}