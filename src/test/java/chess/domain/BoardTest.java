package chess.domain;

import chess.domain.game.Board;
import chess.domain.game.BoardInitializer;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.pieces.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class BoardTest {

    @Test
    @DisplayName("보드는 체스판을 입력받는다")
    void board_createdByGenerator() {
        Board board = new Board(new BoardInitializer());
        assertThat(board).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 source를 움직이면 예외를 발생한다")
    void thrown_sourceNotExist() {
        Board board = new Board(new BoardInitializer());
        assertThatThrownBy(() -> board.move(Position.of("a3"), Position.of("a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("룩을 목적지로 옮길 수 있다")
    void moveRook_sourceAndTargetGiven() {
        Piece piece = new Piece(WHITE, new Rook());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a1"), piece);
            return pieces;
        });

        board.move(Position.of("a1"), Position.of("a8"));

        assertThat(board.piece(Position.of("a8")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("비숍을 목적지로 옮길 수 있다")
    void moveBishop_sourceAndTargetGiven() {
        Piece piece = new Piece(WHITE, new Bishop());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a1"), piece);
            return pieces;
        });

        board.move(Position.of("a1"), Position.of("e5"));

        assertThat(board.piece(Position.of("e5")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("퀸을 목적지로 옮길 수 있다")
    void moveQueen_sourceAndTargetGiven() {
        Piece piece = new Piece(WHITE, new Queen());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("f5"), piece);
            return pieces;
        });

        board.move(Position.of("f5"), Position.of("e4"));

        assertThat(board.piece(Position.of("e4")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("나이트를 목적지로 옮길 수 있다")
    void moveKnight_sourceAndTargetGiven() {
        Piece piece = new Piece(WHITE, new Knight());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("f5"), piece);
            return pieces;
        });

        board.move(Position.of("f5"), Position.of("d4"));

        assertThat(board.piece(Position.of("d4")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("킹을 목적지로 옮길 수 있다")
    void moveKing_sourceAndTargetGiven() {
        Piece piece = new Piece(WHITE, new King());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("f5"), piece);
            return pieces;
        });

        board.move(Position.of("f5"), Position.of("g4"));

        assertThat(board.piece(Position.of("g4")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("폰은 상대기물이 목적지에 존재하지 않으면 대각선으로 움직일 수 없다")
    void pawnCannotMoveDiagonal_targetNotExist() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            Piece whitePawn = new Piece(WHITE, new Pawn());
            pieces.put(Position.of("a2"), whitePawn);
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대기물이 목적지에 존재해야 대각선으로 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("폰은 상대기물이 목적지에 존재하면 대각선으로 움직일 수 있다")
    void pawnCanMoveDiagonal_targetExist() {
        Piece whitePawn = new Piece(WHITE, new Pawn());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a2"), whitePawn);
            pieces.put(Position.of("b3"), new Piece(BLACK, new Pawn()));
            return pieces;
        });

        board.move(Position.of("a2"), Position.of("b3"));
        assertThat(board.piece(Position.of("b3")).get()).isEqualTo(whitePawn);
    }

    @Test
    @DisplayName("폰은 직선 목적지에 다른 기물이 존재하면 움직일 수 없다")
    void pawnCannotMoveVertical_targetExist() {
        Piece whitePawn = new Piece(WHITE, new Pawn());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a2"), whitePawn);
            pieces.put(Position.of("a4"), new Piece(BLACK, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적지에 다른 기물이 존재하면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("폰은 두칸 이동할 때 경로에 다른 기물이 있으면 움직일 수 없다")
    void pawnCannotMoveVertical_pathBlocked() {
        Piece whitePawn = new Piece(WHITE, new Pawn());
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("a2"), whitePawn);
            pieces.put(Position.of("a3"), new Piece(BLACK, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("목적지에 같은 색의 기물이 있으면 움직일 수 없다")
    void pieceCannotMove_ifTargetIsSameColor() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), new Piece(BLACK, new Rook()));
            pieces.put(Position.of("b3"), new Piece(BLACK, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("b8"), Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("목적지에 같은 색의 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("직선 이동경로에 다른 기물이 있으면 움직일 수 없다")
    void rookCannotMove_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), new Piece(BLACK, new Rook()));
            pieces.put(Position.of("b5"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("b3"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("b8"), Position.of("b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("대각선 이동경로에 다른 기물이 있으면 움직일 수 없다")
    void bishopCannotMove_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), new Piece(BLACK, new Bishop()));
            pieces.put(Position.of("d6"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("e5"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("b8"), Position.of("e5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("나이트는 이동경로에 다른 기물이 있어도 이동할 수 있다")
    void knightCanMove_anotherPiecesExistOnPath() {
        Piece piece = new Piece(BLACK, new Knight());

        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b8"), piece);
            pieces.put(Position.of("c8"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("d7"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        board.move(Position.of("b8"), Position.of("d7"));
        assertThat(board.piece(Position.of("d7")).get()).isEqualTo(piece);
    }

    @Test
    @DisplayName("퀸은 수직이동 할 때 이동경로에 다른 기물이 있으면 이동할 수 없다")
    void queenCanNotMoveVertically_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b3"), new Piece(BLACK, new Queen()));
            pieces.put(Position.of("b5"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("b8"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("b3"), Position.of("b8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("퀸은 평행이동 할 때 이동경로에 다른 기물이 있으면 이동할 수 없다")
    void queenCanNotMoveHorizontally_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("b3"), new Piece(BLACK, new Queen()));
            pieces.put(Position.of("e3"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("f3"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("b3"), Position.of("f3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("퀸은 대각선 이동경로에 다른 기물이 있으면 움직일 수 없다")
    void queenCannotMoveDiagonally_anotherPiecesExistOnPath() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new Queen()));
            pieces.put(Position.of("d4"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("b2"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThatThrownBy(() -> board.move(Position.of("g7"), Position.of("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("킹이 잡히면 게임이 끝난다")
    void kingCaught_gameEnd() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("g1"), new Piece(WHITE, new Rook()));
            return pieces;
        });

        board.move(Position.of("g1"), Position.of("g7"));
        assertThat(board.isEnd()).isTrue();
    }

    @Test
    @DisplayName("각 진영의 점수를 계산한다")
    void calculate_eachColorsScore() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("g1"), new Piece(WHITE, new Rook()));
            pieces.put(Position.of("c3"), new Piece(WHITE, new Queen()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new Knight()));
            pieces.put(Position.of("a1"), new Piece(WHITE, new Bishop()));
            pieces.put(Position.of("h2"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertAll(
                () -> assertThat(board.calculateScore(WHITE)).isEqualTo(20.5),
                () -> assertThat(board.calculateScore(BLACK)).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("같은 세로줄에 같은 색의 폰이 복수 존재하면 폰은 0.5점이다.")
    void calculate_pawnScore() {
        Board board = new Board(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("a1"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("a5"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("a4"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("b4"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThat(board.calculateScore(WHITE)).isEqualTo(2);
    }

}
