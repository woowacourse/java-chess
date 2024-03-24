package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.position.Column;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    /**
     * ........  8 (rank 8)
     * ........  7
     * ..r.P...  6
     * ..P.P...  5
     * ...np...  4
     * .B.ppq..  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position targetPosition = new Position(Row.RANK4, Column.D);
        Color currentTurn = Color.WHITE;
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.KNIGHT, currentTurn),
                        new Position(Row.RANK3, Column.F), new Piece(PieceType.QUEEN, currentTurn),
                        new Position(Row.RANK6, Column.C), new Piece(PieceType.ROOK, currentTurn),
                        new Position(Row.RANK4, Column.E), new Piece(PieceType.WHITE_PAWN, currentTurn),
                        new Position(Row.RANK3, Column.D), new Piece(PieceType.WHITE_PAWN, currentTurn),
                        new Position(Row.RANK3, Column.E), new Piece(PieceType.WHITE_PAWN, currentTurn),
                        new Position(Row.RANK6, Column.E), new Piece(PieceType.BLACK_PAWN, currentTurn.opposite()),
                        new Position(Row.RANK5, Column.C), new Piece(PieceType.BLACK_PAWN, currentTurn.opposite()),
                        new Position(Row.RANK5, Column.E), new Piece(PieceType.BLACK_PAWN, currentTurn.opposite()),
                        new Position(Row.RANK3, Column.B), new Piece(PieceType.BISHOP, currentTurn.opposite())
                )
        ), currentTurn);

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK6, Column.E),
                new Position(Row.RANK5, Column.F),
                new Position(Row.RANK2, Column.E),
                new Position(Row.RANK2, Column.C),
                new Position(Row.RANK3, Column.B),
                new Position(Row.RANK5, Column.B)
        );
    }
}
