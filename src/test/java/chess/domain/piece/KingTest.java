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

public class KingTest {

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
    void validBlackKingName() {
        Piece king = new King(TeamColor.BLACK);
        assertThat(king.getPieceName()).isEqualTo("K");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void validWhiteKingName() {
        Piece king = new King(TeamColor.WHITE);
        assertThat(king.getPieceName()).isEqualTo("k");
    }

    @Test
    @DisplayName("킹 이 움직일 수 없는 경우")
    void moveKingFail() {
        Piece piece = board.get(Position.valueOf("e1"));
        assertFalse(piece.isMovable(Position.valueOf("e2"), board));
        assertFalse(piece.isMovable(Position.valueOf("d2"), board));
        assertFalse(piece.isMovable(Position.valueOf("f2"), board));
    }

    @Test
    @DisplayName("킹 이 8방향 모두 움직일 수 있는 경우")
    void move_king_all_direction() {
        Piece piece = new King(TeamColor.BLACK, Position.valueOf("b4"));

        assertTrue(piece.isMovable(Position.valueOf("a4"), board));
        assertTrue(piece.isMovable(Position.valueOf("c3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b5"), board));
        assertTrue(piece.isMovable(Position.valueOf("a3"), board));
        assertTrue(piece.isMovable(Position.valueOf("c3"), board));
        assertTrue(piece.isMovable(Position.valueOf("a5"), board));
        assertTrue(piece.isMovable(Position.valueOf("c5"), board));
    }

    @Test
    @DisplayName("킹이 2칸 이상 움직이는 경우")
    void king_over_move_fail() {
        Piece piece = new King(TeamColor.BLACK, Position.valueOf("b4"));

        assertFalse(piece.isMovable(Position.valueOf("b6"), board));
    }

}
