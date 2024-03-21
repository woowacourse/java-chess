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

public class BishopTest {
    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ....P...  3
     * .p......  2
     * ..b.....  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position targetPosition = new Position(Row.RANK1, Column.C);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.BISHOP, Color.WHITE),
                        new Position(Row.RANK2, Column.B), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.RANK3, Column.E), new Piece(PieceType.BLACK_PAWN, Color.BLACK)
                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK2, Column.D),
                new Position(Row.RANK3, Column.E)
        );
    }
}
