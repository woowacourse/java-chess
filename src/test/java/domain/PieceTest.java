package domain;

import domain.piece.*;
import domain.piece.bishop.BlackBishop;
import domain.piece.bishop.WhiteBishop;
import domain.piece.king.BlackKing;
import domain.piece.king.WhiteKing;
import domain.piece.knight.BlackKnight;
import domain.piece.knight.WhiteKnight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.queen.BlackQueen;
import domain.piece.queen.WhiteQueen;
import domain.piece.rook.BlackRook;
import domain.piece.rook.WhiteRook;
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
                Arguments.of(new BlackBishop(), "B"),
                Arguments.of(new BlackKing(), "K"),
                Arguments.of(new BlackKnight(), "N"),
                Arguments.of(new BlackPawn(), "P"),
                Arguments.of(new BlackQueen(), "Q"),
                Arguments.of(new BlackRook(), "R"),
                Arguments.of(new WhiteBishop(), "b"),
                Arguments.of(new WhiteKing(), "k"),
                Arguments.of(new WhiteKnight(), "n"),
                Arguments.of(new WhitePawn(), "p"),
                Arguments.of(new WhiteQueen(), "q"),
                Arguments.of(new WhiteRook(), "r")
        );
    }
}