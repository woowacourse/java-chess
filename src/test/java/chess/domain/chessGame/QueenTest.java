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
        Position targetPosition = new Position(Row.RANK2, Column.d);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.QUEEN, Color.WHITE),
                        new Position(Row.RANK2, Column.e), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.RANK1, Column.d), new Piece(PieceType.KING, Color.WHITE),
                        new Position(Row.RANK3, Column.c), new Piece(PieceType.BLACK_PAWN, Color.WHITE),
                        new Position(Row.RANK7, Column.d), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.RANK4, Column.f), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.RANK1, Column.e), new Piece(PieceType.ROOK, Color.BLACK),
                        new Position(Row.RANK1, Column.c), new Piece(PieceType.KNIGHT, Color.BLACK),
                        new Position(Row.RANK2, Column.c), new Piece(PieceType.BLACK_PAWN, Color.BLACK)

                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK3, Column.d),
                new Position(Row.RANK4, Column.d),
                new Position(Row.RANK5, Column.d),
                new Position(Row.RANK6, Column.d),
                new Position(Row.RANK7, Column.d),
                new Position(Row.RANK3, Column.e),
                new Position(Row.RANK4, Column.f),
                new Position(Row.RANK1, Column.e),
                new Position(Row.RANK1, Column.c),
                new Position(Row.RANK2, Column.c)
        );
    }
}
