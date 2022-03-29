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
import chess.domain.position.Rank;
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
        for (Rank rank : Rank.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, rank));
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
                Arguments.of(new Position(Column.A, Rank.EIGHT), new RookPiece(Color.BLACK)),
                Arguments.of(new Position(Column.B, Rank.EIGHT), new KnightPiece(Color.BLACK)),
                Arguments.of(new Position(Column.C, Rank.EIGHT), new BishopPiece(Color.BLACK)),
                Arguments.of(new Position(Column.D, Rank.EIGHT), new QueenPiece(Color.BLACK)),
                Arguments.of(new Position(Column.E, Rank.EIGHT), new KingPiece(Color.BLACK)),
                Arguments.of(new Position(Column.F, Rank.EIGHT), new BishopPiece(Color.BLACK)),
                Arguments.of(new Position(Column.G, Rank.EIGHT), new KnightPiece(Color.BLACK)),
                Arguments.of(new Position(Column.H, Rank.EIGHT), new RookPiece(Color.BLACK)),

                Arguments.of(new Position(Column.A, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.B, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.C, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.D, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.E, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.F, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.G, Rank.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(new Position(Column.H, Rank.SEVEN), new PawnPiece(Color.BLACK)),

                Arguments.of(new Position(Column.A, Rank.ONE), new RookPiece(Color.WHITE)),
                Arguments.of(new Position(Column.B, Rank.ONE), new KnightPiece(Color.WHITE)),
                Arguments.of(new Position(Column.C, Rank.ONE), new BishopPiece(Color.WHITE)),
                Arguments.of(new Position(Column.D, Rank.ONE), new QueenPiece(Color.WHITE)),
                Arguments.of(new Position(Column.E, Rank.ONE), new KingPiece(Color.WHITE)),
                Arguments.of(new Position(Column.F, Rank.ONE), new BishopPiece(Color.WHITE)),
                Arguments.of(new Position(Column.G, Rank.ONE), new KnightPiece(Color.WHITE)),

                Arguments.of(new Position(Column.H, Rank.ONE), new RookPiece(Color.WHITE)),
                Arguments.of(new Position(Column.A, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.B, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.C, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.D, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.E, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.F, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.G, Rank.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(new Position(Column.H, Rank.TWO), new PawnPiece(Color.WHITE))
        );
    }
}
