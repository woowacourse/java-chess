package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("보드는 체스판을 입력받는다")
    void board_createdByGenerator() {
        Board board = new Board(new BoardInitiator());
        assertThat(board).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 source를 움직이면 예외를 발생한다")
    void thrown_sourceNotExist() {
        Board board = new Board(new BoardInitiator());
        assertThatThrownBy(() -> board.move("a3", "a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("출발지와 목적지가 같으면 예외를 발생한다")
    void thrown_sourceEqualsTarget() {
        Board board = new Board(new BoardInitiator());
        assertThatThrownBy(() -> board.move("a3", "a3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출발지와 목적지가 동일할 수 없습니다.");
    }

    @Test
    @DisplayName("룩을 목적지로 옮길 수 있다")
    void moveRook_sourceAndTargetGiven() {
        Piece piece = new Piece(Color.WHITE, new Rook());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a1"), piece);
            return pieces;
        });

        board.move("a1", "a8");

        assertThat(board.piece(Position.of("a8")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("비숍을 목적지로 옮길 수 있다")
    void moveBishop_sourceAndTargetGiven() {
        Piece piece = new Piece(Color.WHITE, new Bishop());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a1"), piece);
            return pieces;
        });

        board.move("a1", "e5");

        assertThat(board.piece(Position.of("e5")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("퀸을 목적지로 옮길 수 있다")
    void moveQueen_sourceAndTargetGiven() {
        Piece piece = new Piece(Color.WHITE, new Queen());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("f5"), piece);
            return pieces;
        });

        board.move("f5", "e4");

        assertThat(board.piece(Position.of("e4")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("나이트를 목적지로 옮길 수 있다")
    void moveKnight_sourceAndTargetGiven() {
        Piece piece = new Piece(Color.WHITE, new Knight());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("f5"), piece);
            return pieces;
        });

        board.move("f5", "d4");

        assertThat(board.piece(Position.of("d4")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("킹을 목적지로 옮길 수 있다")
    void moveKing_sourceAndTargetGiven() {
        Piece piece = new Piece(Color.WHITE, new King());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("f5"), piece);
            return pieces;
        });

        board.move("f5", "g4");

        assertThat(board.piece(Position.of("g4")).get()).isEqualTo(piece);
    }

}
