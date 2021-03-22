package domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("체스판 초기화시 2개의 King이 올바른 자리에 위치한다.")
    @Test
    void initKings() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("e8"))).isInstanceOf(King.class);
        assertThat(board.piece(new Position("e8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("e1"))).isInstanceOf(King.class);
        assertThat(board.piece(new Position("e1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 2개의 퀸이 올바른 자리에 위치한다.")
    @Test
    void initQueen() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("d8"))).isInstanceOf(Queen.class);
        assertThat(board.piece(new Position("d8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("d1"))).isInstanceOf(Queen.class);
        assertThat(board.piece(new Position("d1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 4개의 Bishop이 올바른 자리에 위치한다.")
    @Test
    void initBishop() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("c8"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("c8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("f8"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("f8")).isBlack()).isTrue();

        assertThat(board.piece(new Position("c1"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("c1")).isBlack()).isFalse();
        assertThat(board.piece(new Position("f1"))).isInstanceOf(Bishop.class);
        assertThat(board.piece(new Position("f1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 4개의 Knight가 올바른 자리에 위치한다.")
    @Test
    void initKnight() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("b8"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("b8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("g8"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("g8")).isBlack()).isTrue();

        assertThat(board.piece(new Position("b1"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("b1")).isBlack()).isFalse();
        assertThat(board.piece(new Position("g1"))).isInstanceOf(Knight.class);
        assertThat(board.piece(new Position("g1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 4개의 Rook이 올바른 자리에 위치한다.")
    @Test
    void initRook() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("a8"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("a8")).isBlack()).isTrue();
        assertThat(board.piece(new Position("h8"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("h8")).isBlack()).isTrue();

        assertThat(board.piece(new Position("a1"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("a1")).isBlack()).isFalse();
        assertThat(board.piece(new Position("h1"))).isInstanceOf(Rook.class);
        assertThat(board.piece(new Position("h1")).isBlack()).isFalse();
    }

    @DisplayName("체스판 초기화시 16개의 Pawn이 올바른 자리에 위치한다.")
    @Test
    void initPawn() {
        Board board = new Board();
        board.initChessPieces();

        assertThat(board.piece(new Position("a7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("a7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("b7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("b7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("c7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("c7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("d7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("d7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("e7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("e7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("f7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("f7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("g7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("g7")).isBlack()).isTrue();
        assertThat(board.piece(new Position("h7"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("h7")).isBlack()).isTrue();

        assertThat(board.piece(new Position("a2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("a2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("b2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("b2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("c2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("c2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("d2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("d2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("e2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("e2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("f2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("f2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("g2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("g2")).isBlack()).isFalse();
        assertThat(board.piece(new Position("h2"))).isInstanceOf(Pawn.class);
        assertThat(board.piece(new Position("h2")).isBlack()).isFalse();
    }

}