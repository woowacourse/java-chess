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

public class PawnTest {

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
    void ValidBlackPawnName() {
        Piece pawn = new Pawn(TeamColor.BLACK);
        assertThat(pawn.getPieceName()).isEqualTo("P");
    }

    @Test
    @DisplayName("화이트팀 객체 이름출력")
    void ValidWhitePawnName() {
        Piece pawn = new Pawn(TeamColor.WHITE);
        assertThat(pawn.getPieceName()).isEqualTo("p");
    }

    @Test
    @DisplayName("폰이 시작지점에 있는 경우")
    void start_move() {
        Piece piece = chessBoard.getPiece(Position.valueOf("b2"));

        assertTrue(piece.isMoveAble(Position.valueOf("b3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), chessBoard));
    }

    @Test
    @DisplayName("실패 - 폰이 3 칸 이상 이동하는 경우")
    void move_3block() {
        Piece piece = chessBoard.getPiece(Position.valueOf("b2"));

        assertFalse(piece.isMoveAble(Position.valueOf("b5"), chessBoard));
    }

    @Test
    @DisplayName("폰이 앞으로 전진하는 경우")
    void move_front() {
        Piece pieceBlack = new Pawn(TeamColor.BLACK, Position.valueOf("b4"));
        Piece pieceWhite = new Pawn(TeamColor.WHITE, Position.valueOf("c4"));

        assertTrue(
            pieceBlack.isMoveAble(Position.valueOf("b3"), chessBoard));
        assertTrue(
            pieceWhite.isMoveAble(Position.valueOf("c5"), chessBoard));
    }

    @Test
    @DisplayName("폰이 뒤로 가는 경우")
    void move_back() {
        Piece pieceBlack = new Pawn(TeamColor.BLACK, Position.valueOf("b4"));
        Piece pieceWhite = new Pawn(TeamColor.WHITE, Position.valueOf("c4"));

        assertFalse(
            pieceBlack.isMoveAble(Position.valueOf("b5"), chessBoard));
        assertFalse(
            pieceWhite.isMoveAble(Position.valueOf("c3"), chessBoard));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 있는 경우")
    void move_obstacle() {
        Piece piece = chessBoard.getPiece(Position.valueOf("b2"));
        Piece pieceQueen = new Queen(TeamColor.WHITE, Position.valueOf("b3"));
        chessBoard.getChessBoard().put(Position.valueOf("b3"), pieceQueen);

        assertFalse(piece.isMoveAble(Position.valueOf("b3"), chessBoard));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 있고 대각선에 적의 기물이 있는 경우")
    void move_enemy_catch() {
        Piece piece = chessBoard.getPiece(Position.valueOf("b2"));
        Piece pieceQueen = new Queen(TeamColor.BLACK, Position.valueOf("b3"));
        chessBoard.getChessBoard().put(Position.valueOf("b3"), pieceQueen);
        chessBoard.getChessBoard().put(Position.valueOf("a3"), pieceQueen);
        chessBoard.getChessBoard().put(Position.valueOf("c3"), pieceQueen);

        assertFalse(piece.isMoveAble(Position.valueOf("b3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("a3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 있고 대각선에 팀의 기물이 있는 경우")
    void move_teach_catch() {
        Piece piece = chessBoard.getPiece(Position.valueOf("b2"));
        Piece pieceQueen = new Queen(TeamColor.WHITE, Position.valueOf("b3"));
        chessBoard.getChessBoard().put(Position.valueOf("b3"), pieceQueen);
        chessBoard.getChessBoard().put(Position.valueOf("a3"), pieceQueen);
        chessBoard.getChessBoard().put(Position.valueOf("c3"), pieceQueen);

        assertFalse(piece.isMoveAble(Position.valueOf("b3"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("a3"), chessBoard));
        assertFalse(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 없고 대각선에 적의 기물이 있는 경우")
    void move_free() {
        Piece piece = chessBoard.getPiece(Position.valueOf("b2"));
        Piece pieceQueen = new Queen(TeamColor.BLACK, Position.valueOf("b3"));
        chessBoard.getChessBoard().put(Position.valueOf("a3"), pieceQueen);
        chessBoard.getChessBoard().put(Position.valueOf("c3"), pieceQueen);

        assertTrue(piece.isMoveAble(Position.valueOf("b3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("b4"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("a3"), chessBoard));
        assertTrue(piece.isMoveAble(Position.valueOf("c3"), chessBoard));
    }
}
