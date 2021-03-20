package chess;

import chess.domain.Board;
import chess.domain.Point;
import chess.domain.piece.kind.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static chess.domain.piece.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    @DisplayName("보드 생성")
    @Test
    void create() {
        assertDoesNotThrow(Board::new);
    }

    @DisplayName("초기화된 보드 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void initialize(int value) {
        Board board = new Board();
        Map<Point, Piece> pieces = board.getBoard();

        assertThat(pieces.get(Point.valueOf(value, 0))).isInstanceOf(Rook.class);
        assertThat(pieces.get(Point.valueOf(value, 1))).isInstanceOf(Knight.class);
        assertThat(pieces.get(Point.valueOf(value, 2))).isInstanceOf(Bishop.class);
        assertThat(pieces.get(Point.valueOf(value, 3))).isInstanceOf(Queen.class);
        assertThat(pieces.get(Point.valueOf(value, 4))).isInstanceOf(King.class);
        assertThat(pieces.get(Point.valueOf(value, 5))).isInstanceOf(Bishop.class);
        assertThat(pieces.get(Point.valueOf(value, 6))).isInstanceOf(Knight.class);
        assertThat(pieces.get(Point.valueOf(value, 7))).isInstanceOf(Rook.class);
    }

    @DisplayName("초기화된 폰 위치 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    void initializePawn(int value) {
        Board board = new Board();
        Map<Point, Piece> pieces = board.getBoard();

        assertThat(pieces.get(Point.valueOf(value, 0))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 1))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 2))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 3))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 4))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 5))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 6))).isInstanceOf(Pawn.class);
        assertThat(pieces.get(Point.valueOf(value, 7))).isInstanceOf(Pawn.class);
    }

    @DisplayName("초기화된 빈 위치 확인")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void initializeNull(int value) {
        Board board = new Board();
        Map<Point, Piece> pieces = board.getBoard();

        assertThat(pieces.get(Point.valueOf(value, 0))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 1))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 2))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 3))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 4))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 5))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 6))).isInstanceOf(Empty.class);
        assertThat(pieces.get(Point.valueOf(value, 7))).isInstanceOf(Empty.class);
    }

    @DisplayName("유저로부터 입력받은 대로 기물 이동 확인")
    @Test
    void movePiece() {
        Board board = new Board();
        assertDoesNotThrow(() -> board.movePiece(Point.of("a2"), Point.of("a4"), WHITE));
    }

    @DisplayName("두 왕이 모두 살아있는 지 확인")
    @Test
    void checkBothKingsAlive() {
        Board board = new Board();
        assertTrue(board.hasBothKings());
    }

    @DisplayName("왕이 죽은 경우 확인")
    @Test
    void checkOneKingDead() {
        Board board = new Board();
        board.movePiece(Point.of("e2"), Point.of("e4"), WHITE);
        board.movePiece(Point.of("f7"), Point.of("f5"), BLACK);
        board.movePiece(Point.of("e4"), Point.of("f5"), WHITE);
        board.movePiece(Point.of("g7"), Point.of("g5"), BLACK);
        board.movePiece(Point.of("d1"), Point.of("h5"), WHITE);
        board.movePiece(Point.of("b8"), Point.of("c6"), BLACK);
        board.movePiece(Point.of("h5"), Point.of("e8"), WHITE);
        assertFalse(board.hasBothKings());
    }

    @DisplayName("초기 점수 확인")
    @Test
    void checkScore() {
        Board board = new Board();
        assertEquals(38, board.addScore(WHITE));
        assertEquals(38, board.addScore(BLACK));
    }

    @DisplayName("폰이 겹쳤을 때 0.5로 계산하는 점수 확인")
    @Test
    void checkPawnScore() {
        Board board = new Board();
        board.movePiece(Point.of("e2"), Point.of("e4"), WHITE);
        board.movePiece(Point.of("f7"), Point.of("f5"), BLACK);
        board.movePiece(Point.of("e4"), Point.of("f5"), WHITE);

        assertEquals(37, board.addScore(WHITE));
    }
}
