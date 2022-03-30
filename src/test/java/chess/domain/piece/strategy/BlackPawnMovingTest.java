package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlackPawnMovingTest {

    @Test
    @DisplayName("첫 시작 위치에서 1칸 움직일 경우")
    void isPawnMovingToInitialPositionOneStepTrue() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();
        Board board = new Board(new BoardInitializer());

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a7"), Position.from("a6"))).isTrue();
    }

    @Test
    @DisplayName("첫 시작 위치에서 1칸 움직인 곳에 다른 기물이 있는 경우")
    void isPawnMovingToInitialPositionOneStepFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a7"), new Pawn(Color.BLACK));
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a7"), Position.from("a6"))).isFalse();
    }

    @Test
    @DisplayName("첫 시작 위치에서 2칸 움직일 경우")
    void isPawnMovingToInitialPositionTwoStepTrue() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();
        Board board = new Board(new BoardInitializer());

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a7"), Position.from("a5"))).isTrue();
    }

    @Test
    @DisplayName("첫 시작 위치에서 2칸 움직인 곳에 다른 기물이 있는 경우")
    void isPawnMovingToInitialPositionTwoStepFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a7"), new Pawn(Color.BLACK));
        havingPieceInPath.put(Position.from("a5"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a7"), Position.from("a5"))).isFalse();
    }

    @Test
    @DisplayName("1칸 움직일 경우")
    void isPawnMovingOneStepTrue() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("a5"))).isTrue();
    }

    @Test
    @DisplayName("1칸 움직인 곳에 다른 기물이 있는 경우")
    void isPawnMovingOneStepFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        havingPieceInPath.put(Position.from("a5"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("a5"))).isFalse();
    }

    @Test
    @DisplayName("2칸 움직일 경우")
    void isPawnMovingTwoStepFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("a4"))).isFalse();
    }

    @Test
    @DisplayName("대각선으로 움직일 경우")
    void isPawnMovingDiagonalTrue() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        havingPieceInPath.put(Position.from("b5"), new Pawn(Color.WHITE));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("b5"))).isTrue();
    }

    @Test
    @DisplayName("대각선으로 움직인 곳에 다른 기물이 없는 경우")
    void isPawnMovingDiagonalFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("b5"))).isFalse();
    }

    @Test
    @DisplayName("폰이 이동할 수 없는 방향으로 이동한 경우")
    void isPawnMovingFalse() {
        BlackPawnMoving blackPawnMoving = new BlackPawnMoving();

        Map<Position, Piece> havingPieceInPath = new HashMap<>();
        havingPieceInPath.put(Position.from("a6"), new Pawn(Color.BLACK));
        Board board = new Board(() -> havingPieceInPath);

        assertThat(blackPawnMoving.isPawnMoving(board, Position.from("a6"), Position.from("c6"))).isFalse();
    }
}
