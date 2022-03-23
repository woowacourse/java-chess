package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.FullPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
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
                positions.add(new Position(file, rank));
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
            Arguments.of(new Position(File.A, Rank.EIGHT), new RookPiece(Color.BLACK)),
            Arguments.of(new Position(File.B, Rank.EIGHT), new KnightPiece(Color.BLACK)),
            Arguments.of(new Position(File.C, Rank.EIGHT), new BishopPiece(Color.BLACK)),
            Arguments.of(new Position(File.D, Rank.EIGHT), new QueenPiece(Color.BLACK)),
            Arguments.of(new Position(File.E, Rank.EIGHT), new KingPiece(Color.BLACK)),
            Arguments.of(new Position(File.F, Rank.EIGHT), new BishopPiece(Color.BLACK)),
            Arguments.of(new Position(File.G, Rank.EIGHT), new KnightPiece(Color.BLACK)),
            Arguments.of(new Position(File.H, Rank.EIGHT), new RookPiece(Color.BLACK)),

            Arguments.of(new Position(File.A, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.B, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.C, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.D, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.E, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.F, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.G, Rank.SEVEN), new PawnPiece(Color.BLACK)),
            Arguments.of(new Position(File.H, Rank.SEVEN), new PawnPiece(Color.BLACK)),

            Arguments.of(new Position(File.A, Rank.ONE), new RookPiece(Color.WHITE)),
            Arguments.of(new Position(File.B, Rank.ONE), new KnightPiece(Color.WHITE)),
            Arguments.of(new Position(File.C, Rank.ONE), new BishopPiece(Color.WHITE)),
            Arguments.of(new Position(File.D, Rank.ONE), new QueenPiece(Color.WHITE)),
            Arguments.of(new Position(File.E, Rank.ONE), new KingPiece(Color.WHITE)),
            Arguments.of(new Position(File.F, Rank.ONE), new BishopPiece(Color.WHITE)),
            Arguments.of(new Position(File.G, Rank.ONE), new KnightPiece(Color.WHITE)),

            Arguments.of(new Position(File.H, Rank.ONE), new RookPiece(Color.WHITE)),
            Arguments.of(new Position(File.A, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.B, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.C, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.D, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.E, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.F, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.G, Rank.TWO), new PawnPiece(Color.WHITE)),
            Arguments.of(new Position(File.H, Rank.TWO), new PawnPiece(Color.WHITE))
        );
    }
}
