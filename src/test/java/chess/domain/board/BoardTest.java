package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드 생성 시 32개의 기물이 초기화된다.")
    void printMap() {
        Board board = new Board();

        Map<Position, Piece> boardMap = board.getBoard();

        assertThat(boardMap).hasSize(32);
    }

    @Test
    @DisplayName("보드 팩토리로 체스 시작 보드가 정상적으로 초기화 된다")
    void boardInitializeTest() {
        Map<Position, Piece> map = new HashMap<>();
        map.put(new Position(Row.ONE, Column.A), new Piece(PieceType.ROOK, Color.WHITE));
        map.put(new Position(Row.ONE, Column.H), new Piece(PieceType.ROOK, Color.WHITE));
        map.put(new Position(Row.ONE, Column.B), new Piece(PieceType.KNIGHT, Color.WHITE));
        map.put(new Position(Row.ONE, Column.G), new Piece(PieceType.KNIGHT, Color.WHITE));
        map.put(new Position(Row.ONE, Column.C), new Piece(PieceType.BISHOP, Color.WHITE));
        map.put(new Position(Row.ONE, Column.F), new Piece(PieceType.BISHOP, Color.WHITE));
        map.put(new Position(Row.ONE, Column.D), new Piece(PieceType.QUEEN, Color.WHITE));
        map.put(new Position(Row.ONE, Column.E), new Piece(PieceType.KING, Color.WHITE));

        map.put(new Position(Row.TWO, Column.A), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.H), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.B), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.G), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.C), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.F), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.D), new Piece(PieceType.WHITE_PAWN, Color.WHITE));
        map.put(new Position(Row.TWO, Column.E), new Piece(PieceType.WHITE_PAWN, Color.WHITE));

        map.put(new Position(Row.EIGHT, Column.A), new Piece(PieceType.ROOK, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.H), new Piece(PieceType.ROOK, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.B), new Piece(PieceType.KNIGHT, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.G), new Piece(PieceType.KNIGHT, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.C), new Piece(PieceType.BISHOP, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.F), new Piece(PieceType.BISHOP, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.D), new Piece(PieceType.QUEEN, Color.BLACK));
        map.put(new Position(Row.EIGHT, Column.E), new Piece(PieceType.KING, Color.BLACK));

        map.put(new Position(Row.SEVEN, Column.A), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.H), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.B), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.G), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.C), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.F), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.D), new Piece(PieceType.BLACK_PAWN, Color.BLACK));
        map.put(new Position(Row.SEVEN, Column.E), new Piece(PieceType.BLACK_PAWN, Color.BLACK));

        Board board = new Board();

        assertThat(new Board(map)).isEqualTo(board);
    }
}
