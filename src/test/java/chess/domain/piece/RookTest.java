package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        final Map<Position, Piece> unmodifiableBoard = new ChessBoard().getChessBoard();
        board = new HashMap<>(unmodifiableBoard);
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
        Piece piece = board.get(Position.valueOf("a1"));
        assertFalse(piece.isMoveAble(Position.valueOf("a2"), board));
    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest2() {
        Piece piece = board.get(Position.valueOf("a1"));
        board.put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a2"), board));

    }

    @Test
    @DisplayName("장애물이 없을 경우")
    void moveTest82() {
        Piece piece = board.get(Position.valueOf("a1"));
        board.put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a6"), board));
    }

    @Test
    @DisplayName("같은팀 있는 경우")
    void moveTest4() {
        Piece piece = board.get(Position.valueOf("a1"));

        assertFalse(piece.isMoveAble(Position.valueOf("a2"), board));
    }

    @Test
    @DisplayName("장애물이 없고 target이 상대편인 경우")
    void moveTest3() {
        Piece piece = board.get(Position.valueOf("a1"));
        board.put(Position.valueOf("a2"), Blank.INSTANCE);

        assertTrue(piece.isMoveAble(Position.valueOf("a7"), board));
    }

    @Test
    @DisplayName("공백인 경우 이동잘했는가")
    void moveTest5() {
        Piece origin = board.get(Position.valueOf("a1"));
        board.put(Position.valueOf("a2"), Blank.INSTANCE);

        final ChessBoard chessBoard = new ChessBoard(board);
        chessBoard.move("a1", "a6");
        Piece after = board.get(Position.valueOf("a6"));

        assertThat(after).isEqualTo(origin);
    }

    @Test
    @DisplayName("상대방을 잡는 경우")
    void moveTest6() {
        board.put(Position.valueOf("a2"), Blank.INSTANCE);
        Piece caughtPiece = board.get(Position.valueOf("a7"));

        final ChessBoard chessBoard = new ChessBoard(board);
        chessBoard.move("a1", "a7");
        final Map<Position, Piece> modifiedChessBoard = chessBoard.getChessBoard();
        Piece after = modifiedChessBoard.get(Position.valueOf("a7"));

        assertThat(after).isInstanceOf(Rook.class);
        assertFalse(caughtPiece.isAlive());
    }

}
