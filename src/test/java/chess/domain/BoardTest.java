package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.Color;
import chess.domain.piece.FullPiece;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    @Test
    @DisplayName("Position값이 순서대로 들어가 있는지 확인한다.")
    void create() {
        Board board = Board.create();

        List<Position> positions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                positions.add(new Position(rank, file));
            }
        }

        assertTrue(board.getBoard().keySet().containsAll(positions));
    }

    @ParameterizedTest
    @MethodSource("initialPieces")
    @DisplayName("Piece들이 규칙에 맞게 잘 들어갔는지 확인한다.")
    void initialPieces(Position position, FullPiece piece) {
        Board board = Board.create();

        assertThat(board.getBoard().get(position)).isEqualTo(piece);
    }

    private static Stream<Arguments> initialPieces() {
        return Stream.of(
            Arguments.of(new Position(Rank.EIGHT, File.A), new FullPiece(Color.BLACK, Type.ROOK)),
            Arguments.of(new Position(Rank.EIGHT, File.B), new FullPiece(Color.BLACK, Type.KNIGHT)),
            Arguments.of(new Position(Rank.EIGHT, File.C), new FullPiece(Color.BLACK, Type.BISHOP)),
            Arguments.of(new Position(Rank.EIGHT, File.D), new FullPiece(Color.BLACK, Type.QUEEN)),
            Arguments.of(new Position(Rank.EIGHT, File.E), new FullPiece(Color.BLACK, Type.KING)),
            Arguments.of(new Position(Rank.EIGHT, File.F), new FullPiece(Color.BLACK, Type.BISHOP)),
            Arguments.of(new Position(Rank.EIGHT, File.G), new FullPiece(Color.BLACK, Type.KNIGHT)),
            Arguments.of(new Position(Rank.EIGHT, File.H), new FullPiece(Color.BLACK, Type.ROOK)),

            Arguments.of(new Position(Rank.SEVEN, File.A), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.B), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.C), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.D), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.E), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.F), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.G), new FullPiece(Color.BLACK, Type.PAWN)),
            Arguments.of(new Position(Rank.SEVEN, File.H), new FullPiece(Color.BLACK, Type.PAWN)),

            Arguments.of(new Position(Rank.ONE, File.A), new FullPiece(Color.WHITE, Type.ROOK)),
            Arguments.of(new Position(Rank.ONE, File.B), new FullPiece(Color.WHITE, Type.KNIGHT)),
            Arguments.of(new Position(Rank.ONE, File.C), new FullPiece(Color.WHITE, Type.BISHOP)),
            Arguments.of(new Position(Rank.ONE, File.D), new FullPiece(Color.WHITE, Type.QUEEN)),
            Arguments.of(new Position(Rank.ONE, File.E), new FullPiece(Color.WHITE, Type.KING)),
            Arguments.of(new Position(Rank.ONE, File.F), new FullPiece(Color.WHITE, Type.BISHOP)),
            Arguments.of(new Position(Rank.ONE, File.G), new FullPiece(Color.WHITE, Type.KNIGHT)),
            Arguments.of(new Position(Rank.ONE, File.H), new FullPiece(Color.WHITE, Type.ROOK)),

            Arguments.of(new Position(Rank.TWO, File.A), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.B), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.C), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.D), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.E), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.F), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.G), new FullPiece(Color.WHITE, Type.PAWN)),
            Arguments.of(new Position(Rank.TWO, File.H), new FullPiece(Color.WHITE, Type.PAWN))
        );
    }

}
