package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        this.chessBoard = new ChessBoard();
    }

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new Knight(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackQueenName() {
        Piece queen = new Queen(TeamColor.BLACK);
        assertThat(queen.getPieceName()).isEqualTo("Q");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteQueenName() {
        Piece queen = new Queen(TeamColor.WHITE);
        assertThat(queen.getPieceName()).isEqualTo("q");
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 경우 1칸짜리")
    void movable() {
        Piece piece = new Queen(TeamColor.BLACK, Position.valueOf("b4"));

        assertTrue(piece.isMoveAble(Position.valueOf("a4"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b5"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("a3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("a5"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c5"), chessBoard));
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 경우 2칸짜리")
    void movable_2blocks() {
        Piece piece = new Queen(TeamColor.BLACK, Position.valueOf("b4"));

        assertTrue(piece.isMoveAble(Position.valueOf("b6"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("d6"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("d4"), chessBoard));
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 경우 2칸짜리 _ 실패")
    void movable_2blocks_fail() {
        Piece piece = new Queen(TeamColor.BLACK, Position.valueOf("b4"));

        assertFalse(piece.isMoveAble(Position.valueOf("c6"), chessBoard));
    }
}
