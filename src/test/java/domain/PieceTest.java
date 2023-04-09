package domain;

import domain.piece.*;
import domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {
    @ParameterizedTest(name = "{displayName} - {1}")
    @MethodSource("pieceSymbolSources")
    @DisplayName("장기말 각각의 심볼을 조회할 수 있다.")
    void pieceSymbol(Piece piece, String expectedSymbol) {
        assertThat(piece.getSymbol())
                .isEqualTo(expectedSymbol);
    }

    private static Stream<Arguments> pieceSymbolSources() { // argument source method
        return Stream.of(
                Arguments.of(new Bishop(Turn.BLACK), "B"),
                Arguments.of(new King(Turn.BLACK), "K"),
                Arguments.of(new Knight(Turn.BLACK), "N"),
                Arguments.of(new Pawn(Turn.BLACK), "P"),
                Arguments.of(new Queen(Turn.BLACK), "Q"),
                Arguments.of(new Rook(Turn.BLACK), "R"),
                Arguments.of(new Bishop(Turn.WHITE), "b"),
                Arguments.of(new King(Turn.WHITE), "k"),
                Arguments.of(new Knight(Turn.WHITE), "n"),
                Arguments.of(new Pawn(Turn.WHITE), "p"),
                Arguments.of(new Queen(Turn.WHITE), "q"),
                Arguments.of(new Rook(Turn.WHITE), "r")
        );
    }
}
