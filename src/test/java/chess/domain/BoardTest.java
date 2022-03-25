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

    @Test
    @DisplayName("폰은 상대기물이 목적지에 존재하지 않으면 대각선으로 움직일 수 없다")
    void pawnCannotMoveDiagonal_targetNotExist() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            Piece whitePawn = new Piece(Color.WHITE, new Pawn());
            pieces.put(Position.of("a2"), whitePawn);
            return pieces;
        });

        assertThatThrownBy(() -> board.move("a2", "b3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 상대기물이 목적지에 존재해야 대각선으로 움직일 수 있다.");
    }

    @Test
    @DisplayName("폰은 상대기물이 목적지에 존재하면 대각선으로 움직일 수 있다")
    void pawnCanMoveDiagonal_targetExist() {
        Piece whitePawn = new Piece(Color.WHITE, new Pawn());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a2"), whitePawn);
            pieces.put(Position.of("b3"), new Piece(Color.BLACK, new Pawn()));
            return pieces;
        });

        board.move("a2", "b3");
        assertThat(board.piece(Position.of("b3")).get()).isEqualTo(whitePawn);
    }

    @Test
    @DisplayName("폰은 직선 목적지에 다른 기물이 존재하면 움직일 수 없다")
    void pawnCannotMoveVertical_targetExist() {
        Piece whitePawn = new Piece(Color.WHITE, new Pawn());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a2"), whitePawn);
            pieces.put(Position.of("a4"), new Piece(Color.BLACK, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("a2", "a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 직진할 때 다른 기물이 존재하는 목적지에 이동할 수 없다.");
    }

    @Test
    @DisplayName("폰은 두칸 이동할 때 경로에 다른 기물이 있으면 움직일 수 없다")
    void pawnCannotMoveVertical_pathBlocked() {
        Piece whitePawn = new Piece(Color.WHITE, new Pawn());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a2"), whitePawn);
            pieces.put(Position.of("a3"), new Piece(Color.BLACK, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("a2", "a4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("목적지에 같은 색의 기물이 있으면 움직일 수 없다")
    void pieceCannotMove_ifTargetIsSameColor() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), new Piece(Color.BLACK, new Rook()));
            pieces.put(Position.of("b3"), new Piece(Color.BLACK, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("b8", "b3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 목적지에 같은 색의 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("직선 이동경로에 다른 기물이 있으면 움직일 수 없다")
    void rookCannotMove_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), new Piece(Color.BLACK, new Rook()));
            pieces.put(Position.of("b5"), new Piece(Color.BLACK, new Pawn()));
            pieces.put(Position.of("b3"), new Piece(Color.WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("b8", "b3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("대각선 이동경로에 다른 기물이 있으면 움직일 수 없다")
    void bishopCannotMove_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), new Piece(Color.BLACK, new Bishop()));
            pieces.put(Position.of("d6"), new Piece(Color.BLACK, new Pawn()));
            pieces.put(Position.of("e5"), new Piece(Color.WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("b8", "e5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("나이트는 이동경로에 다른 기물이 있어도 이동할 수 있다")
    void knightCanMove_anotherPiecesExistOnPath() {
        Piece piece = new Piece(Color.BLACK, new Knight());

        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), piece);
            pieces.put(Position.of("c8"), new Piece(Color.BLACK, new Pawn()));
            pieces.put(Position.of("d7"), new Piece(Color.WHITE, new Pawn()));
            return pieces;
        });

        board.move("b8", "d7");
        assertThat(board.piece(Position.of("d7")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("퀸은 수직이동 할 때 이동경로에 다른 기물이 있으면 이동할 수 없다")
    void queenCanNotMoveVertically_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b3"), new Piece(Color.BLACK, new Queen()));
            pieces.put(Position.of("b5"), new Piece(Color.BLACK, new Pawn()));
            pieces.put(Position.of("b8"), new Piece(Color.WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("b3", "b8"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("퀸은 평행이동 할 때 이동경로에 다른 기물이 있으면 이동할 수 없다")
    void queenCanNotMoveHorizontally_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b3"), new Piece(Color.BLACK, new Queen()));
            pieces.put(Position.of("e3"), new Piece(Color.BLACK, new Pawn()));
            pieces.put(Position.of("f3"), new Piece(Color.WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("b3", "f3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("퀸은 대각선 이동경로에 다른 기물이 있으면 움직일 수 없다")
    void queenCannotMoveDiagonally_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(Color.BLACK, new Queen()));
            pieces.put(Position.of("d4"), new Piece(Color.BLACK, new Pawn()));
            pieces.put(Position.of("b2"), new Piece(Color.WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move("g7", "b2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동경로에 다른 기물이 있으면 움직일 수 없다.");
    }

    @Test
    @DisplayName("킹이 잡히면 게임이 끝난다")
    void kingCaught_gameEnd() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(Color.BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(Color.WHITE, new King()));
            pieces.put(Position.of("g1"), new Piece(Color.WHITE, new Rook()));
            return pieces;
        });

        board.move("g1", "g7");
        assertThat(board.isEnd()).isTrue();
    }
}
