package chess.domain.chessGame;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.board.Board;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    /**
     * ........  8 (rank 8)
     * ........  7
     * ...B....  6
     * .n.Rr...  5
     * ...r....  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position targetPosition = new Position(Row.FIVE, Column.D);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.ROOK, Color.BLACK),
                        new Position(Row.FOUR, Column.D), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.FIVE, Column.E), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.FIVE, Column.B), new Piece(PieceType.KNIGHT, Color.WHITE),
                        new Position(Row.SIX, Column.D), new Piece(PieceType.BISHOP, Color.BLACK)
                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        Assertions.assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.FOUR, Column.D),
                new Position(Row.FIVE, Column.E),
                new Position(Row.FIVE, Column.B),
                new Position(Row.FIVE, Column.C)
        );
    }

}
