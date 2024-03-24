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

class KingTest {
    /**
     * ........  8 (rank 8)
     * ...P....  7
     * ........  6
     * ........  5
     * .....P..  4
     * ..p.....  3
     * ..Pkp...  2
     * ..NbR...  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position targetPosition = new Position(Row.RANK2, Column.D);
        Color currentTurn = Color.WHITE;
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.KING, currentTurn),
                        new Position(Row.RANK2, Column.E), new Piece(PieceType.WHITE_PAWN, currentTurn),
                        new Position(Row.RANK1, Column.D), new Piece(PieceType.BISHOP, currentTurn),
                        new Position(Row.RANK3, Column.C), new Piece(PieceType.BLACK_PAWN, currentTurn),
                        new Position(Row.RANK7, Column.D), new Piece(PieceType.BLACK_PAWN, currentTurn.opposite()),
                        new Position(Row.RANK4, Column.F), new Piece(PieceType.BLACK_PAWN, currentTurn.opposite()),
                        new Position(Row.RANK1, Column.E), new Piece(PieceType.ROOK, currentTurn.opposite()),
                        new Position(Row.RANK1, Column.C), new Piece(PieceType.KNIGHT, currentTurn.opposite()),
                        new Position(Row.RANK2, Column.C), new Piece(PieceType.BLACK_PAWN, currentTurn.opposite())

                )
        ), currentTurn);

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK3, Column.D),
                new Position(Row.RANK3, Column.E),
                new Position(Row.RANK1, Column.E),
                new Position(Row.RANK1, Column.C),
                new Position(Row.RANK2, Column.C)
        );
    }
}
