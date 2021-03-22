package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.pieceinformations.TeamColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

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
    void ValidBlackKnightName() {
        Piece knight = new Knight(TeamColor.BLACK);
        assertThat(knight.getPieceName()).isEqualTo("N");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteKnightName() {
        Piece knight = new Knight(TeamColor.WHITE);
        assertThat(knight.getPieceName()).isEqualTo("n");
    }

    @Test
    @DisplayName("이동이 가능한 경우 - 상대 말 잡기 포함")
    void movable() {
        Piece piece = new Knight(TeamColor.WHITE, Position.valueOf("d5"));

        assertTrue(piece.isMoveAble(Position.valueOf("e7"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("f6"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("f4"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("e3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b6"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c7"), chessBoard));
    }

    @Test
    @DisplayName("이동 불가능한 경우 - 영역범위 초과 및 target에 같은팀 포함")
    void fail_same_team() {
        Piece piece = new Knight(TeamColor.BLACK, Position.valueOf("b4"));

        assertFalse(piece.isMoveAble(Position.valueOf("d2"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("b6"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("f6"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("e7"), chessBoard));
    }
}
