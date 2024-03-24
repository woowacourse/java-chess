package chess.domain.positionFilter;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.game.PositionsFilter;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackPawnTest {
    /**
     * ........  8 (rank 8)
     * ...P....  7
     * ..r.r...  6
     * ........  5
     * ........  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("블랙 폰 시작 위치에서 양쪽 대각선에 상대 기물이 있고 앞 2칸은 비어있다.")
    void startPositionPawnWithOnlyAttackablePositions() {
        Position position = new Position(Row.SEVEN, Column.D);
        Piece piece = new Piece(PieceType.BLACK_PAWN, Color.BLACK);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.SIX, Column.C), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.SIX, Column.E), new Piece(PieceType.ROOK, Color.WHITE)
                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

         assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.SIX, Column.C),
                new Position(Row.SIX, Column.E),
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D)
        );
    }

    /**
     * ........  8 (rank 8)
     * ...P....  7
     * ..P.....  6
     * ........  5
     * ........  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("블랙 폰 시작 위치에서 앞으로만 이동할 수 있는 경우")
    void startPositionPawnWithFreePositions() {
        Position position = new Position(Row.SEVEN, Column.D);
        Piece piece = new Piece(PieceType.BLACK_PAWN, Color.BLACK);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.SIX, Column.C), new Piece(PieceType.BLACK_PAWN, Color.BLACK)
                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D)
        );
    }

    /**
     * ........  8 (rank 8)
     * ...P....  7
     * ..Pp....  6
     * ........  5
     * ........  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("블랙 폰 시작 위치에서 움직일 수 없는 경우")
    void startPositionPawnWithCantMovePositions() {
        Position position = new Position(Row.SEVEN, Column.D);
        Piece piece = new Piece(PieceType.BLACK_PAWN, Color.BLACK);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.SIX, Column.C), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.SIX, Column.D), new Piece(PieceType.BLACK_PAWN, Color.WHITE)
                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

        assertThat(movablePositions).isEmpty();
    }
}
