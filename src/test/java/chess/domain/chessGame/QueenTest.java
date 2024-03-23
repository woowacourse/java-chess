package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    /**
     * ........  8 (rank 8)
     * ...P....  7
     * ........  6
     * ........  5
     * .....P..  4
     * ..p.....  3
     * ..Pqp...  2
     * ..NkR...  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position targetPosition = new Position(Row.TWO, Column.D);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.QUEEN, Color.WHITE),
                        new Position(Row.TWO, Column.E), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.ONE, Column.D), new Piece(PieceType.KING, Color.WHITE),
                        new Position(Row.THREE, Column.C), new Piece(PieceType.BLACK_PAWN, Color.WHITE),
                        new Position(Row.SEVEN, Column.D), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.FOUR, Column.F), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.ONE, Column.E), new Piece(PieceType.ROOK, Color.BLACK),
                        new Position(Row.ONE, Column.C), new Piece(PieceType.KNIGHT, Color.BLACK),
                        new Position(Row.TWO, Column.C), new Piece(PieceType.BLACK_PAWN, Color.BLACK)

                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.THREE, Column.D),
                new Position(Row.FOUR, Column.D),
                new Position(Row.FIVE, Column.D),
                new Position(Row.SIX, Column.D),
                new Position(Row.SEVEN, Column.D),
                new Position(Row.THREE, Column.E),
                new Position(Row.FOUR, Column.F),
                new Position(Row.ONE, Column.E),
                new Position(Row.ONE, Column.C),
                new Position(Row.TWO, Column.C)
        );
    }
}
