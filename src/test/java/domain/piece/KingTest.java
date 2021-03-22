package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {


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
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("e1"));
        assertThat(piece.isMoveAble(Position.valueOf("e1"), Position.valueOf("e2"), chessBoard));
        assertThat(piece.isMoveAble(Position.valueOf("e1"), Position.valueOf("d2"), chessBoard));
        assertThat(piece.isMoveAble(Position.valueOf("e1"), Position.valueOf("f2"), chessBoard));
    }

    @Test
    @DisplayName("킹 이 8방향 모두 움직일 수 있는 경우")
    void move_king_all_direction() {
        Piece piece = new King(TeamColor.BLACK, Position.valueOf("b4"));

        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("a4"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("c3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("b3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("b5"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("a3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("c3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("a5"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("c5"), chessBoard));
    }

    @Test
    @DisplayName("킹이 2칸 이상 움직이는 경우")
    void king_over_move_fail() {
        Piece piece = new King(TeamColor.BLACK, Position.valueOf("b4"));

        assertFalse(piece.isMoveAble(Position.valueOf("b4"), Position.valueOf("b6"), chessBoard));
    }

}
