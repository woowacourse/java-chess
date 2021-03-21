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

public class BishopTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        this.chessBoard = new ChessBoard();
    }

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new King(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackBishopName() {
        Piece bishop = new Bishop(TeamColor.BLACK);
        assertThat(bishop.getPieceName()).isEqualTo("B");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteBishopName() {
        Piece bishop = new Bishop(TeamColor.WHITE);
        assertThat(bishop.getPieceName()).isEqualTo("b");
    }


    @Test
    @DisplayName("비숍이 이동이 가능한 경우")
    void movable() {
        Piece piece = new Bishop(TeamColor.BLACK, Position.valueOf("b4"));

        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("c5"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("d6"), chessBoard));
    }

    @Test
    @DisplayName("비숍이 이동이 가능한 경우")
    void fail_movable() {
        Piece piece = new Bishop(TeamColor.WHITE, Position.valueOf("b4"));

        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("e7"), chessBoard));
    }

    @Test
    @DisplayName("실패 - 이동한 장소에 같은 팀의 말이 있는 경우")
    void fail_same_team() {
        Piece piece = new Bishop(TeamColor.BLACK, Position.valueOf("b4"));

        assertFalse(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("e7"), chessBoard));
    }

    @Test
    @DisplayName("실패 - 이동이 불가능한 위치에 있는 경우")
    void fail_position() {
        Piece piece = new Bishop(TeamColor.WHITE, Position.valueOf("b4"));

        assertFalse(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("b5"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("f8"), chessBoard));
    }
}
