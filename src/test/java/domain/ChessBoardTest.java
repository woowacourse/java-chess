package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.State;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        this.chessBoard = new ChessBoard();
    }

    @Test
    @DisplayName("체스판 생성 테스트")
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("말 이동 실패 테스트")
    void failOutOfBoundary() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b10");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("동일한 위치 금지 테스트")
    void failSamePosition() {
        assertThatThrownBy(() -> {
            chessBoard.move("b2", "b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩 움직임 테스트")
    void moveTest() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        assertFalse(piece.isMoveAble(Position.valueOf("a1"), Position.valueOf("a2"), chessBoard));
    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest2() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a1"), Position.valueOf("a2"), chessBoard));

    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest82() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a1"), Position.valueOf("a6"), chessBoard));
    }

    @Test
    @DisplayName("같은팀 있는 경우")
    void moveTest4() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));

        assertFalse(piece.isMoveAble(Position.valueOf("a1"), Position.valueOf("a2"), chessBoard));
    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest3() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        assertFalse(piece.isMoveAble(Position.valueOf("a1"), Position.valueOf("a7"), chessBoard));
    }

    @Test
    @DisplayName("공백인 경우 이동잘했는가")
    void moveTest5() {
        Piece origin = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        chessBoard.move("a1", "a6");
        Piece after = chessBoard.getChessBoard().get(Position.valueOf("a6"));

        assertThat(after).isEqualTo(origin);
    }

    @Test
    @DisplayName("장애물인 경우 이동잘했는가")
    void moveTest6() {
        Piece origin = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);
        Piece caughtPiece = chessBoard.getChessBoard().get(Position.valueOf("a7"));

        chessBoard.move("a1", "a7");
        Piece after = chessBoard.getChessBoard().get(Position.valueOf("a7"));

        State dead = caughtPiece.getState();

        assertThat(dead).isEqualTo(State.DEAD);
    }


}
