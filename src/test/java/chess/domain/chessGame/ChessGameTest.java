package chess.domain.chessGame;

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
        Position targetPosition = new Position(Row.RANK5, Column.D);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.ROOK, Color.BLACK),
                        new Position(Row.RANK4, Column.D), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.RANK5, Column.E), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.RANK5, Column.B), new Piece(PieceType.KNIGHT, Color.WHITE),
                        new Position(Row.RANK6, Column.D), new Piece(PieceType.BISHOP, Color.BLACK)
                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        Assertions.assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK4, Column.D),
                new Position(Row.RANK5, Column.E),
                new Position(Row.RANK5, Column.B),
                new Position(Row.RANK5, Column.C)
        );
    }

}
