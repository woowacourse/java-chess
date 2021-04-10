package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard().getChessBoard();
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
        Piece piece = new Queen(TeamColor.BLACK);

        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("a4"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("c3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("b3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("b5"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("a3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("c3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("a5"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("c5"), board));
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 경우 2칸짜리")
    void movable_2blocks() {
        Piece piece = new Queen(TeamColor.BLACK);

        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("b6"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("d6"), board));
        assertTrue(piece.isMovable(Position.valueOf("b4"), Position.valueOf("d4"), board));
    }

    @Test
    @DisplayName("퀸이 갈 수 있는 경우 2칸짜리 _ 실패")
    void movable_2blocks_fail() {

        Piece piece = new Queen(TeamColor.BLACK);

        assertFalse(piece.isMovable(Position.valueOf("b4"), Position.valueOf("c6"), board));
    }

}
