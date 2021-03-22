package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        this.chessBoard = new ChessBoard();
    }

    @Test
    @DisplayName("객체 생성")
    void create() {
        assertThatCode(() -> new Rook(TeamColor.BLACK)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("블랙팀 객체 이름출력")
    void ValidBlackRockName() {
        Piece rook = new Rook(TeamColor.BLACK);
        assertThat(rook.getPieceName()).isEqualTo("R");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhiteQueenName() {
        Piece rook = new Rook(TeamColor.WHITE);
        assertThat(rook.getPieceName()).isEqualTo("r");
    }

    @Test
    @DisplayName("룩 움직임 테스트")
    void moveTest() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        assertFalse(piece.isMoveAble(Position.valueOf("a2"), chessBoard));
    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest2() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a2"), chessBoard));

    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest82() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a6"), chessBoard));
    }

    @Test
    @DisplayName("같은팀 있는 경우")
    void moveTest4() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));

        assertFalse(piece.isMoveAble(Position.valueOf("a2"), chessBoard));
    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest3() {
        Piece piece = chessBoard.getChessBoard().get(Position.valueOf("a1"));
        chessBoard.getChessBoard().put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a7"), chessBoard));
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
