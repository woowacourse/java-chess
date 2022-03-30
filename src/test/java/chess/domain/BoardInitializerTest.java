package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardInitializerTest {

    @Test
    @DisplayName("Position값이 순서대로 들어가 있는지 확인한다.")
    void create() {
        List<Position> positions = new ArrayList<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, row));
            }
        }

        Map<Position, Piece> board = BoardInitializer.create();

        assertThat(board.keySet().containsAll(positions)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("initialPieces")
    @DisplayName("Piece들이 규칙에 맞게 잘 들어갔는지 확인한다.")
    void initialPieces(Position position, Piece piece) {
        Map<Position, Piece> board = BoardInitializer.create();

        assertThat(board.get(position)).isEqualTo(piece);
    }

    private static Stream<Arguments> initialPieces() {
        return Stream.of(
                Arguments.of(new Position(Column.A, Row.EIGHT), new RookPiece(Color.BLACK)),
                Arguments.of(new Position(Column.B, Row.EIGHT), new KnightPiece(Color.BLACK)),
                Arguments.of(new Position(Column.C, Row.EIGHT), new BishopPiece(Color.BLACK)),
                Arguments.of(new Position(Column.D, Row.EIGHT), new QueenPiece(Color.BLACK)),
                Arguments.of(new Position(Column.E, Row.EIGHT), new KingPiece(Color.BLACK)),
                Arguments.of(new Position(Column.F, Row.EIGHT), new BishopPiece(Color.BLACK)),
                Arguments.of(new Position(Column.G, Row.EIGHT), new KnightPiece(Color.BLACK)),
                Arguments.of(new Position(Column.H, Row.EIGHT), new RookPiece(Color.BLACK)),

                Arguments.of(new Position(Column.A, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.B, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.C, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.D, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.E, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.F, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.G, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.H, Row.SEVEN), new PawnPiece(Color.BLACK)),

                Arguments.of(new Position(Column.A, Row.ONE), new RookPiece(Color.WHITE)),
                Arguments.of(new Position(Column.B, Row.ONE), new KnightPiece(Color.WHITE)),
                Arguments.of(new Position(Column.C, Row.ONE), new BishopPiece(Color.WHITE)),
                Arguments.of(new Position(Column.D, Row.ONE), new QueenPiece(Color.WHITE)),
                Arguments.of(new Position(Column.E, Row.ONE), new KingPiece(Color.WHITE)),
                Arguments.of(new Position(Column.F, Row.ONE), new BishopPiece(Color.WHITE)),
                Arguments.of(new Position(Column.G, Row.ONE), new KnightPiece(Color.WHITE)),

                Arguments.of(new Position(Column.H, Row.ONE), new RookPiece(Color.WHITE)),
                Arguments.of(new Position(Column.A, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.B, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.C, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.D, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.E, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.F, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.G, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.H, Row.TWO), new PawnPiece(Color.WHITE))
        );
    }
}
