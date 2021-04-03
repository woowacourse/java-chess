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

public class BishopTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new ChessBoard().getChessBoard();
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

        assertTrue(piece.isMovable(Position.valueOf("c5"), board));
        assertTrue(piece.isMovable(Position.valueOf("d6"), board));
    }

    @Test
    @DisplayName("비숍이 이동이 가능한 경우")
    void fail_movable() {
        Piece piece = new Bishop(TeamColor.WHITE, Position.valueOf("b4"));

        assertTrue(piece.isMovable(Position.valueOf("e7"), board));
    }

    @Test
    @DisplayName("실패 - 이동한 장소에 같은 팀의 말이 있는 경우")
    void fail_same_team() {
        Piece piece = new Bishop(TeamColor.BLACK, Position.valueOf("b4"));

        assertFalse(piece.isMovable(Position.valueOf("e7"), board));
    }

    @Test
    @DisplayName("실패 - 이동이 불가능한 위치에 있는 경우")
    void fail_position() {
        Piece piece = new Bishop(TeamColor.WHITE, Position.valueOf("b4"));

        assertFalse(piece.isMovable(Position.valueOf("b5"), board));
        assertFalse(piece.isMovable(Position.valueOf("f8"), board));
    }

}
