package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.piece.Bishop;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.pawn.Pawn;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ChessInitializerTest {
    private static Stream<Arguments> provideChessBoardPieces() {
        return Stream.of(
                Arguments.of("a7", Pawn.of(Color.BLACK)), Arguments.of("b7", Pawn.of(Color.BLACK)),
                Arguments.of("c7", Pawn.of(Color.BLACK)), Arguments.of("d7", Pawn.of(Color.BLACK)),
                Arguments.of("e7", Pawn.of(Color.BLACK)), Arguments.of("f7", Pawn.of(Color.BLACK)),
                Arguments.of("g7", Pawn.of(Color.BLACK)), Arguments.of("h7", Pawn.of(Color.BLACK)),
                Arguments.of("a8", new Rook(Color.BLACK)), Arguments.of("b8", new Knight(Color.BLACK)),
                Arguments.of("c8", new Bishop(Color.BLACK)), Arguments.of("d8", new Queen(Color.BLACK)),
                Arguments.of("e8", new King(Color.BLACK)), Arguments.of("f8", new Bishop(Color.BLACK)),
                Arguments.of("g8", new Knight(Color.BLACK)), Arguments.of("h8", new Rook(Color.BLACK)),
                Arguments.of("c2", Pawn.of(Color.WHITE)), Arguments.of("d2", Pawn.of(Color.WHITE)),
                Arguments.of("e2", Pawn.of(Color.WHITE)), Arguments.of("f2", Pawn.of(Color.WHITE)),
                Arguments.of("g2", Pawn.of(Color.WHITE)), Arguments.of("h2", Pawn.of(Color.WHITE)),
                Arguments.of("a1", new Rook(Color.WHITE)), Arguments.of("b1", new Knight(Color.WHITE)),
                Arguments.of("c1", new Bishop(Color.WHITE)), Arguments.of("d1", new Queen(Color.WHITE)),
                Arguments.of("e1", new King(Color.WHITE)), Arguments.of("f1", new Bishop(Color.WHITE)),
                Arguments.of("g1", new Knight(Color.WHITE)), Arguments.of("h1", new Rook(Color.WHITE)));
    }

    @ParameterizedTest
    @MethodSource(value = "provideChessBoardPieces")
    void initBoard(String squareName, Piece piece) {
        BoardInitializer boardInitializer = new ChessInitializer();
        Map<Square, Piece> board = boardInitializer.initPieces();
        assertThat(board.containsValue(piece)).isTrue();
    }
}
