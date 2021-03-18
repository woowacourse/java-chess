package chess;

import chess.domain.Board;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        Piece[][] pieces = board.getBoard();
        assertThat(pieces[value][0]).isInstanceOf(Rook.class);
        assertThat(pieces[value][1]).isInstanceOf(Knight.class);
        assertThat(pieces[value][2]).isInstanceOf(Bishop.class);
        assertThat(pieces[value][3]).isInstanceOf(Queen.class);
        assertThat(pieces[value][4]).isInstanceOf(King.class);
        assertThat(pieces[value][5]).isInstanceOf(Bishop.class);
        assertThat(pieces[value][6]).isInstanceOf(Knight.class);
        assertThat(pieces[value][7]).isInstanceOf(Rook.class);
    }

    @DisplayName("초기화된 폰 위치 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    void initializePawn(int value) {
        Board board = new Board();
        Piece[][] pieces = board.getBoard();

        assertThat(pieces[value][0]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][1]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][2]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][3]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][4]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][5]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][6]).isInstanceOf(Pawn.class);
        assertThat(pieces[value][7]).isInstanceOf(Pawn.class);
    }

    @DisplayName("초기화된 빈 위치 확인")
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void initializeNull(int value) {
        Board board = new Board();
        Piece[][] pieces = board.getBoard();

        assertThat(pieces[value][0]).isInstanceOf(Empty.class);
        assertThat(pieces[value][1]).isInstanceOf(Empty.class);
        assertThat(pieces[value][2]).isInstanceOf(Empty.class);
        assertThat(pieces[value][3]).isInstanceOf(Empty.class);
        assertThat(pieces[value][4]).isInstanceOf(Empty.class);
        assertThat(pieces[value][5]).isInstanceOf(Empty.class);
        assertThat(pieces[value][6]).isInstanceOf(Empty.class);
        assertThat(pieces[value][7]).isInstanceOf(Empty.class);
    }

    @DisplayName("두 왕이 모두 살아있는 지 확인")
    @Test
    void checkBothKingsAlive() {
        Board board = new Board();
        assertTrue(board.hasBothKings());
    }

    //TODO: 왕이 사망하셨을 때도 확인할 것

//    @DisplayName("유저로부터 입력받은 대로 기물 이동 확인")
//    @Test
//    void movePiece() {
//        Board board = new Board();
//        board.movePiece(Point.of("a1"), Point.of("b2"), "BLACK");
//
//    }
}
