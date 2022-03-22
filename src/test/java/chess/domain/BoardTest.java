package chess.domain;

import static chess.domain.File.*;
import static chess.domain.Rank.*;
import static chess.domain.piece.Team.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    @ParameterizedTest
    @DisplayName("초기에 기물이 올바른 위치에 있는지 확인한다.")
    @MethodSource("pieceLocationParameter")
    void checkPieceInitialLocation(File file, Rank rank, Piece piece) {
        Board board = new Board();
        assertThat(board.getPiece(file, rank)).isInstanceOf(piece.getClass());
    }

    private static Stream<Arguments> pieceLocationParameter() {
        return Stream.of(
                Arguments.arguments(A, ONE, new Rook(WHITE)),
                Arguments.arguments(B, ONE, new Knight(WHITE)),
                Arguments.arguments(C, ONE, new Bishop(WHITE)),
                Arguments.arguments(D, ONE, new Queen(WHITE)),
                Arguments.arguments(E, ONE, new King(WHITE)),
                Arguments.arguments(F, ONE, new Bishop(WHITE)),
                Arguments.arguments(G, ONE, new Knight(WHITE)),
                Arguments.arguments(H, ONE, new Rook(WHITE)),
                Arguments.arguments(A, TWO, new Pawn(WHITE)),
                Arguments.arguments(B, TWO, new Pawn(WHITE)),
                Arguments.arguments(C, TWO, new Pawn(WHITE)),
                Arguments.arguments(D, TWO, new Pawn(WHITE)),
                Arguments.arguments(E, TWO, new Pawn(WHITE)),
                Arguments.arguments(F, TWO, new Pawn(WHITE)),
                Arguments.arguments(G, TWO, new Pawn(WHITE)),
                Arguments.arguments(H, TWO, new Pawn(WHITE)),

                Arguments.arguments(A, EIGHT, new Rook(BLACK)),
                Arguments.arguments(B, EIGHT, new Knight(BLACK)),
                Arguments.arguments(C, EIGHT, new Bishop(BLACK)),
                Arguments.arguments(D, EIGHT, new Queen(BLACK)),
                Arguments.arguments(E, EIGHT, new King(BLACK)),
                Arguments.arguments(F, EIGHT, new Bishop(BLACK)),
                Arguments.arguments(G, EIGHT, new Knight(BLACK)),
                Arguments.arguments(H, EIGHT, new Rook(BLACK)),
                Arguments.arguments(A, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(B, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(C, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(D, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(E, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(F, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(G, SEVEN, new Pawn(BLACK)),
                Arguments.arguments(H, SEVEN, new Pawn(BLACK))
        );
    }
}
