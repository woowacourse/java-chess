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

public class PawnTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        final Map<Position, Piece> unmodifiableBoard = new ChessBoard().getChessBoard();
        board = new HashMap<>(unmodifiableBoard);
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
        Piece piece = new Pawn(TeamColor.WHITE);

        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b4"), board));
    }

    @Test
    @DisplayName("실패 - 폰이 3 칸 이상 이동하는 경우")
    void move_3block() {
        Piece piece = new Pawn(TeamColor.WHITE);

        assertFalse(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b5"), board));
    }

    @Test
    @DisplayName("폰이 앞으로 전진하는 경우")
    void move_front() {
        Piece pieceBlack = new Pawn(TeamColor.BLACK);
        Piece pieceWhite = new Pawn(TeamColor.WHITE);

        assertTrue(
                pieceBlack.isMovable(Position.valueOf("b4"), Position.valueOf("b3"), board));
        assertTrue(
                pieceWhite.isMovable(Position.valueOf("c4"), Position.valueOf("c5"), board));
    }

    @Test
    @DisplayName("폰이 뒤로 가는 경우")
    void move_back() {
        Piece pieceBlack = new Pawn(TeamColor.BLACK);
        Piece pieceWhite = new Pawn(TeamColor.WHITE);

        assertFalse(
                pieceBlack.isMovable(Position.valueOf("b4"), Position.valueOf("b5"), board));
        assertFalse(
                pieceWhite.isMovable(Position.valueOf("c4"), Position.valueOf("c3"), board));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 있는 경우")
    void move_obstacle() {
        Piece piece = new Pawn(TeamColor.WHITE);
        Piece pieceQueen = new Queen(TeamColor.WHITE);
        board.put(Position.valueOf("b3"), pieceQueen);

        assertFalse(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b3"), board));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 있고 대각선에 적의 기물이 있는 경우")
    void move_enemy_catch() {
        Piece piece = new Pawn(TeamColor.WHITE);
        Piece pieceQueen = new Queen(TeamColor.BLACK);
        board.put(Position.valueOf("b3"), pieceQueen);
        board.put(Position.valueOf("a3"), pieceQueen);
        board.put(Position.valueOf("c3"), pieceQueen);

        assertFalse(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("a3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("c3"), board));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 있고 대각선에 팀의 기물이 있는 경우")
    void move_teach_catch() {

        Piece piece = new Pawn(TeamColor.WHITE);
        Piece pieceQueen = new Queen(TeamColor.WHITE);
        board.put(Position.valueOf("b3"), pieceQueen);
        board.put(Position.valueOf("a3"), pieceQueen);
        board.put(Position.valueOf("c3"), pieceQueen);

        assertFalse(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b3"), board));
        assertFalse(piece.isMovable(Position.valueOf("b2"), Position.valueOf("a3"), board));
        assertFalse(piece.isMovable(Position.valueOf("b2"), Position.valueOf("c3"), board));
    }

    @Test
    @DisplayName("폰의 정면에 기물이 없고 대각선에 적의 기물이 있는 경우")
    void move_free() {
        Piece piece = new Pawn(TeamColor.WHITE);
        Piece pieceQueen = new Queen(TeamColor.BLACK);
        board.put(Position.valueOf("a3"), pieceQueen);
        board.put(Position.valueOf("c3"), pieceQueen);

        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("b4"), board));
        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("a3"), board));
        assertTrue(piece.isMovable(Position.valueOf("b2"), Position.valueOf("c3"), board));
    }

}
