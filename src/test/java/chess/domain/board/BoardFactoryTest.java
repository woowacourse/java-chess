package chess.domain.board;

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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardFactoryTest {

    @ParameterizedTest
    @MethodSource("initialPieces")
    @DisplayName("규칙에 맞는 체스판이 생성된다.")
    void initialPieces(Position position, Piece piece) {
        Map<Position, Piece> board = new BasicBoardFactory().create();

        assertThat(board.get(position)).isEqualTo(piece);
    }

    private static Stream<Arguments> initialPieces() {
        return Stream.of(
                Arguments.of(Position.of(Column.A, Row.EIGHT), new RookPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.B, Row.EIGHT), new KnightPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.C, Row.EIGHT), new BishopPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.D, Row.EIGHT), new QueenPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.E, Row.EIGHT), new KingPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.F, Row.EIGHT), new BishopPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.G, Row.EIGHT), new KnightPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.H, Row.EIGHT), new RookPiece(Color.BLACK)),

                Arguments.of(Position.of(Column.A, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.B, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.C, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.D, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.E, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.F, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.G, Row.SEVEN), new PawnPiece(Color.BLACK)),
                Arguments.of(Position.of(Column.H, Row.SEVEN), new PawnPiece(Color.BLACK)),

                Arguments.of(Position.of(Column.A, Row.ONE), new RookPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.B, Row.ONE), new KnightPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.C, Row.ONE), new BishopPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.D, Row.ONE), new QueenPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.E, Row.ONE), new KingPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.F, Row.ONE), new BishopPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.G, Row.ONE), new KnightPiece(Color.WHITE)),

                Arguments.of(Position.of(Column.H, Row.ONE), new RookPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.A, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.B, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.C, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.D, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.E, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.F, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.G, Row.TWO), new PawnPiece(Color.WHITE)),
                Arguments.of(Position.of(Column.H, Row.TWO), new PawnPiece(Color.WHITE))
        );
    }

    @Test
    @DisplayName("원하는 상태의 체스판을 생성한다.")
    void createCustomBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.from("a1"), new BishopPiece(Color.BLACK));

        Map<Position, Piece> actual = new CustomBoardFactory(board).create();

        assertThat(actual.get(Position.from("a1"))).isEqualTo(new BishopPiece(Color.BLACK));
    }
}
