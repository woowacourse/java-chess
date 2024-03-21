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

public class WhitePawnTest {
    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ..R.R...  3
     * ...p....  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("화이트 폰 시작 위치에서 양쪽 대각선에 상대 기물이 있고 앞 2칸은 비어있다.")
    void startPositionPawnWithOnlyAttackablePositions() {
        Position targetPosition = new Position(Row.RANK2, Column.D);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.RANK3, Column.C), new Piece(PieceType.ROOK, Color.BLACK),
                        new Position(Row.RANK3, Column.E), new Piece(PieceType.ROOK, Color.BLACK)
                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK3, Column.C),
                new Position(Row.RANK3, Column.E),
                new Position(Row.RANK3, Column.D),
                new Position(Row.RANK4, Column.D)
        );
    }

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ..p.....  3
     * ...p....  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("화이트 폰 시작 위치에서 앞으로만 이동할 수 있는 경우")
    void startPositionPawnWithFreePositions() {
        Position targetPosition = new Position(Row.RANK2, Column.D);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.RANK3, Column.C), new Piece(PieceType.WHITE_PAWN, Color.WHITE)
                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).containsExactlyInAnyOrder(
                new Position(Row.RANK3, Column.D),
                new Position(Row.RANK4, Column.D)
        );
    }

    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ..pP....  3
     * ...p....  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("화이트 폰 시작 위치에서 움직일 수 없는 경우")
    void startPositionPawnWithCantMovePositions() {
        Position targetPosition = new Position(Row.RANK2, Column.D);
        ChessGame chessGame = new ChessGame(new Board(
                Map.of(
                        targetPosition, new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.RANK3, Column.C), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.RANK3, Column.D), new Piece(PieceType.BLACK_PAWN, Color.BLACK)
                )
        ));

        List<Position> result = chessGame.generateMovablePositions(targetPosition);

        assertThat(result).isEmpty();
    }
}
